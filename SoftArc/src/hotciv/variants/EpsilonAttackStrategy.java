package hotciv.variants;

import java.util.Random;

import hotciv.common.Utility;
import hotciv.framework.AttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class EpsilonAttackStrategy implements AttackStrategy {

	@Override
	public boolean attack(Game game, Position attacker, Position defender) {
		Unit a = game.getUnitAt(attacker);
		Unit d = game.getUnitAt(defender);
		
		Random rng = new Random();
		
		int aStrength = a.getAttackingStrength() + Utility.getFriendlySupport(game, attacker, a.getOwner()) + Utility.getTerrainFactor(game, attacker);
		int dStrength = d.getDefensiveStrength() + Utility.getFriendlySupport(game, defender, a.getOwner()) + Utility.getTerrainFactor(game, defender);
		
		//Calculate the winner, false if defender wins, true if attacker does.
		if (aStrength * (rng.nextInt(6) + 1) > dStrength * (rng.nextInt(6) + 1)) {
			game.incrementBattlesWon(a.getOwner());
			return true;
		}
		
		return false;
	}

}
