package se.liu.ida.timha404.aleev379.tddd78.punchtower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the playable character and is called whenever a new game starts.
 */

public class Player extends Entity{

	public static final int ITEM_HEAD= 0;
	public static final int ITEM_SHOULDER= 1;
	public static final int ITEM_CHEST= 2;
	public static final int ITEM_LEGS= 3;
	public static final int ITEM_BOOTS= 4;
	public static final int ITEM_WEAPON= 5;


	private Image image;

	private Item[] equipped = new Item[]{null,null,null,null,null,null};


	public Player(final String name, final int initiative, final int defense, final int attack) {
		super(new Stats(name, initiative, defense, attack), STANDARD_HP, name);
		try {
			image = ImageIO.read(new File("res/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Item getItem(final int itemIndex) {
		return equipped[itemIndex];
	}

	/**
	 * Equips the player with the given item and adjusts player stats accordingly.
	 * @param item
	 * @param itemIndex
	 */

	public void equip(final Item item, final int itemIndex) {
		if (equipped[itemIndex] != null) {
			stats.decrease(equipped[itemIndex].getStats());

		}
		this.equipped[itemIndex] = item;
		stats.increase(equipped[itemIndex].getStats());

	}

	public void attack() {
		Monster monster = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getMonster();
		Combat.attack(this, monster, 0);
	}


	public void render(Graphics g, int x, int y)
	{
		stats.render(g,x,y,new Color(0x00bb00));
		g.drawImage(image,x+200,y+200,200,400,null);
	}
}
