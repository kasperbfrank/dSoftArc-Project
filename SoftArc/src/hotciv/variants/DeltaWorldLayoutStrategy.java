package hotciv.variants;

import hotciv.common.TileImpl;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.WorldLayoutStrategy;

public class DeltaWorldLayoutStrategy implements WorldLayoutStrategy {
	
	Tile[][] tileArray = new Tile[16][16];

	@Override
	public Tile[][] buildWord(Game game) {
		
		String[] worldLayout = new String[] {
				"OOOPPMPPPPPOOOOO",
				"OOPHHPPPPFFFPPOO",
				"OPPPPPMPPPOOOPPO",
				"OPPMMMPPPPOOPPPP",
				"OOOPFPPPHHPPPPOO",
				"OPFPPFPPPPPHHPPO",
				"OOOPPPOOOOOOOOOO",
				"OPPPPPOPPPHPPMOO",
				"OPPPPPOPPHPPPFOO",
				"PFFFPPPPOPFFPPPP",
				"PPPPPPPPOOOPPPPP",
				"OPPMMMPPPPOOOOOO",
				"OOPPPPPPFFPPPPOO",
				"OOOOPPPPPPPPPOOO",
				"OOPPPHHPPOOOOOOO",
				"OOOOOPPPPPPPPPOO"
			};
		
		game.insertCityAtPosition(new Position(8,12), Player.RED);
		game.insertCityAtPosition(new Position(4,5), Player.BLUE);
		
		String line;
	    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
	      line = worldLayout[r];
	      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
	        char tileChar = line.charAt(c);
	        String type = "error";
	        if ( tileChar == 'O' ) { type = GameConstants.OCEANS; }
	        if ( tileChar == 'P' ) { type = GameConstants.PLAINS; }
	        if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
	        if ( tileChar == 'F' ) { type = GameConstants.FOREST; }
	        if ( tileChar == 'H' ) { type = GameConstants.HILLS; }
	        
	        tileArray[r][c] = new TileImpl(new Position(r,c), type);
	      }
	    }
		
		return tileArray;
	}

}