package hotciv.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Random;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.factories.AlphaCivFactory;
import hotciv.framework.AttackStrategy;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.stub.ObserverStub;
import hotciv.variants.BetaWinnerStrategy;
import hotciv.variants.EpsilonWinnerStrategy;
import hotciv.variants.ZetaWinnerStrategy;

import org.junit.Before;
import org.junit.Test;

/** Skeleton class for ZetaCiv test cases

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
public class TestZetaCiv {

	String[] worldLayout;
	private Game game;
	private GameObserver obs;
	/** Fixture for alphaciv testing. */
	@Before
	public void setUp() {
		game = new GameImpl(new AlphaCivFactory());
		obs = new ObserverStub();
		game.addObserver(obs);
	}
	
	@Test
	public void successfulAttacksPre20thRoundDoesNotIncreaseBattlesWon(){
		game.moveUnit(new Position(3,3), new Position(4,4));
		assertEquals("RED's battles won should be 2", (Integer)2, game.getBattlesWon().get(Player.RED));
	}
	
	@Test
	public void successfulAttacksPost20thRoundDoesIncreaseBattlesWon(){
		
		//1 Round will pass
		game.endOfTurn();
		game.endOfTurn();
		
		game.moveUnit(new Position(3,3), new Position(4,4));
		assertEquals("RED's battles won should be 3", (Integer)3, game.getBattlesWon().get(Player.RED));
	}
	
	@Test
	public void conqueringAllCitiesPre20thRoundWinsGame(){
		Position p = new Position(1,1);
		game.insertCityAtPosition(p, Player.RED);
		
		assertEquals("RED should be the winner because he holds all the cities", Player.RED, game.getWinner());
	}
	
	@Test
	public void conqueringAllCitiesPost20thRoundDoesNotWinGame(){
		//1 Round passes
		game.endOfTurn();
		game.endOfTurn();
		
		Position p = new Position(1,1);
		game.insertCityAtPosition(p, Player.RED);
		
		assertEquals("There should be no winner if all cities get conquered post 19 rounds", null, game.getWinner());
	}
	
	@Test
	public void post20thRoundThreeSuccessfulAttacksWinsGame(){
		//1 Round passes
		game.endOfTurn();
		game.endOfTurn();
		
		game.moveUnit(new Position(3,3), new Position(4,4));
		assertEquals("If game is in round 20 or later 3 successful attacks should win RED the game", Player.RED, game.getWinner());
	}
}

class GameStubForWinnerAndAttackStrategyTesting implements Game{

	private HashMap<Player, Integer> battlesWon = new HashMap<Player, Integer>();
	private Player playerInTurn;
	private int round;
	private int i = 0;
	private City[][] cityArray = new City[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
	private AttackStrategy attackStrategy;
	
	public GameStubForWinnerAndAttackStrategyTesting(){
		battlesWon.put(Player.RED, 2);
		battlesWon.put(Player.BLUE, 2);
		
		cityArray[0][0] = new CityImpl(Player.RED);
		cityArray[1][1] = new CityImpl(Player.BLUE);
		
		this.playerInTurn = Player.RED;
		this.round = 20;
		
		this.attackStrategy = new fixedZetaAttackStrategy();
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
		return playerInTurn;
	}

	@Override
	public Player getWinner() {
		return new ZetaWinnerStrategy(new BetaWinnerStrategy(), new EpsilonWinnerStrategy()).getWinner(this);
	}

	@Override
	public int getAge() {
		return 0;
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		
		Unit u = this.getUnitAt(new Position(3,3));
		
		if(this.attackStrategy.attack(this, from, to) == false){
			return false;
		}
			
		//Attack was won.
		return true;
	}

	@Override
	public void endOfTurn() {
		if (i == 0) {
			i++;
		} else {
			round++;
			i = 0;
		}
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
		return cityArray;
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
		cityArray[p.getRow()][p.getColumn()] = new CityImpl(player);
	}

	@Override
	public HashMap<Player, Integer> getBattlesWon() {
		return battlesWon;
	}

	@Override
	public int getRound() {
		return round;
	}

	@Override
	public void incrementBattlesWon(Player player) {
		int won = this.getBattlesWon().get(player).intValue();
		battlesWon.put(player, won + 1);
	}

	@Override
	public void addObserver(GameObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTileFocus(Position position) {
		// TODO Auto-generated method stub
		
	}
}

class fixedZetaAttackStrategy implements AttackStrategy {

	@Override
	public boolean attack(Game game, Position attacker, Position defender) {
		Unit a = game.getUnitAt(attacker);
		Unit d = game.getUnitAt(defender);
		
		Random rng = new Random();
		
		int aStrength = 10;
		int dStrength = 1;
		
		//Calculate the winner, false if defender wins, true if attacker does.
		if (aStrength * (rng.nextInt(6) + 1) > dStrength * (rng.nextInt(6) + 1)) {
			if (game.getRound() > 20){
				game.incrementBattlesWon(a.getOwner());
			}
			return true;
		}
		
		return false;
	}
	
}
