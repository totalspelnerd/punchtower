package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.util.Random;

/**
 * This is an enum to represent the different kinds of items available in the game.
 */

public enum ItemType{
	HEAD, SHOULDER, CHEST, LEGS, BOOTS, WEAPON;


	/**
	 * This method is used to generate a random item type for item generation.
	 * @return random ItemType converted to string.
	 */

	public static ItemType randomItemType() {
		int pick = new Random().nextInt(ItemType.values().length);
		return ItemType.values()[pick];
	}
}
