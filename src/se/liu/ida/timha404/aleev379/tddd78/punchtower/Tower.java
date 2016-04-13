package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class is used to set and keep track of variables that belong no other place.
 */


public class Tower{

	private int floor;
	private double normalDropChance = 1.0;
	private double rareDropChance = 0.1;
	private double epicDropChance = 0.05;
	private double legendaryDropChance = 0.01;
	private Player player;
	private Monster curMonster;

	public Tower(final int floor)//, final int normalDropChance, final int rareDropChance, final int epicDropChance,
				 //final int legendaryDropChance)
	{
		this.floor = floor;
		//this.normalDropChance = normalDropChance;
		//this.rareDropChance = rareDropChance;
		//this.epicDropChance = epicDropChance;
		//this.legendaryDropChance = legendaryDropChance;
	}


}
