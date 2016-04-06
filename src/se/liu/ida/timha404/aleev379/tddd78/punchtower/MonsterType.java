package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.util.Random;

public enum MonsterType{

	OGRE(), TROLL();




	/**
	 * This static method is used to select a random monster type when generating a new monster in Monster.generateRandom(),
	 * and needs to be static to be usable.
	 */

	public static String randomMonsterType() {
		int pick = new Random().nextInt(MonsterType.values().length);
		return MonsterType.values()[pick].toString();
	}
}
