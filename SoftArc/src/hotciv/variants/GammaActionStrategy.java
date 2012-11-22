package hotciv.variants;

import hotciv.common.UnitImpl;
import hotciv.framework.ActionStrategy;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class GammaActionStrategy implements ActionStrategy {

	@Override
	public void performAction(Game game, Position p) {
		Unit unit = game.getUnitAt(p);
		
		if(unit.getTypeString().equals(GameConstants.ARCHER)){
			//Cast fortify/cancel fortify
			if (unit.getDefensiveStrength() > GameConstants.ARCHER_DEFENSIVE_STRENGTH) {
				game.insertUnitAtPosition(p, new UnitImpl(game.getPlayerInTurn(), unit.getTypeString(), GameConstants.ARCHER_DEFENSIVE_STRENGTH));
			} else {
				game.insertUnitAtPosition(p, new UnitImpl(game.getPlayerInTurn(), unit.getTypeString(), GameConstants.ARCHER_DEFENSIVE_STRENGTH * 2));
			}
		}else if(unit.getTypeString().equals(GameConstants.SETTLER)){
			//Build city at unit position
			game.insertCityAtPosition(p, unit.getOwner());	
		}
	}

}
