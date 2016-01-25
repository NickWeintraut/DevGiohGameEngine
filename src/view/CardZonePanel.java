package view;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.awt.FlowLayout;
import java.util.ArrayList;

import base.*;

import javax.swing.*;

import view.CardPanel;


/*
 * A panel that contains CardPanels the same way CardZones contain Cards
 */
public class CardZonePanel extends JPanel {
	
	CardZone cardzone;
	String name;
	
	private int selectCount;
	private ArrayList<CardPanel> cardPanels;
	
	/*
	 * Constructor that takes an array of CardPanels and a name.
	 * Uses the name to make a JLabel with the panel's name on it and adds each CardPanel
	 * in the array to itself.
	 * 
	 * @param cardPanels, an array of CardPanels
	 * @param name the name of the panel
	 */
	public CardZonePanel(CardZone cardzone, String name){
		this.cardzone = cardzone;
		this.name = name;
		selectCount = 0;
		cardPanels = new ArrayList<CardPanel>();
		BorderFactory.createLineBorder(Color.BLACK);
		
		setLayout(new FlowLayout(FlowLayout.LEADING));
		
			for(Card c: cardzone.getCards()){
				addCardPanel(new CardPanel(c));
				cardPanels.add(new CardPanel(c));
			}
		
		setVisible(true);
	}
	
	public boolean containsCard(Card c){
		if(cardzone.hasCard(c))
			return true;
		else
			return false;
	}
	
	public CardPanel getCardPanelForCard(Card card)
	{
		CardPanel found = null;
		Iterator<CardPanel> panelIt = cardPanels.iterator();
		while(panelIt.hasNext() && found == null)
		{
			CardPanel panel = panelIt.next();
			if(panel.getCard().equals(card))
				found = panel;
		}
		return found;
	}
	
	
	public List<CardPanel> getCardPanels(List<Card> cards)
	{
		List<CardPanel> cardPanels = new LinkedList<CardPanel>();
		for(CardPanel cardPanel : this.cardPanels)
		{
			//System.out.println(cardPanel.getCard().getName());
			if(cards.contains(cardPanel.getCard()))
					cardPanels.add(cardPanel);
		}
		return cardPanels;
	}
	
	public ArrayList<CardPanel> getCardPanels(){
		return cardPanels;
	}
	
	public void addCardPanel(CardPanel panel) {
		cardPanels.add(panel);
		add(panel);
	}
	
	public void removeCardPanel(CardPanel panel){
		cardPanels.remove(panel);
		remove(panel);
	}
	
	public void refreshCardPanels()
	{
		for(CardPanel panel : cardPanels)
		{
			panel.refresh();
		}
	}
	
	public CardZone getCardZone(){
		return cardzone;
	}
	
	public void makeAllUnSelectable()
	{
		for(CardPanel panel : cardPanels)
		{
			panel.setSelectable(false);
		}
	}

}
