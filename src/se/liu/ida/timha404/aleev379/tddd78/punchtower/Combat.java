package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.util.Random;

public class Combat {
	public static final double HIT_CHANCE_QUICK = 1.0;
	public static final double HIT_CHANCE_NORMAL = 0.75;
	public static final double HIT_CHANCE_HEAVY = 0.55;

	public static final double CRIT_CHANCE_QUICK = 0.05;
	public static final double CRIT_CHANCE_NORMAL = 0.1;
	public static final double CRIT_CHANCE_HEAVY = 0.15;

	public static final double QUICK_MOD = 1;
	public static final double NORMAL_MOD = 1.2;
	public static final double HEAVY_MOD = 1.7;

	public static final int ATTACK_QUICK = 0;
	public static final int ATTACK_NORMAL = 1;
	public static final int ATTACK_HEAVY = 2;

	public static final Random rnd = new Random();

	public static AttackData attack(final Entity attacker, final Entity defender, final int attackType) {
		boolean hit = false;
		boolean crit = false;
		double dmgMod = 0.0;
		switch(attackType) {
			case ATTACK_QUICK:
				hit = (rnd.nextDouble() <= HIT_CHANCE_QUICK);
				crit = (rnd.nextDouble() <= CRIT_CHANCE_QUICK);
				dmgMod = QUICK_MOD;
				break;
			case ATTACK_NORMAL:
				hit = (rnd.nextDouble() <= HIT_CHANCE_NORMAL);
				crit = (rnd.nextDouble() <= CRIT_CHANCE_NORMAL);
				dmgMod = NORMAL_MOD;
				break;
			case ATTACK_HEAVY:
				hit = (rnd.nextDouble() <= HIT_CHANCE_HEAVY);
				crit = (rnd.nextDouble() <= CRIT_CHANCE_HEAVY);
				dmgMod = HEAVY_MOD;
				break;
		}
		return calcDamage(attacker, defender, hit, crit, dmgMod);

	}

	public static AttackData calcDamage(Entity attacker, Entity defender, boolean hit, boolean crit, double typeMod) {

		int damage = (int) (attacker.getStats().attack*(defender.getStats().defense / (defender.getStats().defense*1.1 + 100))*typeMod);
		if (attacker instanceof Player && StateTower.floor < 13) {
			damage *=2;
		}
		if (crit) {
			damage *= (int) (rnd.nextDouble()*0.5+1.7); // These magic numbers are used to set the critical hit modifier.
		}
		if (!hit) damage=0;
		boolean kill = (damage >= defender.hp);
		defender.hp -= damage;
		defender.hp = defender.hp < 0 ? 0 : defender.hp;
		AttackData data =new AttackData(attacker, defender, hit, crit, kill, damage);

		return data;
	}

}
