package hotciv.factories;

import java.util.ArrayList;
import java.util.Iterator;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackStrategy;
import hotciv.framework.HotCivFactory;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.framework.WorldLayoutStrategy;
import hotciv.variants.AlphaActionStrategy;
import hotciv.variants.AlphaAgingStrategy;
import hotciv.variants.AlphaAttackStrategy;
import hotciv.variants.AlphaWinnerStrategy;
import hotciv.variants.AlphaWorldLayoutStrategy;
import hotciv.variants.GammaActionStrategy;

public class GammaCivFactory implements HotCivFactory {

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new AlphaWinnerStrategy();
	}

	@Override
	public WorldLayoutStrategy createWorldLayoutStrategy() {
		return new AlphaWorldLayoutStrategy();
	}

	@Override
	public ActionStrategy createActionStrategy() {
		return new GammaActionStrategy();
	}

	@Override
	public AgingStrategy createAgingStrategy() {
		return new AlphaAgingStrategy();
	}
	
	@Override
	public AttackStrategy createAttackStrategy() {
		return new AlphaAttackStrategy();
	}

}
