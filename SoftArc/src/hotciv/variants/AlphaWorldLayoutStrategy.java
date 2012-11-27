package hotciv.variants;

import hotciv.common.TileImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.WorldLayoutStrategy;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
	
	Tile[][] tileArray = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

	@Override
	public Tile[][] buildWord(Game game) {
		for (int i = 0; i < GameConstants.WORLDSIZE; i++){
			for (int j = 0; j < GameConstants.WORLDSIZE; j++){
				tileArray[i][j] = new TileImpl(new Position(i, j), GameConstants.PLAINS);
			}
		}

		tileArray[1][0] = new TileImpl(new Position(1,0), GameConstants.OCEANS);
		tileArray[0][1] = new TileImpl(new Position(0,1), GameConstants.HILLS);
		tileArray[2][2] = new TileImpl(new Position(2,2), GameConstants.MOUNTAINS);
		
		game.insertCityAtPosition(new Position(1,1), Player.RED);
		game.insertCityAtPosition(new Position(4,1), Player.BLUE);
		
		game.insertUnitAtPosition(new Position(2,0), new UnitImpl(Player.RED, GameConstants.ARCHER, GameConstants.ARCHER_DEFENSIVE_STRENGTH, GameConstants.ARCHER_ATTACKING_STRENGTH));
		game.insertUnitAtPosition(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION, GameConstants.LEGION_DEFENSIVE_STRENGTH, GameConstants.LEGION_ATTACKING_STRENGTH));
		game.insertUnitAtPosition(new Position(4,3), new UnitImpl(Player.RED, GameConstants.SETTLER, GameConstants.SETTLER_DEFENSIVE_STRENGTH, GameConstants.SETTLER_ATTACKING_STRENGTH));
		
		return tileArray;
	}


}
