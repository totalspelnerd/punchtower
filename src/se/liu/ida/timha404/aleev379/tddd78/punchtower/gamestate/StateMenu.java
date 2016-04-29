package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.FontLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchTower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Renderer;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.enums.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This is our main menu. Where the player can choose a character and the carry on to play the game.
 */
public class StateMenu extends Gamestate {

	private final static int STAN_X =150;
	private final static int BRICK_X =515;
	private final static int TED_X = 830;

	private final static int STAN_WIDTH =320;
	private final static int BRICK_WIDTH =270;
	private final static int TED_WIDTH = 290;

	private final static int CHARACTER_Y =500;


	@Override
	public void init() {
		final PunchPanel panel = PunchTower.getInstance().getFrame().getPanel();
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), PlayerType.STAN.name);
		panel.getActionMap().put(PlayerType.STAN.name, new AbstractAction()
				{
					@Override public void actionPerformed(final ActionEvent e) {
						GamestateHandler.getInstance().pushGamestate(new StateInformation(PlayerType.STAN));
						removeKeystrokes();
					}
				});
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"),PlayerType.BRICK.name);
		panel.getActionMap().put(PlayerType.BRICK.name, new AbstractAction()
				{
					@Override public void actionPerformed(final ActionEvent e) {
						GamestateHandler.getInstance().pushGamestate(new StateInformation(PlayerType.BRICK));
						removeKeystrokes();
					}
				});
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"),PlayerType.TED.name);
		panel.getActionMap().put(PlayerType.TED.name, new AbstractAction()
				{
					@Override public void actionPerformed(final ActionEvent e) {
						GamestateHandler.getInstance().pushGamestate(new StateInformation(PlayerType.TED));
						removeKeystrokes();
					}
				});

	}

	@Override
	public void update(final float timeElapsed) {

	}

	@Override
	public void render(final Graphics g) {
		g.drawImage(ImageLoader.background, 0, 0, PunchPanel.WIDTH, PunchPanel.HEIGHT, null);

		String description = "HELLO AND WELCOME ADVENTURER!\nDo you wish to wander up a tower and get some epic loot? If yes, then this is the game for you!\nIf no, you might as well just turn of the game.\nTo begin with I need to know who you are.\n\nChoose a character below with 1-3 on your keyboard. You do have a keyboard, right?";
		String iniGuy = "I'm the fastest man ALIVE! I'm like a snake ready to attack.";
		String atkGuy = "Who needs defense when I can have a POWERFUL offense.";
		String defGuy = "Defense if the BEST offense. Nothing can hurt me.";

		g.setFont(FontLoader.mono30);
		g.setColor(Color.WHITE);

		int xOffset = 100;
		Renderer.renderTextMultiLine(g, description, xOffset, 100, PunchPanel.WIDTH - xOffset * 2);

		g.setFont(FontLoader.mono20);

		g.setColor(Color.YELLOW);
		Renderer.renderTextMultiLine(g, "1. " + PlayerType.STAN.name+"\n"+iniGuy, STAN_X, CHARACTER_Y, STAN_WIDTH);

		g.setColor(Color.RED);
		Renderer.renderTextMultiLine(g,"2. " + PlayerType.BRICK.name+"\n" + atkGuy, BRICK_X, CHARACTER_Y, BRICK_WIDTH);

		g.setColor(Color.CYAN);
		Renderer.renderTextMultiLine(g,"3. " + PlayerType.TED.name+"\n" +  defGuy, TED_X, CHARACTER_Y, TED_WIDTH);

	}

	private void removeKeystrokes()
	{
		final PunchPanel panel = PunchTower.getInstance().getFrame().getPanel();
		panel.getInputMap().remove(KeyStroke.getKeyStroke("1"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("2"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("3"));
		panel.getActionMap().remove(PlayerType.STAN.name);
		panel.getActionMap().remove(PlayerType.BRICK.name);
		panel.getActionMap().remove(PlayerType.TED.name);
	}


}
