package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class is used to represent monsters
 */


public class Monster{
	private String name;
	private int stat1;
	private int stat2;
	private int stat3;

	public Monster(final String name, final int stat1, final int stat2, final int stat3) {
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

	/**
	 * This method is used to generate a new random monster
	 * @return thisMonster, a new monster
	 */

	public Monster generateMonster() {
		String type = MonsterType.randomMonsterType();
		Monster thisMonster = new Monster(type, 1,2,3);
		return thisMonster;
	}
}
