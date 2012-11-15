package hotciv.variants;

import hotciv.framework.AgingStrategy;

public class BetaAgingStrategy implements AgingStrategy {

	@Override
	public int doAging(int age) {
		int gameAge = 0;
		
		if (age < -100) {
			gameAge = age + 100;
		} else if(age >= -100 && age < 50) {
			switch(age) {
			case -100:
				gameAge = -1;
				break;
			case -1:
				gameAge = 1;
				break;
			case 1:
				gameAge = 50;
				break;
			}
		} else if (age >= 50 && age < 1750) {
			gameAge = age + 50;
		} else if (age >= 1750 && age < 1900) {
			gameAge = age + 25;
		} else if (age >= 1900 && age < 1970) {
			gameAge = age + 5;
		} else if (age >= 1970) {
			gameAge = age + 1;
		}
		
		return gameAge;
	}

}
