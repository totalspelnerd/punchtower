package se.liu.ida.timha404.aleev379.tddd78.punchtower;


/**
 * This class handles our experience math to calculate the amount of experience needed for a certain level.<br/>
 * Everything in this class is static since we would not need an object to generate the experience.
 * You can't change its ourcome by altering some variables.
 */
public final class Experience {

	private Experience(){}

	public static int getXp(int level)
	{
		if(level <= 1)
			return 0;
		int xpLast = 0;
		for(int i = 2;i<=level;i++)
		{
			xpLast+=getXpDiff(i);
		}
		return xpLast;
	}
	
	private static int getXpDiff(int level)
	{
		return (int)(1000*Math.pow(2, level/7.0)); // 7.0 is a magic number to scale the xp into a certain function.
	}
}
