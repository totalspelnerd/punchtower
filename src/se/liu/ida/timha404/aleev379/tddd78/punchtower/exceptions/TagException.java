package se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions;

public class TagException extends NumberFormatException
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
