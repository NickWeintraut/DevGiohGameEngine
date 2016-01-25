package dgcards;

public interface Enhanceable {

	public boolean canBeEnhanced();
	
	public void enhance(int attack, int health, EnhancementCard e);
}
