package hotciv.test;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.*;
import hotciv.variants.AlphaAgingStrategy;
import hotciv.variants.AlphaWinnerStrategy;
import hotciv.variants.GammaActionStrategy;

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
public class TestGammaCiv {
  
  private Game game;
  /** Fixture for GammaCiv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(Player.RED, Player.BLUE, new AlphaAgingStrategy(), new AlphaWinnerStrategy(), new GammaActionStrategy());
  }
}
