package hotciv.variants;

import hotciv.framework.AgingStrategy;

public class BetaAgingStrategy implements AgingStrategy {

	@Override
	public int doAging(int age) {
		
		return age + 100;
	}

}
