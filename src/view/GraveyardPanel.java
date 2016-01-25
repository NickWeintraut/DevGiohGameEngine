package view;

import java.awt.*;

import javax.swing.*;
import dgcardZones.DGCardZone;

public class GraveyardPanel extends JPanel {

	private DGCardZone graveyard;
	private JLabel numberOfCards;
	
	public GraveyardPanel(String description, DGCardZone graveyard){
		this.graveyard = graveyard;
		setLayout(new GridLayout(2,1));
		JLabel name = new JLabel(description);
		setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		setBackground(Color.GRAY);
		add(name);
		numberOfCards = new JLabel(Integer.toString(graveyard.getCards().size()) + " cards");
		add(numberOfCards);
		
		setVisible(true);
	}
	
	public void refreshSize()
	{
		numberOfCards.setText(graveyard.getCards().size() + " cards");
	}
	
	public DGCardZone getGraveyard(){
		return graveyard;
	}
}
