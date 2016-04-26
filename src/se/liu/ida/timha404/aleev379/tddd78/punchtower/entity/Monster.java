package se.liu.ida.timha404.aleev379.tddd78.punchtower.entity;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.AttackData;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Renderer;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Stats;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.AttackType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.MonsterType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.awt.*;
import java.util.Random;

/**
 * This class is used to represent monsters
 */
public class Monster extends Entity{

	private static Random rnd = new Random();

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

		stats.render(g,x,y,new Color(0xaa0000)); // Darker red border around the stats.
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
		// Magic numbers used in calculating stats for a monster to keep the game balanced
		final double base = 1.023151432;
		final double start = 1077.587445;
		double statBase = start * Math.pow(base, floor);
		double lowFloorMod = Math.min(14/floor, 400) + 150; // Explicit numbers to make the first few floor easier.
		if (floor < StateTower.TUTORIAL_FLOOR) {

			// Magic numbers to make the stats vary within the range 0.9 - 1.2 times the calculated stat
			ini = (int) ((statBase * 0.9 + statBase * rnd.nextDouble() * 0.3)-lowFloorMod)/3;
			def = (int) ((statBase * 0.9 + statBase * rnd.nextDouble() * 0.3)-lowFloorMod)/3;
			atk = (int) ((statBase * 0.9 + statBase * rnd.nextDouble() * 0.3)-lowFloorMod)/3;
		}else {
			ini = (int) (statBase * 0.9 + statBase * rnd.nextDouble() * 0.3)/3;
			def = (int) (statBase * 0.9 + statBase * rnd.nextDouble() * 0.3)/3;
			atk = (int) (statBase * 0.9 + statBase * rnd.nextDouble() * 0.3)/3;
		}
		Monster thisMonster = new Monster(type, ini, atk, def);
		return thisMonster;
	}
}
