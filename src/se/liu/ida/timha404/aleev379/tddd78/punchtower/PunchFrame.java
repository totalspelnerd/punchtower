package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import javax.swing.*;


/**
 * JFrame used to display the game.
 * Displays PunchPanel.
 * @see PunchPanel
 */
public class PunchFrame extends JFrame
{

    PunchPanel panel;

    public PunchFrame()
    {
	panel = new PunchPanel();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	setContentPane(panel);

	// Sometimes the panel won't resize correctly on pack so we will run it until it does it correctly.
	while(panel.wrongSize())
	    pack();
	setVisible(true);
    }

    /**
     * Updates the panel which will update the current gamestate
     * @param deltaTime time since last update
     */
    public void update(float deltaTime)
    {
	panel.update(deltaTime);
    }
}
