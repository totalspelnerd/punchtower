package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to represent monsters
 */
public class Monster{

	private String name;
	private Image image;
	private Stat stats;

	public Monster(final String name, final int initiative, final int defense, final int attack) {
		this.name = name;
		try {
			this.image = ImageIO.read(new File("res/ogre.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stats = new Stat(name,initiative, defense, attack);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getInitiative() {
		return stats.initiative;
	}

	public void setInitiative(final int initiative) {
		stats.initiative = initiative;
	}

	public int getDefense() {
		return stats.defense;
	}

	public void setDefense(final int defense) {
		stats.defense = defense;
	}

	public int getAttack() {
		return stats.attack;
	}

	public void setAttack(final int attack) {
		stats.attack = attack;
	}

	public Stat getStats()
	{
		return stats;
	}

	public void render(Graphics g, int x, int y)
	{
		stats.render(g,x,y,new Color(0xaa0000));
		g.drawImage(image, x-400,y+200,450,400,null);
	}

	/**
	 * This method is used to generate a new random monster
	 * @return thisMonster, a new monster
	 */

	public static Monster generateMonster() {
		String type = MonsterType.randomMonsterType();
		Monster thisMonster = new Monster(type, 1,2129,3);
		return thisMonster;
	}
}
