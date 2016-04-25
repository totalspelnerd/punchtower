package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateMenu;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.awt.*;

/**
 * This class represents the playable character and is called whenever a new game starts.
 */

public class Player extends Entity{

	public static final int ITEM_HEAD= 0;
	public static final int ITEM_SHOULDER= 1;
	public static final int ITEM_CHEST= 2;
	public static final int ITEM_LEGS= 3;
	public static final int ITEM_BOOTS= 4;
	public static final int ITEM_WEAPON= 5;

	private int playerIndex;

	private Item[] equipped = new Item[]{null,null,null,null,null,null};
	private int lastLevel;
	private int level;
	private int xp;


	public Player(int playerIndex, final int initiative, final int attack, final int defense) {
		super(new Stats(StateMenu.names[playerIndex], initiative, attack, defense), STANDARD_HP, StateMenu.names[playerIndex]);
		this.playerIndex = playerIndex;
		lastLevel = 1;
		level = 1;
		xp = 0;
		for (int i = 0; i < equipped.length; i++) {
			equip(new Item(ItemType.values()[i],25, 25, 25, Rarity.NORMAL),i);
		}
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public Item getItem(final int itemIndex) {
		return equipped[itemIndex];
	}

	/**
	 * Equips the player with the given item and adjusts player stats accordingly.
	 * @param item
	 * @param itemIndex
	 */

	public void equip(final Item item, final int itemIndex) {
		Stats temp = stats.clone();
		if (equipped[itemIndex] != null) {
			stats.decrease(equipped[itemIndex].getStats());

		}
		this.equipped[itemIndex] = item;
		stats.increase(equipped[itemIndex].getStats());
		switch (playerIndex) {
			case 0:
				if (temp.initiative < stats.initiative) {
					stats.initiative += (int) Math.min(30, temp.initiative*0.1f);
				}
				break;
			case 1:
				if (temp.attack < stats.attack) {
					//stats.defense -= (25);
					stats.attack += (int) Math.min(30, temp.attack*0.05f);
				}
				break;
			case 2:
				if (temp.defense < stats.defense) {
					//stats.attack -= (25);
					stats.defense += (int) Math.min(50, temp.defense*0.1f);
				}
				break;
		}



	}

	public AttackData attack(int attackType) {
		Monster monster = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getMonster();
		if (hp <= 0) {
			return new AttackData(this, monster, false, false, false, 0);
		}
		return Combat.attack(this, monster, attackType);
	}
	
	public void spec(int stat)
	{
		switch (stat) {
		case 0:
			stats.initiative += 10*Math.pow(2, lastLevel/7.0);
			break;
		case 1:
			stats.attack += 10*Math.pow(2, lastLevel/7.0);
			break;
		case 2:
			stats.defense += 10*Math.pow(2, lastLevel/7.0);
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
}
