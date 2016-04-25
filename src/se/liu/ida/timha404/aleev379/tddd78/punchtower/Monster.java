package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.awt.*;
import java.util.Random;

/**
 * This class is used to represent monsters
 */
public class Monster extends Entity{

	public static Random rnd = new Random();

	public Monster(final String name, final int initiative, final int attack, final int defense) {
		super(new Stats(name, initiative, attack, defense), STANDARD_HP, name);
	}


	public void render(Graphics g, int x, int y) {
		stats.render(g,x,y,new Color(0xaa0000));
		g.drawImage(ImageLoader.monster, x-400,y+200,450,400,null);
		g.setColor(Color.red);
		g.fillRect(x-210, y, 200, 20);
		g.setColor(Color.green);
		g.fillRect(x-210, y, (int)(200*(hp/(float)STANDARD_HP)), 20);

	}

	/**
	 * Generates a new AttackData with information about an attack made by a monster
	 * @param attackType represents the kind of attack used (not used for monsters atm)
	 * @return A new AttackData object with the details of the attack event
	 */
	public AttackData attack(int attackType) {
		Player player = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getPlayer();

		if (hp <= 0) {
			return new AttackData(this, player, false, false, false, 0);
		}
		return Combat.attack(this, player, rnd.nextInt(3));

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
		double base = 1.023151432;
		double start = 1077.587445;
		double statBase = start * Math.pow(base, floor);
		double lowFloorMod = Math.min(14/floor, 400) + 150; // Explicit numbers to make the first few floor easier.
		if (floor < 13) { // explicit number to decide which floors are easier

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
