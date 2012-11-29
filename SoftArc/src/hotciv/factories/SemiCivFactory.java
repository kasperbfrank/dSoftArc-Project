package hotciv.factories;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackStrategy;
import hotciv.framework.HotCivFactory;
import hotciv.framework.WinnerStrategy;
import hotciv.framework.WorldLayoutStrategy;
import hotciv.variants.AlphaAgingStrategy;
import hotciv.variants.AlphaAttackStrategy;
import hotciv.variants.AlphaWinnerStrategy;
import hotciv.variants.AlphaWorldLayoutStrategy;
import hotciv.variants.BetaAgingStrategy;
import hotciv.variants.DeltaWorldLayoutStrategy;
import hotciv.variants.EpsilonAttackStrategy;
import hotciv.variants.EpsilonWinnerStrategy;
import hotciv.variants.GammaActionStrategy;

public class SemiCivFactory implements HotCivFactory {
	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new EpsilonWinnerStrategy();
	}

	@Override
	public WorldLayoutStrategy createWorldLayoutStrategy() {
		return new DeltaWorldLayoutStrategy();
	}

	@Override
	public ActionStrategy createActionStrategy() {
		return new GammaActionStrategy();
	}

	@Override
	public AgingStrategy createAgingStrategy() {
		return new BetaAgingStrategy();
	}
	
	@Override
	public AttackStrategy createAttackStrategy() {
		return new EpsilonAttackStrategy();
	}
}
