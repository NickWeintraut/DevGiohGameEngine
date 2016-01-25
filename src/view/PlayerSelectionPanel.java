package view;

import java.awt.Font;

import javax.swing.JLabel;

import dggameComponents.DGPlayer;

public class PlayerSelectionPanel extends SelectablePanel {

	JLabel playerLabel;
	DGPlayer player;
	
	public PlayerSelectionPanel(DGPlayer player) {
		this.player = player;
		playerLabel = new JLabel(player.getName());
		Font playerFont = new Font("Verdana", Font.BOLD, 12);
		playerLabel.setFont(playerFont);
		add(playerLabel);
	}
	
	public DGPlayer getReference()
	{
		return this.player;
	}
	
	

}
