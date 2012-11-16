package hotciv.test;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.*;
import hotciv.variants.AlphaActionStrategy;
import hotciv.variants.AlphaAgingStrategy;
import hotciv.variants.AlphaWinnerStrategy;
import hotciv.variants.DeltaWorldLayoutStrategy;

import org.junit.*;

import static org.junit.Assert.*;

/** Skeleton class for DeltaCiv test cases

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
public class TestDeltaCiv {

	String[] worldLayout = new String[16];
	private Game game;
	/** Fixture for deltaciv testing. */
	@Before
	public void setUp() {
		game = new GameImpl(Player.RED, Player.BLUE, new AlphaAgingStrategy(), new AlphaWinnerStrategy(), new AlphaActionStrategy(), new DeltaWorldLayoutStrategy(), 
				new Position(8,12), new Position(4,5), worldLayout);
	}
	
	@Test
	public void redHasCityAt8_12(){
		City c = game.getCityAt(new Position(8,12));
		assertEquals("City at position (8,12) is owned by red", Player.RED, c.getOwner());
	}

	@Test
	public void blueHasCityAt4_5(){
		City c = game.getCityAt(new Position(4,5));
		assertEquals("City at position (4,5) is owned by blue", Player.BLUE, c.getOwner());
	}
}
