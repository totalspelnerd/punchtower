package se.liu.ida.timha404.aleev379.tddd78.punchtower;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Balance{
	public static StateTower tower = new StateTower(0) ;

	public static void thing() {
		tower.init();
		try {
			PrintWriter pwi = new PrintWriter(new File("ini.txt"));
			PrintWriter pwa = new PrintWriter(new File("atk.txt"));
			PrintWriter pwd = new PrintWriter(new File("def.txt"));
			PrintWriter pwt = new PrintWriter(new File("tot.txt"));
			while (tower.getFloor() < 1000) {
				pwi.write(tower.getPlayer().getStats().initiative + "\n");
				pwa.write(tower.getPlayer().getStats().attack + "\n");
				pwd.write(tower.getPlayer().getStats().defense + "\n");
				pwt.write((tower.getPlayer().getStats().initiative + tower.getPlayer().getStats().attack + tower.getPlayer().getStats().defense)+ "\n");
				tower.nextFloor();
			}
			pwi.close();
			pwa.close();
			pwd.close();
			pwt.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


	}

	public static void main(String[] args) {
		thing();
	}
}
