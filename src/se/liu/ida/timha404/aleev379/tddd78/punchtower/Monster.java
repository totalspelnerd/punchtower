package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to represent monsters
 */
public class Monster extends Entity{

	private Image image;

	public Monster(final String name, final int initiative, final int defense, final int attack) {
		super(new Stats(name, initiative, defense, attack), STANDARD_HP, name);
		try {
			this.image = ImageIO.read(new File("res/ogre.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void render(Graphics g, int x, int y)
	{
		stats.render(g,x,y,new Color(0xaa0000));
		g.drawImage(image, x-400,y+200,450,400,null);
	}

	public void attack() {
		Player player = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getPlayer();
		Combat.attack(this, player, 0);

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
