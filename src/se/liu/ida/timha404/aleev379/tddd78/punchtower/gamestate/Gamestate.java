package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import java.awt.*;

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

    public void renderTextShadow(Graphics g, String text, int x, int y, boolean center) {
		Color c = g.getColor();
   		if (center) {
   			x-=g.getFontMetrics().stringWidth(text)/2;
   		}
		g.setColor(c.darker().darker());
		g.drawString(text, x + 2, y + 2);
		g.setColor(c);
		g.drawString(text, x, y);
	}

	public void renderTextMultiLine(Graphics g, String text, int x, int y, int width) {
		String[] lines = text.split("\n");

		int lineNr = 0, lineLength = 0;
		StringBuilder sb = new StringBuilder();

		for (String line : lines) 	{
			String[] words = line.split(" ");
			for (String word : words) {
				if (lineLength == 0 || (lineLength + g.getFontMetrics().stringWidth(word)) < width) {

				} else {

					renderTextShadow(g, sb.toString(), x, (int) (y + lineNr * g.getFont().getSize() * 5.0 / 4.0), false); // The division 5.0/4.0 is to get a margin between lines of text
					lineNr++;
					sb.setLength(0);

				}
				sb.append(word).append(" ");
				lineLength = g.getFontMetrics().stringWidth(sb.toString());
			}
			renderTextShadow(g, sb.toString(), x, (int) (y + lineNr * g.getFont().getSize() * 5.0 / 4.0), false); // The division 5.0/4.0 is to get a margin between lines of text

			lineNr++;
			lineLength = 0;
			sb.setLength(0);
		}
	}
}


