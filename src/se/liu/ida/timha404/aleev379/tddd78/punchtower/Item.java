package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class is used to create items, specifically by calling the generateItem() method.
 */


import java.util.Random;

public class Item{
	private ItemType iType;
	private int stat1;
	private int stat2;
	private int stat3;
	private Rarity rarity;


	public Item(final ItemType iType,final int stat1,final int stat2,final int stat3, final Rarity rarity) {
		this.iType = iType;
		this.stat1 = stat1;
		this.stat2 = stat2;
		this.stat3 = stat3;
		this.rarity = rarity;
	}

	/**
	 * This method is used to generate a new Item based on the current drop rates and the current floor.
	 * @param  curTower The current tower object
	 * @return A new item
	 */

	public Item generateItem(Tower curTower) {
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
		}

		Item thisItem = new Item(ItemType.randomItemType(),(int)((new Random().nextDouble()+curTower.getFloor()/100)*rarityMod*100),(int)((new Random().nextDouble()+curTower.getFloor()/100)*rarityMod*100),(int)((new Random().nextDouble()+curTower.getFloor()/100)*rarityMod*100),thisRarity);

		return thisItem;
	}

}
