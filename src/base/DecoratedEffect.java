package base;

/*
 * Decorated effects are effects that know what priority they will request in the activation stack;
 */
abstract public class DecoratedEffect extends Effect implements Updatable{
	
	static final long serialVersionUID = 355;
	
	protected Effect effect;
	protected Priority priority;

	public DecoratedEffect(Effect effect, Card source, Priority priority, String name) {
		super(source, name);
		this.effect = effect;
		this.isActive = true;
		this.priority = priority;
	}
	
	public abstract void update();

}
