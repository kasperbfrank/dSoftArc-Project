package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Player;

public class CityImpl implements City{
	
	private Player owner;
	private int money;
	private String production;
	
	public CityImpl(Player owner){
		this.owner = owner;
	}

	@Override
	public Player getOwner() {
		return owner;
	}

	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public String getProduction() {
		return production;
	}

	@Override
	public String getWorkforceFocus(){
		return null;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void setMoney(int money){
		this.money = money;
	}
	
	public void setProduction(String prod){
		this.production = prod;
	}
	
	public void setOwner(Player player){
		this.owner = player;
	}

}
