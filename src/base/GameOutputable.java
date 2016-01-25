package base;

//the game engines functions by outputing data to one object that can both take input and be updated with the gamestate
public interface GameOutputable extends Updatable, InputResponder {

	public void setGame(Game game);
	
}
