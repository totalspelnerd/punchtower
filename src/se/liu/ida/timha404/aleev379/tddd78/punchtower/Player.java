package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.awt.*;

/**
 * This class represents the playable character and is called whenever a new game starts.
 */

public class Player extends Entity{

	private PlayerType playerType;

	private Item[] equipped = new Item[]{null,null,null,null,null,null};
	private int lastLevel;
	private int level;
	private int xp;


	public Player(PlayerType type, final int initiative, final int attack, final int defense) {
		super(new Stats(type.name, initiative, attack, defense), STANDARD_HP, type.name);
		playerType = type;
		lastLevel = 1;
		level = 1;
		xp = 0;
		for (int i = 0; i < equipped.length; i++) {
			equip(new Item(ItemType.values()[i],25, 25, 25, Rarity.NORMAL),i); // Magic numbers for starter item stats
		}
	}

	public Item getItem(final int itemIndex) {
		return equipped[itemIndex];
	}

	/**
	 * Equips the player with the given item and adjusts player stats accordingly.
	 * @param item The item to be equipped
	 * @param itemIndex An index representing the item slot to be used
	 */

	public void equip(final Item item, final int itemIndex) {
		Stats temp = stats.clone();
		if (equipped[itemIndex] != null) {
			stats.decrease(equipped[itemIndex].getStats());

		}
		this.equipped[itemIndex] = item;
		stats.increase(equipped[itemIndex].getStats());

		// All the following Magic numbers in this method is used to adjust the balance of the game. Testsed to make them good.
		switch (playerType) {
			case STAN:
				if (temp.initiative < stats.initiative) {
					stats.initiative += (int) Math.min(30, temp.initiative*0.1f);
				}
				break;
			case BRICK:
				if (temp.attack < stats.attack) {
					//stats.defense -= (25);
					stats.attack += (int) Math.min(30, temp.attack*0.05f);
				}
				break;
			case TED:
				if (temp.defense < stats.defense) {
					//stats.attack -= (25);
					stats.defense += (int) Math.min(50, temp.defense*0.1f);
				}
				break;
		}



	}

	public AttackData attack(AttackType type) {
		Monster monster = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getMonster();
		if (hp <= 0) {
			return new AttackData(this, monster, false, false, false, 0);
		}
		return Combat.attack(this, monster, type);
	}
	
	public void spec(int stat)
	{
		switch (stat) { // Magic numbers to maintain balance
		case 0:
			stats.initiative += (int) (10*Math.pow(2, lastLevel/8.0));
			break;
		case 1:
			stats.attack += (int) (10*Math.pow(2, lastLevel/8.0));
			break;
		case 2:
			stats.defense += (int) (10*Math.pow(2, lastLevel/8.0));
			break;
		}
		lastLevel++;
	}
	
	public boolean didLevelUp()
	{
		// level should never be less than the last level
		assert(level >= lastLevel);
		return level!=lastLevel;
	}
	
	public void addXp()
	{
		// We should only add xp if we are currently not leveling up.
		assert(lastLevel == level) : "lastLevel can't be different from level: " + lastLevel + " "+level;
		Monster monster = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getMonster();
		xp += monster.stats.getTotal();
		while(xp > Experience.getXp(level+1))
		{
			level++;
		}
	}


	public void render(Graphics g, int x, int y)
	{
		stats.render(g,x,y,new Color(0x00bb00));
		g.drawImage(ImageLoader.player,x+100,y+200,200,400,null);
		Renderer.renderProgression(g,x+stats.getWidth()+10,y,200,20,Color.RED, Color.GREEN,0, STANDARD_HP,hp);
		Renderer.renderProgression(g,x+stats.getWidth()+10,y+20,200,10,Color.YELLOW.darker().darker(), Color.YELLOW,0, STACK_CAP,(int)initiativeStack);
		g.setFont(FontLoader.mono20);
		g.setColor(Color.CYAN);
		Renderer.renderTextShadow(g, "lvl: "+level, x+stats.getWidth()+10, y+45, false);
		Renderer.renderProgression(g, x+stats.getWidth()+10, y+50, 200, 10, Color.BLUE.darker().darker(), Color.BLUE, Experience.getXp(level), Experience.getXp(level+1), xp);
	}

	public PlayerType getPlayerType()
	{
		return playerType;
	}
}
