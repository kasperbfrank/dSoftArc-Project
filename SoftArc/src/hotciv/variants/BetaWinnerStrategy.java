package hotciv.variants;

import hotciv.common.CityImpl;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;

public class BetaWinnerStrategy implements WinnerStrategy {

	@Override
	public Player getWinner(Game game) {
		boolean win = true;
		
		CityImpl[][] cityArray = game.getAllCities();
		
		for(CityImpl[] cArray : cityArray){
			for(CityImpl city : cArray){
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
