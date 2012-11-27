package hotciv.test;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.factories.EpsilonCivFactory;
import hotciv.framework.*;

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
		game = new GameImpl(new EpsilonCivFactory());
	}
//	@Test
//	public void 
	
}
