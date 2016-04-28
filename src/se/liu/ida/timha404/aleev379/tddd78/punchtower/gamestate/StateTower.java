package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.AttackData;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.FontLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Item;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchTower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Renderer;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.SaveFile;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.SaveLoad;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.entity.Entity;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.entity.Monster;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.entity.Player;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.AttackType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.ItemType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.PlayerType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.StatType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.TagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Gamestate of our game. Here is where our game updates and renders. The main game logic is here.
 */
public class StateTower extends Gamestate
{


	/**
	 * Number of tutorial floors.
	 */
	public static final int TUTORIAL_FLOOR = 13;
	/**
	 * Standard initiative value for players
	 */
	public static final int STD_INI = 50;

	/**
	 * Standard attack value for players
	 */
	public static final int STD_ATK = 50;

	/**
	 * Standard defence value for players
	 */
	public static final int STD_DEF = 50;

	/**
	 * Timer time for the attackdata to disapear (in seconds)
	 */
	public static final float ATTACKDATA_TIMER = 3.0f;

	/**
	 * Rare rarity items drop chance
	 */
	public static final float RARE_DROP_CHANCE = 0.2f;

	/**
	 * Epic rarity items drop chance
	 */
	public static final float EPIC_DROP_CHANCE = 0.1f;

	/**
	 * Legendary rarity items drop chance
	 */
	public static final float LEGENDARY_DROP_CHANCE = 0.02f;

	/**
	 * Random used inside of StateTower
	 */
	private static Random rnd = new Random();

	private Player player = null;
	private Item item = null;
	private int floor;
	private Monster monster = null;
	private boolean floorClear = false;
	private boolean newGame = false;

	private List<AttackData> attackData = new ArrayList<>();
	private List<Float> attackTimer = new ArrayList<>();

	//private int attackType = 0;

	private StateTower(Player player, int floor)
	{
		this.player = player;
		this.floor = floor;
	}

	public StateTower(PlayerType type) {
		player = new Player(type, STD_INI, STD_ATK, STD_DEF);
		floor = 1;
	}

	public void init() {
		item = Item.generateRandomItem(floor);
		monster = Monster.generateMonster(floor);


		PunchPanel temp = PunchTower.getInstance().getFrame().getPanel();

		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"), "equipItem");
		temp.getActionMap().put("equipItem", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if (!player.didLevelUp()) {
					if (floorClear) {
						player.equip(item, ItemType.valueOf(item.getItemType().toString()).ordinal());
						nextFloor();
					}
				}
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "skipEquip");
		temp.getActionMap().put("skipEquip", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if (!player.didLevelUp()) {
					if (newGame) {
						startOver();
					}
					if (floorClear) {
						nextFloor();
					}
				}
			}
		});

		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), "quickAttack");
		temp.getActionMap().put("quickAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if (!player.didLevelUp()) attackEvent(AttackType.QUICK);
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"), "normalAttack");
		temp.getActionMap().put("normalAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if (!player.didLevelUp()) attackEvent(AttackType.NORMAL);
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"), "heavyAttack");
		temp.getActionMap().put("heavyAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if (!player.didLevelUp()) attackEvent(AttackType.HEAVY);
			}
		});

		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), "specIni");
		temp.getActionMap().put("specIni", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if (player.didLevelUp()) player.spec(StatType.INITIATIVE);
			}
		});

		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"), "specAtk");
		temp.getActionMap().put("specAtk", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if (player.didLevelUp()) player.spec(StatType.ATTACK);
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"), "specDef");
		temp.getActionMap().put("specDef", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if (player.didLevelUp()) player.spec(StatType.DEFENSE);
			}
		});
	}


	public int getFloor() {
		return floor;
	}

	public void nextFloor() {
		newGame = false;
		floor++;
		monster = Monster.generateMonster(floor);
		player.setHp(Entity.STANDARD_HP);
		item = Item.generateRandomItem(floor);
		floorClear = false;
		SaveLoad.save(this,SaveLoad.SAVE_FILE);
	}

	public void startOver() {
		removeKeystrokes();
		GamestateHandler.getInstance().setGamestate(new StateMenu());
	}

	public Player getPlayer() {
		return player;
	}


	public Monster getMonster() {
		return monster;
	}

	@Override public void update(final float timeElapsed)
	{
		assert (attackData.size() == attackTimer.size()) :
			"attackData and attackTimer is not the same size, cannot map them together.";
		int size = attackData.size();
		for (int i = 0; i < size; i++) {
			float f = attackTimer.get(i).floatValue() - timeElapsed;
			attackTimer.set(i, f);
			if (f <= 0) {
				attackData.remove(i);
				attackTimer.remove(i);
				i--; // Since we are removing an object in the list we need to decrement i with one to be able to look through all the data and not just one
				size--;
			}
		}
	}

	public void attackEvent(AttackType type) {
		if (floorClear || newGame) {
			return;
		}
		if (player.getStats().initiative >= monster.getStats().initiative) {
			playerAttack(type);
			if (player.getInitiativeStack() < Entity.STACK_CAP) monsterAttack();
			else player.resetInitiativeStack();
		} else {
			if (player.getInitiativeStack() < Entity.STACK_CAP) monsterAttack();
			else player.resetInitiativeStack();
			playerAttack(type);
		}
	}

	public void playerAttack(AttackType type) {
		AttackData playerData = player.attack(type);
		attackData.add(playerData);
		attackTimer.add(ATTACKDATA_TIMER);
		if (playerData.kill) {
			player.addXp();
			floorClear = true;
		}
		player.incInitiativeStack();
	}

	public void monsterAttack() {
		AttackData monsterData = monster.attack(AttackType.values()[rnd.nextInt(AttackType.values().length)]);
		attackData.add(monsterData);
		attackTimer.add(ATTACKDATA_TIMER);
		if (monsterData.kill) {
			newGame = true;
			SaveLoad.delete(SaveLoad.SAVE_FILE);
		}
	}


	@Override public void render(final Graphics g)
	{
		if (floor == 66 || floor == 666) { // Magic number for Constants for easter egg backgrounds
			g.drawImage(ImageLoader.backgroundHell, 0, 0, PunchPanel.WIDTH, PunchPanel.HEIGHT, null);
		} else if (floor > 50) { // Magic number above floor 50 the background changes
			g.drawImage(ImageLoader.background50, 0, 0, PunchPanel.WIDTH, PunchPanel.HEIGHT, null);

		} else {
			g.drawImage(ImageLoader.background, 0, 0, PunchPanel.WIDTH, PunchPanel.HEIGHT, null);
		}
		final int playerY = 40;
		player.render(g, 10, playerY); // Magic number: Player relative position

		renderFloor(g);

		renderAttackData(g);

		renderNewGame(g);
	}

	public void renderFloor(Graphics g)
	{

		final int lineSize = 30;
		if (floorClear) {
			if (!player.didLevelUp()) {
				// RENDER ITEM
				g.setColor(Color.WHITE);
				g.setFont(FontLoader.mono20);
				Renderer.renderTextShadow(g, "NEW", PunchPanel.WIDTH / 2 + 10,
										  PunchPanel.HEIGHT / 2 - item.getStats().getHeight() / 2 - 5, false);
				item.render(g, PunchPanel.WIDTH / 2 + 10, PunchPanel.HEIGHT / 2 - item.getStats().getHeight() / 2);

				Item playerItem = player.getItem(ItemType.valueOf(item.getItemType().toString()).ordinal());
				if (playerItem != null) {
					// RENDER PLAYER ITEM STATS
					g.setColor(Color.WHITE);
					Renderer.renderTextShadow(g, "OLD", PunchPanel.WIDTH / 2 - playerItem.getStats().getWidth() - 10,
											  PunchPanel.HEIGHT / 2 - playerItem.getStats().getHeight() / 2 - 5, false);
					playerItem.render(g, PunchPanel.WIDTH / 2 - playerItem.getStats().getWidth() - 10,
									  PunchPanel.HEIGHT / 2 - playerItem.getStats().getHeight() / 2);
				}
				// RENDER TEXT TO SCREEN
				Renderer.renderTextShadow(g, "Press E to equip the new item and move on! For GLORY!", PunchPanel.WIDTH / 2,
										  PunchPanel.HEIGHT / 2 + item.getStats().getHeight() / 2 + lineSize, true);
				Renderer.renderTextShadow(g, "Press SPACE to leave it and move on! I aint no BITCH!", PunchPanel.WIDTH / 2,
										  PunchPanel.HEIGHT / 2 + item.getStats().getHeight() / 2 + lineSize * 2, true);
			} else {
				final int levelUpTextY = 400;
				g.setColor(Color.WHITE);
				g.setFont(FontLoader.mono72);
				Renderer.renderTextShadow(g, "Level Up!", PunchPanel.WIDTH / 2, levelUpTextY, true);
				g.setFont(FontLoader.mono20);
				// RENDER SPEC TO SCREEN
				Renderer.renderTextShadow(g, "Press F1 to spec in INITIATIVE", PunchPanel.WIDTH / 2,
										  PunchPanel.HEIGHT / 2 + item.getStats().getHeight() / 2 + lineSize, true, true);
				Renderer.renderTextShadow(g, "Press F2 to spec in ATTACK", PunchPanel.WIDTH / 2,
										  PunchPanel.HEIGHT / 2 + item.getStats().getHeight() / 2 + lineSize * 2, true, true);
				Renderer.renderTextShadow(g, "Press F3 to spec in DEFENCE", PunchPanel.WIDTH / 2,
										  PunchPanel.HEIGHT / 2 + item.getStats().getHeight() / 2 + lineSize * 3, true, true);

			}
		} else {
			final int textOffset = 200;
			final int monsterY = 40;
			monster.render(g, PunchPanel.WIDTH - monster.getStats().getWidth() - 10, monsterY);
			g.setColor(Color.WHITE);
			Renderer.renderTextShadow(g, "1: quick attack", PunchPanel.WIDTH / 2 - textOffset, PunchPanel.HEIGHT / 2, false);
			Renderer
				.renderTextShadow(g, "2: normal attack", PunchPanel.WIDTH / 2 - textOffset, PunchPanel.HEIGHT / 2 + lineSize,
								  false);
			Renderer
				.renderTextShadow(g, "3: heavy attack", PunchPanel.WIDTH / 2 - textOffset, PunchPanel.HEIGHT / 2 + lineSize * 2,
								  false);
		}

		g.setFont(FontLoader.mono72);
		g.setColor(Color.WHITE);
		Renderer.renderTextShadow(g, "Floor:" + floor, PunchPanel.WIDTH / 2, 100, true);
		if (floor < TUTORIAL_FLOOR) {
			final int textY = 180;
			g.setFont(FontLoader.mono40);
			Renderer.renderTextShadow(g, "TUTORIAL FLOOR", PunchPanel.WIDTH / 2, textY, true);
		}
	}

	public void renderAttackData(Graphics g)
	{
		final int yPos = 300;
		final int gravity = 300;
		final int xMonsterOffset = 300;
		final int xPlayerOffset = 200;
		g.setFont(FontLoader.sans72);
		for (int i = 0; i < attackData.size(); i++) {
			AttackData data = attackData.get(i);

			if (data.attacker instanceof Player) {
				Renderer
					.renderNumberDrop(g, ATTACKDATA_TIMER - attackTimer.get(i), PunchPanel.WIDTH - xMonsterOffset, yPos, 100,
									  -100, gravity, data.toString(), Color.RED);
			} else {
				Renderer.renderNumberDrop(g, ATTACKDATA_TIMER - attackTimer.get(i), xPlayerOffset, yPos, 100, -100, gravity,
										  data.toString(), Color.RED);
			}
		}
	}

	public void renderNewGame(Graphics g)
	{
		g.setFont(FontLoader.mono40);
		if (newGame) {
			g.setColor(Color.WHITE);
			final int deadTextWidth = 1024;
			final int deadTextHeight = 400;
			final int deadTextY = 100;
			final int deadText2Y = 150;
			g.drawImage(ImageLoader.deadText, PunchPanel.WIDTH / 2 - deadTextWidth / 2, PunchPanel.HEIGHT / 2 - deadTextY,
						deadTextWidth, deadTextHeight, null);
			Renderer.renderTextShadow(g, "Press SPACE to play again or ESC to exit.", PunchPanel.WIDTH / 2,
									  PunchPanel.HEIGHT / 2 + deadText2Y, true);
		}
	}

	private void removeKeystrokes()
	{
		final PunchPanel panel = PunchTower.getInstance().getFrame().getPanel();
		panel.getInputMap().remove(KeyStroke.getKeyStroke("E"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("SPACE"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("1"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("2"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("3"));
		panel.getActionMap().remove("equipItem");
		panel.getActionMap().remove("skipEquip");
		panel.getActionMap().remove("quickAttack");
		panel.getActionMap().remove("normalAttack");
		panel.getActionMap().remove("heavyAttack");
	}

	public void saveToFile(SaveFile file)
	{
		file.addTag("floor", Integer.toString(floor));
		player.saveToFile(file);
	}

	public static StateTower loadFromFile(SaveFile file) throws TagException
	{
		int floor = file.getTagAsInt("floor");
		StateTower tower = new StateTower(Player.loadFromFile(file), floor);
		return tower;
	}

}
