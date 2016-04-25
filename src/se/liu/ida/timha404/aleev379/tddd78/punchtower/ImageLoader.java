package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class loads all of the needed images in our game.
 */
public final class ImageLoader{

	/**
	 * Background images of our game. No need to load more than once
	 */
	public static Image background1 = null;
	public static Image background2 = null;
	public static Image background3 = null;
	public static Image background4 = null;

	/**
	 * The image of the text when you die. No need to load more than one
	 */
	public static Image deadText = null;

	/**
	 * The image of monster. No need to load more than one
	 */
	public static Image monster = null;

	/**
	 * The image of player. No need to load more than one
	 */
	public static Image player = null;

	private ImageLoader(){}

	static {
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		try {
			monster = ImageIO.read(new File("res/ogre.png"));
			player = ImageIO.read(new File("res/player.png"));
			background1 = ImageIO.read(new File("res/background1.jpg"));
			background2 = ImageIO.read(new File("res/background2.jpg"));
			background3 = ImageIO.read(new File("res/background3.png"));
			background4 = ImageIO.read(new File("res/background4.jpg"));
			deadText = ImageIO.read(new File("res/dead.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
