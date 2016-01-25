package loginstuff;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class TabMenu extends JPanel{
	
	private LoadDeckScreen login;
	
	
	
	public TabMenu()
	{
		setLayout(new BorderLayout());
		
		login = new LoadDeckScreen();
		
		add(login, BorderLayout.CENTER);
		
		setVisible(true);
		
	}
	

}
