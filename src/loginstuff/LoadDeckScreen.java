package loginstuff;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
/*
 * This class handles the JPanel buttons as if it was a Button Group.
 * So its radio buttons, but for JPanels!
 * @author Olivia Lam
 * */
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoadDeckScreen extends JPanel{
	
	private JLabel fileName;

	private JPanel loadPanel;
	private JPanel revertPanel;
	
	private JButton confirm;
	private JPanel buttons;
	private boolean real;
	private JTextField deckFileName;
	
	private DeckList deck;
	
	public LoadDeckScreen()
	{
		
		setLayout(new GridLayout(2,1));
		
		loadPanel = new JPanel();
		loadPanel.setLayout(new GridLayout(2,1));
		
		buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		confirm = new JButton("Confirm");
		confirm.addActionListener(new confirmListener());
		buttons.add(confirm, BorderLayout.WEST);
		deckFileName = new JTextField();
		
		loadPanel.add(deckFileName);
		loadPanel.add(buttons);
		
		revertPanel = new JPanel();
		revertPanel.setLayout(new BorderLayout());
		JLabel hasDeckLabel = new JLabel("Deck loaded!");
		JButton revertButton = new JButton("Revert");
		revertButton.addActionListener(new revertListener());
		revertPanel.add(hasDeckLabel, BorderLayout.WEST);
		revertPanel.add(revertButton, BorderLayout.EAST);
		revertPanel.setVisible(false);
		
		real = false;
		
		add(loadPanel);
		add(revertPanel);
		
		setVisible(true);
	}
	
	public DeckList getDeckList()
	{
		return this.deck;
	}
	
	public boolean hasDeckList()
	{
		return (this.deck != null);
	}
	
	private class confirmListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try
			{
				deck = loadDeck(deckFileName.getText());
				revertPanel.setVisible(true);
				loadPanel.setVisible(false);
				
			} catch(FileNotFoundException e)
			{
				JOptionPane.showMessageDialog(null, "The given DeckList was not found!");
			}
		}
	}
	
	private class revertListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			deck = null;
			revertPanel.setVisible(false);
			loadPanel.setVisible(true);
			
		}
	}
	
	public DeckList loadDeck(String filename) throws FileNotFoundException
	{
		return DeckList.deserialize(filename);
	}
	
	private class exit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
			
		}
		
	}
	
	public void setReal(boolean real)
	{
		this.real = real;
	}
	
	public boolean isReal()
	{
		return this.real;
	}

	public void setVisibility(boolean show)
	{
		setVisible(show);
	}

	
}