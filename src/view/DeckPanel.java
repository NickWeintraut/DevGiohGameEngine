package view;

import java.awt.Color;
import java.awt.GridLayout;
import dgcardZones.Deck;
import javax.swing.*;

/*
 * DeckPanel is the GUI componenet to represent a Deck object.
 * Ohter subclasses of CardZone use CardZonePanel, decks are different because it's
 * multiple cards that are stacked on top of each other instead of all being layed
 * out in front of the player. So Decks get their own type of panel.
 * 
 * @author Eric M. Lynn!
 */
public class DeckPanel extends JPanel{

	private Deck theDeck;
	private JLabel numberOfCards;
	
	/*
	 * Instantiates the panel, giving it a border and a JLabel describing the panel
	 * @param description a description of the panel
	 * @theDeck Deck object that the panel is to represent
	 */
	public DeckPanel(String description, Deck theDeck){
		setLayout(new GridLayout(5,1));
		this.theDeck = theDeck;
		JLabel deck = new JLabel(description);
		setBackground(Color.ORANGE);
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		add(deck);
		numberOfCards = new JLabel(Integer.toString(theDeck.getCards().size()) + " cards");
		add(numberOfCards);
		
		for(int i = 0; i<3; i++){
			add(new JLabel("      "));
		}
		
		setVisible(true);
	}
	
	public void refreshSize()
	{
		numberOfCards.setText(theDeck.getCards().size() + " cards");
	}
	
	public Deck getDeck(){
		return theDeck;
	}
}