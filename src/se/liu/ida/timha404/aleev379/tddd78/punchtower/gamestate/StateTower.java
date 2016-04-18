package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.AttackData;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Entity;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Item;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Monster;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Player;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchTower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Stats;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Tower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ItemType;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class StateTower extends Gamestate{
    Item item = null;
	Image image = null;
	Image deadText = null;
	public static int floor;
	public static final int STD_INI = 50;
	public static final int STD_ATK = 50;
	public static final int STD_DEF = 50;

	private double normalDropChance = 1.0;
	private double rareDropChance = 0.2;
	private double epicDropChance = 0.1;
	private double legendaryDropChance = 0.05;

	private Player player = null;
	private Monster monster = null;
	private boolean floorClear = false;
	private boolean newGame = false;
	//private int attackType = 0;
    //public StateTower() {
//	}
	public void init() {
		floor = 1;
		item = Item.generateRandomItem(this);
		player = new Player("TOP KEK", STD_INI, STD_ATK, STD_DEF);
		monster = Monster.generateMonster(floor);
		try {
			image = ImageIO.read(new File("res/stone-wall-texture-wallpaper-2.jpg"));
			deadText = ImageIO.read(new File("res/dead.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		PunchPanel temp = PunchTower.getInstance().getFrame().getPanel();
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"),"equipItem");
		temp.getActionMap().put("equipItem", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if (floorClear) {
					player.equip(item, ItemType.valueOf(item.getItemType().toString()).ordinal());
					nextFloor();
				}
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),"skipEquip");
		temp.getActionMap().put("skipEquip", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if (newGame) {
					startOver();
				}
				if (floorClear) {
					nextFloor();
				}
			}
		});

		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"),"quickAttack");
		temp.getActionMap().put("quickAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				attackEvent(0);
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"),"normalAttack");
		temp.getActionMap().put("normalAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				attackEvent(1);
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"),"heavyAttack");
		temp.getActionMap().put("heavyAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				attackEvent(2);
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),"exit");
		temp.getActionMap().put("exit", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				gameOver();
			}
		});
		}


	public int getFloor() {
		return floor;
	}

	public void nextFloor() {
		/*Stats curStats = player.getItem(ItemType.valueOf(item.getItemType().toString()).ordinal()).getStats();
		int totalCurStats = curStats.initiative + curStats.attack + curStats.defense;
		Stats newStats = item.getStats();
		int totalNewStats = newStats.initiative + newStats.attack + newStats.defense;

		if (totalNewStats >totalCurStats )
			player.equip(item, ItemType.valueOf(item.getItemType().toString()).ordinal());
		*/
		newGame = false;
		this.floor++;
		monster = Monster.generateMonster(floor);
		player.setHp(Entity.STANDARD_HP);
		item = Item.generateRandomItem(this);
		floorClear = false;
	}

	public void startOver() {
		newGame = false;
		floor = 1;
		player = new Player(player.getName(), STD_INI, STD_ATK, STD_DEF);
		monster = Monster.generateMonster(floor);
		player.setHp(Entity.STANDARD_HP);
		item = Item.generateRandomItem(this);
		floorClear = false;
	}

	public void gameOver() {
		System.exit(0);
	}

	public double getNormalDropChance() {
		return normalDropChance;
	}

	public void setNormalDropChance(final int normalDropChance) {
		this.normalDropChance = normalDropChance;
	}

	public double getRareDropChance() {
		return rareDropChance;
	}

	public void setRareDropChance(final int rareDropChance) {
		this.rareDropChance = rareDropChance;
	}

	public double getEpicDropChance() {
		return epicDropChance;
	}

	public void setEpicDropChance(final int epicDropChance) {
		this.epicDropChance = epicDropChance;
	}

	public double getLegendaryDropChance() {
		return legendaryDropChance;
	}

	public void setLegendaryDropChance(final int legendaryDropChance) {
		this.legendaryDropChance = legendaryDropChance;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(final Monster curMonster) {
		this.monster = curMonster;
	}

    @Override
    public void update(final float timeElapsed)
    {

	}

	public void tick()
	{

	}

	public void attackEvent (int type) {
		if (floorClear)
		{
			return;
		}
		if (player.getStats().initiative >= monster.getStats().initiative) {
			playerAttack(type);
			monsterAttack();
		} else {
			monsterAttack();
			playerAttack(type);
		}
	}

	public AttackData playerAttack(int type) {
		AttackData playerData = player.attack(type);
		if (playerData.kill) {

			floorClear = true;
		}
		return playerData;
	}

	public AttackData monsterAttack() {
		AttackData monsterData = monster.attack(0);
		if (monsterData.kill) {
			newGame = true;

		}
		return monsterData;
	}


    @Override
    public void render(final Graphics g)
    {
		g.drawImage(image,0,0,1280,720,null);
		player.render(g,10,40);

		if(floorClear) {

			item.render(g,PunchPanel.WIDTH/2 + 10,PunchPanel.HEIGHT/2-item.getStats().getHeight()/2);
			Item playerItem = player.getItem(ItemType.valueOf(item.getItemType().toString()).ordinal());
			g.setFont(Stats.defaultFont);
			if (playerItem != null) {
				playerItem.render(g, PunchPanel.WIDTH / 2 - playerItem.getStats().getWidth() - 10, PunchPanel.HEIGHT / 2 - playerItem.getStats().getHeight() / 2);
				renderTextShadow(g, "OLD", PunchPanel.WIDTH / 2 - playerItem.getStats().getWidth() - 10, PunchPanel.HEIGHT / 2 - playerItem.getStats().getHeight() / 2 - 5, false);
			}
			renderTextShadow(g, "NEW", PunchPanel.WIDTH / 2 + 10, PunchPanel.HEIGHT / 2 - item.getStats().getHeight() / 2 - 5, false);
			renderTextShadow(g, "Press E to equip the new item and move on! For GLORY!",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 30, true);
			renderTextShadow(g, "Press ENTER to leave it and move on! I aint no BITCH!",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 60, true);


		} else {
			monster.render(g, PunchPanel.WIDTH-monster.getStats().getWidth()-10, 40);
			renderTextShadow(g, "1: quick attack", PunchPanel.WIDTH/2-200, PunchPanel.HEIGHT/2-25, true);
			renderTextShadow(g, "2: normal attack", PunchPanel.WIDTH/2-200, PunchPanel.HEIGHT/2, true);
			renderTextShadow(g, "3: heavy attack", PunchPanel.WIDTH/2-200, PunchPanel.HEIGHT/2+25, true);
		}
		if (newGame) {
			g.drawImage(deadText, PunchPanel.WIDTH/2 - 512, PunchPanel.HEIGHT/2 - 100, 1024, 400, null);
			renderTextShadow(g, "Press ENTER to play again or ESC to exit.", PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + 150, true);
		}

		g.setFont(new Font(Font.MONOSPACED,Font.PLAIN,80));
		g.setColor(Color.WHITE);
		renderTextShadow(g, "Floor:"+floor, PunchPanel.WIDTH/2, 100, true);
		if (floor < 13) {
			g.setFont(new Font(Font.MONOSPACED,Font.PLAIN,40));
			renderTextShadow(g, "TUTORIAL FLOOR", PunchPanel.WIDTH/2, 180, true);
		}
    }

	public void renderTextShadow(Graphics g, String text, int x, int y, boolean center) {
		if (center) {
			x-=g.getFontMetrics().stringWidth(text)/2;
		}
		g.setColor(Color.BLACK);
		g.drawString(text, x + 2, y + 2);
		g.setColor(Color.WHITE);
		g.drawString(text, x, y);

	}
}
