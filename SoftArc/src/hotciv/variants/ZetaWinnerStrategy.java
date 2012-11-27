package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;



public class ZetaWinnerStrategy implements WinnerStrategy {
	
	private WinnerStrategy preWS;
	private WinnerStrategy postWS;
	
	public ZetaWinnerStrategy(WinnerStrategy preWS, WinnerStrategy postWS){
		this.preWS = preWS;
		this.postWS = postWS;
	}

	@Override
	public Player getWinner(Game game) {
		if (game.getRound() > 20) {
			return postWS.getWinner(game);
		}
		return preWS.getWinner(game);
	}


}
