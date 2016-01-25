package base;

/*
 * i am not sure why this is an exception, i believe there was some reason in that it allows the game to quit the current turn.
 * yeah thats it. not the best implementation but it got the job done amiright
 * oh wait actually i dont think this is used, i came up with a better solution in player order i think or wait maybe its still used in the turn class to stop iteration of the phases yeah maybe
 */
public class PlayerDeathException extends Exception {

	static final long serialVersionUID = 33;
	
	private Player deadPlayer;
	private Player deathMessage;
	
	public PlayerDeathException(Player player, String message) {
		super(message);
		this.deadPlayer = player;
	}
	
	public Player getPlayer()
	{
		return this.deadPlayer;
	}

}
