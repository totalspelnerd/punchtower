package se.liu.ida.timha404.aleev379.tddd78.punchtower.entity;

import se.liu.ida.timha404.aleev379.tddd78.punchtower.AttackData;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Experience;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.FontLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ImageLoader;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Item;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Renderer;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.SaveFile;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Stats;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.exceptions.TagException;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.ItemType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.Rarity;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.StatType;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.GamestateHandler;
import se.liu.ida.timha404.aleev379.tddd78.punchtower.gamestate.StateTower;

import java.awt.*;

/**
 * This class represents the playable character and is called whenever a new game starts.
 */

public class Player extends Entity{

	private PlayerType playerType;

	private static final int BASIC_GEAR_STAT = 25;
	private static final int COLOR_STATS_BORDER = 0x00bb00;

	private Item[] equipped = new Item[]{null,null,null,null,null,null};
	private int lastLevel;
	private int level;
	private int xp;


	private Player(PlayerType type, final int initiative, final int attack, final int defense,boolean equip)
	{
		super(new Stats(type.name, initiative, attack, defense), STANDARD_HP);
		playerType = type;
		lastLevel = 1;
		level = 1;
		xp = 0;
		if(equip) {
			for (int i = 0; i < equipped.length; i++) {
				equip(new Item(ItemType.values()[i], BASIC_GEAR_STAT, BASIC_GEAR_STAT, BASIC_GEAR_STAT, Rarity.NORMAL), i); // Magic numbers for starter item stats
			}
		}
	}

	public Player(PlayerType type, final int initiative, final int attack, final int defense) {
		this(type,initiative,attack,defense,true);
	}

	public Item getItem(final int itemIndex) {
		return equipped[itemIndex];
	}

	/**
	 * Equips the player with the given item and adjusts player stats accordingly.
	 * @param item The item to be equipped
	 * @param itemIndex An index representing the item slot to be used
	 */

	public void equip(final Item item, final int itemIndex) {
		Stats temp = Stats.clone(stats);
		if (equipped[itemIndex] != null) {
			stats.decrease(equipped[itemIndex].getStats());

		}
		this.equipped[itemIndex] = item;
		stats.increase(equipped[itemIndex].getStats());

		// All the following Magic numbers in this method is used to adjust the balance of the game. Tested to make them good.
		switch (playerType) {
			case STAN:
				if (temp.initiative < stats.initiative) {
					stats.initiative += (int) Math.min(30, temp.initiative*0.1f);
				}
				break;
			case BRICK:
				if (temp.attack < stats.attack) {
					stats.attack += (int) Math.min(30, temp.attack*0.05f);
				}
				break;
			case TED:
				if (temp.defense < stats.defense) {
					stats.defense += (int) Math.min(50, temp.defense*0.1f);
				}
				break;
		}



	}

	public AttackData attack(AttackType type) {
		Monster monster = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getMonster();
		if (hp <= 0) {
			return new AttackData(this, monster, false, false, false, 0);
		}
		return Combat.attack(this, monster, type);
	}
	
	public void spec(StatType stat)
	{
		final double levelDivisor = 8.0;
		switch (stat) { // Magic numbers to maintain balance
			case INITIATIVE:
				stats.initiative += (int) (10*Math.pow(2, lastLevel/levelDivisor));
				break;
			case ATTACK:
				stats.attack += (int) (10*Math.pow(2, lastLevel/levelDivisor));
				break;
			case DEFENSE:
				stats.defense += (int) (10*Math.pow(2, lastLevel/levelDivisor));
				break;
		}
		lastLevel++;
	}
	
	public boolean didLevelUp()
	{
		// level should never be less than the last level
		assert(level >= lastLevel);
		return level!=lastLevel;
	}
	
	public void addXp()
	{
		// We should only add xp if we are currently not leveling up.
		assert(lastLevel == level) : "lastLevel can't be different from level: " + lastLevel + " "+level;
		Monster monster = ((StateTower) GamestateHandler.getInstance().getCurrentGamestate()).getMonster();
		xp += monster.stats.getTotal();
		calcLevel();
	}

	private void calcLevel()
	{
		while(xp > Experience.getXp(level + 1))
		{
			level++;
		}
	}


	public void render(Graphics g, int x, int y)
	{
		stats.render(g,x,y,new Color(COLOR_STATS_BORDER)); // magic number is a little dark green color (Better looking than Color.GREEN.darker())
		final int playerYOffset = 200;
		final int playerWidth = 200;
		final int playerHeight = 400;
		final int progressionBarWidth = 200;
		final int progressionBarHeight = 20;
		final int lineSize = 30;
		g.drawImage(ImageLoader.player, x + 100, y + playerYOffset, playerWidth, playerHeight, null);
		Renderer.renderProgression(g, x + stats.getWidth() + 10, y, progressionBarWidth, progressionBarHeight, Color.RED, Color.GREEN, 0, STANDARD_HP, hp);
		Renderer.renderProgression(g,x+stats.getWidth()+10,y+progressionBarHeight,progressionBarWidth,progressionBarHeight/2,Color.YELLOW.darker().darker(), Color.YELLOW,0, STACK_CAP,(int)initiativeStack);
		g.setFont(FontLoader.mono20);
		g.setColor(Color.CYAN);
		Renderer.renderTextShadow(g, "lvl: "+level, x+stats.getWidth()+10, y+progressionBarHeight+lineSize, false);
		Renderer.renderProgression(g, x+stats.getWidth()+10, y+progressionBarHeight+lineSize+5, progressionBarWidth, 10, Color.BLUE.darker().darker(), Color.BLUE, Experience.getXp(level), Experience.getXp(level+1), xp);
	}

	public PlayerType getPlayerType()
	{
		return playerType;
	}

	public void saveToFile(SaveFile file)
	{
		file.addTag("playerType", Integer.toString(playerType.ordinal()));
		file.addTag("xp",Integer.toString(xp));
		file.addTag("playerIni",Integer.toString(stats.initiative));
		file.addTag("playerAtk",Integer.toString(stats.attack));
		file.addTag("playerDef",Integer.toString(stats.defense));
		for (int i = 0; i < equipped.length; i++)
		{
			file.addTag("itemIni"+i,Integer.toString(equipped[i].getStats().initiative));
			file.addTag("itemAtk"+i,Integer.toString(equipped[i].getStats().attack));
			file.addTag("itemDef"+i,Integer.toString(equipped[i].getStats().defense));
			file.addTag("itemRarity"+i,Integer.toString(equipped[i].getRarity().ordinal()));
		}
	}

	public static Player loadFromFile(SaveFile file) throws TagException
	{
		int ini = file.getTagAsInt("playerIni");
		int atk = file.getTagAsInt("playerAtk");
		int def = file.getTagAsInt("playerDef");
		PlayerType type = PlayerType.values()[file.getTagAsInt("playerType")];
		Player player = new Player(type,ini,atk,def,false);
		player.xp = file.getTagAsInt("xp");
		player.calcLevel();
		player.lastLevel = player.level;
		for (int i = 0; i < player.equipped.length; i++) {
			int iniItem = file.getTagAsInt("itemIni"+i);
			int atkItem = file.getTagAsInt("itemAtk"+i);
			int defItem = file.getTagAsInt("itemDef"+i);
			int rarityOrdinal = file.getTagAsInt("itemRarity"+i);
			player.equipped[i] = new Item(ItemType.values()[i],iniItem,atkItem,defItem,Rarity.values()[rarityOrdinal]);
		}
		return player;
	}
}
