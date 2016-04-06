package se.liu.ida.timha404.aleev379.tddd78.punchtower;

/**
 * The meat of the game. Here is where the main gameloop is and where the game starts to run (main method).
 * This class handles all the calling to the drawing / updates.
 */

public class PunchTower
{
    /**
     * Frame cap of the game.
     */
    public static final int FRAME_CAP = 60;

    private static final PunchTower INSTANCE = new PunchTower();

    /**
     * Boolean to keep the gameloop running. <br>
     * If true it will keep running otherwise it will stop.
     */
    private boolean running = true;

    /**
     * Current fps of the game.
     */
    private int fps = 0;

    private PunchTower()
    {

    }

    /**
     * This method is called to run the gameloop. It is not threaded so it will take up the whole thread and run forever.
     */
    private void run()
    {
	float delta = 0;
	float updateTime = 1.0f/FRAME_CAP;
	float updateDelta = 0;
	float frameTime = 1.0f;
	float frameDelta = 0;
	int frames = 0;

	Timer timer = new Timer();
	PunchFrame frame = new PunchFrame();
	while(running)
	{
	    delta = timer.timeElapsed();
	    updateDelta += delta;
	    frameDelta += delta;
	    if(updateDelta >= updateTime)
	    {
		frame.update(updateTime);
		frame.repaint();
		frames++;
		updateDelta-=updateTime;
	    }
	    if(frameDelta >= frameTime)
	    {
		fps = frames;
		frames = 0;
		frameDelta-=frameTime;
	    }
	}
    }

    /**
     * This method will make the gameloop stop running and therefor will close the game.
     */
    public void closeGameLoop()
    {
	running = false;
    }

    public int getFPS()
    {
	return fps;
    }

    /**
     * Starts the gameloop.
     */
    public void start()
    {
	running = true;
	run();
    }

    /**
     *
     * @return The instance of PunchTower
     */
    public static PunchTower getInstance()
    {
	return INSTANCE;
    }

    /**
     * Main method
     */
    public static void main(String[] args)
    {
    	INSTANCE.start();
    }
}

