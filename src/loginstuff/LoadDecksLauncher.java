package loginstuff;

import java.awt.Frame;
import java.util.LinkedList;

import base.Player;
import view.DGGameView;
import dggameComponents.DGGame;
import dggameComponents.DGPlayer;

public class LoadDecksLauncher {

	public LoadDecksLauncher() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		MenuGUI gui = new MenuGUI();
		
		while(gui.getDeckLists() == null)
		{
			try
			{
				Object object = new Object();
				object.wait(1000);
			} catch(Exception e)
			{
				
			}
		}
		
		LinkedList<DeckList> deckLists = gui.getDeckLists();
		
		LinkedList<DGPlayer> dgPlayers = new LinkedList<DGPlayer>();
		int i = 1;
		for(DeckList deckList : deckLists)
		{
			dgPlayers.add(new DGPlayer(deckList.toString(), deckList.getCards()));
			i++;
		}
		LinkedList<Player> genericPlayers = new LinkedList<Player>();
		for(DGPlayer player : dgPlayers)
		{
			genericPlayers.add(player);
		}
		
		DGGameView frame = new DGGameView(dgPlayers);
		frame.setVisible(true);
		frame.pack();
		
		DGGame game = new DGGame(genericPlayers, frame);
		
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		game.start();
		
		frame.setExtendedState(Frame.NORMAL);
		
		/*LinkedList<DGPlayer> players = new LinkedList<DGPlayer>();
		DeckList brendan = DeckList.deserialize("/Users/Nick/Desktop/Decks/Brendan.ser");
		DeckList nick = DeckList.deserialize("/Users/Nick/Desktop/Decks/Nick.ser");
		DeckList olivia = DeckList.deserialize("/Users/Nick/Desktop/Decks/Eric.ser");
		DeckList eric = DeckList.deserialize("/Users/Nick/Desktop/Decks/Olivia.ser");
		
		players.add(new DGPlayer("Brendan", brendan.getCards()));
		players.add(new DGPlayer("Nick", nick.getCards()));
		players.add(new DGPlayer("Olivia", olivia.getCards()));
		players.add(new DGPlayer("Eric", eric.getCards()));
		
		LinkedList<Player> genericPlayers = new LinkedList<Player>();
		for(DGPlayer player : players)
		{
			genericPlayers.add(player);
		}
		
		DGGameView frame = new DGGameView(players);
		
		DGGame game = new DGGame(genericPlayers, frame);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		game.start();
		
		frame.setExtendedState(Frame.NORMAL); */

	}

}
