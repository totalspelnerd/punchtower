package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * Different type of stats for our game.
 */
public enum StatType
{
	/**
	 * Enum for initiative stat.
	 */
	INITIATIVE("ini"),
	/**
	 * Enum for attack stat.
	 */
	ATTACK("atk"),
	/**
	 * Enum for defense stat.
	 */
	DEFENSE("def");

	/**
	 * Name of the stat, shortened.
	 */
	public final String name;

	private StatType(String name)
	{
		this.name = name;
	}
}
