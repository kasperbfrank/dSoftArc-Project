package hotciv.framework;

public interface HotCivFactory {

	public WinnerStrategy getWinnerStrategy();
	
	public WorldLayoutStrategy getWorldLayoutStrategy();
	
	public ActionStrategy getActionStrategy();
	
	public AgingStrategy getAgingStrategy();
	
}
