package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

/**
 * This class handles all of our type of rendering we do on multiple places. Generally to make text look the same.
 */
public final class Renderer
{

	private static final double FONT_LINE_RATIO = 5.0/4.0;
	private static final int ALPHA_VALUE = 125;


	private Renderer() {}

	public static void renderNumberDrop(Graphics g, float timeElapsed, int x, int y, float vx, float vy, float gravity,String text, Color color)
	{
		float xPos = x + vx*timeElapsed;
		float yPos = y + vy*timeElapsed + gravity*timeElapsed*timeElapsed/2;
		g.setColor(color);
		renderTextShadow(g, text, (int)xPos, (int)yPos, true);
	}
	
	public static void renderTextShadow(Graphics g, String text, int x, int y, boolean center) {
		renderTextShadow(g,text,x,y,center,false);
	}

	public static void renderTextShadow(Graphics g, String text, int x, int y, boolean center, boolean background) {
		Color c = g.getColor();
		if (center) {
			x-=g.getFontMetrics().stringWidth(text)/2;
		}
		if(background)
		{
			g.setColor(new Color(0,0,0,ALPHA_VALUE)); // Alpha color of 125
			g.fillRect(x-5,y-g.getFont().getSize(),g.getFontMetrics().stringWidth(text)+10,g.getFont().getSize()+10);
		}
		g.setColor(c.darker().darker().darker().darker());
		g.drawString(text, x + 2, y + 2);
		g.setColor(c);
		g.drawString(text, x, y);
	}

	public static void renderTextMultiLine(Graphics g, String text, int x, int y, int width)
	{
		renderTextMultiLine(g,text,x,y,width,true);
	}

	public static void renderTextMultiLine(Graphics g, String text, int x, int y, int width, boolean background) {
		String[] lines = text.split("\n");
		List<String> complete = new ArrayList<>();
		int longestLine = 0;
		int lineLength = 0;
		StringBuilder sb = new StringBuilder();

		for (String line : lines) 	{
			String[] words = line.split(" ");
			for (String word : words) {
				if (lineLength != 0 && (lineLength + g.getFontMetrics().stringWidth(word)) >= width) {
					if(longestLine < lineLength)
						longestLine = lineLength;
					complete.add(sb.toString());
					sb.setLength(0);

				}
				sb.append(word).append(" ");
				lineLength = g.getFontMetrics().stringWidth(sb.toString());
			}
			if(longestLine < lineLength)
				longestLine = lineLength;
			complete.add(sb.toString());
			sb.setLength(0);
			lineLength = 0;
		}
		longestLine -= g.getFontMetrics().stringWidth(" ");
		if(background)
		{
			Color c = g.getColor();
			g.setColor(new Color(0,0,0,ALPHA_VALUE)); // Alpha color of 125
			g.fillRect(x-5,y,longestLine+10,(int)(complete.size() * g.getFont().getSize() * FONT_LINE_RATIO)+10);// The division 5.0/4.0 is to get a margin between lines of text
			g.setColor(c);
		}
		for (int i = 0; i < complete.size(); i++) {
			renderTextShadow(g, complete.get(i), x, (int) (y + g.getFont().getSize()+ i * g.getFont().getSize() * FONT_LINE_RATIO), false); // The division 5.0/4.0 is to get a margin between lines of text
		}
	}

	public static void renderProgression(Graphics g, int x, int y, int width, int height, Color back, Color front, int min, int max, int value)
	{
		int diff = max-min;
		value -= min;
		g.setColor(back);
		g.fillRect(x,y,width,height);
		g.setColor(front);
		g.fillRect(x,y,width*value/diff,height);
	}
}
