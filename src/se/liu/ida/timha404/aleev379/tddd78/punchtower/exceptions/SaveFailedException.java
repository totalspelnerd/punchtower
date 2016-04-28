package se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions;

/**
 * This exception is thrown when the player could not successfully save a file.
 */
public class SaveFailedException extends Exception
{
	public SaveFailedException(String filename, Exception e)
	{
		super("Could not save file: "+filename);
		setStackTrace(e.getStackTrace());
	}
}
