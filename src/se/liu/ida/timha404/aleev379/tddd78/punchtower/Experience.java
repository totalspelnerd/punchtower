package se.liu.ida.timha404.aleev379.tddd78.punchtower;

public class Experience {
	
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
	
	public static int getXpDiff(int level)
	{
		return (int)(1000*Math.pow(2.0f, level/7.0));
	}
}
