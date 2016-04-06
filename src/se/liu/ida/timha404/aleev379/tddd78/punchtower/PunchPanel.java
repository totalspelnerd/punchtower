package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the current gamestate and updates it.
 */
public class PunchPanel extends JPanel
{

    /**
     * Width of the panel
     */
    public static final int WIDTH = 1280;
    /**
     * Height of the panel
     */
    public static final int HEIGHT = 720;


    public PunchPanel()
    {
	setPreferredSize(new Dimension(WIDTH,HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	//GamestateHandler.getInstance().render(g);
	g.drawString(Integer.toString(PunchTower.getInstance().getFPS()),10,20);
    }

    /**
     * Update the gamestate
     * @param timeElapsed time elapsed since last update
     */
    public void update(float timeElapsed)
    {
	//GamestateHandler.getInstance().update(timeElapsed);
    }

    /**
     * @return true if the size is not set to the correct one.
     */
    public boolean wrongSize()
    {
	return getWidth()!=WIDTH || getHeight()!=HEIGHT;
    }
}
