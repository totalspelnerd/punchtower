package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.LoadFailedException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.SaveFailedException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.TagException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.Gamestate;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import javax.swing.*;
import java.io.File;

/**
 * This class handles the saving and loading in the game.
 */
public class SaveLoad
{

	public static final String SAVE_FILE = "save.dat";

	public static void save(StateTower tower)
	{
		SaveFile save = tower.saveToFile(new SaveFile(SAVE_FILE));
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
}
