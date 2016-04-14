package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * This class is used to represent monsters
 */
public class Monster extends Entity{

	private static Image image;

	static {
		try {
			image = ImageIO.read(new File("res/ogre.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Monster(final String name, final int initiative, final int attack, final int defense) {
		super(new Stats(name, initiative, attack, defense), STANDARD_HP, name);
	}


	public void render(Graphics g, int x, int y) {
		stats.render(g,x,y,new Color(0xaa0000));
		g.drawImage(image, x-400,y+200,450,400,null);
		g.setColor(Color.red);
		g.fillRect(x-210, y, 200, 20);
		g.setColor(Color.green);
		g.fillRect(x-210, y, (int)(200*(hp/(float)STANDARD_HP)), 20);

	}

	public AttackData attack() {
		Player player = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getPlayer();

		if (hp <= 0) {
			return new AttackData(this, player, false, false, false, 0);
		}
		return Combat.attack(this, player, new Random().nextInt(3));

	}

	/**
	 * This method is used to generate a new random monster
	 * @return thisMonster, a new monster
	 */

	public static Monster generateMonster(int floor) {
		Player player = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getPlayer();
		String type = MonsterType.randomMonsterType();
		double exponent = 1.3;
		double monsterMod = 5;
		int ini = (int)(player.getStats().initiative*0.9+player.getStats().initiative* new Random().nextDouble()*0.2);
		int def = (int)((new Random().nextInt(100) + Math.pow(floor, exponent))*monsterMod);
		int atk = (int)((new Random().nextInt(100) + Math.pow(floor, exponent))*monsterMod);
		Monster thisMonster = new Monster(type, ini, atk, def);
		return thisMonster;
	}
}
