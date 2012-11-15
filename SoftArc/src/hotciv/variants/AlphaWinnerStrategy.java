package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;

public class AlphaWinnerStrategy implements WinnerStrategy {
	
	@Override
	public Player getWinner(Game game) {
		Player winner;
		if (game.getAge() == -3000){
			  winner = Player.RED;
		  }
		return Player.RED;
	}

}
