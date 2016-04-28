package se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions;

/**
 * This class handles our tag exceptions when trying to access a non existing tag in SaveFile or unable to convert it to correct field.
 * @see se.liu.ida.timha404.aleev379.tddd78.punchtower.SaveFile
 */
public class TagException extends Exception
{
	public TagException(String tag, Exception e)
	{
		super("The tag " + tag + " could not be loaded.");
		setStackTrace(e.getStackTrace());
	}

	public TagException(String tag)
	{
		super("The tag " + tag + " could not be loaded.");
	}
}
