package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class is used to create items, specifically by calling the generateItem() method.
 */


import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.ItemType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.Rarity;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.StatType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.util.Random;

import java.awt.*;

/**
 * This class represents the items in our game. Containing a stat, rarity and type.
 */
public class Item{
	private ItemType itemType;
	private Stats stats;
	private Rarity rarity;


	public Item(final ItemType itemType,final int initiative,final int attack, final int defense, final Rarity rarity) {
		this.itemType = itemType;
		stats = new Stats(itemType.toString(), initiative, attack, defense);
		this.rarity = rarity;
	}

	/**
	 *
	 * @return the stats of the item.
	 */
	public Stats getStats()
	{
		return stats;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public Rarity getRarity()
	{
		return rarity;
	}

	/**
	 * This method is used to generate a new Item based on the current drop rates and the current floor.
	 * @param floor The current floor of the tower
	 * @return A new item
	 */
	public static Item generateRandomItem(int floor) {
		Random rnd = new Random();
		double drop = rnd.nextDouble();
		Rarity rarity;

		if (drop <= StateTower.LEGENDARY_DROP_CHANCE) {
			rarity = Rarity.LEGENDARY;
		} else if (drop <= StateTower.EPIC_DROP_CHANCE) {
			rarity = Rarity.EPIC;
		} else if (drop <= StateTower.RARE_DROP_CHANCE) {
			rarity = Rarity.RARE;
		} else {
			rarity = Rarity.NORMAL;
		}

		final double exponent = 1.3;
		double ini = rnd.nextInt(100)+Math.pow(floor,exponent)*rarity.modifier;
		double def = rnd.nextInt(100)+Math.pow(floor,exponent)*rarity.modifier;
		double atk = rnd.nextInt(100)+Math.pow(floor,exponent)*rarity.modifier;

		if (floor > StateTower.TUTORIAL_FLOOR && floor < 100) {
			final double nerfMod = 0.85; // Modifier to nerf the game between the floors StateTower.TUTORIAL_FLOOR and 100
			ini *= nerfMod;
			atk *= nerfMod;
			def *= nerfMod;
		}
		final double ancientLegendaryChance = 0.5;

		if (rarity == Rarity.LEGENDARY && rnd.nextDouble() >= ancientLegendaryChance) {
			StatType stat = StatType.values()[rnd.nextInt(3)];
			switch(stat) {
				case INITIATIVE:
					ini *=(int) rarity.modifier;
					break;
				case ATTACK:
					atk *=(int) rarity.modifier;
					break;
				case DEFENSE:
					def *=(int) rarity.modifier;
					break;
				default:
					assert(false) : "A new stat has been added but is not accounted for.";
			}
		}

		Item thisItem = new Item(ItemType.randomItemType(),(int)ini,(int)atk,(int)def,rarity);

		return thisItem;
	}

	/**
	 * This methid renders an items stats to the screen by calling Stats.render()
	 * @param g provides the Stats.render() method with its required Graphic
	 * @param x is the x-coordinate for the renderer
	 * @param y is the y-coordinate for the renderer
	 */

	public void render(Graphics g, int x, int y)
	{
		stats.render(g,x,y,new Color(rarity.hexColor));
	}

}
