package hotciv.variants;

import thirdparty.ThirdPartyFractalGenerator;
import hotciv.common.TileImpl;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.WorldLayoutStrategy;

public class FractalWorldLayoutAdapter implements WorldLayoutStrategy {

	Tile[][] tileArray = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

	ThirdPartyFractalGenerator generator = new ThirdPartyFractalGenerator();

	@Override
	public Tile[][] buildWord(Game game) {
		for ( int r = 0; r < 16; r++ ) {
			for ( int c = 0; c < 16; c++ ) {
				String type = "";
				if ( generator.getLandscapeAt(r,c) == '.' ) { type = GameConstants.OCEANS; }
				if ( generator.getLandscapeAt(r,c) == 'o' ) { type = GameConstants.PLAINS; }
				if ( generator.getLandscapeAt(r,c) == 'M' ) { type = GameConstants.MOUNTAINS; }
				if ( generator.getLandscapeAt(r,c) == 'f' ) { type = GameConstants.FOREST; }
				if ( generator.getLandscapeAt(r,c) == 'h' ) { type = GameConstants.HILLS; }
				tileArray[r][c] = new TileImpl(new Position(r,c), type);
			}
		}
		return tileArray;
	}
}
