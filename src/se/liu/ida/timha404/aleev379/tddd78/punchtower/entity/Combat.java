package se.liu.ida.timha404.aleev379.tddd78.punchtower.entity;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.AttackData;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.entity.Entity;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.entity.Player;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.AttackType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.PlayerType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.util.Random;

/**
 * This class handles the combat of the game. With algorithms for attacking and blocking.
 */
public final class Combat {

	/**
	 * Random number generator for the combat.
	 */
	private static final Random RANDOM = new Random();

	private Combat(){}

	public static AttackData attack(final Entity attacker, final Entity defender, final AttackType attackType) {
		boolean hit = (RANDOM.nextDouble() <= attackType.hitChance);
		boolean crit = (RANDOM.nextDouble() <= attackType.critChance);
		double dmgMod = attackType.dmgModifier;
		return calcDamage(attacker, defender, hit, crit, dmgMod);

	}

	public static AttackData calcDamage(Entity attacker, Entity defender, boolean hit, boolean crit, double typeMod) {

		double damage = (int) (attacker.getStats().attack* (1-(defender.getStats().defense / (float) (defender.getStats().defense + 100)))*typeMod);
		StateTower tower = (StateTower)GamestateHandler.getInstance().getCurrentGamestate();
		if (attacker instanceof Player && tower.getFloor() < StateTower.TUTORIAL_FLOOR) { // Magic number represents the number of tutorial floors.
			int damageMult =(int)  ((StateTower.TUTORIAL_FLOOR - tower.getFloor())*0.25 + 1); // Magic numbers used to make the game easier in the beginning.
			if (((Player)attacker).getPlayerType() == PlayerType.STAN) {
				damageMult += 0.5; // Magic number represents a damage modifier to make other characters other than speedy stan playable.
			}
			damage *= damageMult;
		}
		if (crit) {
			damage *= (RANDOM.nextDouble()*0.5+1.7); // These magic numbers are used to set the critical hit modifier. number between 1.7-2.2
		}
		if (!hit) damage=0;
		boolean kill = (damage >= defender.hp);
		defender.hp -= (int)damage;
		defender.hp = defender.hp < 0 ? 0 : defender.hp;
		AttackData data =new AttackData(attacker, defender, hit, crit, kill, (int)damage);

		return data;
	}

}
