package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.entity.Entity;

/**
 * This class is a simple data class that only contains data about a single attack. Used for logging and rendering
 */
public class AttackData {

	/**
	 * Entity of the attacker
	 */
	public Entity attacker;

	/**
	 * Entity of the defender
	 */
	public Entity defender; // Could potientially be used in the future.

	/**
	 * True if attack is a hit
	 */
	public boolean hit;

	/**
	 * True if attack is a crit
	 */
	public boolean crit;

	/**
	 * True if attacker killed the defender
	 */
	public boolean kill;

	/**
	 * Damage of the attack
	 */
	public int damage;

	public AttackData(final Entity attacker, final Entity defender, final boolean hit, final boolean crit, final boolean kill,
					  final int damage)
	{
		this.attacker = attacker;
		this.defender = defender;
		this.hit = hit;
		this.crit = crit;
		this.kill = kill;
		this.damage = damage;
	}
	
	public String toString()
	{
		if(!hit)
			return "Miss";
		String s = Integer.toString(damage);
		if(kill)
			s+= " Fatal";
		if(crit)
			s += " Crit";
		return s;
	}
}