package view;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

import base.Phase;
import dggameComponents.DGPlayer;

public class PlayerPanel extends JPanel{

	private JLabel healthLabel;
	private PlayerSelectionPanel playerName;
	private DeckPanel deck;
	private GraveyardPanel graveyard;
	private CardZonePanel hand;
	private CardZonePanel field;
	private PhasePanel phasePanel;
	private List<CardZonePanel> selectableZonePanels;
	
	public PlayerPanel(DGPlayer player, boolean isTop){
		
		selectableZonePanels = new LinkedList<CardZonePanel>();
		
		deck = new DeckPanel("Deck",player.getDeck());
		
		graveyard = new GraveyardPanel("Graveyard",player.getGraveyard());
		
		JPanel deckGraveBox = new JPanel();
		deckGraveBox.setLayout(new BorderLayout());
		deckGraveBox.add(deck, BorderLayout.CENTER);
		deckGraveBox.add(graveyard, BorderLayout.SOUTH);
		deckGraveBox.setBackground(new Color(0.6f, 0.2f, 0.2f));
		
		hand = new CardZonePanel(player.getHand(), "Hand: ");
		hand.setBackground(new Color(0.2f, 0.2f, .5f));
		selectableZonePanels.add(hand);
		
		field = new CardZonePanel(player.getField(), "Cards played: ");
		field.setBackground(new Color(0.2f, 0.5f, 0.2f));
		selectableZonePanels.add(field);
		System.out.println(field.getCardZone().toString());
		
		JPanel namePhasePanel = new JPanel();
		namePhasePanel.setLayout(new BorderLayout());
		
		playerName = new PlayerSelectionPanel(player);
		
		phasePanel = new PhasePanel();
		phasePanel.setBorder(BorderFactory.createLineBorder(Color.PINK));
		JPanel phase = new JPanel();
		phase.setLayout(new BorderLayout());
		phase.add(phasePanel, BorderLayout.WEST);
		add(phase, BorderLayout.EAST);
		
		namePhasePanel.add(playerName, BorderLayout.WEST);
		namePhasePanel.add(phasePanel, BorderLayout.EAST);
		
		JLabel handLabel = new JLabel("Hand: ");
		healthLabel = new JLabel("Health points: " + Integer.toString(player.getRemainingLifePoints()));
		deckGraveBox.add(healthLabel, BorderLayout.NORTH);
		
		hand.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JPanel handPanel = new JPanel();
		handPanel.setLayout(new BorderLayout());
		handPanel.add(handLabel, BorderLayout.WEST);
		handPanel.add(hand, BorderLayout.CENTER);
		handPanel.setBackground(new Color(0.4f,0.2f,0.5f));
		
		field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JPanel playPanel = new JPanel();
		playPanel.setLayout(new BorderLayout());
		JLabel cardsPlayedLabel = new JLabel("Cards played: ");
		cardsPlayedLabel.setForeground(new Color(0.8f,0.8f,0.2f));
		cardsPlayedLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		
		playPanel.add(cardsPlayedLabel, BorderLayout.WEST);
		playPanel.add(field, BorderLayout.CENTER);
		playPanel.setBackground(new Color(0.1f, 0.2f, 0.1f));
		
		setLayout(new BorderLayout());
		
		if(isTop){
			add(deckGraveBox, BorderLayout.WEST);
			add(playPanel, BorderLayout.SOUTH);
			add(handPanel, BorderLayout.CENTER);
			add(namePhasePanel, BorderLayout.NORTH);
		}
		else{
			add(deckGraveBox,BorderLayout.WEST);
			add(namePhasePanel, BorderLayout.NORTH);
			add(playPanel, BorderLayout.CENTER);
			add(handPanel, BorderLayout.SOUTH);
		}
		
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.setBackground(new Color(0.1f, 0.1f, 0.1f));
		setVisible(true);
	}
	
	public void setPhase(Phase phase)
	{
		if(phase == null && phasePanel.isVisible())
		{
			phasePanel.setVisible(false);
		}
		phasePanel.setPhaseName(phase);
		if(phase != null && !phasePanel.isVisible())
		{
			phasePanel.setVisible(true);
		}
	}
	
	public DeckPanel getDeckPanel(){
		return deck;
	}
	
	public GraveyardPanel getGraveyardPanel()
	{
		return graveyard;
	}
	
	public CardZonePanel getHandPanel(){
		return hand;
	}
	
	public CardZonePanel getFieldPanel(){
		return field;
	}
	
	public void setHandVisible(boolean visible){
		hand.setVisible(visible);
	}
	
	public DGPlayer getReference()
	{
		return this.playerName.getReference();
	}
	
	public PlayerSelectionPanel getPlayerSelectPanel()
	{
		return this.playerName;
	}
	
	public void setLife(int amount)
	{
		healthLabel.setText("" + amount);
	}
	
	
	public void makeAllUnSelectable()
	{
		for(CardZonePanel panel : selectableZonePanels)
		{
			panel.makeAllUnSelectable();
		}
		playerName.setSelectable(false);
	}	
}
