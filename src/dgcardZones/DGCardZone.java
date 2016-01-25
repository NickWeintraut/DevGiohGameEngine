package dgcardZones;

import java.util.ArrayList;
import java.util.LinkedList;

import base.CardZone;
import base.Card;
import dgcards.*;
import base.Player;

/*
 * Subclass of CardZone- Meant to be a general card zone but with more methods/functionality,
 * also not abstract and able to be instantiated
 */
public class DGCardZone extends CardZone{
	
	/*
	 * no-param constructor- instantiates an empty linkedlist of cards through the 
	 * super constructor
	 */
	public DGCardZone(Player owner){
		super(owner);
	}

	/*
	 * calls the super constructor using the passed-in linkedlist of cards
	 * @param cards the linkedlist to be passed in
	 */
	public DGCardZone(LinkedList<Card> cards, Player owner){
		super(cards, owner);
	}
	
	public DGCardZone(LinkedList<Card> cards)
	{
		super(cards);
	}
	
	/*
	 * Returns an arraylist of all instances of the EnchantmentCard class
	 * @returns an arraylist of the enchantmentcards in the collection
	 */
	public ArrayList<EnhancementCard> getEnhancementCards(){
		ArrayList<EnhancementCard> merp = new ArrayList<EnhancementCard>();
		for(Card c: cards){
			if(c instanceof EnhancementCard)
				merp.add((EnhancementCard)c);
		}
		
		return merp;
		
	}
	
	/*
	 * Returns an arraylist of all instances of the CreatureCard class
	 * @returns an arraylist of the creaturecards in the collection
	 */
	public ArrayList<CreatureCard> getCreatureCards(){
		ArrayList<CreatureCard> merp = new ArrayList<CreatureCard>();
		for(Card c: cards){
			if(c instanceof CreatureCard)
				merp.add((CreatureCard)c);
		}
		
		return merp;
	}
	
	public String toString()
	{
		return "DGCardZone";
	}
}
