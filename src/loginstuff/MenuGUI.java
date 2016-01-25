package loginstuff;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import view.DGGameView;
import dggameComponents.DGGame;
import dggameComponents.DGPlayer;
import base.Player;


public class MenuGUI extends JFrame{
	
	private JTabbedPane tabbedPane;
	
	private static final int PREFERRED_WIDTH = 500;
    private static final int PREFERRED_HEIGHT = 500;
    private static final Dimension PREFERRED_SIZE =
                            new Dimension(PREFERRED_WIDTH,PREFERRED_HEIGHT);
	
    private LoadDeckScreen tab;
    private LinkedList<LoadDeckScreen> deckScreens;
    private JButton playGame;
    private LinkedList<DeckList> decks;
    
    private JPanel stuff;
    
    
	private static final String PLAYER_TITLE ="Player ";
	
	public MenuGUI() 
	{
		setLayout(new BorderLayout());
		tabbedPane = new JTabbedPane();
		deckScreens = new LinkedList<LoadDeckScreen>();
		makeTabs(4); //this is connected to something that brings in an int and makes the amount of players needed.
		//not made yet
		playGame = new JButton("Start Game!");
		
		playGame.addActionListener(new startGame());
		final Container contentPane = getContentPane();
		
		contentPane.add(tabbedPane);
		
		add(playGame, BorderLayout.NORTH);
		
		pack();
		setSize(PREFERRED_SIZE);
		setVisible(true);
	}
	
	private class startGame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			LinkedList<DeckList> decks = new LinkedList<DeckList>();
			for(LoadDeckScreen deckScreen : deckScreens)
			{
				if(deckScreen.hasDeckList())
				{
					decks.add(deckScreen.getDeckList());
				}
			}
			if(decks.size() > 1)
			{
				setDecks(decks);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You must load at least two decklists! Don't just play with yourself.");
			}
			
			
		}
		
	}
	
	private void makeTabs(int amount)
	{
		for(int i=1; i<=amount; i++)
		{
			tab = new LoadDeckScreen();
			deckScreens.add(tab);
			tabbedPane.addTab(PLAYER_TITLE + i, tab);
			tabbedPane.setTabPlacement(JTabbedPane.TOP);
		
		}
	}
	
	public LinkedList<DeckList> getDeckLists()
	{
		return decks;
	}
	
	public void setDecks(LinkedList<DeckList> decks)
	{
		this.decks = decks;
	}
	
	
	
}
 