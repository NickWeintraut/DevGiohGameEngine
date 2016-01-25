package view;
import java.awt.*;
import javax.swing.*;
import base.*;

public class PhasePanel extends JPanel {
	
	private JLabel displayPhase;
	private JLabel prompt;
	
	private Color bkgColor = new Color(255,228,255);
	private Color textColor = new Color(0,255,0);
	
	public PhasePanel(){
		setLayout(new GridLayout(2,1));
		
		// change it later so that the arraylist can have multiple phases other than the generic main 4.
		
		displayPhase = new JLabel("Inactive");
		prompt = new JLabel();
		setBackground(bkgColor);
		setForeground(textColor);
		
		setBorder(BorderFactory.createTitledBorder("Phase "));
		add(displayPhase);
		add(prompt);
		setVisible(true);
	}
	
	public void setPhaseName(Phase phase) {
		try{
			displayPhase.setText(phase.toString());
		}catch(NullPointerException e){
			displayPhase.setText("Inactive");
		}
	}
	
	public void setPrompt(String message){
		prompt.setText(message);
	}
	
}