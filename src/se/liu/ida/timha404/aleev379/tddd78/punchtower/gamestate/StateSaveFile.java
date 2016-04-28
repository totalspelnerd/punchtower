package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.FontLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.LoadFailedException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchTower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Renderer;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.SaveLoad;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.TagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This gamestate is used to display if a save was found. If it is the user can choose to continue playing on it or start a new game.
 */
public class StateSaveFile extends Gamestate
{

	private boolean loadFail;

	@Override
	public void init()
	{
		final PunchPanel panel = PunchTower.getInstance().getFrame().getPanel();
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), "loadSave");
		panel.getActionMap().put("loadSave", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(!loadFail) {
					try {
						GamestateHandler.getInstance().pushGamestate(SaveLoad.load("save.dat"));
						removeKeystrokes();
					} catch (LoadFailedException|TagException e1) {
						// We dont need to use e1 for anything since we are handling the problem in another way.
						loadFail = true;
					}
				}
			}
		});
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"),"newGame");
		panel.getActionMap().put("newGame", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(!loadFail) {
					GamestateHandler.getInstance().pushGamestate(new StateMenu());
					removeKeystrokes();
					SaveLoad.delete(SaveLoad.SAVE_FILE);
				}
			}
		});
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"),"failLoad");
		panel.getActionMap().put("failLoad", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				if(loadFail) {
					GamestateHandler.getInstance().pushGamestate(new StateMenu());
					removeKeystrokes();
					SaveLoad.delete(SaveLoad.SAVE_FILE);
				}
			}
		});
	}

	@Override
	public void update(final float timeElapsed) {

	}

	@Override
	public void render(final Graphics g) {
		g.drawImage(ImageLoader.background, 0, 0, PunchPanel.WIDTH, PunchPanel.HEIGHT, null);
		if(loadFail)
		{
			String description = "The save file is corrupt and could not be read. Out of date or have you been tempering with it?\n\nPress SPACE to start a new game.";
			int xOffset = 100;
			g.setColor(Color.WHITE);
			g.setFont(FontLoader.mono30);
			Renderer.renderTextMultiLine(g, description, xOffset, 100, PunchPanel.WIDTH - xOffset * 2);
		}
		else {
			String description = "A save file has been detected, would you like to load it to keep playing?";
			int xOffset = 100;
			g.setColor(Color.WHITE);
			g.setFont(FontLoader.mono30);
			Renderer.renderTextMultiLine(g, description, xOffset, 100, PunchPanel.WIDTH - xOffset * 2);
			final int textPos = 300;
			final int rowSize = 40;
			Renderer.renderTextShadow(g, "1. Load save file. ", PunchPanel.WIDTH >> 1, textPos, true);
			Renderer.renderTextShadow(g, "2. Start new game. Save will disappear.", PunchPanel.WIDTH >> 1, textPos + rowSize, true);
		}
	}

	private void removeKeystrokes()
	{
		final PunchPanel panel = PunchTower.getInstance().getFrame().getPanel();
		panel.getInputMap().remove(KeyStroke.getKeyStroke("1"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("2"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("SPACE"));
		panel.getActionMap().remove("loadSave");
		panel.getActionMap().remove("newGame");
		panel.getActionMap().remove("failLoad");
	}
}
