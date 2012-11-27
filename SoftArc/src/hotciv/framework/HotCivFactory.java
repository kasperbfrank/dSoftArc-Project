package hotciv.framework;

import java.util.Iterator;

public interface HotCivFactory {

	public WinnerStrategy createWinnerStrategy();
	
	public WorldLayoutStrategy createWorldLayoutStrategy();
	
	public ActionStrategy createActionStrategy();
	
	public AgingStrategy createAgingStrategy();
		
}
