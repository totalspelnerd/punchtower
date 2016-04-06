package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.awt.*;

public abstract class Gamestate
{
    /**
     * The gamestate behind this one. Used to go back.
     * @see GamestateHandler#goBack()
     * @see GamestateHandler#goBack(int)
     */
    public Gamestate lastGamestate = null;

    /**
     * Update the game logic
     * @param timeElapsed time elapsed since last update.
     */
    public abstract void update(float timeElapsed);

    /**
     * Rneders the game.
     * @param g graphics component for the game.
     */
    public abstract void render(Graphics g);
}
