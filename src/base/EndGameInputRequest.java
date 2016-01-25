package base;

/*
 * Input request that lets the ui know the game is over
 */
public class EndGameInputRequest extends InputRequest {

	public EndGameInputRequest(String message, Player owner) {
		super(message, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasAnswer() {
		// TODO Auto-generated method stub
		return false;
	}

}
