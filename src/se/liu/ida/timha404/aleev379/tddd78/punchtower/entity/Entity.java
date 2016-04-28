package se.liu.ida.timha404.aleev379.tddd78.punchtower.entity;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.Stats;

/**
 * Entity is something that can fight something else in the game.
 */
public class Entity{

	/**
	 * The standard hp for normal enemies and players.
	 */
	public static final int STANDARD_HP = 2500;

	/**
	 * Max number that can be added to the initiative stack.
	 */
	public static final int MAX_STACK_INC = 100;

	/**
	 * Initiative stack size.
	 */
	public static final int STACK_CAP = 300;

	/**
	 * Initiative increase
	 */

	public static final float INITIATIVE_INCREASE = 0.1f;

	protected Stats stats;
	protected int hp;
	protected float initiativeStack;

	protected Entity(final Stats stats, final int hp) {
		this.stats = stats;
		this.hp = hp;
		initiativeStack = 0;
	}

	public Stats getStats() {
		return stats;
	}

	public void setHp(final int hp) {
		this.hp = hp;
	}

	public void incInitiativeStack()
	{
		float inc = stats.initiative*INITIATIVE_INCREASE;
		inc = inc > MAX_STACK_INC ? MAX_STACK_INC : inc;
		initiativeStack += inc;
		initiativeStack = initiativeStack > STACK_CAP ? STACK_CAP : initiativeStack;
	}

	public void resetInitiativeStack()
	{
		initiativeStack = 0;
	}

	public float getInitiativeStack()
	{
		return initiativeStack;
	}

}
