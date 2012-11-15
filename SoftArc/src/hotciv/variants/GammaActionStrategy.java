package hotciv.variants;

import hotciv.framework.ActionStrategy;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class GammaActionStrategy implements ActionStrategy {

	@Override
	public void performAction(Game game, Position p) {
		// TODO Auto-generated method stub
		Unit unit = game.getUnitAt(p);
		
		if(unit.getTypeString().equals(GameConstants.ARCHER)){
			//Cast fortify/cancel fortify
			if (unit.getDefensiveStrength() > GameConstants.archerDS) {
				unit.setDefensiveStrength(unit.getDefensiveStrength()/2);
			} else {
				unit.setDefensiveStrength(GameConstants.archerDS * 2);
			}
		}else if(unit.getTypeString().equals(GameConstants.SETTLER)){
			//Build city at unit position
			game.insertCityAtPosition(p);	
		}
	}

}
