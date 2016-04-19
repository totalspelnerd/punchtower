package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class is used to create items, specifically by calling the generateItem() method.
 */


import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.util.Random;

import java.awt.*;

public class Item{
	private ItemType iType;
	private Stats stats;
	private Rarity rarity;


	public Item(final ItemType iType,final int initiative,final int attack, final int defense, final Rarity rarity) {
		this.iType = iType;
		stats = new Stats(iType.toString(), initiative, attack, defense);
		this.rarity = rarity;
	}

	public Stats getStats()
	{
		return stats;
	}

	public ItemType getItemType() {
		return iType;
	}

	/**
	 * This method is used to generate a new Item based on the current drop rates and the current floor.
	 * @param  curTower The current tower object
	 * @return A new item
	 */

	public static Item generateRandomItem(StateTower curTower) {
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
		} else { //if (drop <= curTower.getNormalDropChance()) {
			thisRarity = Rarity.NORMAL;
			rarityMod = 1;
		}

		double exponent = 1.3;
		int ini = (int)(rnd.nextInt(100)+Math.pow(curTower.getFloor(),exponent)*rarityMod);
		int def = (int)(rnd.nextInt(100)+Math.pow(curTower.getFloor(),exponent)*rarityMod);
		int atk = (int)(rnd.nextInt(100)+Math.pow(curTower.getFloor(),exponent)*rarityMod);
		if (rnd.nextDouble() >= 0.5) {
			int temping = rnd.nextInt(3);
			switch(temping) {
				case 0:
					ini *=(int) rarityMod;
				case 1:
					atk *=(int) rarityMod;
				case 2:
					def *=(int) rarityMod;
			}
		}

		Item thisItem = new Item(ItemType.randomItemType(),ini,atk,def,thisRarity);

		return thisItem;
	}

	public void render(Graphics g, int x, int y)
	{
		stats.render(g,x,y,new Color(rarity.hexColor));
	}

}
