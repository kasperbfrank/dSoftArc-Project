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
	
	//Position.
	private Unit[][] unitArray = new Unit[16][16];
	private Tile[][] tileArray = new Tile[16][16];
	
	public GameImpl(Player p1, Player p2){
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
  
  public City getCityAt( Position p ) { return null; }
  
  public Player getPlayerInTurn() {
	  return playerInTurn; 
  }
  
  public Player getWinner() { return null; }
  public int getAge() { return 0; }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }
  
  public void endOfTurn() {
	  if(playerIterator.hasNext()){
		  playerInTurn = playerIterator.next();
	  }else{
		  playerIterator = playerList.iterator();
		  playerInTurn = playerIterator.next();
	  }
  }
  
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}
}
