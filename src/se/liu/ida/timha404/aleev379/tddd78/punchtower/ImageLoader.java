package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageLoader{

	public static Image background;
	public static Image deadText;

	static {
		try {
			background = ImageIO.read(new File("res/stone-wall-texture-wallpaper-2.jpg"));
			deadText = ImageIO.read(new File("res/dead.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
