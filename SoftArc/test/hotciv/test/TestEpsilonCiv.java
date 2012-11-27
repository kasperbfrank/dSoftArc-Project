package hotciv.test;

import java.util.HashMap;
import java.util.Random;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.TileImpl;
import hotciv.common.UnitImpl;
import hotciv.common.Utility;
import hotciv.factories.EpsilonCivFactory;
import hotciv.framework.*;
import hotciv.variants.EpsilonWinnerStrategy;

import org.junit.*;
import static org.junit.Assert.*;

/** Skeleton class for EpsilonCiv test cases

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
public class TestEpsilonCiv {

	String[] worldLayout;
	private Game game;
	/** Fixture for alphaciv testing. */
	@Before
	public void setUp() {
		game = new GameStubForAttackTesting();
	}
	
	@Test
	public void successfulAttacksIncreaseBattlesWon(){
		game.moveUnit(new Position(3,3), new Position(4,4));
		assertEquals("RED's battles won should be 3", (Integer)3, game.getBattlesWon().get(Player.RED));
	}
	
	@Test
	public void afterThreeSuccessfulAttacksGameIsWon(){
		game.moveUnit(new Position(3,3), new Position(4,4));
		
		assertEquals("After 3 successful attacks, RED wins the game", Player.RED, game.getWinner());
	}
	
}

/**
 * Test stub for automated testing.
 */

class GameStubForAttackTesting implements Game{

	private HashMap<Player, Integer> battlesWon = new HashMap<Player, Integer>();
	private Player playerInTurn;
	
	public GameStubForAttackTesting(){
		battlesWon.put(Player.RED, 2);
		battlesWon.put(Player.BLUE, 2);
		
		this.playerInTurn = Player.RED;
	}

	public Tile getTileAt(Position p) {
		if ( p.getRow() == 0 && p.getColumn() == 0 ) {
			return new StubTile(GameConstants.FOREST, 0, 0);
		}
		if ( p.getRow() == 1 && p.getColumn() == 0 ) {
			return new StubTile(GameConstants.HILLS, 1, 0);
		}
		return new StubTile(GameConstants.PLAINS, 0, 1);
	}
	
	public Unit getUnitAt(Position p) {
		if ( p.getRow() == 2 && p.getColumn() == 3 ||
				p.getRow() == 3 && p.getColumn() == 2 ||
				p.getRow() == 3 && p.getColumn() == 3 ) {
			return new StubUnit(GameConstants.ARCHER, Player.RED);
		}
		if ( p.getRow() == 4 && p.getColumn() == 4 ) {
			return new StubUnit(GameConstants.ARCHER, Player.BLUE);
		}
		return null;
	}
	
	public City getCityAt(Position p) {
		if ( p.getRow() == 1 && p.getColumn() == 1 ) {
			return new City() {
				public Player getOwner() { return Player.RED; }
				public int getSize() { return 1; }
				public String getProduction() {
					return null;
				}
				public String getWorkforceFocus() {
					return null;
				}
				@Override
				public int getMoney() {
					return 0;
				}
				@Override
				public void setMoney(int money) {
					
				}
				@Override
				public void setProduction(String prod) {
					
				}
				@Override
				public void setOwner(Player player) {
					
				}
			};
		}
		return null;
	}

	@Override
	public Player getPlayerInTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getWinner() {
		return new EpsilonWinnerStrategy().getWinner(this);
	}

	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		
		Unit u = this.getUnitAt(new Position(3,3));
		
		if(this.attack(from, to) == false){
			return false;
		}
			
		//Attack was won.
		return true;
	}

	@Override
	public void endOfTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeWorkForceFocusInCityAt(Position p, String balance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeProductionInCityAt(Position p, String unitType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void performUnitActionAt(Position p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public City[][] getAllCities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUnitAtPosition(Position p, Unit u) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteUnitAtPosition(Position p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertCityAtPosition(Position p, Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean attack(Position attacker, Position defender) {
		Unit a = this.getUnitAt(attacker);
		Unit d = this.getUnitAt(defender);
		
		Random rng = new Random();
		
		int aStrength = 10;
		int dStrength = 1;
		
		//Calculate the winner, false if defender wins, true if attacker does.
		if (aStrength * (rng.nextInt(6) + 1) > dStrength * (rng.nextInt(6) + 1)) {
			int won = (Integer)battlesWon .get(a.getOwner()).intValue();
			battlesWon.put(a.getOwner(), won + 1);
			return true;
		}
		
		return false;
	}

	@Override
	public HashMap<Player, Integer> getBattlesWon() {
		return battlesWon;
	}

	@Override
	public int getRound() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
