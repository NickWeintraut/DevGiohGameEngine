package dgcards;

import java.util.ArrayList;
import dggameComponents.DGPlayer;
import base.Condition;
import base.Effect;
import base.PresetCondition;

public class EnhancementCard extends DGCard {

	static final long serialVersionUID = 4;
	
	private int healthBonus;
	private int attackBonus;
	
	public EnhancementCard(String name, ArrayList<Effect> effects, int attackBonus, int healthBonus){
		super(name);
		this.healthBonus = healthBonus;
		this.attackBonus = attackBonus;
	}
	
	public void enhance(Enhanceable e) {
			e.enhance(attackBonus, healthBonus, this);
	}
	
	public Condition findPlayCondition()
	{
		if(this.owner != null && this.owner instanceof DGPlayer)
		{
			return ((DGPlayer) owner).getPlayEnhancementCondition();
		}
		else
			return new PresetCondition(false);
	}
	
	public int getHealthBonus(){
		return healthBonus;
	}
	
	public int getAttackBonus(){
		return attackBonus;
	}
	
	
	
}
