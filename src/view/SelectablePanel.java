package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public abstract class SelectablePanel extends JPanel{

	private boolean isSelected;
	private boolean selectable;
	
	private static final Color unselectableColor = Color.GRAY;
	private static final Color selectableColor = Color.GREEN;
	private static final Color selectedColor = Color.ORANGE;
	
	public SelectablePanel(){
		isSelected = false;
		selectable = false;
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(!(isSelectable())){
				}
				else if(isSelected()){
					setSelected(false);
				}
				else{
					setSelected(true);
				}
			}
		});
	}
	
	public boolean isSelectable(){
		return selectable;
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public void setSelectable(boolean selectable){
		this.selectable = selectable;
		if(selectable == false){
			setSelected(false);
		}
		else
			updateBorder();
	}
	
	private void updateBorder()
	{
		if(!(isSelectable())){
			setBorder(BorderFactory.createLineBorder(unselectableColor, 5));
		}
		else if(!isSelected()){
			setBorder(BorderFactory.createLineBorder(selectableColor, 5));
		}
		else{
			setBorder(BorderFactory.createLineBorder(selectedColor, 5));
		}
	}
	
	private void setSelected(boolean isSelected){
		this.isSelected = isSelected;
		updateBorder();
	}
	
}
