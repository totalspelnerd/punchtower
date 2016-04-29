package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.awt.*;

/**
 * This class is for everything Stats related, as in keeping track of stats and rendering them at a certain position.
 */
public class Stats
{
	/**
	 * Start y pos of the text.
	 */
	private static final int STARTY = 20;

	/**
	 * Row height of the text.
	 */
	private static final int ROW_HEIGHT = 25;

	/**
	 * Border size of the stats panel.
	 */
	private static final int BORDER_SIZE = 4;

	/**
	 * Text offset from the border.
	 */
	private static final int TEXT_OFFSET = 2;

	/**
	 * Header text of stats
	 */
	public String header;

	/**
	 * Initiative value
	 */
	public int initiative;

	/**
	 * Defense value
	 */
	public int defense;

	/**
	 * Attack value
	 */
	public int attack;


	/**
	 * Width of the stats panel
	 */
	public int width;

	/**
	 * Height of the stats panel
	 */
	public int height;

	/**
	 * This canvas is needed to calculate the widths of the strings in a certain font.
	 */
	private static final Canvas FONT_CANVAS = new Canvas();

	public Stats(String header, int initiative, int defense, int attack)
	{
		this.header = header;
		this.initiative = initiative;
		this.defense = defense;
		this.attack = attack;

		updateSize();
	}

	public void decrease(Stats stat) {
		this.initiative -= stat.initiative;
		this.defense -= stat.defense;
		this.attack -= stat.attack;
		updateSize();
	}

	public void increase(Stats stat) {
		this.initiative += stat.initiative;
		this.defense += stat.defense;
		this.attack += stat.attack;

		updateSize();
	}

	/**
	 * @return The width of the panel
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @return The height of the panel
	 */
	public int getHeight()
	{
		return height;
	}


	/**
	 * Updates the size of the rendered stat panel to fit new stat numbers
	 */
	public void updateSize() {
		// Get a FontMetrics from the canvas from the defaultFont (Used to calculate the size of the strings in the font)
		FontMetrics fm = FONT_CANVAS.getFontMetrics(FontLoader.mono20);

		// Calculate the width of the panel with the given text to be rendered.
		width = fm.stringWidth(header);
		width = Math.max(fm.stringWidth("INI " + initiative), width);
		width = Math.max(fm.stringWidth("ATK " + attack), width);
		width = Math.max(fm.stringWidth("DEF " + defense), width);

		// Add border and textoffset to the size
		width += TEXT_OFFSET * 2 + BORDER_SIZE * 2;

		// Calculate the height of the panel.
		height = ROW_HEIGHT * 4 + BORDER_SIZE * 2;
	}

	/**
	 * Renders the stats panel to the Graphics component
	 * @param g The graphics component that is being drawn too.
	 * @param x The x position of the panel
	 * @param y The y position of the panel
	 * @param borderColor The color of the border of the panel
	 */
	public void render(Graphics g, int x, int y, Color borderColor)
	{


		// Draw the border.
		g.setColor(borderColor);
		g.fillRect(x, y, width, height);

		// Draw the background.
		g.setColor(Color.BLACK);
		g.fillRect(x+BORDER_SIZE, y+BORDER_SIZE, width-BORDER_SIZE * 2, height-BORDER_SIZE*2);

		// Set default font to stats.
		g.setFont(FontLoader.mono20);

		// Draw header of stats as strings.
		g.setColor(Color.WHITE);
		g.drawString(header, x + BORDER_SIZE+TEXT_OFFSET, y + STARTY);

		g.fillRect(x+BORDER_SIZE+TEXT_OFFSET,y+STARTY+1,width-BORDER_SIZE*2-TEXT_OFFSET*2,2); // Underline

		// Draw stats as strings.
		g.drawString("INI " + initiative, x + BORDER_SIZE+TEXT_OFFSET, y + STARTY + ROW_HEIGHT);
		g.drawString("ATK " + attack, x + BORDER_SIZE+TEXT_OFFSET, y + STARTY + ROW_HEIGHT * 2);
		g.drawString("DEF " + defense, x + BORDER_SIZE+TEXT_OFFSET, y + STARTY + ROW_HEIGHT * 3);

	}
	
	public int getTotal()
	{
		return initiative+attack+defense;
	}
	

	public static Stats clone(Stats stat)
	{
		return new Stats(stat.header,stat.initiative,stat.attack,stat.defense);
	}
}
