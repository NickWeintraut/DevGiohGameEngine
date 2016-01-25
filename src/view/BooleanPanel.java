package view;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import base.Activatable;

public class BooleanPanel extends JPanel implements Activatable{

	private boolean answer;
	
	
	public BooleanPanel() {
		JRadioButton yes = new JRadioButton("yes");
		JRadioButton no = new JRadioButton("no");
		
		ButtonGroup buttons = new ButtonGroup();
		
		ActionListener toggle = new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == yes)
				{
					answer = true;
				}
				if(e.getSource() == no)
				{
					answer = false;
				}
			}
		};
		
		buttons.add(yes);
		buttons.add(no);
		
		yes.addActionListener(toggle);
		no.addActionListener(toggle);
		add(yes);
		add(no);
		deactivate();
		
	}
	
	public boolean isActive()
	{
		return this.isVisible();
	}
	
	public void activate(){
		this.setVisible(true);
	}
	
	public void deactivate(){
		this.setVisible(false);
	}
	
	public boolean getAnswer()
	{
		return answer;
	}

}
