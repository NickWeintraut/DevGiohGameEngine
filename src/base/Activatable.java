package base;


/*
 * Some things are activatable, most of the interfaces in the project aren't actually used for polymorphism or to couple to, but
 * are in the project and implemented just to let devs know what has what
 */
public interface Activatable {

	public void activate();
	
	public void deactivate();
	
	public boolean isActive();
	
}
