package hotciv.common;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class UnitImpl implements Unit{

	private Player owner;
	private String type;
	
	private int attackingStrength;
	private int defensiveStrength;
	
	public UnitImpl(Player owner, String type){
		this.owner = owner;
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
		return defensiveStrength;
	}

	@Override
	public int getAttackingStrength() {
		// TODO Auto-generated method stub
		return attackingStrength;
	}

	@Override
	public void setDefensiveStrength(int ds) {
		// TODO Auto-generated method stub
		defensiveStrength = ds;
	}

	@Override
	public void setAttackingStrength(int as) {
		// TODO Auto-generated method stub
		attackingStrength = as;
	}

}
