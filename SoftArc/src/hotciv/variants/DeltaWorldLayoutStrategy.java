package hotciv.variants;

import hotciv.common.TileImpl;
import hotciv.common.UnitImpl;
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
				"...ooMooooo.....",
				"..ohhoooofffoo..",
				".oooooMooo...oo.",
				".ooMMMoooo..oooo",
				"...ofooohhoooo..",
				".ofoofooooohhoo.",
				"...ooo..........",
				".ooooo.ooohooM..",
				".ooooo.oohooof..",
				"offfoooo.offoooo",
				"oooooooo...ooooo",
				".ooMMMoooo......",
				"..ooooooffoooo..",
				"....ooooooooo...",
				"..ooohhoo.......",
				".....ooooooooo.."
			};
		
		String line;
	    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
	      line = worldLayout[r];
	      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
	        char tileChar = line.charAt(c);
	        String type = "error";
	        if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
	        if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
	        if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
	        if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
	        if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
	        
	        tileArray[r][c] = new TileImpl(new Position(r,c), type);
	      }
	    }
	    
	    game.insertCityAtPosition(new Position(8,12), Player.RED);
		game.insertCityAtPosition(new Position(4,5), Player.BLUE);
		
		game.insertUnitAtPosition(new Position(2,1), new UnitImpl(Player.RED, GameConstants.ARCHER, GameConstants.ARCHER_DEFENSIVE_STRENGTH, GameConstants.ARCHER_ATTACKING_STRENGTH));
		game.insertUnitAtPosition(new Position(3,2), new UnitImpl(Player.BLUE, GameConstants.LEGION, GameConstants.LEGION_DEFENSIVE_STRENGTH, GameConstants.LEGION_ATTACKING_STRENGTH));
		game.insertUnitAtPosition(new Position(4,3), new UnitImpl(Player.RED, GameConstants.SETTLER, GameConstants.SETTLER_DEFENSIVE_STRENGTH, GameConstants.SETTLER_ATTACKING_STRENGTH));
				
		return tileArray;
	}

}
