package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchFrame;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchTower;

import java.awt.*;

/**
 * Handles the gametstate. <br>
 * Handles changing state, going back to last state, updating and rendering of state.<br>
 * This class should never be called outside of PunchFrame or PunchPanel.
 *
 * @see Gamestate
 * @see PunchFrame
 * @see PunchTower
 */

public final class GamestateHandler
{
    /**
     * Current gamestate that should be rendered and updated
     */
    private Gamestate currentGamestate = null;

    /**
     * instance of the gamestate handler
     */
    private static final GamestateHandler INSTANCE = new GamestateHandler();

	private boolean hasInit = false;

    private GamestateHandler(){}


    /**
     * Makes the given gamestate the current one to be rendered and updated. It will also keep the current gamestate so the user can go back to it.
     * @param gamestate the gamestate that will be pushed
     */
    public void pushGamestate(Gamestate gamestate)
    {
		hasInit = false;
		gamestate.lastGamestate = INSTANCE.currentGamestate;
		INSTANCE.currentGamestate = gamestate;
		gamestate.init();
		hasInit = true;
	}


	public Gamestate getCurrentGamestate() {
		return currentGamestate;
	}

	/**
     * Makes the given gamestate the current one to be rendered and updated. It will not keep the current gamestate so the user can't go back to it.
     * @param gamestate the gamestate that will be set
     */
    public void setGamestate(Gamestate gamestate)
    {
		hasInit = false;
		INSTANCE.currentGamestate = gamestate;
		gamestate.init();
		hasInit = true;
	}

    /**
     * Goes back to the last gametate
     */
    public void goBack() // Could potentially be used in the future to pop gamestates
    {
	if(currentGamestate.lastGamestate != null)
	    currentGamestate = currentGamestate.lastGamestate;
    }

    /**
     * Goes back given amount of times
     * @param amount goes back this many amount of times.
     */
    public void goBack(int amount) // Could potentially be used in the future to pop gamestates
    {
	for(int i = 0;i<amount;i++)
	    if(currentGamestate.lastGamestate != null)
	  	currentGamestate = currentGamestate.lastGamestate;
    }

    /**
     * Updates the current gamestate
     * @param deltaTime the time since last update
     */
    public void update(float deltaTime)
        {
    	currentGamestate.update(deltaTime);
        }

    /**
     * Ticks the current gamestate.
     */
    public void tick()
    {
    currentGamestate.tick();
    }

    /**
     * Renders the current gamestate
     * @param g graphics component needed to render.
     */
    public void render(Graphics g)
    {
		if(hasInit)
			currentGamestate.render(g);
    }

    /**
     *
     * @return The instance of the gamestate handler.
     */
    public static GamestateHandler getInstance()
    {
	return INSTANCE;
    }


}
