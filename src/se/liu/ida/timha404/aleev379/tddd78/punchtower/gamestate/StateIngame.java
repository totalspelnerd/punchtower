package se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate;

import java.awt.*;
import java.util.Random;

public class StateIngame extends Gamestate
{

    public StateIngame()
    {

    }

    @Override
    public void update(final float timeElapsed)
    {
	x++;
    }

    int x = 0;

    @Override
    public void render(final Graphics g)
    {
	g.fillRect(x,10,100,100);
    }
}
