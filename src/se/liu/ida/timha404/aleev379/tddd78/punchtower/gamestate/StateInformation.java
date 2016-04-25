package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchTower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This is pretty much the tutorial of our game. It describes the basic controlls of the game.
 */
public class StateInformation extends Gamestate
{

	private int playerIndex;
	private int state = 0;

	public StateInformation(int playerIndex)
	{
		this.playerIndex= playerIndex;
		final PunchPanel panel = PunchTower.getInstance().getFrame().getPanel();
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "next");
		panel.getActionMap().put("next", new AbstractAction()
		{
			@Override public void actionPerformed(final ActionEvent e) {
				state++;
				if(state == 3)
				{
					panel.getInputMap().remove(KeyStroke.getKeyStroke("SPACE"));
					panel.getActionMap().remove("next");
					GamestateHandler.getInstance().pushGamestate(new StateTower(StateInformation.this.playerIndex));
				}
			}
		});
	}

	@Override
	public void init()
	{

	}

	@Override
	public void update(final float timeElapsed)
	{

	}

	@Override
	public void render(final Graphics g)
	{
		g.drawImage(ImageLoader.background, 0, 0, PunchPanel.WIDTH, PunchPanel.HEIGHT, null);
		String text = "";
		switch(state)
		{
			case 0:
				text = "So you are " + StateMenu.names[playerIndex] + ", huh? Never heard of you.\nAs you will see the mechanics of the game are quite simple. A monster appears and you can choose from three different attacks; quick, normal and heavy. These are chosen with the buttons 1-3 on your keyboard.\n\nPress space to continue.";
				break;
			case 1:
				text = "When you have killed a monster with your amazing skills, he will drop a piece of loot for you. This loot will be armor or weapons you can use in furure fights. If you want to replace your current item you press E, if not press ENTER.\n\nPress SPACE to continue.";
				break;
			case 2:
				text = "Before you go, let me just tell you a bit about your stats. \nYou will have an HP-bar in the top left corner of the screen. Try to keep it green.\nBelow it you will find a thin yellow bar. This is your 'double-hit' bar. When full, you strike twice!\nThe blue bar represents your progress into your player level.\n'INI' is short for initiative and makes you attack faster, higher INI than the monster means you attack first!\n'ATK' is short for attack and makes you hit harder. More ATK, more dead monsters.\n'DEF' is short for defense and makes you tougher. The best offense is a good defense!\n\nPress SPACE to start!";
				break;
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
		Renderer.renderTextMultiLine(g,text,100,100,1100);
	}
}
