package hotciv.test;

import hotciv.common.CityImpl;
import hotciv.common.GameImpl;
import hotciv.common.UnitImpl;
import hotciv.framework.*;
import hotciv.variants.AlphaActionStrategy;
import hotciv.variants.AlphaAgingStrategy;
import hotciv.variants.AlphaWinnerStrategy;

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
  
  private Game game;
  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(Player.RED, Player.BLUE, new AlphaAgingStrategy(), new AlphaWinnerStrategy(), new AlphaActionStrategy());
  }
  
  /**
   * Player tests.
   */
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
  
  /**
   * Game world tests.
   */
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
  
  /**
   * Unit initialization and control tests.
   */
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
	  Position to;
	  Position from;
	  to = new Position(2,1);
	  from = new Position(3,2);
	  
	  assertFalse("RED cannot move BLUE's units", game.moveUnit(from, to));
	  
	  game.endOfTurn();
	  
	  to = new Position(2,1);
	  from = new Position(2,0);
	  
	  assertFalse("BLUE cannot move RED's units", game.moveUnit(from, to));
  }
  
  @Test
  public void onlyOnePlayerAtTile(){
	  Position to = new Position(4,3);
	  Position from = new Position(2,0);
	  
	  assertFalse("Unit should not be able to move to another units location", game.moveUnit(from, to));
  }
  
  @Test
  public void shouldOnlyMoveUnits(){
	  Unit u = new UnitImpl(Player.RED, GameConstants.ARCHER);
	  
	  Position to = new Position(4,3);
	  Position from = new Position(0,0);
	  
	  assertNull("Unit should be an actual unit", game.getUnitAt(from));
	  
	  assertFalse("Should not be able to move anything but units", game.moveUnit(from, to));
  }
  
  /**
   * Attacking tests.
   */
  @Test
  public void attackerAlwaysWins(){
	  //Blue Legion
	  Position to = new Position(3,2);
	  
	  //Red Archer
	  Position from = new Position(2,0);
	  
	  //Moving Red Archer to Blue Legion makes the red unit defeat his blue opponent meaning the red player is victorious..
	  game.moveUnit(from, to);
	  Player p = game.getUnitAt(new Position(2,0)).getOwner();
	  
	  assertEquals("Attacking unit should always win", Player.RED, p);
  }
  
  /**
   * Game cities tests.
   */
  @Test
  public void redHasCityAt1_1(){
	  City c = game.getCityAt(new Position(1,1));
	  assertEquals("City at position (1,1) is owned by red", Player.RED, c.getOwner());
  }
  
  @Test
  public void blueHasCityAt1_1(){
	  City c = game.getCityAt(new Position(4,1));
	  assertEquals("City at position (4,1) is owned by blue", Player.BLUE, c.getOwner());
  }
  
  @Test
  public void cityPopulationIsAlways1(){
	  City c = game.getCityAt(new Position(1,1));
	  assertEquals("City population is 1", 1, c.getSize());
  }
  
  @Test
  public void endOfRoundIncreasesProductionBy6(){
	  CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
	  
	  //Play 1 rounds of sick and action-packed civilization
	  game.endOfTurn();
	  game.endOfTurn();
	  
	  assertEquals("Production of cities should now have increased by 6", 6, c.getMoney());
  }
  
  /**
   * Unit production tests.
   */
  @Test
  public void produceUnitAndDeductPrice(){
	  CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
	  
	  //Play 2 rounds
	  game.endOfTurn();
	  game.endOfTurn();
	  game.endOfTurn();
	  game.endOfTurn();
	  
	  game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
	  
	  assertEquals("A red archer should be in production", GameConstants.ARCHER, c.getProduction());
	  
	  //Since price of archer is 10 and money after 2 rounds is 12 money should be 2 after starting archer production
	  assertEquals("Money should be 2 after producing Archer", 2, c.getMoney());
  }
  
  @Test
  public void placeUnitAtNonOccupiedTile(){
	  
	  Position p = new Position(1,1);
	  
	  //Play 2 rounds
	  game.endOfTurn();
	  game.endOfTurn();
	  game.endOfTurn();
	  game.endOfTurn();
	  
	  game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
	  
	  //First check to see if freshly produced unit is placed on tile.
	  Unit u = game.getUnitAt(p);
	  assertNotNull("Unit u should be at the city's tile, in this example (1,1)", u);
	  
	  //Now check if another unit can be placed on top of the first unit.
	  
	  //Play 1 round
	  game.endOfTurn();
	  game.endOfTurn();
	  
	  game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION);
	  u = game.getUnitAt(p);
	  assertEquals("Unit at tile (1,1) should still be archer, as there can only be one unit per tile", GameConstants.ARCHER, u.getTypeString());
	  
	  //Place this unit on a surrounding tile, starting with the tile just north of the city position, then going clockwise around the city.
	  u = game.getUnitAt(new Position(p.getColumn(), p.getRow() - 1));
	  assertNotNull("Unit u should be just north of the city's tile, in this example (1,0)", u);
	  
	  //Place unit on another surrounding tile.
	  //Play 1 round
	  game.endOfTurn();
	  game.endOfTurn();
	  
	  game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION);
	  u = game.getUnitAt(new Position(p.getColumn() + 1, p.getRow() - 1));
	  
	  assertNotNull("Unit u should be just north of the city's tile, in this example (2,0)", u);
  }
  
  /**
   * Game aging tests.
   */
  @Test
  public void gameStartsAtYear4000BC(){
	  assertEquals("Game should start at year 4000 BC (year -4000)", -4000, game.getAge());
  }
  
  @Test
  public void endOfRoundAdvancesYearBy100(){
	  int previousRoundYear = game.getAge();
	  
	  //Both players have to end their turn for a round to pass
	  game.endOfTurn();
	  game.endOfTurn();
	  
	  assertEquals("End of Round should advance year by 100", previousRoundYear + 100, game.getAge());
  }
  
  /**
   * Game winning tests.
   */
  @Test
  public void nooneHasWonBefore3000BC(){
	  assertEquals("Noone should have won before the year 3000 BC (year -3000)", null, game.getWinner());
  }
  
  @Test
  public void redWinsAtYear3000BC(){
	  
	  //Playing 10 rounds of the most fun civilization game ever
	  for(int i=0; i<20; i++){
          game.endOfTurn();
	  }
	  
	  //10 rounds later....
	  assertEquals("Red Player should win at year 3000 BC (year -3000)", Player.RED, game.getWinner());
  }
}