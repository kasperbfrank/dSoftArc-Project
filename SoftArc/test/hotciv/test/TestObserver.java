package hotciv.test;

import hotciv.common.GameImpl;
import hotciv.factories.AlphaCivFactory;
import hotciv.framework.*;

import hotciv.stub.*;

import org.junit.*;

import static org.junit.Assert.*;

/** Skeleton class for AlphaCiv test cases

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
public class TestObserver {
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
	public void testObserver(){
		game.moveUnit(new Position(2,0), new Position(2,1));
		assertNotNull("obs.wcaPos should not be null, which means the observer has been notified.", ((ObserverStub)obs).wcaPos);
		
		game.endOfTurn();
		assertNotNull("obs.turnEnds should not be null, which means the observer has been notified.", ((ObserverStub)obs).turnEnds);
		
		game.setTileFocus(new Position(1,1));
		assertNotNull("obs.tileFocusPos should not be null, which means the observer has been notified.", ((ObserverStub)obs).tileFocusPos);
	}
}