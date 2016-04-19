package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchPanel;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.PunchTower;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StateMenu extends Gamestate {

	public static final String[] names = {"Spastic Stan","Brutal Brick","Tanking Ted"};

	@Override public void init() {
		final PunchPanel panel = PunchTower.getInstance().getFrame().getPanel();
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("1"), "stan");
		panel.getActionMap().put("stan", new AbstractAction()
				{
					@Override public void actionPerformed(final ActionEvent e) {
						GamestateHandler.getInstance().pushGamestate(new StateInformation(0));
						removeKeystrokes();
					}
				});
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("2"),"brick");
		panel.getActionMap().put("brick", new AbstractAction()
				{
					@Override public void actionPerformed(final ActionEvent e) {
						GamestateHandler.getInstance().pushGamestate(new StateInformation(1));
						removeKeystrokes();
					}
				});
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("3"),"ted");
		panel.getActionMap().put("ted", new AbstractAction()
				{
					@Override public void actionPerformed(final ActionEvent e) {
						GamestateHandler.getInstance().pushGamestate(new StateInformation(2));
						removeKeystrokes();
					}
				});

	}

	@Override public void update(final float timeElapsed) {

	}

	@Override public void render(final Graphics g) {
		g.drawImage(ImageLoader.background, 0, 0, PunchPanel.WIDTH, PunchPanel.HEIGHT, null);

		String description = "HELLO AND WELCOME ADVENTURER!\nDo you wish to wander up a tower and get some epic loot? If yes, then this is the game for you!\nIf no, you might as well just turn of the game.\nTo begin with I need to know who you are.\n\nChoose a character below with 1-3 on your keyboard. You do have a keyboard, right?";
		String iniGuy = "I'm the fastest man ALIVE! I'm like a snake ready to attack.";
		String atkGuy = "Who needs defense when I can have a POWERFUL offense.";
		String defGuy = "Defense if the BEST offense. Nothing can hurt me.";

		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		Renderer.renderTextMultiLine(g, description, 100, 100, 1100);

		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 23));

		g.setColor(Color.YELLOW);
		Renderer.renderTextMultiLine(g, "1. " + names[0]+"\n"+iniGuy, 150, 500, 320);

		g.setColor(Color.RED);
		Renderer.renderTextMultiLine(g,"2. " + names[1]+"\n" + atkGuy, 515, 500, 270);

		g.setColor(Color.CYAN);
		Renderer.renderTextMultiLine(g,"3. " + names[2]+"\n" +  defGuy, 800, 500, 290);
	}

	private void removeKeystrokes()
	{
		final PunchPanel panel = PunchTower.getInstance().getFrame().getPanel();
		panel.getInputMap().remove(KeyStroke.getKeyStroke("1"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("2"));
		panel.getInputMap().remove(KeyStroke.getKeyStroke("3"));
		panel.getActionMap().remove("stan");
		panel.getActionMap().remove("brick");
		panel.getActionMap().remove("ted");
	}


}
