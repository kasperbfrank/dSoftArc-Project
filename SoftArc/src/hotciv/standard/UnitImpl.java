package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class UnitImpl implements Unit{

	private Player owner;
	private Position p;
	private String type;
	
	public UnitImpl(Player owner, Position p, String type){
		this.owner = owner;
		this.p = p;
		this.type = type;
	}
	
	@Override
	public String getTypeString() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public Player getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}

	@Override
	public int getMoveCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDefensiveStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAttackingStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

}
