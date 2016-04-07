package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class represents the playable character and is called whenever a new game starts.
 */

public class Character{
	private String name;
	private int stat1;
	private int stat2;
	private int stat3;
	private Item head = null;
	private Item shoulder = null;
	private Item chest = null;
	private Item legs = null;
	private Item boots = null;
	private Item weapon = null;


	public Character(final String name, final int stat1, final int stat2, final int stat3) {
		this.name = name;
		this.stat1 = stat1;
		this.stat2 = stat2;
		this.stat3 = stat3;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getStat1() {
		return stat1;
	}

	public void setStat1(final int stat1) {
		this.stat1 = stat1;
	}

	public int getStat2() {
		return stat2;
	}

	public void setStat2(final int stat2) {
		this.stat2 = stat2;
	}

	public int getStat3() {
		return stat3;
	}

	public void setStat3(final int stat3) {
		this.stat3 = stat3;
	}

    	// Change these to be called getHeadItem? To better understand the code.

	public Item getHead() {
		return head;
	}

	public void setHead(final Item head) {
		this.head = head;
	}

	public Item getShoulder() {
		return shoulder;
	}

	public void setShoulder(final Item shoulder) {
		this.shoulder = shoulder;
	}

	public Item getChest() {
		return chest;
	}

	public void setChest(final Item chest) {
		this.chest = chest;
	}

	public Item getLegs() {
		return legs;
	}

	public void setLegs(final Item legs) {
		this.legs = legs;
	}

	public Item getBoots() {
		return boots;
	}

	public void setBoots(final Item boots) {
		this.boots = boots;
	}

	public Item getWeapon() {
		return weapon;
	}

	public void setWeapon(final Item weapon) {
		this.weapon = weapon;
	}
}
