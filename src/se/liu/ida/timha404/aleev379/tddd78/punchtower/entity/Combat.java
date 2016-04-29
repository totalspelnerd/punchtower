package se.liu.ida.timha404.aleev379.tddd78.punchtower.entity;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.AttackData;
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

	private static final double STAN_NERF_TUTORIAL = 0.5;
	private static final double CRIT_MIN = 1.7;
	private static final double CRIT_DIFF = 0.5;
	private static final double TUTORIAL_FLOOR_MODIFIER = 0.25;

	private Combat(){}

	public static AttackData attack(final Entity attacker, final Entity defender, final AttackType attackType) {
		boolean hit = (RANDOM.nextDouble() <= attackType.hitChance);
		boolean crit = (RANDOM.nextDouble() <= attackType.critChance);
		double dmgMod = attackType.dmgModifier;
		return calcDamage(attacker, defender, hit, crit, dmgMod);

	}

	public static AttackData calcDamage(Entity attacker, Entity defender, boolean hit, boolean crit, double typeMod) {
		assert(!attacker.equals(defender)) : "Attacker can't attack himself.";
		double damage = (int) (attacker.getStats().attack* (1-(defender.getStats().defense / (float) (defender.getStats().defense + 100)))*typeMod);
		StateTower tower = (StateTower)GamestateHandler.getInstance().getCurrentGamestate();
		if (attacker instanceof Player && tower.getFloor() < StateTower.TUTORIAL_FLOOR) {
			int damageMult =(int)  ((StateTower.TUTORIAL_FLOOR - tower.getFloor())*TUTORIAL_FLOOR_MODIFIER + 1);
			if (((Player)attacker).getPlayerType() != PlayerType.STAN) {
				damageMult += STAN_NERF_TUTORIAL;
			}
			damage *= damageMult;
		}
		if (crit) {
			damage *= (RANDOM.nextDouble()*CRIT_DIFF+CRIT_MIN);
		}
		if (!hit) damage=0;
		boolean kill = (damage >= defender.hp);
		AttackData data = new AttackData(attacker, defender, hit, crit, kill, (int)damage);
		defender.hp -= (int)damage;
		defender.hp = defender.hp < 0 ? 0 : defender.hp;


		assert(damage >= 0) : "Attacker should always do damage defender.";

		return data;
	}

}
