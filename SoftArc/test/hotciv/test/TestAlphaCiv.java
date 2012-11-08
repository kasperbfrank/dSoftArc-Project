package hotciv.test;

import hotciv.framework.*;
import hotciv.standard.GameImpl;

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
public class TestAlphaCiv {
	private Tile t;
  private Game game;
  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(Player.RED, Player.BLUE);
  }

  /*@Test
  public void shouldHaveRedCityAt1_1() {
    City c = game.getCityAt(new Position(1,1));
    assertNotNull("There should be a city at (1,1)", c);
    Player p = c.getOwner();
    assertEquals( "City at (1,1) should be owned by red",
      Player.RED, p );
  }*/
  
  @Test
  public void isRedFirstPlayerInTurn(){
	  Player p = game.getPlayerInTurn();
	  assertEquals("RED should be in turn if the game has just started", Player.RED, p);
  }
  
  @Test
  public void shiftsPlayerOnNextTurn(){
	  Player p = game.getPlayerInTurn();
	  assertEquals("RED should be in turn if the game has just started", Player.RED, p);
	  game.endOfTurn();
	  p = game.getPlayerInTurn();
	  assertEquals("BLUE should be in turn 2", Player.BLUE, p);
	  game.endOfTurn();
	  p = game.getPlayerInTurn();
	  assertEquals("RED should be in turn 3", Player.RED, p);
  }
  
  @Test
  public void shouldHaveOceanAt1_0() {
	  Tile t = game.getTileAt(new Position(1,0));
	  assertEquals("There should be ocean at (1,0)", GameConstants.OCEANS, t.getTypeString());
  }
  
  @Test
  public void shouldHaveHillsAt0_1() {
	  Tile t = game.getTileAt(new Position(0,1));
	  assertEquals("There should be hills at (0,1)", GameConstants.HILLS, t.getTypeString());
  }
  
  @Test
  public void shouldHaveMountainsAt2_2() {
	  Tile t = game.getTileAt(new Position(2,2));
	  assertEquals("There should be mountains at (2,2)", GameConstants.MOUNTAINS, t.getTypeString());
  }
  
  @Test
  public void shouldHavePlainsElsewhere(){
	  Tile t = game.getTileAt(new Position(0,0));
	  assertEquals("There should be plains at (0,0)", GameConstants.PLAINS, t.getTypeString());
  }
  
  @Test
  public void redHasArcherAndSettlerAtStart(){
	  Unit u = game.getUnitAt(new Position(2,0));
	  assertEquals("unit should be an Archer", GameConstants.ARCHER, u.getTypeString());
	  assertEquals("Owner of Archer-unit should be Player.RED", Player.RED, u.getOwner());
	  
	  u = game.getUnitAt(new Position(4,3));
	  assertEquals("unit should be a Settler", GameConstants.SETTLER, u.getTypeString());
	  assertEquals("Owner of Settler-unit should be Player.RED", Player.RED, u.getOwner());
  }
  
  @Test
  public void blueHasLegionAtStart(){
	  Unit u = game.getUnitAt(new Position(3,2));
	  assertEquals("unit should be a Legion", GameConstants.LEGION, u.getTypeString());
	  assertEquals("Owner of Legion-unit should be Player.BLUE", Player.BLUE, u.getOwner());
  }
  
  @Test
  public void unitCannotMoveOverMountain(){
	  Position to = new Position(2,2);
	  Position from = new Position(2,0);
	  
	  assertFalse("Cannot move to mountains at (2,2)", game.moveUnit(from, to));
  }
  
  @Test
  public void onlyPlayerInTurnCanMovePlayersUnits(){
	  Position to = new Position(2,1);
	  Position from = new Position(3,2);
	  
	  assertFalse("RED cannot move BLUE's units", game.moveUnit(from, to));
  }
  
}