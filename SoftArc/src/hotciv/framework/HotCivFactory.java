package hotciv.framework;

import java.util.Iterator;

public interface HotCivFactory {

	public WinnerStrategy getWinnerStrategy();
	
	public WorldLayoutStrategy getWorldLayoutStrategy();
	
	public ActionStrategy getActionStrategy();
	
	public AgingStrategy getAgingStrategy();
	
	public Iterator<Player> getPlayerIterator();
	
}
