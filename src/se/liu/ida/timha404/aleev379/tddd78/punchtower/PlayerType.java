package se.liu.ida.timha404.aleev379.tddd78.punchtower;

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

	public final String name;

	private PlayerType(String name)
	{
		this.name = name;
	}
}
