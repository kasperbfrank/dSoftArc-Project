package hotciv.test;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.factories.AlphaCivFactory;
import hotciv.factories.GammaCivFactory;
import hotciv.framework.*;
import hotciv.stub.ObserverStub;
import hotciv.variants.AlphaAgingStrategy;
import hotciv.variants.AlphaWinnerStrategy;
import hotciv.variants.AlphaWorldLayoutStrategy;
import hotciv.variants.GammaActionStrategy;

import org.junit.*;

import static org.junit.Assert.*;

/** Skeleton class for GammaCiv test cases

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
	public void settlerBuildsCityAtHisPositionAndRemovesHimself(){
		Position p = new Position(4,3);

		game.performUnitActionAt(p);

		assertNotNull("There is now a City at (4,3)", game.getCityAt(p));
		assertNull("There should not be any unit at (4,3)", game.getUnitAt(p));
	}

	@Test
	public void archerFortifiesHimeselfOrCancels(){
		Position p = new Position(2,0);

		game.performUnitActionAt(p);

		assertEquals("Archer should now have double defensive strength", GameConstants.ARCHER_DEFENSIVE_STRENGTH * 2, game.getUnitAt(p).getDefensiveStrength());

		game.performUnitActionAt(p);
		assertEquals("Archer should now have normal defensive strength", GameConstants.ARCHER_DEFENSIVE_STRENGTH, game.getUnitAt(p).getDefensiveStrength());

	}
}
