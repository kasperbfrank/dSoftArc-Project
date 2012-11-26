package hotciv.factories;

import java.util.ArrayList;
import java.util.Iterator;

import hotciv.framework.ActionStrategy;

import hotciv.framework.AgingStrategy;
import hotciv.framework.HotCivFactory;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.framework.WorldLayoutStrategy;

import hotciv.variants.*;

public class AlphaCivFactory implements HotCivFactory {

	@Override
	public WinnerStrategy getWinnerStrategy() {
		return new AlphaWinnerStrategy();
	}

	@Override
	public WorldLayoutStrategy getWorldLayoutStrategy() {
		return new AlphaWorldLayoutStrategy();
	}

	@Override
	public ActionStrategy getActionStrategy() {
		return new AlphaActionStrategy();
	}

	@Override
	public AgingStrategy getAgingStrategy() {
		return new AlphaAgingStrategy();
	}
	
	@Override
	public Iterator<Player> getPlayerIterator() {
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(Player.RED);
		playerList.add(Player.BLUE);
		
		return playerList.iterator();
	}

}
