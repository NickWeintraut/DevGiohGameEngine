package view;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

import base.Activatable;

public class ConfirmPanel extends JPanel implements Activatable{
	
	private JLabel someText;
	private JButton confirmButton;
	private boolean isConfirmed;
	private BooleanPanel boolPanel;
	private DGGameView gameViewController;
	
	public ConfirmPanel(String text, DGGameView viewController){
		isConfirmed = false;
		someText = new JLabel(text);
		confirmButton = new JButton("Confirm");
		this.gameViewController = viewController;
		boolPanel = new BooleanPanel();
		
		setLayout(new FlowLayout());
		add(someText);
		add(boolPanel);
		add(confirmButton);
		
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				isConfirmed = true;
				
			}
		});
		confirmButton.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e)
			{
					
			}
			
			public void keyTyped(KeyEvent e)
			{
				
			}
			
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					isConfirmed = true;
				}
			}
		}); 
		deactivate();
	}
	
	public JButton getConfirmButton()
	{
		return confirmButton;
	}
	
	public void activate()
	{
		setVisible(true);
		isConfirmed = false;
	}
	
	public void deactivate()
	{
		setVisible(false);
		isConfirmed = false;
	}
	
	public boolean isActive()
	{
		return isConfirmed;
	}
	
	public void setMessageText(String message)
	{
		someText.setText(message);
	}
	
	public BooleanPanel getBoolPanel()
	{
		return this.boolPanel;
	}
	
	public boolean isConfirmed(){
		return isConfirmed;
	}
	
	public void setText(String someText){
		this.someText.setText(someText);
	}
}