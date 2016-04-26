package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class loads all of the needed images in our game.
 */
public final class ImageLoader{

	/**
	 * Background images of our game. No need to load more than once
	 */
	public static Image background = null;

	/**
	 * Background images of our game. No need to load more than once
	 */
	public static Image background50 = null;
	/**
	 * Background images of our game. No need to load more than once
	 */
	public static Image backgroundHell = null;

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
		monster = read("res/ogre.png");
		player = read("res/player.png");
		background = read("res/background.jpg");
		background50 = read("res/background50.png");
		backgroundHell = read("res/backgroundHell.jpg");
		deadText = read("res/dead.png");
	}

	private static Image getErrorImage()
	{
		boolean[] errorImage = new boolean[]{
			true ,true ,true ,false,true ,true ,false,false,true ,true ,false,
			true ,false,false,false,true ,false,true ,false,true ,false,true ,
			true ,true ,false,false,true ,true ,false,false,true ,true ,false,
			true ,false,false,false,true ,false,true ,false,true ,false,true ,
			true ,true ,true ,false,true ,false,true ,false,true ,false,true };
		BufferedImage image = new BufferedImage(11,5,BufferedImage.TYPE_INT_RGB); // Size of image with the booleans above.
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				if(errorImage[x+y*image.getWidth()])
					image.setRGB(x,y,0x000000);
				else
					image.setRGB(x,y,0xff00ff); // PURPLE PINK COLOR
			}
		}
		return image;
	}

	private static Image read(String file)
	{
		try{
			Image image = ImageIO.read(new File(file));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return getErrorImage();
		}
	}
}
