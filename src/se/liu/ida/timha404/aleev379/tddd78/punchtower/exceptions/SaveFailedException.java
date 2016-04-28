package se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions;

public class SaveFailedException extends Exception
{
	public SaveFailedException(Exception e)
	{
		super("Could not save file.");
		setStackTrace(e.getStackTrace());
	}
}
