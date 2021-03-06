package hotciv.common;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;

public class TileImpl implements Tile{
	
	private Position p;
	private String type;
	
	public TileImpl(Position p, String type){
		this.p = p;
		this.type = type;
	}

	@Override
	public Position getPosition() {
		return p;
	}

	@Override
	public String getTypeString() {
		return type;
	}

}
