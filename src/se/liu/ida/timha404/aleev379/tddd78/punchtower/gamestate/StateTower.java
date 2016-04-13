package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.Item;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Monster;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Player;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Tower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ItemType;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class StateTower extends Gamestate
{
    Item item;
	Image image;
	private int floor;
	private double normalDropChance = 1.0;
	private double rareDropChance = 0.1;
	private double epicDropChance = 0.05;
	private double legendaryDropChance = 0.01;
	private Player player;
	private Monster monster;
    public StateTower()
    {
		floor = new Random().nextInt(100)+1;
		item = Item.generateRandomItem(this);
		player = new Player("TOP KEK", 50, 50, 50);
		monster = Monster.generateMonster();
		try {
			image = ImageIO.read(new File("res/stone-wall-texture-wallpaper-2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public int getFloor() {
		return floor;
	}

	public void setFloor(final int floor) {
		this.floor = floor;
	}

	public double getNormalDropChance() {
		return normalDropChance;
	}

	public void setNormalDropChance(final int normalDropChance) {
		this.normalDropChance = normalDropChance;
	}

	public double getRareDropChance() {
		return rareDropChance;
	}

	public void setRareDropChance(final int rareDropChance) {
		this.rareDropChance = rareDropChance;
	}

	public double getEpicDropChance() {
		return epicDropChance;
	}

	public void setEpicDropChance(final int epicDropChance) {
		this.epicDropChance = epicDropChance;
	}

	public double getLegendaryDropChance() {
		return legendaryDropChance;
	}

	public void setLegendaryDropChance(final int legendaryDropChance) {
		this.legendaryDropChance = legendaryDropChance;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(final Monster curMonster) {
		this.monster = curMonster;
	}

    @Override
    public void update(final float timeElapsed)
    {

	}

	public void tick()
	{
		item = Item.generateRandomItem(this);
		player.equip(item, ItemType.valueOf(item.getItemType().toString()).ordinal());
		player.attack();

	}

    @Override
    public void render(final Graphics g)
    {
		g.drawImage(image,0,0,1280,720,null);
		if(item != null)
			item.render(g,PunchPanel.WIDTH/2-item.getStats().getWidth()/2,PunchPanel.HEIGHT/2-item.getStats().getHeight()/2);
		player.render(g,10,40);
		monster.render(g, PunchPanel.WIDTH-monster.getStats().getWidth()-10, 40);
		g.setFont(new Font(Font.MONOSPACED,Font.PLAIN,100));
		g.drawString("Floor: "+floor,640-g.getFontMetrics().stringWidth("Floor: "+floor)/2,100);
    }
}
