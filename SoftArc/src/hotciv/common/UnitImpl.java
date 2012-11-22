package hotciv.common;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class UnitImpl implements Unit{

	private Player owner;
	private String type;
	
	private int attackingStrength;
	private int defensiveStrength;
	
	public UnitImpl(Player owner, String type, int defensiveStrength){
		this.owner = owner;
		this.type = type;
		
		this.defensiveStrength = defensiveStrength;
	}
	
	@Override
	public String getTypeString() {
		return type;
	}

	@Override
	public Player getOwner() {
		return owner;
	}

	@Override
	public int getMoveCount() {
		return 0;
	}

	@Override
	public int getDefensiveStrength() {
		return defensiveStrength;
	}

	@Override
	public int getAttackingStrength() {
		return attackingStrength;
	}

}
