package se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions;

/**
 * This class is our exception class for when loading fails in our game.
 */
public class LoadFailedException extends Exception
{
	public LoadFailedException(String filename, Exception e)
	{
		super("Could not load file: "+filename);
		setStackTrace(e.getStackTrace());
	}

	public LoadFailedException(String filename)
	{
		super("Could not load file: "+filename);
	}
}
