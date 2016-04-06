package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class is used to keep track of time in our game.
 *
 */

public class Timer
{
    private long currentTime;
    private static final long NANO_SECOND = 1000000000L;

    public Timer()
    {

    }

    /**
     * @return timeElapsed since last call in seconds.
     */
    public float timeElapsed()
    {
	long elapsed = currentTime;
	currentTime = System.nanoTime();
	return (currentTime-elapsed)/NANO_SECOND;
    }
}
