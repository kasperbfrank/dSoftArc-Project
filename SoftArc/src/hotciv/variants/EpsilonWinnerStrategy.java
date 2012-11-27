package hotciv.variants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;

public class EpsilonWinnerStrategy implements WinnerStrategy {

	@Override
	public Player getWinner(Game game) {
		HashMap<Player, Integer> battlesWon = game.getBattlesWon();
		Iterator i = battlesWon.entrySet().iterator();
		
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			if ((Integer)me.getValue() == 3) {
				return (Player) me.getKey();
			};
		}
		return null;
	}

}
