package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.awt.Font;

/**
 * Loads all our fonts needed in the game.<br/>
 * Don't need to not recreate fonts everywhere or create them inside of drawcalls.
 */
public final class FontLoader {
	private FontLoader(){}

	/**
	 * MONOSPACE font with size 20
	 */
	public static Font mono20 = new Font(Font.MONOSPACED, Font.PLAIN, 20); // size of font

	/**
	 * MONOSPACE font with size 30
	 */
	public static Font mono30 = new Font(Font.MONOSPACED, Font.PLAIN, 30); // size of font
	/**
	 * MONOSPACE font with size 40
	 */
	public static Font mono40 = new Font(Font.MONOSPACED, Font.PLAIN, 40); // size of font
	/**
	 * MONOSPACE font with size 72
	 */
	public static Font mono72 = new Font(Font.MONOSPACED, Font.PLAIN, 72); // size of font
	/**
	 * SANS_SERIF font with size 72
	 */
	public static Font sans72 = new Font(Font.SANS_SERIF, Font.BOLD, 72); // size of font
}
