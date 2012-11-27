package hotciv.factories;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.HotCivFactory;
import hotciv.framework.WinnerStrategy;
import hotciv.framework.WorldLayoutStrategy;
import hotciv.variants.AlphaActionStrategy;
import hotciv.variants.AlphaAgingStrategy;
import hotciv.variants.AlphaWorldLayoutStrategy;
import hotciv.variants.BetaWinnerStrategy;
import hotciv.variants.EpsilonWinnerStrategy;
import hotciv.variants.ZetaWinnerStrategy;

public class ZetaCivFactory implements HotCivFactory {

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new ZetaWinnerStrategy(new BetaWinnerStrategy(), new EpsilonWinnerStrategy());
	}

	@Override
	public WorldLayoutStrategy createWorldLayoutStrategy() {
		return new AlphaWorldLayoutStrategy();
	}

	@Override
	public ActionStrategy createActionStrategy() {
		return new AlphaActionStrategy();
	}

	@Override
	public AgingStrategy createAgingStrategy() {
		return new AlphaAgingStrategy();
	}

}
