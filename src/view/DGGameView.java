package view;



import javax.swing.*;

import dgcards.CreatureCard;
import dggameComponents.DGPlayer;
import dginput.CreatureSelectionRequest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import base.CardMovement;
import base.BooleanInputRequest;
import base.Card;
import base.CardSelectionRequest;
import base.CardZone;
import base.EndGameInputRequest;
import base.Engine;
import base.Game;
import base.GameOutputable;
import base.InputRequest;
import base.InvalidSelectionException;
import base.Player;
import base.PlayerSelectionRequest;
import base.StateChangeManager;

import java.awt.*;
import java.util.ArrayList;

public class DGGameView extends JFrame implements GameOutputable{
	
	List<PlayerPanel> players;
	ConfirmPanel confirmPanel;
	private JPanel playersView;
	boolean inputConfirmed;
	private Game game;
	
	public DGGameView(List<DGPlayer> players){
		LinkedList<PlayerPanel> playerPanels = new LinkedList<PlayerPanel>();
		for(DGPlayer player : players)
		{
			playerPanels.add(new PlayerPanel(player, false));
		}
		this.players = playerPanels;
		setTitle("Its time to duel!");
		setLayout(new BorderLayout());
		confirmPanel = new ConfirmPanel("", this);
		playersView = new JPanel();
		switch(players.size()){
			case 2:
				playersView.setLayout(new GridLayout(2,1));
				break;
			case 3:
			case 4:
				playersView.setLayout(new GridLayout(2,2));
		}
		for(PlayerPanel p: playerPanels) {
			playersView.add(p);
		}
		playersView.setVisible(true);
		playersView.setBackground(Color.BLACK);
		confirmPanel.setVisible(false);
		confirmPanel.setBackground(new Color(0.4f, 0.4f, 0.6f));
		add(playersView, BorderLayout.CENTER);
		add(confirmPanel, BorderLayout.SOUTH);
		pack();
		setVisible(true);
		/*JButton confirmButtonRef = confirmPanel.getConfirmButton();
		getRootPane().setDefaultButton(confirmButtonRef);
        confirmButtonRef.requestFocus();*/
	}
	
	public List<PlayerPanel> getPlayerPanels(){
		return players;
	}
	
	public void refreshPlayerPhases()
	{
		for(PlayerPanel pPanel : players)
		{
			if(pPanel.getReference().equals(Engine.getCurrentGame().getCurrentPlayer()))
				pPanel.setPhase(Engine.getCurrentGameState().getCurrentPhase());
			else
				pPanel.setPhase(null);
		}
	}
	
	public void update()
	{
		//System.out.println("UPDATING VIEW FOR UPDATE " + Engine.getCurrentTopUpdateID());
		refreshPlayers();
		refreshPlayerLifeTotals();
		refreshPlayerPhases();
		refreshHandsVisible();
		StateChangeManager changeManager = game.getCurrentStateChangeManager();
		HashSet<CardMovement> movements = changeManager.getCardMovement();
		for(CardMovement movement : movements)
		{
			CardZone beginningZone = movement.getBeginningZone();
			CardZone endZone = movement.getEndZone();
			Card card = movement.getCardMoved();
			
			//System.out.println(card.getName());
			CardPanel cardPanel = getCardPanelForCard(card);
			CardZonePanel beginZonePanel = getCardZonePanelForCardZone(beginningZone);
			CardZonePanel endZonePanel = getCardZonePanelForCardZone(endZone);
			if(cardPanel == null && beginZonePanel == null && endZonePanel == null)
			{
				//System.out.println("what is goin on");
				//do nothing
			}
			else if(beginZonePanel == null && endZonePanel == null)
			{
				//System.out.println("something went completely wrong, but...");
			}
			else if(cardPanel == null && endZonePanel == null)
			{
				//System.out.println("went from a zone we know of to zone we don't, but the card wasn't showing in the zone we know before. actually kind of impossible, but too late for us to do anything");
			}
			else if(cardPanel == null && beginZonePanel == null)
			{
				//System.out.println("came to a zone we know from an unknown zone!!!! looks like meat's back on the menu, boys!!!!!");
				//came to a zone we know from an unknown zone!!!! looks like meat's back on the menu, boys!!!!!
				endZonePanel.addCardPanel(new CardPanel(card));
			}
			else if(cardPanel == null)
			{
				//System.out.println("well, it should have been showing before, but I guess better late than never!");
				endZonePanel.addCardPanel(new CardPanel(card));
			}
			else if(beginZonePanel == null)
			{
				//System.out.println("this also shouldn't have happened, very weird.");
				endZonePanel.addCardPanel(cardPanel);
			}
			else if(endZonePanel == null)
			{
				//System.out.println("hey there");
				//the card is exiting the realm of displayed cardzones. Bon voyage!
				beginZonePanel.removeCardPanel(cardPanel);
			}
			else
			{
				//we're displaying every piece involved in this move!!!!! nice!!!
				beginZonePanel.removeCardPanel(cardPanel);
				endZonePanel.addCardPanel(cardPanel);
			}
		}
		refreshCardPanels();
		//ayayay java needs me to repaint otherwise it waits forever
		repaint();
	}
	
	public void refreshCardPanels()
	{
		for(PlayerPanel pPanel : players)
		{
			pPanel.getHandPanel().refreshCardPanels();
			pPanel.getFieldPanel().refreshCardPanels();
			pPanel.getDeckPanel().refreshSize();
			pPanel.getGraveyardPanel().refreshSize();
		}
	}
	
	public void setGame(Game game)
	{
		this.game = game;
	}
	
	public InputRequest takeInputRequest(InputRequest i)
	{
		makeAllUnSelectable();
		confirmPanel.setText(i.getOwner().getName() + "! " + i.getMessage());
		confirmPanel.activate();

		if(i instanceof CreatureSelectionRequest)
		{
			CreatureSelectionRequest request = (CreatureSelectionRequest) i;
			List<CardPanel> creaturePanels = getCreaturePanelsOnFields(request.getPossibleCreatures());
			for(CardPanel panel : creaturePanels)
			{
				panel.setSelectable(true);
			}
			Thread b = new Thread();
			synchronized(b) {
				while(!request.hasAnswer())
				{
					while(!confirmPanel.isConfirmed())
					{
						try {
							b.wait(250);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					List<CreatureCard> creatures = new LinkedList<CreatureCard>();
					for(CardPanel panel: creaturePanels)
					{
						if(panel.isSelected())
						{
							creatures.add((CreatureCard) panel.getCard());
						}
					}
					try
					{
						request.setCreatures(creatures);
					} catch(InvalidSelectionException e)
					{
						confirmPanel.setText("Invalid Selection: " + request.getMessage());
						confirmPanel.activate();
					}
				}
			}
		}
		else if(i instanceof PlayerSelectionRequest)
		{
			PlayerSelectionRequest request = (PlayerSelectionRequest) i;
			List<PlayerSelectionPanel> playerPanels = getPlayerSelectionPanels(request.getPossiblePlayers());
			for(PlayerSelectionPanel panel : playerPanels)
			{
				panel.setSelectable(true);
			}
			Thread b = new Thread();
			synchronized (b)
			{
				while(!request.hasAnswer())
				{
					while(!confirmPanel.isConfirmed())
					{
						try {
							b.wait(250);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					List<Player> players = new LinkedList<Player>();
					for(PlayerSelectionPanel panel: playerPanels)
					{
						if(panel.isSelected())
						{
							players.add((Player) panel.getReference());
						}
					}
					try
					{
						request.setSelectedPlayers(players);
					} catch(InvalidSelectionException e)
					{
						confirmPanel.setText("Invalid Selection: " + request.getMessage());
						confirmPanel.activate();
					}
				}
			}
			
		}
		else if(i instanceof CardSelectionRequest)
		{
			CardSelectionRequest request = (CardSelectionRequest) i;
			/*for(Card card : request.getPossibleCards())
			{
				//System.out.println(card.getName());
			}*/
			List<CardPanel> cardPanels = getCardPanelsInHands(request.getPossibleCards());
			for(CardPanel panel : cardPanels)
			{
				//System.out.println("Selectable!");
				panel.setSelectable(true);
			}
			Thread b = new Thread();
			synchronized(b) {
				while(!request.hasAnswer())
				{
					while(!confirmPanel.isConfirmed())
					{
						try {
							b.wait(250);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				//System.out.println(confirmPanel.isConfirmed());
					List<Card> cards = new LinkedList<Card>();
					for(CardPanel panel: cardPanels)
					{
						if(panel.isSelected())
						{
							//System.out.println("Hellooooo");
							//System.out.println(panel.getCard().getName());
							cards.add((Card) panel.getCard());
						}
					}
					try
					{
						//System.out.println("Selected Cards");
						//System.out.println(cards);
						//System.out.println("Possible Cards");
						//System.out.println(request.getPossibleCards());
						request.setSelectedCards(cards);
					} catch(InvalidSelectionException e)
					{
						confirmPanel.setText("Invalid Selection: " + request.getMessage());
						confirmPanel.activate();
					}
				}
			}
		}
		else if(i instanceof BooleanInputRequest)
		{
			confirmPanel.getBoolPanel().activate();
			BooleanInputRequest request = (BooleanInputRequest) i;
			Thread b = new Thread();
			synchronized(b) {
				while(!request.hasAnswer())
				{
					while(!confirmPanel.isConfirmed())
					{
						try {
							b.wait(250);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					request.setAnswer(confirmPanel.getBoolPanel().getAnswer());
				}
			}
			confirmPanel.getBoolPanel().deactivate();
		}
		else if(i instanceof EndGameInputRequest)
		{
			refreshPlayers();
			System.out.println("The game has ended");
		}
		
		makeAllUnSelectable();
		
		return i;
	}
	
	
	public List<CardPanel> getCreaturePanelsOnFields(List<CreatureCard> creatures)
	{
		List<CardPanel> creaturePanels = new LinkedList<CardPanel>();
		List<Card> cardSearch = new LinkedList<Card>();
		cardSearch.addAll(creatures);
		for(PlayerPanel pPanel : players)
		{
			creaturePanels.addAll(pPanel.getFieldPanel().getCardPanels(cardSearch));
		}
		return creaturePanels;
	}
	
	public List<CardPanel> getCardPanelsInHands(List<Card> cards)
	{
		List<CardPanel> cardPanels = new LinkedList<CardPanel>();
		for(PlayerPanel pPanel : players)
		{
			//System.out.println(pPanel.getHandPanel().getCardPanels(cards));
			cardPanels.addAll(pPanel.getHandPanel().getCardPanels(cards));
		}
		return cardPanels;
	}
	
	public List<PlayerSelectionPanel> getPlayerSelectionPanels(List<Player> players)
	{
		List<PlayerSelectionPanel> playerSelectionPanels = new LinkedList<PlayerSelectionPanel>();
		for(PlayerPanel pPanel: this.players)
		{
			if(players.contains(pPanel.getReference()))
				playerSelectionPanels.add(pPanel.getPlayerSelectPanel());
		}
		return playerSelectionPanels;
	}
	
	public CardZonePanel getCardZonePanelForCardZone(CardZone zone)
	{
		CardZonePanel found = null;
		Iterator<PlayerPanel> playerPanelIt = players.iterator();
		while(playerPanelIt.hasNext() && found == null)
		{
			PlayerPanel pPanel = playerPanelIt.next();
			CardZonePanel panel = pPanel.getHandPanel();
			if(panel.getCardZone().equals(zone))
				found = panel;
			else
			{
				panel = pPanel.getFieldPanel();
				if(panel.getCardZone().equals(zone))
					found = panel;
			}
		}
		return found;
	}
	
	public CardPanel getCardPanelForCard(Card card)
	{
		CardPanel found = null;
		Iterator<PlayerPanel> playerPanelIt = players.iterator();
		while(playerPanelIt.hasNext() && found == null)
		{
			PlayerPanel pPanel = playerPanelIt.next();
			CardZonePanel panel = pPanel.getHandPanel();
			found = panel.getCardPanelForCard(card);
			if(found == null)
			{
				panel = pPanel.getFieldPanel();
				found = panel.getCardPanelForCard(card);
			}
		}
		return found;
	}
	
	public void refreshPlayerLifeTotals()
	{
		for(PlayerPanel pPanel : players)
		{
			pPanel.setLife(pPanel.getReference().getRemainingLifePoints());
		}
	}
	
	public void refreshPlayers()
	{
		List<PlayerPanel> deadPlayerPanels = new LinkedList<PlayerPanel>();
		for(PlayerPanel pPanel : players)
		{
			if(!pPanel.getReference().isAlive())
				deadPlayerPanels.add(pPanel);
		}
		players.remove(deadPlayerPanels);
		for(PlayerPanel pPanel : deadPlayerPanels)
		{
			playersView.remove(pPanel);
		}
	}
	
	public void refreshHandsVisible()
	{
		for(PlayerPanel pPanel : players)
		{
			if(Engine.getCurrentGame().getCurrentPlayer().equals(pPanel.getReference()))
			{
				pPanel.setHandVisible(true);
			}
			else
				pPanel.setHandVisible(false);
		}
	}
	
	public void makeAllUnSelectable()
	{
		for(PlayerPanel pPanel : players)
		{
			pPanel.makeAllUnSelectable();
		}
	}

}
