package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;

public class TileImpl implements Tile{
	
	private Position p;
	
	public TileImpl(Position p){
		this.p = p;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return p;
	}

	@Override
	public String getTypeString() {
		// TODO Auto-generated method stub
		
		String type = GameConstants.PLAINS;
		
		if(p.equals(new Position(1,0))){
			type = GameConstants.OCEANS;
		}else if(p.equals(new Position(0,1))){
			type = GameConstants.HILLS;
		}else if(p.equals(new Position(2,2))){
			type = GameConstants.MOUNTAINS;
		}
		
		return type;
	}

}
