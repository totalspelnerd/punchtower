package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * This class handles our logging for the game problems.
 * This class can't be an object since we wouldn't need more than one logger in our game.
 */
public final class PunchLogger
{

	/**
	 * Logger for the game.
	 */
	public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private PunchLogger(){}

	public static void setup() throws IOException
	{
		Logger rootLogger = Logger.getLogger("");
		Handler[] handlers = rootLogger.getHandlers();
		if (handlers[0] instanceof ConsoleHandler) {
			rootLogger.removeHandler(handlers[0]);
		}


		LOGGER.setLevel(Level.WARNING);
		FileHandler file = new FileHandler("Logging.txt");

		SimpleFormatter formatter = new SimpleFormatter();
		file.setFormatter(formatter);
		LOGGER.addHandler(file);
	}
}
