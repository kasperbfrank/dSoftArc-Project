package hotciv.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import hotciv.framework.*;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
*/

public class GameImpl implements Game {
	
	private ArrayList<Player> playerList = new ArrayList();
	private Player playerInTurn;
	private Iterator<Player> playerIterator;
	private ArrayList<Unit> unitList = new ArrayList();
	private int age = -4000;
	private Player winner;
	
	//Position.
	private Unit[][] unitArray = new Unit[16][16];
	private Tile[][] tileArray = new Tile[16][16];
	private City[][] cityArray = new City[16][16];
	
	private AgingStrategy agingStrategy;
	private WinnerStrategy winnerStrategy;
	private ActionStrategy actionStrategy;
	
	public GameImpl(Player p1, Player p2, AgingStrategy as, WinnerStrategy ws, ActionStrategy acs){
		
		playerList.add(p1);
		playerList.add(p2);
		
		this.agingStrategy = as;
		this.winnerStrategy = ws;
		
		playerInTurn = p1;
		
		playerIterator = playerList.iterator();
		playerIterator.next();
		
		for (int i = 0; i < 16; i++){
			for (int j = 0; j < 16; j++){
				tileArray[i][j] = new TileImpl(new Position(i, j), GameConstants.PLAINS);
			}
		}
		
		tileArray[1][0] = new TileImpl(new Position(1,0), GameConstants.OCEANS);
		tileArray[0][1] = new TileImpl(new Position(0,1), GameConstants.HILLS);
		tileArray[2][2] = new TileImpl(new Position(2,2), GameConstants.MOUNTAINS);

		unitArray[2][0] = new UnitImpl(Player.RED, GameConstants.ARCHER);
		unitArray[3][2] = new UnitImpl(Player.BLUE, GameConstants.LEGION);
		unitArray[4][3] = new UnitImpl(Player.RED, GameConstants.SETTLER);
		
		cityArray[1][1] = new CityImpl(Player.RED);
		cityArray[4][1] = new CityImpl(Player.BLUE);
		
	}
	
  public Tile getTileAt( Position p ) {
	  return tileArray[p.getRow()][p.getColumn()];
  }
  
  public Unit getUnitAt( Position p ) {
	  return unitArray[p.getRow()][p.getColumn()];
  }
  
  public City getCityAt( Position p ) { 
	  return cityArray[p.getRow()][p.getColumn()]; 
  }
  
  public Player getPlayerInTurn() {
	  return playerInTurn; 
  }
  
  public Player getWinner() {
	  return winner; 
  }
  
  public int getAge() { 
	  return age; 
  }
  
  public boolean moveUnit( Position from, Position to ) {
	
	Tile t = getTileAt(to);
	Unit u = unitArray[from.getRow()][from.getColumn()];
	
	if (u != null) {
		if(!t.getTypeString().equals(GameConstants.MOUNTAINS) && playerInTurn.equals(u.getOwner()) && unitArray[to.getRow()][to.getColumn()] == null){
			
			//Move path free
			unitArray[to.getRow()][to.getColumn()] = u;
			unitArray[from.getRow()][from.getColumn()] = null;
			
			//Conquer city if city is there.
			City c = cityArray[to.getRow()][to.getColumn()];
			if(c != null){
				c.setOwner(playerInTurn);
				winner = this.winnerStrategy.getWinner(this);
			}
				
			return true;
		} else if(!t.getTypeString().equals(GameConstants.MOUNTAINS) && playerInTurn.equals(u.getOwner()) && unitArray[to.getRow()][to.getColumn()] != null) {
			//Unit is blocking Tile
			if(!playerInTurn.equals(unitArray[to.getRow()][to.getColumn()].getOwner())){
				//You have defeated an enemy!
				unitArray[to.getRow()][to.getColumn()] = null;
				unitArray[to.getRow()][to.getColumn()] = u;
				return true;
			} else {
				//Friendly unit is blocking the Tile
				return false;
			}
		} else {
			return false;
		}
	}	
	return false;
  }
  
  public void endOfTurn() {
	  if(playerIterator.hasNext()){
		  playerInTurn = playerIterator.next();
	  }else{
		  //End of Round
		  playerIterator = playerList.iterator();
		  playerInTurn = playerIterator.next();
		  
		  age = agingStrategy.doAging(this.age);
		  
		  winner = winnerStrategy.getWinner(this);
		  
		  for (int i = 0; i < cityArray.length; i++) {
	            for (int j = 0; j < cityArray[i].length; j++) {
	                if (cityArray[i][j] != null){
	                	City c = cityArray[i][j];
	                	c.setMoney(c.getMoney() + 6);
	                }
	            }
		  }
	  }
  }
  
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  
  public void changeProductionInCityAt( Position p, String unitType ) {
	  City c = this.getCityAt(p);
	  
	  if (unitType.equals(GameConstants.ARCHER)){
		  if (c.getMoney() >= GameConstants.archerCost){
			  c.setProduction(GameConstants.ARCHER);
			  c.setMoney(c.getMoney() - GameConstants.archerCost);
		  }  
	  } else if (unitType.equals(GameConstants.LEGION)){
		  if (c.getMoney() >= GameConstants.legionCost){
			  c.setProduction(GameConstants.LEGION);
			  c.setMoney(c.getMoney() - GameConstants.legionCost);
		  }  
	  } else if (unitType.equals(GameConstants.SETTLER)){
		  if (c.getMoney() >= GameConstants.settlerCost){
			  c.setProduction(GameConstants.SETTLER);
			  c.setMoney(c.getMoney() - GameConstants.settlerCost);
		  }  
	  }
	  
	  Unit u = new UnitImpl(this.playerInTurn, c.getProduction());
	  
	  //Place unit on tile, if not already occupied.
	  if(this.unitArray[p.getRow()][p.getColumn()] == null){
		  this.unitArray[p.getRow()][p.getColumn()] = u;
	  }else{
		  Position[] pArray = this.getTilesAround(p);
		  for(Position pos : pArray){
			  if(this.unitArray[pos.getRow()][pos.getColumn()] == null){
				  this.unitArray[pos.getRow()][pos.getColumn()] = u;
			  }
		  }
	  }
	   
  }
  public void performUnitActionAt( Position p ) {
  }
  
  private Position[] getTilesAround(Position  p){
	  Position[] pArray = new Position[8];
	  
	  //Get the surrounding tiles.
	  pArray[0] = new Position(p.getColumn(), p.getRow() - 1);
	  pArray[1] = new Position(p.getColumn() + 1, p.getRow() - 1);
	  pArray[2] = new Position(p.getColumn() + 1, p.getRow());
	  pArray[3] = new Position(p.getColumn() + 1, p.getRow() + 1);
	  pArray[4] = new Position(p.getColumn(), p.getRow() + 1);
	  pArray[5] = new Position(p.getColumn() - 1, p.getRow() + 1);
	  pArray[6] = new Position(p.getColumn() - 1, p.getRow());
	  pArray[7] = new Position(p.getColumn() - 1, p.getRow() - 1);
	  
	  return pArray;
  }
  
  public City[][] getAllCities(){
	  return cityArray;
  }
}
