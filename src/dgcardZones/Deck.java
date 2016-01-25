package dgcardZones;

import java.util.*;

import base.Player;
import base.Card;


/**
 * The Deck class is meant to represent a deck of cards. It can be shuffled and drawn from.
 * @author Eric M. Lynn!
 */
public class Deck extends DGCardZone {

	/**
	 * Deck requires a linkedlist of cards to be instantiated.
	 */
	public Deck(LinkedList<Card> cards) {
		super(cards);
	}
	
	public Deck(LinkedList<Card> cards, Player owner)
	{
		super(cards, owner);
	}
	
	/**
	 * Takes each card in the deck and swaps it's position with some random other card in the
	 * deck for the sake of randomizing the positions of each card.
	 * 
	 * ghetto af - Eric
	 */
	public void shuffle() {
		Random gen = new Random();
		ArrayList<Card> shuffleCards = new ArrayList<Card>();
		for(Card card : cards)
		{
			shuffleCards.add(card);
		}
		for(int i = 0; i < shuffleCards.size(); i++) {
			Card card1 = shuffleCards.get(i);
			int randomI = gen.nextInt(shuffleCards.size() -1);
			Card card2 = shuffleCards.get(randomI);
			
			Card temp = card1;
			card1 = card2;
			card2 = temp;
			
			shuffleCards.set(i, card1);
			shuffleCards.set(randomI, card2);
		}
		cards.clear();
		for(Card card : shuffleCards)
		{
			cards.add(card);
		}
		
	}
	
	/**
	 * Draws (returns) the first card in the deck.
	 * @returns the first card in the deck.
	 */
	public Card draw(){
		Card c = cards.getFirst();
		cards.removeFirst();
		return c;
	}
	
	/*
	 * Returns the specific card passed in to be drawn
	 * @param c the card to be drawn
	 * @returns the specified card or null if the card is not in the deck
	 */
	public Card draw(Card c){
		if(cards.contains(c)){
			Card returnCard = c;
			cards.remove(c);
			return returnCard;
		}
		else
			return null;
	}
	
	/*
	 * takes the first five cards in the deck and uses them to instantiate and return a Hand
	 * @returns A hand instantiated with the first five cards in the deck
	 */
	public LinkedList<Card> drawMultiple(int amount){
		LinkedList<Card> hand = new LinkedList<>();
		for(int i = 0; i<amount; i++){
			hand.add(draw());
		}
		return hand;
	}
	
	public String toString()
	{
		return "Deck";
	}

}
