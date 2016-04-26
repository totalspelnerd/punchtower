package se.liu.ida.timha404.aleev379.tddd78.punchtower.enums;

/**
 * This is an enum to represent the different rarities of items available in the game.
 */
public enum Rarity {
	/**
	 * Rarity and colorcode of Normal gear
	 */
	NORMAL(0xffffff,1.0f),

	/**
	 * Rarity and colorcode of Rare gear
	 */
	RARE(0x0000ff,1.5f),

	/**
	 * Rarity and colorcode of Epic gear
	 */
	EPIC(0x9900ff,2.0f),

	/**
	 * Rarity and colorcode of Legendary gear
	 */
	LEGENDARY(0xff7700,3.0f);

	/**
	 * The color of the rarity of a certain item.
	 */
	public final int hexColor;

	/**
	 * Modifier for the rarity
	 */
	public final float modifier;

	Rarity(int hexColor, float modifier)
	{
	    this.hexColor = hexColor;
		this.modifier = modifier;

	}
}
