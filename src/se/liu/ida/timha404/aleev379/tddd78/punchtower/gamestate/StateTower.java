package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.AttackData;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.AttackType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Entity;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.FontLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Item;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ItemType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Monster;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Player;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PlayerType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchTower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Renderer;

/**
 * Gamestate of our game. Here is where our game updates and renders. The main game logic is here.
 */
public class StateTower extends Gamestate{

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

	private Item item = null;
	private int floor;

	public static final float ATTACKDATA_TIMER = 2.0f;

	public static final float NORMAL_DROP_CHANCE= 1.0f;
	public static final float RARE_DROP_CHANCE= 0.2f;
	public static final float EPIC_DROP_CHANCE= 0.1f;
	public static final float LEGENDARY_DROP_CHANCE= 0.02f;

	private Player player = null;
	private Monster monster = null;
	private boolean floorClear = false;
	private boolean newGame = false;
	private static Random RANDOM = new Random();
		
	private List<AttackData> attackData = new ArrayList<AttackData>();
	private List<Float> attackTimer = new ArrayList<Float>();
	
	//private int attackType = 0;

    public StateTower(PlayerType type) {
		player = new Player(type, STD_INI, STD_ATK, STD_DEF);
	}

	public void init() {
		floor = 1;
		item = Item.generateRandomItem(floor);
		monster = Monster.generateMonster(floor);


		PunchPanel temp = PunchTower.getInstance().getFrame().getPanel();
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("E"),"equipItem");
		temp.getActionMap().put("equipItem", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(!player.didLevelUp())
				{
					if (floorClear) {
						player.equip(item, ItemType.valueOf(item.getItemType().toString()).ordinal());
						nextFloor();
					}
				}
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"),"skipEquip");
		temp.getActionMap().put("skipEquip", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(!player.didLevelUp())
				{
					if (newGame) {
						startOver();
					}
					if (floorClear) {
						nextFloor();
					}
				}
			}
		});

		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"),"quickAttack");
		temp.getActionMap().put("quickAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(!player.didLevelUp())
					attackEvent(AttackType.QUICK);
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"),"normalAttack");
		temp.getActionMap().put("normalAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(!player.didLevelUp())
					attackEvent(AttackType.NORMAL);
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"),"heavyAttack");
		temp.getActionMap().put("heavyAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(!player.didLevelUp())
					attackEvent(AttackType.HEAVY);
			}
		});
		
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"),"specIni");
		temp.getActionMap().put("specIni", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(player.didLevelUp())
					player.spec(0);
			}
		});
		
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F2"),"specAtk");
		temp.getActionMap().put("specAtk", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(player.didLevelUp())
					player.spec(1);
			}
		});
		
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F3"),"specDef");
		temp.getActionMap().put("specDef", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(player.didLevelUp())
					player.spec(2);
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
		floor++;
		monster = Monster.generateMonster(floor);
		player.setHp(Entity.STANDARD_HP);
		item = Item.generateRandomItem(floor);
		floorClear = false;
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

    @Override
    public void update(final float timeElapsed)
    {
		assert(attackData.size()==attackTimer.size()) : "attackData and attackTimer is not the same size, cannot map them together.";
    	for(int i = 0; i<attackData.size();i++)
    	{
    		float f = attackTimer.get(i).floatValue() - timeElapsed;
    		attackTimer.set(i, f);
    		if(f <=0)
    		{
    			attackData.remove(i);
    			attackTimer.remove(i);
    			i--;
				// Since we are removing an object in the list we need to decrement i with one to be able to look through all the data and not just one
    		}
    	}
	}

	public void attackEvent(AttackType type) {
		if (floorClear || newGame)
		{
			return;
		}
		if (player.getStats().initiative >= monster.getStats().initiative) {
			playerAttack(type);
			if(player.getInitiativeStack() < Entity.STACK_CAP)
				monsterAttack();
			else
				player.resetInitiativeStack();
		} else {
			if(player.getInitiativeStack() < Entity.STACK_CAP)
				monsterAttack();
			else
				player.resetInitiativeStack();
			playerAttack(type);
		}
	}

	public AttackData playerAttack(AttackType type) {
		AttackData playerData = player.attack(type);
		attackData.add(playerData);
		attackTimer.add(ATTACKDATA_TIMER);
		if (playerData.kill) {
			player.addXp();
			floorClear = true;
		}
		player.incInitiativeStack();
		return playerData;
	}

	public AttackData monsterAttack() {
		AttackData monsterData = monster.attack(AttackType.values()[RANDOM.nextInt(AttackType.values().length)]);
		attackData.add(monsterData);
		attackTimer.add(ATTACKDATA_TIMER);
		if (monsterData.kill) {
			newGame = true;
		}
		return monsterData;
	}


    @Override
    public void render(final Graphics g)
	{
		if(floor == 66 || floor == 666) {
			g.drawImage(ImageLoader.background4, 0, 0, 1280, 720, null);

		}else if (floor>50) {
			g.drawImage(ImageLoader.background3, 0, 0, 1280, 720, null);

		}else{
			//(ImageLoader.background1, ImageLoader.background2);
			g.drawImage(ImageLoader.background1, 0, 0, 1280, 720, null);
		}
		player.render(g,10,40);

		renderFloor(g);
				
		renderAttackData(g);
		
		renderNewGame(g);
    }
    
    public void renderFloor(Graphics g)
    {

		if(floorClear) {
			if(!player.didLevelUp())
			{
				// RENDER ITEM
				g.setColor(Color.WHITE);
				g.setFont(FontLoader.mono20);
				Renderer.renderTextShadow(g, "NEW", PunchPanel.WIDTH / 2 + 10, PunchPanel.HEIGHT / 2 - item.getStats().getHeight() / 2 - 5, false);
				item.render(g,PunchPanel.WIDTH/2 + 10,PunchPanel.HEIGHT/2-item.getStats().getHeight()/2);
				
				Item playerItem = player.getItem(ItemType.valueOf(item.getItemType().toString()).ordinal());
				if (playerItem != null) {
					// RENDER PLAYER ITEM STATS
					g.setColor(Color.WHITE);
					Renderer.renderTextShadow(g, "OLD", PunchPanel.WIDTH / 2 - playerItem.getStats().getWidth() - 10, PunchPanel.HEIGHT / 2 - playerItem.getStats().getHeight() / 2 - 5, false);
					playerItem.render(g, PunchPanel.WIDTH / 2 - playerItem.getStats().getWidth() - 10, PunchPanel.HEIGHT / 2 - playerItem.getStats().getHeight() / 2);
				}
				// RENDER TEXT TO SCREEN
				Renderer.renderTextShadow(g, "Press E to equip the new item and move on! For GLORY!",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 30, true);
				Renderer.renderTextShadow(g, "Press SPACE to leave it and move on! I aint no BITCH!",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 60, true);
			}
			else
			{
				g.setColor(Color.WHITE);
				g.setFont(FontLoader.mono72);
				Renderer.renderTextShadow(g, "Level Up!", PunchPanel.WIDTH/2, 400, true);
				g.setFont(FontLoader.mono20);
				// RENDER SPEC TO SCREEN
				Renderer.renderTextShadow(g, "Press F1 to spec in INITIATIVE",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 30, true,true);
				Renderer.renderTextShadow(g, "Press F2 to spec in ATTACK",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 70, true,true);
				Renderer.renderTextShadow(g, "Press F3 to spec in DEFENCE",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 110, true,true);
				
			}
		} else {
			monster.render(g, PunchPanel.WIDTH-monster.getStats().getWidth()-10, 40);
			g.setColor(Color.WHITE);
			Renderer.renderTextShadow(g, "1: quick attack", PunchPanel.WIDTH/2-200, PunchPanel.HEIGHT/2-25, true);
			Renderer.renderTextShadow(g, "2: normal attack", PunchPanel.WIDTH/2-200, PunchPanel.HEIGHT/2, true);
			Renderer.renderTextShadow(g, "3: heavy attack", PunchPanel.WIDTH/2-200, PunchPanel.HEIGHT/2+25, true);
		}
		
		g.setFont(FontLoader.mono72);
		g.setColor(Color.WHITE);
		Renderer.renderTextShadow(g, "Floor:"+floor, PunchPanel.WIDTH/2, 100, true);
		if (floor < 13) {
			g.setFont(FontLoader.mono40);
			Renderer.renderTextShadow(g, "TUTORIAL FLOOR", PunchPanel.WIDTH/2, 180, true);
		}
    }
    
    public void spec(int stat)
    {
    	
    }
    
    public void renderAttackData(Graphics g)
    {
		g.setFont(FontLoader.sans72);
		for(int i = 0;i<attackData.size();i++)
		{
			AttackData data = attackData.get(i);
			if(data.attacker instanceof Player)
			{
				Renderer.renderNumberDrop(g, 2.0f-attackTimer.get(i), PunchPanel.WIDTH-300, 300, 100, -100, 300, data.toString(), Color.RED);
			}
			else
			{
				Renderer.renderNumberDrop(g, 2.0f-attackTimer.get(i), 200, 300, 100, -100, 300, data.toString(), Color.RED);
			}
		}
    }
    
    public void renderNewGame(Graphics g)
    {
    	g.setFont(FontLoader.mono40);
		if (newGame) {
			g.setColor(Color.WHITE);
			g.drawImage(ImageLoader.deadText, PunchPanel.WIDTH/2 - 512, PunchPanel.HEIGHT/2 - 100, 1024, 400, null);
			Renderer.renderTextShadow(g, "Press SPACE to play again or ESC to exit.", PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + 150, true);
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

}
