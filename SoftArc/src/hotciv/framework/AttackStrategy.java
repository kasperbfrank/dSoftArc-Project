package hotciv.framework;

public interface AttackStrategy {
	public boolean attack(Game game, Position attacker, Position defender);
}
