package base;

//similar to CardMovement, but for gamevalues
public class GameValueChange {

	private GameValue reference;
	private int amount;
	
	
	public GameValueChange(GameValue reference, int amount) {
		this.reference = reference;
		this.amount = amount;
	}


	public GameValue getReference() {
		return reference;
	}


	public int getAmount() {
		return amount;
	}

}
