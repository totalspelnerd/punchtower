package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.LoadFailedException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.SaveFailedException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.TagException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.Gamestate;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.io.File;

/**
 * This class handles the saving and loading in the game. It doesn't need to be an object since it doesn't depend on anything from the outside.
 */
public final class SaveLoad
{

	/**
	 * Name of the save file
	 */
	public static final String SAVE_FILE = "save.dat";

	private SaveLoad(){}

	public static void save(StateTower tower, String filename) throws SaveFailedException
	{
		SaveFile save = new SaveFile(filename);
		tower.saveToFile(save);
		save.saveToFile();
	}

	public static Gamestate load(String filename) throws LoadFailedException, TagException
	{
		SaveFile file = new SaveFile(filename);
		file.load();
		return StateTower.loadFromFile(file);
	}

	public static void delete(String filename)
	{
		File file = new File(filename);
		if(file.exists())
			if (!file.delete())
				PunchLogger.LOGGER.severe("Could not delete file. Is the game in a restricted folder? Run Admin.");
	}
}
