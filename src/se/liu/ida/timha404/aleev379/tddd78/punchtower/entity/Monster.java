package se.liu.ida.timha404.aleev379.tddd78.punchtower.entity;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.AttackData;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Renderer;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Stats;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.awt.*;
import java.util.Random;

/**
 * This class is used to represent monsters
 */
public class Monster extends Entity{

	private static Random rnd = new Random();

	private static final double BASE_FUNCTION = 1.023151432;
	private static final double START_FUNCTION = 1077.587445;
	private static final int STATS_BOREDER_COLOR = 0xaa0000;
	private static final int TUTORIAL_MODIFIER_MIN = 150;
	private static final double STAT_MODIFIER_MIN = 0.9;
	private static final double STAT_MODIFIER_DIFF = 0.3;
	private final static int MONSTER_OFFSET_X = 400;
	private final static int MONSTER_OFFSET_Y = 200;
	private final static int MONSTER_WIDTH = 450;
	private final static int MONSTER_HEIGHT = 450;
	private final static int HEALTH_OFFSET_X = 220;
	private final static int HEALTH_WIDTH = 210;
	private final static int HEALTH_HEIGHT = 20;

	public Monster(final String name, final int initiative, final int attack, final int defense) {
		super(new Stats(name, initiative, attack, defense), STANDARD_HP);
	}


	public void render(Graphics g, int x, int y) {

		stats.render(g,x,y,new Color(STATS_BOREDER_COLOR)); // Darker red border around the stats.
		g.drawImage(ImageLoader.monster, x - MONSTER_OFFSET_X, y + MONSTER_OFFSET_Y, MONSTER_WIDTH, MONSTER_HEIGHT, null);
		Renderer.renderProgression(g, x - HEALTH_OFFSET_X, y, HEALTH_WIDTH, HEALTH_HEIGHT, Color.red, Color.green, 0, STANDARD_HP, hp);

	}

	/**
	 * Generates a new AttackData with information about an attack made by a monster
	 * @param type represents the kind of attack used (not used for monsters atm)
	 * @return A new AttackData object with the details of the attack event
	 */
	public AttackData attack(AttackType type) {
		Player player = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getPlayer();

		if (hp <= 0) {
			return new AttackData(this, player, false, false, false, 0);
		}
		return Combat.attack(this, player, type);

	}

	/**
	 * This method is used to generate a new random monster
	 * @return thisMonster, a new monster
	 */

	public static Monster generateMonster(int floor) {
		String type = MonsterType.randomMonsterType();
		int ini;
		int atk;
		int def;
		double statBase = START_FUNCTION * Math.pow(BASE_FUNCTION, floor);
		double lowFloorMod = StateTower.TUTORIAL_FLOOR/(double)floor + TUTORIAL_MODIFIER_MIN;
		if (floor < StateTower.TUTORIAL_FLOOR) {
			ini = (int) (statBase*(STAT_MODIFIER_MIN + rnd.nextDouble() * STAT_MODIFIER_DIFF)-lowFloorMod)/3;
			def = (int) (statBase*(STAT_MODIFIER_MIN + rnd.nextDouble() * STAT_MODIFIER_DIFF)-lowFloorMod)/3;
			atk = (int) (statBase*(STAT_MODIFIER_MIN + rnd.nextDouble() * STAT_MODIFIER_DIFF)-lowFloorMod)/3;
		}else {
			ini = (int) (statBase * STAT_MODIFIER_MIN + statBase * rnd.nextDouble() * STAT_MODIFIER_DIFF)/3;
			def = (int) (statBase * STAT_MODIFIER_MIN + statBase * rnd.nextDouble() * STAT_MODIFIER_DIFF)/3;
			atk = (int) (statBase * STAT_MODIFIER_MIN + statBase * rnd.nextDouble() * STAT_MODIFIER_DIFF)/3;
		}
		Monster thisMonster = new Monster(type, ini, atk, def);
		return thisMonster;
	}
}
