package hotciv.common;

import java.util.HashMap;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

public class LogDecorator implements Game{

	private Game game;
	public LogDecorator(Game game) {
		this.game = game;
	}
	@Override
	public Tile getTileAt(Position p) {
		return game.getTileAt(p);
	}

	@Override
	public Unit getUnitAt(Position p) {
		return game.getUnitAt(p);
	}

	@Override
	public City getCityAt(Position p) {
		return game.getCityAt(p);
	}

	@Override
	public Player getPlayerInTurn() {
		return game.getPlayerInTurn();
	}

	@Override
	public Player getWinner() {
		Player player = game.getWinner();
		System.out.println("Winner: " + player);
		return player;
	}

	@Override
	public int getAge() {
		return game.getAge();
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		Unit unit = this.getUnitAt(from);
		System.out.println(unit.getOwner() + "moves " + unit.getTypeString() + " from " + "(" + from.getRow() + "," + from.getColumn() + ") to (" +to.getRow() + "," + to.getColumn() + ").");
		return game.moveUnit(from, to);
	}

	@Override
	public void endOfTurn() {
		System.out.println(this.getPlayerInTurn() + " ends turn.");
		game.endOfTurn();
	}

	@Override
	public void changeWorkForceFocusInCityAt(Position p, String balance) {
		System.out.println(this.getPlayerInTurn() + "changed work force focus in city at (" + p.getRow() + "," + p.getColumn() + ") to " + balance + ".");
		game.changeWorkForceFocusInCityAt(p, balance);
	}

	@Override
	public void changeProductionInCityAt(Position p, String unitType) {
		City city = this.getCityAt(p);
		System.out.println(this.getPlayerInTurn() + " changes production in city at " + "(" + p.getRow() + "," + p.getColumn() + ") to " + unitType);
		game.changeProductionInCityAt(p, unitType);
	}

	@Override
	public void performUnitActionAt(Position p) {
		System.out.println(this.getPlayerInTurn() + " " + this.getUnitAt(p).getTypeString() + " performed action at (" + p.getRow() + "," + p.getColumn() + ").");
		game.performUnitActionAt(p);
	}

	@Override
	public City[][] getAllCities() {
		return game.getAllCities();
	}

	@Override
	public void insertUnitAtPosition(Position p, Unit u) {
		game.insertUnitAtPosition(p, u);
	}

	@Override
	public void deleteUnitAtPosition(Position p) {
		game.deleteUnitAtPosition(p);
	}

	@Override
	public void insertCityAtPosition(Position p, Player player) {
		game.insertCityAtPosition(p, player);
	}

	@Override
	public HashMap<Player, Integer> getBattlesWon() {
		return game.getBattlesWon();
	}

	@Override
	public void incrementBattlesWon(Player player) {
		game.incrementBattlesWon(player);
	}

	@Override
	public int getRound() {
		return game.getRound();
	}
	

}
