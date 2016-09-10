package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.PlayerType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.Gamestate;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Displays the current gamestate and updates it.
 * @see Gamestate
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

    /**
     * Initialize the PunchPanel.
     */
    public PunchPanel()
    {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		GamestateHandler.getInstance().render(g);

		g.setFont(FontLoader.mono20);
		g.setColor(Color.WHITE);
		Renderer.renderTextShadow(g,Integer.toString(PunchTower.getInstance().getFPS())+" FPS",10,FontLoader.mono20.getSize(),false);
		PunchTower.getInstance().addFrames();
    }

    /**
     * Update the gamestate.
     * @param timeElapsed time elapsed since last update.
     */
    public void update(float timeElapsed)
    {
    	GamestateHandler.getInstance().update(timeElapsed);
    }
    /**
     * Tick the gamestate.
     */
    public void tick()
    {
        GamestateHandler.getInstance().tick();
    }
    /**
     * Repaints the panel, which will render the gamestate.
     */
    public void render()
    {
        repaint();
    }

    /**
     * @return true if the size is not set to the correct one.
     */
    public boolean wrongSize()
    {
	return getWidth()!=WIDTH || getHeight()!=HEIGHT;
    }
    
    public void addInput(String key, String name, AbstractAction action)
    {
    	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key),name);
		getActionMap().put(name, action);
    }
    
    public void removeInput(String key, String name)
    {
    	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(KeyStroke.getKeyStroke(key));
		getActionMap().remove(name);
    }

}
