package hotciv.factories;

import java.util.ArrayList;
import java.util.Iterator;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.HotCivFactory;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.framework.WorldLayoutStrategy;
import hotciv.variants.AlphaActionStrategy;
import hotciv.variants.AlphaAgingStrategy;
import hotciv.variants.AlphaWinnerStrategy;
import hotciv.variants.AlphaWorldLayoutStrategy;
import hotciv.variants.BetaAgingStrategy;
import hotciv.variants.BetaWinnerStrategy;

public class BetaCivFactory implements HotCivFactory {

	@Override
	public WinnerStrategy getWinnerStrategy() {
		return new BetaWinnerStrategy();
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
		return new BetaAgingStrategy();
	}

	@Override
	public Iterator<Player> getPlayerIterator() {
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(Player.RED);
		playerList.add(Player.BLUE);
		
		return playerList.iterator();
	}

}
