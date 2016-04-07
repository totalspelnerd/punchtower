package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.Item;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Monster;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Player;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Tower;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class StateIngame extends Gamestate
{
    Item item;
    Player c;
	Monster monster;
	Image image;
	int floor;
	Tower tower;
    public StateIngame()
    {
		floor = new Random().nextInt(100)+1;
		tower = new Tower(floor);
		item = Item.generateRandomItem(tower);
		c = new Player("TOP KEK", 50, 50, 50);
		monster = Monster.generateMonster();
		try {
			image = ImageIO.read(new File("res/stone-wall-texture-wallpaper-2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void update(final float timeElapsed)
    {

	}

	public void tick()
	{
		item = Item.generateRandomItem(tower);
	}

    @Override
    public void render(final Graphics g)
    {
		g.drawImage(image,0,0,1280,720,null);
		if(item != null)
			item.render(g,PunchPanel.WIDTH/2-item.getStats().getWidth()/2,PunchPanel.HEIGHT/2-item.getStats().getHeight()/2);
		c.render(g,10,40);
		monster.render(g, PunchPanel.WIDTH-monster.getStats().getWidth()-10, 40);
		g.setFont(new Font(Font.MONOSPACED,Font.PLAIN,100));
		g.drawString("Floor: "+floor,640-g.getFontMetrics().stringWidth("Floor: "+floor)/2,100);
    }
}
