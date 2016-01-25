package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import base.Card;
import base.Effect;
import dgcards.*;
public class CardPanel extends SelectablePanel{

	private JLabel name;
	private JLabel rare;
	private JLabel element;
	private JLabel attackPower;
	private JLabel health;
	
	private Card theCard;
	
	private static Font cardFont = new Font("Sans Serif", Font.PLAIN, 10);
	
	
	public CardPanel(Card c){
		
		theCard = c;
		name = new JLabel(c.getName());
		name.setFont(cardFont);
		setLayout(new GridLayout(6,1));
		if(c instanceof CreatureCard)
			instantiateCreatureCard((CreatureCard)c);
		if(c instanceof EnhancementCard)
			instantiateEnhancementCard((EnhancementCard)c);
		
		
		setBackground(Color.CYAN);
		setBorder(BorderFactory.createLineBorder(Color.RED));
		setVisible(true);
		
	}
	
	private void instantiateCreatureCard(CreatureCard c){
		rare = new JLabel("Rare");
		rare.setFont(cardFont);
		element = new JLabel(c.getElement().toString());
		element.setFont(cardFont);
		attackPower = new JLabel("AP: " + Integer.toString(c.getAttackPower()));
		attackPower.setFont(cardFont);
		health = new JLabel("Health: " + Integer.toString(c.getHealth()));
		health.setFont(cardFont);
		
		add(name);
		add(element);
		add(attackPower);
		add(health);
		if(c.isRare())
			add(rare);
		else
			add(new JLabel(""));
		try{
			for(Effect effect : c.getEffects())
			{
				if(!effect.toString().equals(""))
				{	
				JLabel effectLabel = new JLabel(effect.toString());
				effectLabel.setFont(cardFont);
				add(effectLabel);
				}
			}
		}catch(Exception e){
			add(new JLabel(" "));
		}
		
	}
	
	private void instantiateEnhancementCard(EnhancementCard c){
		attackPower = new JLabel("Adds " + c.getAttackBonus() + " AP" );
		attackPower.setFont(cardFont);
		health = new JLabel("Adds " + c.getHealthBonus() + " health" );
		health.setFont(cardFont);
		
		add(name);
		add(attackPower);
		add(health);
		add(new JLabel(" "));
		add(new JLabel(" "));
		add(new JLabel(" "));
	}
	
	public Card getCard(){
		return theCard;
	}
	
	public void refresh()
	{
		if(theCard instanceof CreatureCard)
		{
			attackPower.setText("AP: " + ((CreatureCard) theCard).getAttackPower());
			health.setText("Health: " + ((CreatureCard) theCard).getHealth());
		}
	}
	
	
	
}
