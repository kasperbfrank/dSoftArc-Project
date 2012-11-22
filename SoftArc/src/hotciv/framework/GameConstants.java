package hotciv.framework;

/** Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.

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
public class GameConstants {
  // The size of the world is set permanently to a 16x16 grid 
  public static final int WORLDSIZE = 16;
  // Valid unit types
  public static final String ARCHER    = "archer";
  public static final String LEGION    = "legion";
  public static final String SETTLER   = "settler";
  // Valid terrain types
  public static final String PLAINS    = "plains";
  public static final String OCEANS    = "ocean";
  public static final String FOREST    = "forest";
  public static final String HILLS     = "hills";
  public static final String MOUNTAINS = "mountain";
  // Valid production balance types
  public static final String productionFocus = "hammer";
  public static final String foodFocus = "apple";
  //Unit costs
  public static final int archerCost = 10;
  public static final int legionCost = 5;
  public static final int settlerCost = 15;
  //Attack/defense strength
  public static final int ARCHER_ATTACKING_STRENGTH = 10;
  public static final int ARCHER_DEFENSIVE_STRENGTH = 10;
  public static final int SETTLER_ATTACKING_STRENGTH = 10;
  public static final int SETTLER_DEFENSIVE_STRENGTH = 10;
  public static final int LEGION_ATTACKING_STRENGTH = 10;
  public static final int LEGION_DEFENSIVE_STRENGTH = 10;
}
