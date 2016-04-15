package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.awt.*;

public abstract class Entity{

	public static final int STANDARD_HP = 10000;

	protected Stats stats;
	protected int hp;
	protected String name;

	public Entity(final Stats stats, final int hp, final String name) {
		this.stats = stats;
		this.hp = hp;
		this.name = name;
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

	public abstract AttackData attack(int attackType);
	public abstract void render(Graphics g, int x, int y);


}
