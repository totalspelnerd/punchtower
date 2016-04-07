package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * This class is used to keep track of time in our game. Generally only used in Gameloop
 *
 * @see PunchTower
 */

public class Timer
{
    /**
     * The time of the system since last update (timeElapsed).
     */
    private long currentTime;

    /**
     * Nano second to second converter. Used to convert currentTime (ns) to seconds.
     */
    private static final long NANO_SECOND = 1000000000L;

    /**
     * Initialize timer.
     */
    public Timer()
    {
	resetTime();
    }

    /**
     * Resets the timer to 0
     */
    public void resetTime()
    {
	currentTime = System.nanoTime();
    }

    /**
     * @return timeElapsed since last call in seconds.
     */
    public float timeElapsed()
    {
	long lastTime = currentTime;
	resetTime();
	return (float)(currentTime - lastTime) / NANO_SECOND;
    }
}
