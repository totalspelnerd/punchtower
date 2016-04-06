package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class is used to set and keep track of variables that belong no other place.
 */


public class Tower{
	private int floor;
	private double normalDropChance = 0.8;
	private double rareDropChance = 0.1;
	private double epicDropChance = 0.05;
	private double legendaryDropChance = 0.01;

	public Tower(final int floor)//, final int normalDropChance, final int rareDropChance, final int epicDropChance,
				 //final int legendaryDropChance)
	{
		this.floor = floor;
		//this.normalDropChance = normalDropChance;
		//this.rareDropChance = rareDropChance;
		//this.epicDropChance = epicDropChance;
		//this.legendaryDropChance = legendaryDropChance;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(final int floor) {
		this.floor = floor;
	}

	public double getNormalDropChance() {
		return normalDropChance;
	}

	public void setNormalDropChance(final int normalDropChance) {
		this.normalDropChance = normalDropChance;
	}

	public double getRareDropChance() {
		return rareDropChance;
	}

	public void setRareDropChance(final int rareDropChance) {
		this.rareDropChance = rareDropChance;
	}

	public double getEpicDropChance() {
		return epicDropChance;
	}

	public void setEpicDropChance(final int epicDropChance) {
		this.epicDropChance = epicDropChance;
	}

	public double getLegendaryDropChance() {
		return legendaryDropChance;
	}

	public void setLegendaryDropChance(final int legendaryDropChance) {
		this.legendaryDropChance = legendaryDropChance;
	}


}
