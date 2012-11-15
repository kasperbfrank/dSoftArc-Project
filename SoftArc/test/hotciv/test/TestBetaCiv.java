package hotciv.test;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.*;
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
  
  private Game game;
  /** Fixture for betaCiv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(Player.RED, Player.BLUE, new BetaAgingStrategy(), new BetaWinnerStrategy());
  }
  
  // Aging Tests
  @Test
  public void between4000BCAnd100BC100YearsPassPerRound() {
	  for (int year = -4000; year < -100; year += 100) {
		  
		  assertEquals("100 Years should pass per round", year, game.getAge());
		  
		  game.endOfTurn();
		  game.endOfTurn();
	  }
  }
  
}
