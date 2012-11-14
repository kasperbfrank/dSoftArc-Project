package hotciv.standard;

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
	private int age;
	private Player winner;
	
	private int archerCost = 10;
	private int legionCost = 5;
	private int settlerCost = 15;
	
	//Position.
	private Unit[][] unitArray = new Unit[16][16];
	private Tile[][] tileArray = new Tile[16][16];
	private CityImpl[][] cityArray = new CityImpl[16][16];
	
	public GameImpl(Player p1, Player p2){
		age = -4000;
		
		playerList.add(p1);
		playerList.add(p2);
		
		playerInTurn = p1;
		
		playerIterator = playerList.iterator();
		playerIterator.next();
		
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
	  Tile t = tileArray[p.getRow()][p.getColumn()];
	  
	  if(t == null){
		  t = new TileImpl(new Position(p.getRow(), p.getColumn()), GameConstants.PLAINS);
	  }
	  
	  return t;
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
	  
	if(!t.getTypeString().equals(GameConstants.MOUNTAINS) && playerInTurn.equals(u.getOwner()) && unitArray[to.getRow()][to.getColumn()] == null){
		
		//Move path free
		unitArray[to.getRow()][to.getColumn()] = u;
		unitArray[from.getRow()][from.getColumn()] = null;
			
		return true;
	} else if(!t.getTypeString().equals(GameConstants.MOUNTAINS) && playerInTurn.equals(u.getOwner()) && unitArray[to.getRow()][to.getColumn()] != null) {
		//Unit is blocking Tile
		if(!playerInTurn.equals(unitArray[to.getRow()][to.getColumn()].getOwner())){
			//You have defeated an enemy!
			System.out.println("YOU ARE VICTORIOUS!");
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
  
  public void endOfTurn() {
	  if(playerIterator.hasNext()){
		  playerInTurn = playerIterator.next();
	  }else{
		  //End of Round
		  playerIterator = playerList.iterator();
		  playerInTurn = playerIterator.next();
		  
		  this.age += 100;
		  
		  if (this.age == -3000){
			  winner = Player.RED;
		  }
		  
		  for (int i = 0; i < cityArray.length; i++) {
	            for (int j = 0; j < cityArray[i].length; j++) {
	                if (cityArray[i][j] != null){
	                	CityImpl c = cityArray[i][j];
	                	c.setMoney(c.getMoney() + 6);
	                }
	            }
		  }
	  }
  }
  
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  
  public void changeProductionInCityAt( Position p, String unitType ) {
	  CityImpl c = (CityImpl) this.getCityAt(p);
	  
	  if (unitType.equals(GameConstants.ARCHER)){
		  if (c.getMoney() >= archerCost){
			  c.setProduction(GameConstants.ARCHER);
			  c.setMoney(c.getMoney() - archerCost);
		  }  
	  } else if (unitType.equals(GameConstants.LEGION)){
		  if (c.getMoney() >= legionCost){
			  c.setProduction(GameConstants.LEGION);
			  c.setMoney(c.getMoney() - legionCost);
		  }  
	  } else if (unitType.equals(GameConstants.SETTLER)){
		  if (c.getMoney() >= settlerCost){
			  c.setProduction(GameConstants.SETTLER);
			  c.setMoney(c.getMoney() - settlerCost);
		  }  
	  }
	  
	  UnitImpl u = new UnitImpl(this.playerInTurn, c.getProduction());
	  
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
  public void performUnitActionAt( Position p ) {}
  
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
}
