package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class is a simple data class that only contains data about a single attack. Used for logging and rendering
 */
public class AttackData {

	public Entity attacker;
	public Entity defender;
	public boolean hit;
	public boolean crit;
	public boolean kill;
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