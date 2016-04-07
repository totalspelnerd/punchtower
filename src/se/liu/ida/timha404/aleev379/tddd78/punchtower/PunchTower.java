package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateIngame;

/**
 * The meat of the game. Here is where the main gameloop is and where the game starts to run (main method).
 * This class handles all the calling to the drawing / updates.
 */

public class PunchTower
{
    /**
     * Frame cap of the game.
     */
    public static final int UPDATES_PER_SECOND = 60;

    /**
     * Frame cap of the game.
     */
    public static final int FRAME_CAP = 60;

    /**
     * Instance of the game.
     */
    private static final PunchTower INSTANCE = new PunchTower();

    /**
     * Boolean to keep the gameloop running. <br>
     * If true it will keep running otherwise it will stop.
     */
    private boolean running = true;

    /**
     * Current frames per second of the game.
     */
    private int fps = 0;

    /**
     * Current updates per second of the game.
     */
    private int ups = 0;

    /**
     * Private since this should never be created anywhere else.<br>
     * We will never have more than one instance of the game running on one process (at the same time).
     */
    private PunchTower()
    {

    }
    int frames = 0;
    /**
     * This method is called to run the gameloop. It is not threaded so it will take up the whole thread and run forever.
     */
    private void run()
    {
	float delta;
	float updateTime = 1.0f/UPDATES_PER_SECOND;
	float updateDelta = 0;
	float renderTime = 1.0f/FRAME_CAP;
	float renderDelta = 0;
	float frameTime = 1.0f; // Once every second
	float frameDelta = 0;

	int updates = 0;

	Timer timer = new Timer();
	GamestateHandler.getInstance().setGamestate(new StateIngame());
	PunchFrame frame = new PunchFrame();

	while(running)
	{
	    delta = timer.timeElapsed();
	    updateDelta += delta;
	    frameDelta += delta;
	    renderDelta += delta;
	    if(updateDelta >= updateTime)
	    {
		frame.update(updateTime);
		updates++;
		updateDelta-=updateTime;
	    }
	    if(renderDelta >= renderTime)
	    {
		frame.pack(); // NEED TO UPDATE THE EVENT SYSTEM TO MAKE LIU COMPUTER RENDER AT 60 FPS. swing bug?
		frame.render();
		renderDelta-=renderTime;
	    }
	    if(frameDelta >= frameTime)
	    {
		fps = frames;
		ups = updates;
		frames = 0;
		updates = 0;
		frame.tick();
		frameDelta-=frameTime;
	    }
	    try
	    {
		Thread.sleep(1);
	    }
	    catch(InterruptedException e)
	    {
		// Doesn't matter if this happens every once in a while.
		// Since this is more to controll the fps to a more exact time.
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

    public int getFames()
            {
        	return frames;
            }

    public int getUPS()
        {
    	return ups;
        }

    public void addFrames()
    {
	frames++;
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

