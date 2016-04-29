package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.LoadFailedException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.SaveFailedException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.TagException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.Gamestate;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import javax.swing.*;
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

	public static void save(StateTower tower, String filename)
	{
		SaveFile save = new SaveFile(filename);
		tower.saveToFile(save);
		try
		{
			save.saveToFile();
		}
		catch(SaveFailedException e)
		{
			e.printStackTrace();
			PunchLogger.LOGGER.severe("Saving could not be completed.");
		}
	}

	public static Gamestate load(String filename) throws LoadFailedException, TagException
	{
		if(!new File(filename).exists()) {
			PunchLogger.LOGGER.warning("The given save file could not be found. " + filename);
			throw new LoadFailedException(filename);
		}
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
