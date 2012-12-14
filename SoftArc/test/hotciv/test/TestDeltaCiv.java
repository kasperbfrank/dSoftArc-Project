package hotciv.test;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.factories.AlphaCivFactory;
import hotciv.factories.DeltaCivFactory;
import hotciv.framework.*;
import hotciv.stub.ObserverStub;
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
	public void redHasCityAt8_12(){
		City c = game.getCityAt(new Position(8,12));
		assertEquals("City at position (8,12) is owned by red", Player.RED, c.getOwner());
	}

	@Test
	public void blueHasCityAt4_5(){
		City c = game.getCityAt(new Position(4,5));
		assertEquals("City at position (4,5) is owned by blue", Player.BLUE, c.getOwner());
	}
	
	@Test
	public void plainsAt0_3(){
		Position p = new Position(0,3);
		Tile t = game.getTileAt(p);
		assertEquals("Tile at position (0,3) should be of type plains", GameConstants.PLAINS, t.getTypeString());
	}
	
	@Test
	public void oceansAt0_0(){
		Position p = new Position(0,0);
		Tile t = game.getTileAt(p);
		assertEquals("Tile at position (0,0) should be of type oceans", GameConstants.OCEANS, t.getTypeString());
	}
	
	@Test
	public void mountainsAt0_5(){
		Position p = new Position(0,5);
		Tile t = game.getTileAt(p);
		assertEquals("Tile at position (0,5) should be of type mountains", GameConstants.MOUNTAINS, t.getTypeString());
	}
	
	@Test
	public void forestAt1_11(){
		Position p = new Position(1,11);
		Tile t = game.getTileAt(p);
		assertEquals("Tile at position (1,11) should be of type forest", GameConstants.FOREST, t.getTypeString());
	}
	
	@Test
	public void hillsAt1_4(){
		Position p = new Position(1,4);
		Tile t = game.getTileAt(p);
		assertEquals("Tile at position (1,4) should be of type hills", GameConstants.HILLS, t.getTypeString());
	}
}
