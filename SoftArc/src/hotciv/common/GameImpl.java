package hotciv.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

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

	private ArrayList<Player> playerList = new ArrayList<Player>();
	private HashMap<Player, Integer> battlesWon = new HashMap<Player, Integer>();
	
	private Player playerInTurn;
	private Iterator<Player> playerIterator;
	private int age = -4000;
	private Player winner;
	private int round = 0;

	//Position.
	private Unit[][] unitArray = new Unit[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
	private Tile[][] tileArray;
	private City[][] cityArray = new City[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

	private AgingStrategy agingStrategy;
	private WinnerStrategy winnerStrategy;
	private ActionStrategy actionStrategy;
	private WorldLayoutStrategy worldLayoutStrategy;
	
	private HotCivFactory factory;
	
	public GameImpl(HotCivFactory factory){
		
		this.factory = factory;
		
		this.agingStrategy = factory.createAgingStrategy();
		this.winnerStrategy = factory.createWinnerStrategy();
		this.actionStrategy = factory.createActionStrategy();
		this.worldLayoutStrategy = factory.createWorldLayoutStrategy();
		
		playerList.add(Player.RED);
		playerList.add(Player.BLUE);
		
		playerIterator = playerList.iterator();
		playerInTurn = playerIterator.next();
		
		tileArray = worldLayoutStrategy.buildWord(this);
		
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
		Unit u = getUnitAt(from);
		
		if (u != null && this.getUnitAt(from).getOwner().equals(playerInTurn)) {
			
			//If friendly unit on tile or mountains.
			if (t.getTypeString().equals(GameConstants.MOUNTAINS) || (this.getUnitAt(to) != null && this.getUnitAt(to).getOwner().equals(playerInTurn))){
				return false;
			}
			
			//If enemy unit on tile. 
			if (this.getUnitAt(to) != null && this.getUnitAt(to).getOwner().equals(playerInTurn)){
				if(this.attack(from, to) == false){
					this.deleteUnitAtPosition(from);
					return false;
				}
			}
			
			//Move path free
			this.insertUnitAtPosition(to, u);
			this.deleteUnitAtPosition(from);

			//Conquer city if city is there.
			City c = this.getCityAt(to);
			if(c != null){
				c.setOwner(playerInTurn);
				winner = this.winnerStrategy.getWinner(this);
			}
			return true;
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
			
			round++;

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
		Unit u = null;

		if (unitType.equals(GameConstants.ARCHER)){
			if (c.getMoney() >= GameConstants.archerCost){
				c.setProduction(GameConstants.ARCHER);
				c.setMoney(c.getMoney() - GameConstants.archerCost);
				
				u = new UnitImpl(this.playerInTurn, c.getProduction(), GameConstants.ARCHER_DEFENSIVE_STRENGTH, GameConstants.ARCHER_ATTACKING_STRENGTH);
			}  
		} else if (unitType.equals(GameConstants.LEGION)){
			if (c.getMoney() >= GameConstants.legionCost){
				c.setProduction(GameConstants.LEGION);
				c.setMoney(c.getMoney() - GameConstants.legionCost);
				
				u = new UnitImpl(this.playerInTurn, c.getProduction(), GameConstants.LEGION_DEFENSIVE_STRENGTH, GameConstants.LEGION_ATTACKING_STRENGTH);
			}  
		} else if (unitType.equals(GameConstants.SETTLER)){
			if (c.getMoney() >= GameConstants.settlerCost){
				c.setProduction(GameConstants.SETTLER);
				c.setMoney(c.getMoney() - GameConstants.settlerCost);
				
				u = new UnitImpl(this.playerInTurn, c.getProduction(), GameConstants.SETTLER_DEFENSIVE_STRENGTH, GameConstants.SETTLER_ATTACKING_STRENGTH);
			}  
		}

		

		//Place unit on tile, if not already occupied.
		if(this.unitArray[p.getColumn()][p.getRow()] == null){
			this.unitArray[p.getColumn()][p.getRow()] = u;
		}else{
			Position[] pArray = this.getTilesAround(p);
			for(Position pos : pArray){
				if(this.getUnitAt(pos) == null){
					this.insertUnitAtPosition(pos, u);
				}
			}
		}

	}
	public void performUnitActionAt( Position p ) {
		Unit unit = this.getUnitAt(p);
		if (unit.getOwner().equals(this.playerInTurn)) {
			this.actionStrategy.performAction(this, p);
		}
	}

	private Position[] getTilesAround(Position  p){
		Position[] pArray = new Position[8];

		//Get the surrounding tiles.
		pArray[0] = new Position(p.getRow() - 1, p.getColumn());
		pArray[1] = new Position(p.getRow() - 1, p.getColumn() + 1);
		pArray[2] = new Position(p.getRow(), p.getColumn() + 1);
		pArray[3] = new Position(p.getRow() + 1, p.getColumn() + 1);
		pArray[4] = new Position(p.getRow() + 1, p.getColumn());
		pArray[5] = new Position(p.getRow() + 1, p.getColumn() - 1);
		pArray[6] = new Position(p.getRow(), p.getColumn() - 1);
		pArray[7] = new Position(p.getRow() - 1, p.getColumn() - 1);

		return pArray;
	}

	public City[][] getAllCities(){
		return cityArray;
	}
	
	public void insertUnitAtPosition(Position p, Unit u){
		this.unitArray[p.getRow()][p.getColumn()] = u;
	}
	
	@Override
	public void deleteUnitAtPosition(Position p) {
		this.unitArray[p.getColumn()][p.getRow()] = null;
	}

	public void insertCityAtPosition(Position p, Player player) {
		cityArray[p.getRow()][p.getColumn()] = new CityImpl(player);
		unitArray[p.getRow()][p.getColumn()] = null;
	}

	@Override
	public boolean attack(Position attacker, Position defender) {
		Unit a = this.getUnitAt(attacker);
		Unit d = this.getUnitAt(defender);
		
		Random rng = new Random();
		
		int aStrength = a.getAttackingStrength() + Utility.getFriendlySupport(this, attacker, a.getOwner()) + Utility.getTerrainFactor(this, attacker);
		int dStrength = d.getDefensiveStrength() + Utility.getFriendlySupport(this, defender, a.getOwner()) + Utility.getTerrainFactor(this, defender);
		
		//Calculate the winner, false if defender wins, true if attacker does.
		if (aStrength * (rng.nextInt(6) + 1) > dStrength * (rng.nextInt(6) + 1)) {
			int won = (Integer)battlesWon.get(a.getOwner()).intValue();
			battlesWon.put(a.getOwner(), won + 1);
			return true;
		}
		
		return false;
	}

	@Override
	public HashMap<Player, Integer> getBattlesWon() {
		return this.battlesWon;
	}

	@Override
	public int getRound() {
		// TODO Auto-generated method stub
		return 0;
	}
}
