package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import javafx.scene.paint.Color;

/**
 * This is an enum to represent the different rarities of items available in the game.
 */
public enum Rarity {
	NORMAL(0xffffff), RARE(0x0000ff), EPIC(0x9900ff), LEGENDARY(0xff7700);

	/**
	 * The color of the rarity of a certain item.
	 */
	public int hexColor;

	Rarity(int hexColor)
	{
	    this.hexColor = hexColor;
	}
}
