package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.Gamestate;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Displays the current gamestate and updates it.
 * @see Gamestate
 */
public class PunchPanel extends JPanel implements KeyListener
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
	addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		GamestateHandler.getInstance().render(g);
		g.setFont(new Font(Font.MONOSPACED,Font.PLAIN,20));
		g.setColor(Color.WHITE);
		Renderer.renderTextShadow(g,Integer.toString(PunchTower.getInstance().getFPS())+" FPS",10,20,false);
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

    @Override
	public void keyTyped(final KeyEvent e) {

    }

    @Override
	public void keyPressed(final KeyEvent e) {

    }

    @Override
	public void keyReleased(final KeyEvent e) {

    }
}
