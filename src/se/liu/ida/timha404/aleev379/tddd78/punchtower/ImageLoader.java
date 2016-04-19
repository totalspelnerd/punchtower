package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageLoader{

	public static Image background;
	public static Image deadText;
	public static Image monster;
	public static Image player;

	static {
		try {
			monster = ImageIO.read(new File("res/ogre.png"));
			player = ImageIO.read(new File("res/player.png"));
			background = ImageIO.read(new File("res/stone-wall-texture-wallpaper-2.jpg"));
			deadText = ImageIO.read(new File("res/dead.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
