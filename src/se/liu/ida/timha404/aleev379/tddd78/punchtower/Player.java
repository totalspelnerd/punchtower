package se.liu.ida.timha404.aleev379.tddd78.punchtower;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class represents the playable character and is called whenever a new game starts.
 */

public class Player
{
    
	private String name;

	private Image image;

	private Stat stats;

	private Item head = null;
	private Item shoulder = null;
	private Item chest = null;
	private Item legs = null;
	private Item boots = null;
	private Item weapon = null;


	public Player(final String name, final int initiative, final int defense, final int attack) {
		this.name = name;
		try {
			image = ImageIO.read(new File("res/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		stats = new Stat(name,initiative,defense,attack);
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

    	// Change these to be called getHeadItem? To better understand the code.

	public Item getHead() {
		return head;
	}

	public void setHead(final Item head) {
		this.head = head;
	}

	public Item getShoulder() {
		return shoulder;
	}

	public void setShoulder(final Item shoulder) {
		this.shoulder = shoulder;
	}

	public Item getChest() {
		return chest;
	}

	public void setChest(final Item chest) {
		this.chest = chest;
	}

	public Item getLegs() {
		return legs;
	}

	public void setLegs(final Item legs) {
		this.legs = legs;
	}

	public Item getBoots() {
		return boots;
	}

	public void setBoots(final Item boots) {
		this.boots = boots;
	}

	public Item getWeapon() {
		return weapon;
	}

	public void setWeapon(final Item weapon) {
		this.weapon = weapon;
	}

	public void render(Graphics g, int x, int y)
	{
		stats.render(g,x,y,new Color(0x00bb00));
		g.drawImage(image,x+200,y+200,200,400,null);
	}
}
