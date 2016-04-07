package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class is used to create items, specifically by calling the generateItem() method.
 */


import java.util.Random;

import java.awt.*;

public class Item{
	private ItemType iType;
	private Stat stats;
	private Rarity rarity;


	public Item(final ItemType iType,final int initiative,final int defense,final int attack, final Rarity rarity) {
		this.iType = iType;
		stats = new Stat(iType.toString(),initiative,defense,attack);
		this.rarity = rarity;
	}

	public Stat getStats()
	{
		return stats;
	}

	/**
	 * This method is used to generate a new Item based on the current drop rates and the current floor.
	 * @param  curTower The current tower object
	 * @return A new item
	 */

	public static Item generateRandomItem(Tower curTower) {
		Random rnd = new Random();
		double drop = rnd.nextDouble();
		Rarity thisRarity = null;
		double rarityMod = 0;
		if (drop <= curTower.getLegendaryDropChance()) {
			thisRarity = Rarity.LEGENDARY;
			rarityMod = 3;
		} else if (drop <= curTower.getEpicDropChance()) {
			thisRarity = Rarity.EPIC;
			rarityMod = 2;
		} else if (drop <= curTower.getRareDropChance()) {
			thisRarity = Rarity.RARE;
			rarityMod = 1.5;
		} else if (drop <= curTower.getNormalDropChance()) {
			thisRarity = Rarity.NORMAL;
			rarityMod = 1;
		} else {
		    thisRarity = Rarity.NORMAL;
		    rarityMod = 1;
		}

		double exponent = 1.3;
		int ini = (int)(new Random().nextInt(100)+Math.pow(curTower.getFloor(),exponent)*rarityMod);
		int def = (int)(new Random().nextInt(100)+Math.pow(curTower.getFloor(),exponent)*rarityMod);
		int atk = (int)(new Random().nextInt(100)+Math.pow(curTower.getFloor(),exponent)*rarityMod);
		Item thisItem = new Item(ItemType.randomItemType(),ini,def,atk,thisRarity);

		return thisItem;
	}

	public void render(Graphics g, int x, int y)
	{
		stats.render(g,x,y,new Color(rarity.hexColor));
	}

}
