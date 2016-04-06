package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.awt.*;

/**
 * Handles the gametstate. <br>
 * Handles changing state, going back to last state, updating and rendering of state.
 * @see Gamestate
 */

public class GamestateHandler
{
    /**
     * Current gamestate that should be rendered and updated
     */
    private Gamestate current;

    /**
     * instance of the gamestate handler
     */
    private static final GamestateHandler INSTANCE = new GamestateHandler();

    private GamestateHandler(){}


    /**
     * Makes the given gamestate the current one to be rendered and updated. It will also keep the current gamestate so the user can go back to it.
     * @param gamestate the gamestate that will be pushed
     */
    public void pushGamestate(Gamestate gamestate)
    {
	gamestate.lastGamestate = INSTANCE.current;
	INSTANCE.current = gamestate;
    }


    /**
     * Makes the given gamestate the current one to be rendered and updated. It will not keep the current gamestate so the user can't go back to it.
     * @param gamestate the gamestate that will be set
     */
    public void setGamestate(Gamestate gamestate)
    {
	INSTANCE.current = gamestate;
    }

    /**
     * Goes back to the last gametate
     */
    public void goBack()
    {
	if(current.lastGamestate != null)
	    current = current.lastGamestate;
    }

    /**
     * Goes back given amount of times
     * @param amount goes back this many amount of times.
     */
    public void goBack(int amount)
    {
	for(int i = 0;i<amount;i++)
	    if(current.lastGamestate != null)
	  	current = current.lastGamestate;
    }

    /**
     * Updates the current gamestate
     * @param deltaTime the time since last update
     */
    public void update(float deltaTime)
    {
	current.update(deltaTime);
    }

    /**
     * Renders the current gamestate
     * @param g graphics component needed to render.
     */
    public void render(Graphics g)
    {
	current.render(g);
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
