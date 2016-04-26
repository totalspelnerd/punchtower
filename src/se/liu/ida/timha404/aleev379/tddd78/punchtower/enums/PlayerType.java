package se.liu.ida.timha404.aleev379.tddd78.punchtower.enums;

/**
 * Enum used to keep track of the player type.
 */
public enum PlayerType
{
	/**
	 * Speedy stan character
	 */
	STAN("Speedy Stan"),
	/**
	 * Brutal brick character
	 */
	BRICK("Brutal Brick"),
	/**
	 * Tanky Ted character
	 */
	TED("Tanky Ted");

	/**
	 * Name of the player.
	 */
	public final String name;

	private PlayerType(String name)
	{
		this.name = name;
	}
}
