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
			JOptionPane.showMessageDialog(null,e.getStackTrace(),e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	public static Gamestate load(String filename) throws LoadFailedException, TagException
	{
		if(!new File(filename).exists())
			throw new LoadFailedException(filename);
		SaveFile file = new SaveFile(filename);
		file.load();
		return StateTower.loadFromFile(file);
	}

	public static void delete(String filename)
	{
		if (!new File(filename).delete()) JOptionPane
			.showMessageDialog(null, "Save file could not be deleted.\nGuess you can keep the save...", "Delete failed",
							   JOptionPane.ERROR_MESSAGE);
	}
}
