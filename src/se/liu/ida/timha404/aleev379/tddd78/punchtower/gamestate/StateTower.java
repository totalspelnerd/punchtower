package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.AttackData;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Entity;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.FontLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Item;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ItemType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Monster;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Player;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchTower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Renderer;

/**
 * Gamestate of our game. Here is where our game updates and renders. The main game logic is here.
 */
public class StateTower extends Gamestate{
    Item item = null;
	public static int floor;
	public static final int STD_INI = 50;
	public static final int STD_ATK = 50;
	public static final int STD_DEF = 50;

	private double normalDropChance = 1.0;
	private double rareDropChance = 0.2;
	private double epicDropChance = 0.1;
	private double legendaryDropChance = 0.02;

	private Player player = null;
	private Monster monster = null;
	private boolean floorClear = false;
	private boolean newGame = false;
		
	List<AttackData> attackData = new ArrayList<AttackData>();
	List<Float> attackTimer = new ArrayList<Float>();
	
	//private int attackType = 0;

    public StateTower(int playerIndex) {
		player = new Player(playerIndex, STD_INI, STD_ATK, STD_DEF);
	}

	public void init() {
		floor = 1;
		item = Item.generateRandomItem(this);
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
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),"skipEquip");
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
					attackEvent(0);
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"),"normalAttack");
		temp.getActionMap().put("normalAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(!player.didLevelUp())
					attackEvent(1);
			}
		});
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"),"heavyAttack");
		temp.getActionMap().put("heavyAttack", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(!player.didLevelUp())
					attackEvent(2);
			}
		});
		
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("4"),"specIni");
		temp.getActionMap().put("specIni", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(player.didLevelUp())
					player.spec(0);
			}
		});
		
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("5"),"specAtk");
		temp.getActionMap().put("specAtk", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(player.didLevelUp())
					player.spec(1);
			}
		});
		
		temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("6"),"specDef");
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
		item = Item.generateRandomItem(this);
		floorClear = false;
	}

	public void startOver() {
		removeKeystrokes();
		GamestateHandler.getInstance().setGamestate(new StateMenu());
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
    	for(int i = 0; i<attackData.size();i++)
    	{
    		float f = attackTimer.get(i).floatValue() - timeElapsed;
    		attackTimer.set(i, f);
    		if(f <=0)
    		{
    			attackData.remove(i);
    			attackTimer.remove(i);
    			i--;
    		}
    	}
	}

	public void attackEvent(int type) {
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

	public AttackData playerAttack(int type) {
		AttackData playerData = player.attack(type);
		attackData.add(playerData);
		attackTimer.add(2.0f);
		assert(attackData.size()==attackTimer.size());
		if (playerData.kill) {
			player.addXp();
			floorClear = true;
		}
		player.incInitiativeStack();
		return playerData;
	}

	public AttackData monsterAttack() {
		AttackData monsterData = monster.attack(0);
		attackData.add(monsterData);
		attackTimer.add(2.0f);
		assert(attackData.size()==attackTimer.size());
		if (monsterData.kill) {
			newGame = true;
		}
		return monsterData;
	}


    @Override
    public void render(final Graphics g)
    {
		g.drawImage(ImageLoader.background, 0, 0, 1280, 720, null);
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
				Renderer.renderTextShadow(g, "Press ENTER to leave it and move on! I aint no BITCH!",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 60, true);
			}
			else
			{
				g.setColor(Color.WHITE);
				g.setFont(FontLoader.mono72);
				Renderer.renderTextShadow(g, "Level Up!", PunchPanel.WIDTH/2, 400, true);
				g.setFont(FontLoader.mono20);
				// RENDER SPEC TO SCREEN
				Renderer.renderTextShadow(g, "Press 4 to spec in INITIATIVE",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 30, true,true);
				Renderer.renderTextShadow(g, "Press 5 to spec in ATTACK",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 70, true,true);
				Renderer.renderTextShadow(g, "Press 6 to spec in DEFENCE",PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + item.getStats().getHeight()/2 + 110, true,true);
				
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
			Renderer.renderTextShadow(g, "Press ENTER to play again or ESC to exit.", PunchPanel.WIDTH/2, PunchPanel.HEIGHT/2 + 150, true);
		}
    }

	private void removeKeystrokes()
	{
		final PunchPanel panel = PunchTower.getInstance().getFrame().getPanel();
		panel.getInputMap().remove(KeyStroke.getKeyStroke("E"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("ENTER"));
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
