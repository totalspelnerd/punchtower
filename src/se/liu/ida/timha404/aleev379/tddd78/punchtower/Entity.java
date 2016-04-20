package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.awt.*;

/**
 * Entity is something that can fight something else in the game.
 */
public abstract class Entity{

	/**
	 * The standard hp for normal enemies and players.
	 */
	public static final int STANDARD_HP = 2500;
	public static final int MAX_STACK_INC = 100;
	public static final int STACK_CAP = 300;

	protected Stats stats;
	protected int hp;
	protected float initiativeStack;
	protected String name;

	protected Entity(final Stats stats, final int hp, final String name) {
		this.stats = stats;
		this.hp = hp;
		this.name = name;
		initiativeStack = 0;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(final Stats stats) {
		this.stats = stats;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(final int hp) {
		this.hp = hp;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void incInitiativeStack()
	{
		float inc = stats.initiative*0.1f; // 10% of initiative
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


	public abstract AttackData attack(int attackType);
	public abstract void render(Graphics g, int x, int y);


}
