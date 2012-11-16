package hotciv.framework;

public interface WorldLayoutStrategy {
	public Tile[][] buildWord(Game game, Position redCityPosition, Position blueCityPosition, String[] worldMap);
}
