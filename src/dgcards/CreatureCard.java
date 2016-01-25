package dgcards;

import java.util.ArrayList;

import dgconditions.CreatureCondition;
import dgeffects.CreatureDeathCheckEffect;
import dggameComponents.DGPlayer;
import base.Condition;
import base.Effect;
import base.PresetCondition;
import damage.*;

public class CreatureCard extends DGCard implements Damagable, DamageSource, Enhanceable {
	static final long serialVersionUID = 3;
	
	private final int START_HEALTH;
	private final int START_ATTACK;
	
	private int attackPower;
	private int health;
	private Element element;
	private boolean isRare;
	private ArrayList<EnhancementCard> enhancements;
	
	public CreatureCard(String name, int attackPower, int health, Element element, boolean isRare){
		super(name);
		START_HEALTH = health;
		START_ATTACK = attackPower;
		this.attackPower = START_ATTACK;
		this.health = START_HEALTH;
		this.element = element;
		this.isRare = isRare;
		addEffect(new CreatureDeathCheckEffect(this));
		enhancements = new ArrayList<EnhancementCard>();
	}
	
	public CreatureCard()
	{
		super();
		START_HEALTH = health;
		START_ATTACK = attackPower;
		addEffect(new CreatureDeathCheckEffect(this));
		enhancements = new ArrayList<EnhancementCard>();
	}
	
	public Condition findPlayCondition()
	{
		if(this.owner != null && this.owner instanceof DGPlayer)
		{
			return ((DGPlayer) owner).getPlayCreatureCondition();
		}
		else
			return new PresetCondition(false);
	}
	
	public int getAttackPower(){
		return attackPower;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setAttackPower(int attackPower){
		this.attackPower = attackPower;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public Element getElement(){
		return element;
	}
	
	public int getBaseHealth()
	{
		return START_HEALTH;
	}
	
	public void attack(Damagable d){
		d.takeDamage(getAttackPower(), this);
	}
	
	public void addEnhancement(EnhancementCard c){
		enhancements.add(c);
	}
	
	public void removeEnhancement(EnhancementCard c) {
		enhancements.remove(c);
	}
	
	public boolean canBeEnhanced()
	{
		return isRare();
	}
	
	public void enhance(int attack, int health, EnhancementCard e)
	{
		setAttackPower(this.attackPower + attack);
		setHealth(this.health + health);
		addEnhancement(e);
	}
	
	public ArrayList<EnhancementCard> getEnhancements(){
		return enhancements;
	}

	@Override
	public void takeDamage(int amount, DamageSource source) {
		if(getElement().isSuperiorTo(source.getElement())){
			setHealth(getHealth() - 2*amount);
		}
		else{
			setHealth(getHealth() - amount);
		}
		System.out.println(System.identityHashCode(this));
		for(Effect effect : effects)
		{
			if(effect instanceof CreatureDeathCheckEffect)
			{
				System.out.println(System.identityHashCode(((CreatureCondition)((CreatureDeathCheckEffect) effect).getCondition()).getCreature()));
			}
		}
		
	}
	
	public void destroy()
	{
		enhancements.clear();
		refreshStats();
		super.destroy();
	}
	
	public void refreshStats()
	{
		this.attackPower = START_ATTACK;
		this.health = START_HEALTH;
	}
	
	public boolean isRare(){
		return isRare;
	}
	
	
	
	
}
