package base;

/*
 * Stores information about one movement of a card, used in generating game states (in case conditions care about where cards are moving)
 */
public class CardMovement {
	
	private Card cardMoved;
	private CardZone beginningZone;
	private CardZone endZone;

	public CardMovement(Card cardMoved, CardZone beginningZone, CardZone endZone) {
		this.cardMoved = cardMoved;
		this.beginningZone = beginningZone;
		this.endZone = endZone;
	}

	public Card getCardMoved() {
		return cardMoved;
	}

	public CardZone getBeginningZone() {
		return beginningZone;
	}

	public CardZone getEndZone() {
		return endZone;
	}

}
