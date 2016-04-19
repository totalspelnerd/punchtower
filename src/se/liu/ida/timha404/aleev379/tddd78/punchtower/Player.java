package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
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


	private Item[] equipped = new Item[]{null,null,null,null,null,null};


	public Player(final String name, final int initiative, final int attack, final int defense) {
		super(new Stats(name, initiative, attack, defense), STANDARD_HP, name);
		for (int i = 0; i < equipped.length; i++) {
			equip(new Item(ItemType.values()[i],25, 25, 25, Rarity.NORMAL),i);

		}
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
		if (equipped[itemIndex] != null) {
			stats.decrease(equipped[itemIndex].getStats());

		}
		this.equipped[itemIndex] = item;
		stats.increase(equipped[itemIndex].getStats());

	}

	public AttackData attack(int attackType) {
		Monster monster = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getMonster();
		if (hp <= 0) {
			return new AttackData(this, monster, false, false, false, 0);
		}
		return Combat.attack(this, monster, attackType);
	}


	public void render(Graphics g, int x, int y)
	{
		stats.render(g,x,y,new Color(0x00bb00));
		g.drawImage(ImageLoader.player,x+100,y+200,200,400,null);
		g.setColor(Color.red);
		g.fillRect(x+stats.getWidth()+10, y, 200, 20);
		g.setColor(Color.green);
		g.fillRect(x+stats.getWidth()+10, y, (int)(200*(hp/(float)STANDARD_HP)), 20);

	}
}
