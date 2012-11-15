package hotciv.framework;

import hotciv.common.CityImpl;

public interface WinnerStrategy {
	public Player getWinner(Game game);
}
