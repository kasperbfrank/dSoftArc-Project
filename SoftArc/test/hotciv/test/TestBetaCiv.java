package hotciv.test;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.*;
import hotciv.variants.AlphaActionStrategy;
import hotciv.variants.AlphaWorldLayoutStrategy;
import hotciv.variants.BetaAgingStrategy;
import hotciv.variants.BetaWinnerStrategy;

import org.junit.*;
import static org.junit.Assert.*;

/** Skeleton class for BetaCiv test cases

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     DEFINATLY NOT Henrik B Christensen 
     Computer Science Department
     Aarhus University

   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class TestBetaCiv {
	
	String[] worldLayout;
	private Game game;
	/** Fixture for betaCiv testing. */
	@Before
	public void setUp() {
		game = new GameImpl(Player.RED, Player.BLUE, new BetaAgingStrategy(), new BetaWinnerStrategy(), new AlphaActionStrategy(), new AlphaWorldLayoutStrategy(),
				new Position(1,1), new Position(4,1), worldLayout);
	}

	// Aging Tests
	@Test
	public void worldAgesCorrectly() {
		int year = -4000;

		for (int i = -4000; i < -100; i += 100) {

			assertEquals("100 Years should pass per round", i, game.getAge());

			game.endOfTurn();
			game.endOfTurn();
		}

		//Year -100
		year = -100;
		assertEquals("Age should be -100", year, game.getAge());

		game.endOfTurn();
		game.endOfTurn();

		//Year -1
		year = -1;
		assertEquals("Age should be -1", year, game.getAge());

		game.endOfTurn();
		game.endOfTurn();

		//Year +1
		year = 1;
		assertEquals("Age should be +1", year, game.getAge());

		game.endOfTurn();
		game.endOfTurn();

		//Year +50
		year = 50;
		assertEquals("Age should be +50", year, game.getAge());

		for (int i = 50; i < 1750; i += 50) {

			assertEquals("50 Years should pass per round", i, game.getAge());

			game.endOfTurn();
			game.endOfTurn();
		}

		for (int i = 1750; i < 1900; i += 25) {

			assertEquals("25 Years should pass per round", i, game.getAge());

			game.endOfTurn();
			game.endOfTurn();
		}

		for (int i = 1900; i < 1970; i += 5) {

			assertEquals("5 Years should pass per round", i, game.getAge());

			game.endOfTurn();
			game.endOfTurn();
		}

		for (int i = 1970; i < 2000; i++) {

			assertEquals("1 Year should pass per round", i, game.getAge());

			game.endOfTurn();
			game.endOfTurn();
		}
	}

	//Winning tests
	@Test
	public void playerWinsWhenOwningAllCities(){
		Position from = new Position(2,0);
		Position to = new Position(4,1);

		game.moveUnit(from, to);

		assertEquals("RED wins when RED owns all cities on the map", Player.RED, game.getWinner());
	}

}
