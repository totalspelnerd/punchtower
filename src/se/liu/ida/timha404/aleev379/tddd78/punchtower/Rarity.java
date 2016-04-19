package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This is an enum to represent the different rarities of items available in the game.
 */
public enum Rarity {
	/**
	 * Rarity and colorcode of Normal gear
	 */
	NORMAL(0xffffff),

	/**
	 * Rarity and colorcode of Rare gear
	 */
	RARE(0x0000ff),

	/**
	 * Rarity and colorcode of Epic gear
	 */
	EPIC(0x9900ff),

	/**
	 * Rarity and colorcode of Legendary gear
	 */
	LEGENDARY(0xff7700);

	/**
	 * The color of the rarity of a certain item.
	 */
	public final int hexColor;

	Rarity(int hexColor)
	{
	    this.hexColor = hexColor;
	}
}
