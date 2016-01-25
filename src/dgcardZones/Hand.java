package dgcardZones;

import java.util.LinkedList;
import base.Player;
import base.Card;

/*
 * Class to represent a player's hand of cards in a game.
 * 
 * @author Eric M. Lynn!
 */
public class Hand extends DGCardZone{
	
	public Hand(Player owner)
	{
		super(owner);
	}
	
	/*
	 * Calls the super constructor with a passed-in linkedlist of cards
	 */
	public Hand(LinkedList<Card> cards, Player owner) {
		super(cards, owner);
	}
	
	/*
	 * Returns a linkedlist of the cards in the hand that are able to be played
	 */
	public LinkedList<Card> playableCards(){
		LinkedList<Card> playableCards = new LinkedList<Card>();
		for(Card c: cards){
			if(c.isPlayable())
				playableCards.add(c);
		}
		
		return playableCards;
	}
	
	public String toString()
	{
		return "Hand";
	}

}
