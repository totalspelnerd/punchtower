package se.liu.ida.timha404.aleev379.tddd78.punchtower.enums;

import java.util.Random;

/**
 * This is an enum to represent the different kinds of items available in the game.
 */

public enum ItemType{
	// These are all enums for keeping track of different gear. Names are for respective item slot.
	HEAD, SHOULDER, CHEST, LEGS, BOOTS, WEAPON;

	private static final Random RANDOM = new Random();

	/**
	 * This method is used to generate a random item type for item generation.
	 * @return random ItemType converted to string.
	 */

	public static ItemType randomItemType() {
		int pick = RANDOM.nextInt(ItemType.values().length);
		return ItemType.values()[pick];
	}
}
