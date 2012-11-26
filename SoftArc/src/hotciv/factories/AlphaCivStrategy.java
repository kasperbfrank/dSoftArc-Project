package hotciv.factories;

import hotciv.framework.ActionStrategy;

import hotciv.framework.AgingStrategy;
import hotciv.framework.HotCivFactory;
import hotciv.framework.WinnerStrategy;
import hotciv.framework.WorldLayoutStrategy;

import hotciv.variants.*;

public class AlphaCivStrategy implements HotCivFactory {

	@Override
	public WinnerStrategy getWinnerStrategy() {
		return new AlphaWinnerStrategy();
	}

	@Override
	public WorldLayoutStrategy getWorldLayoutStrategy() {
		return new AlphaWorldLayoutStrategy();
	}

	@Override
	public ActionStrategy getActionStrategy() {
		return new AlphaActionStrategy();
	}

	@Override
	public AgingStrategy getAgingStrategy() {
		return new AlphaAgingStrategy();
	}

}
