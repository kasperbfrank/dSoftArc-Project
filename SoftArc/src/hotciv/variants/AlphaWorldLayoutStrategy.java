package hotciv.variants;

import hotciv.common.TileImpl;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.WorldLayoutStrategy;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
	
	Tile[][] tileArray = new Tile[16][16];

	@Override
	public Tile[][] buildWord(Game game, Position redCityPosition,
			Position blueCityPosition, String[] worldMap) {
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 16; j++){
				tileArray[i][j] = new TileImpl(new Position(i, j), GameConstants.PLAINS);
			}
		}

		tileArray[1][0] = new TileImpl(new Position(1,0), GameConstants.OCEANS);
		tileArray[0][1] = new TileImpl(new Position(0,1), GameConstants.HILLS);
		tileArray[2][2] = new TileImpl(new Position(2,2), GameConstants.MOUNTAINS);
		
		game.insertCityAtPosition(redCityPosition, Player.RED);
		game.insertCityAtPosition(blueCityPosition, Player.BLUE);
		
		return tileArray;
	}


}
