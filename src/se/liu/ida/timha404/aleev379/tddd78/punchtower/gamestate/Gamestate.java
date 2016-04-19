package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Color;

/**
 * This class is generally used to handle different states in the game. Such as mainmenu, optionmenu or ingame.
 */
public abstract class Gamestate
{
    /**
     * The gamestate behind this one. Used to go back.
     * @see GamestateHandler#goBack()
     * @see GamestateHandler#goBack(int)
     */
    public Gamestate lastGamestate = null;

    public abstract void init();

    /**
     * Update the game logic
     * @param timeElapsed time elapsed since last update.
     */
    public abstract void update(float timeElapsed);

    /**
     * Tick of the game. Called once every second.
     */
    public void tick() {}

    /**
     * Rneders the game.
     * @param g graphics component for the game.
     */
    public abstract void render(Graphics g);
}


