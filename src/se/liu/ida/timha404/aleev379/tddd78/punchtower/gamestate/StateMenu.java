package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;

import java.awt.*;

public class StateMenu extends Gamestate {

	@Override public void init() {



	}

	@Override public void update(final float timeElapsed) {

	}

	@Override public void render(final Graphics g) {
		g.drawImage(ImageLoader.background, 0, 0, PunchPanel.WIDTH, PunchPanel.HEIGHT, null);
		String description = "HELLO AND WELCOME ADVENTURER!\n";
		String iniGuy = "Your initiative stat will be increased. A lot.";
		String atkGuy = "Your attack stat will be increased. \nA lot.";
		String defGuy = "Your defense stat will be increased.\nA lot.";
		g.setColor(new Color(0,0,0, 125));
		g.fillRect(200-5, 600-25, 250, 100);
		g.fillRect(500-5, 600-25, 265, 100);
		g.fillRect(800-5, 600-25, 270, 100);
		g.fillRect(100-5, 100-25, 1110, 200);
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		renderTextMultiLine(g, description, 100, 100, 1100);
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 23));
		g.setColor(Color.YELLOW);
		renderTextMultiLine(g, iniGuy, 200, 600, 250);
		g.setColor(Color.RED);
		renderTextMultiLine(g, atkGuy, 500, 600, 270);
		g.setColor(Color.CYAN);
		renderTextMultiLine(g, defGuy, 800, 600, 300);
	}


}
