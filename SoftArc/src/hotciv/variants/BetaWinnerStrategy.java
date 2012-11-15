package hotciv.variants;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;

public class BetaWinnerStrategy implements WinnerStrategy {

	@Override
	public Player getWinner(Game game) {
		boolean win = true;
		
		City[][] cityArray = game.getAllCities();
		
		for(City[] cArray : cityArray){
			for(City city : cArray){
				if(city != null){
					if(!city.getOwner().equals(game.getPlayerInTurn())){
						win = false;
					}
				}
			}
		}
		
		if(win){
			return game.getPlayerInTurn();
		}
		return null;
	}

}
