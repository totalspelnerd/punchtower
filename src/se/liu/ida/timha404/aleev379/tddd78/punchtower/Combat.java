package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.util.Random;

/**
 * This class handles the combat of the game. With algorithms for attacking and blocking.
 */
public class Combat {

	// The hit chance of the different attacks
	public static final double HIT_CHANCE_QUICK = 1.0;
	public static final double HIT_CHANCE_NORMAL = 0.85;
	public static final double HIT_CHANCE_HEAVY = 0.75;

	// The crit chance of the different attacks
	public static final double CRIT_CHANCE_QUICK = 0.05;
	public static final double CRIT_CHANCE_NORMAL = 0.1;
	public static final double CRIT_CHANCE_HEAVY = 0.15;

	// The damage modifier for the different attacks
	public static final double QUICK_MOD = 1;
	public static final double NORMAL_MOD = 1.4;
	public static final double HEAVY_MOD = 2;

	// The index of the attack
	public static final int ATTACK_QUICK = 0;
	public static final int ATTACK_NORMAL = 1;
	public static final int ATTACK_HEAVY = 2;

	/**
	 * Random number generator for the combat.
	 */
	public static final Random RANDOM = new Random();

	private Combat(){}

	public static AttackData attack(final Entity attacker, final Entity defender, final int attackType) {
		boolean hit = false;
		boolean crit = false;
		double dmgMod = 0.0;
		switch(attackType) {
			case ATTACK_QUICK:
				hit = (RANDOM.nextDouble() <= HIT_CHANCE_QUICK);
				crit = (RANDOM.nextDouble() <= CRIT_CHANCE_QUICK);
				dmgMod = QUICK_MOD;
				break;
			case ATTACK_NORMAL:
				hit = (RANDOM.nextDouble() <= HIT_CHANCE_NORMAL);
				crit = (RANDOM.nextDouble() <= CRIT_CHANCE_NORMAL);
				dmgMod = NORMAL_MOD;
				break;
			case ATTACK_HEAVY:
				hit = (RANDOM.nextDouble() <= HIT_CHANCE_HEAVY);
				crit = (RANDOM.nextDouble() <= CRIT_CHANCE_HEAVY);
				dmgMod = HEAVY_MOD;
				break;
		}
		return calcDamage(attacker, defender, hit, crit, dmgMod);

	}

	public static AttackData calcDamage(Entity attacker, Entity defender, boolean hit, boolean crit, double typeMod) {

		int damage = (int) (attacker.getStats().attack* (1-(defender.getStats().defense / (float) (defender.getStats().defense + 100)))*typeMod);
		if (attacker instanceof Player && StateTower.floor < 13) {
			int damageMult =(int)  ((15 - StateTower.floor)*0.25 + 1);
			if (attacker.getName() != "Speedy Stan") {
				damageMult += 0.5;
			}
			damage *= damageMult;
		}
		if (crit) {
			damage *= (int) (RANDOM.nextDouble()*0.5+1.7); // These magic numbers are used to set the critical hit modifier.
		}
		if (!hit) damage=0;
		boolean kill = (damage >= defender.hp);
		defender.hp -= damage;
		defender.hp = defender.hp < 0 ? 0 : defender.hp;
		AttackData data =new AttackData(attacker, defender, hit, crit, kill, damage);

		return data;
	}

}
