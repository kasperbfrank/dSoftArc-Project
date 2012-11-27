package hotciv.factories;

import java.util.ArrayList;
import java.util.Iterator;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.HotCivFactory;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.framework.WorldLayoutStrategy;
import hotciv.variants.AlphaActionStrategy;
import hotciv.variants.AlphaAgingStrategy;
import hotciv.variants.AlphaWinnerStrategy;
import hotciv.variants.AlphaWorldLayoutStrategy;
import hotciv.variants.DeltaWorldLayoutStrategy;

public class DeltaCivFactory implements HotCivFactory {

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new AlphaWinnerStrategy();
	}

	@Override
	public WorldLayoutStrategy createWorldLayoutStrategy() {
		return new DeltaWorldLayoutStrategy();
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
