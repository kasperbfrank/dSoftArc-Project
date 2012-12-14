package hotciv.stub;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class ObserverStub implements GameObserver{

	public Position wcaPos;
	public Player turnEnds;
	public int turnEndsAge;
	public Position tileFocusPos;
	
	@Override
	public void worldChangedAt(Position pos) {
		this.wcaPos = pos;
	}

	@Override
	public void turnEnds(Player nextPlayer, int age) {
		this.turnEnds = nextPlayer;
		this.turnEndsAge = age;
	}

	@Override
	public void tileFocusChangedAt(Position position) {
		this.tileFocusPos = position;
	}
	
}
