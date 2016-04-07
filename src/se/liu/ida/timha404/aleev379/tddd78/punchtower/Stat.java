package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.awt.*;

public class Stat
{
	private static final int STARTY = 20;
	private static final int ROW_HEIGHT = 25;

	public String header;
	public int initiative;
	public int defense;
	public int attack;

	public int width = -1;

	private static final Canvas fontCanvas = new Canvas();
	private static final Font defaultFont = new Font(Font.MONOSPACED, Font.PLAIN, 20);

	public Stat(String header, int initiative, int defense, int attack)
	{
		this.header = header;
		this.initiative = initiative;
		this.defense = defense;
		this.attack = attack;
		FontMetrics fm = fontCanvas.getFontMetrics(defaultFont);
		width = fm.stringWidth(header);
		width = Math.max(fm.stringWidth("INI " + initiative), width);
		width = Math.max(fm.stringWidth("DEF " + defense), width);
		width = Math.max(fm.stringWidth("ATK " + attack), width);
		width += 4 + 8;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return ROW_HEIGHT * 4 + 8;
	}

	public void render(Graphics g, int x, int y, Color borderColor)
	{

		g.setFont(defaultFont);
		g.setColor(borderColor);
		g.fillRect(x, y, width, ROW_HEIGHT * 4 + 8);
		g.setColor(Color.BLACK);
		g.fillRect(x+4, y+4, width-8, ROW_HEIGHT * 4);
		g.setColor(Color.WHITE);

		// DRAW STATS
		g.drawString(header, x + 6, y + STARTY);
		g.fillRect(x+6,y+STARTY+1,width-12,2); // Underline
		g.drawString("INI " + initiative, x + 6, y + STARTY + ROW_HEIGHT);
		g.drawString("DEF " + defense, x + 6, y + STARTY + ROW_HEIGHT * 2);
		g.drawString("ATK " + attack, x + 6, y + STARTY + ROW_HEIGHT * 3);

	}
}
