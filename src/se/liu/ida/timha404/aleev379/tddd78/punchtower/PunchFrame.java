package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import javax.swing.*;


/**
 * This class is used to display the game with a JFrame.
 * Displays PunchPanel.
 * @see PunchPanel
 */
public class PunchFrame extends JFrame
{
    /**
     * The content pane of the frame.
     */
    PunchPanel panel;

    /**
     * Initializer for the PunchFrame.
     */
    public PunchFrame()
    {
	panel = new PunchPanel();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("PunchTower");
	setContentPane(panel);
	setFocusable(true);

	// Sometimes the panel won't resize correctly on pack so we will run it until it does it correctly.
	while(panel.wrongSize())
	    pack();
	setVisible(true);
    }

    /**
     * Updates the panel which will update the current gamestate.
     * @param deltaTime time since last update.
     */
    public void update(float deltaTime)
    {
	panel.update(deltaTime);
    }

    /**
     * Ticks the panel which will tick the current gamestate.
     */
    public void tick()
    {
	setTitle("PunchTower | " + PunchTower.getInstance().getFPS() + " fps, " + PunchTower.getInstance().getUPS() + " ups");
	panel.tick();
    }

    /**
     * Renders the panel which will render the current gamestate.
     */
    public void render()
    {
	panel.render();
    }
}
