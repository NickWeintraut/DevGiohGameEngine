package base;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * StackManager has protocols for iterating through activatable instructions that allow simultaneous changes to be made in the same update tick
 * and abilities to chain before gameplay progresses.
 */
public class ActivationStackManager extends UpdaterGameObject {

	static final long serialVersionUID = 612;
	
	private InputManager input;

	private boolean isRunning;

	//is this update call at the top of the update hierarchy, or inside the initial update call?
	//important for triggeredEffects that trigger during phase actions and not state changes
	private boolean isTopUpdate;

	private List<ActivatableInstructions> passList;

	private LinkedList<ActivatableInstructions> yieldList;

	public ActivationStackManager() {
		super();
		this.input = Engine.getCurrentInputManager();
		isRunning = false;
		passList = new LinkedList<ActivatableInstructions>();
		yieldList = new LinkedList<ActivatableInstructions>();
		isTopUpdate = true;
	}
	
	public boolean hasActivatables()
	{
		if(passList.size() == 0 && yieldList.size() == 0)
			return false;
		else
			return true;
	}
	
	/*
	public void reorderYieldInstructions()
	{
		GameObject[] yieldArray = new GameObject[yieldList.size()];
		OrderInputRequest answeredRequest = (OrderInputRequest) input.ask(new OrderInputRequest(yieldList.toArray(yieldArray), "Please re-order these activation triggers in the order you wish them to activate."));
		GameObject[] orderArray = answeredRequest.getOutputOrder();
		LinkedList<ActivatableInstructions> newOrderList = new LinkedList<ActivatableInstructions>();
		for(int i = 0; i < orderArray.length; i++)
		{
			newOrderList.add((ActivatableInstructions) orderArray[i]);
		}
	}
	*/
	
	public boolean hasActivatables(Priority priority)
	{
		switch(priority)
		{
		case YIELD: if(yieldList.size() > 0)
		{
			return true;
		}
		else
			return false;
		case PASS: if(passList.size() > 0)
		{
			return true;
		}
		else
			return false;
		default:
			return false;
		}
	}
	
	public boolean getIsRunning()
	{
		return isRunning;
	}
	
	public ActivatableInstructions getTopYieldInstruction()
	{
		return yieldList.removeFirst();
	}
	
	public boolean isTopUpdate()
	{
		return isTopUpdate;
	}
	
	public void runStack()
	{
		//beginning
		isRunning = true;
		isTopUpdate = true;
		
		int yieldSize = 1;
		
		while(hasActivatables())
		{
			if(hasActivatables(Priority.PASS))
			{
				Iterator<ActivatableInstructions> it = passList.iterator();
				while(it.hasNext())
				{
					it.next().performInstructions();
					it.remove();
				}
			}
			else
			{
				if(yieldList.size() > yieldSize)
				{
					//reorderYieldInstructions();
				}
				getTopYieldInstruction().performInstructions();
				yieldSize --;
			}
			isTopUpdate = false;
			Engine.nextTopUpdateID();
			updateUpdatables();
		}
		
		isTopUpdate = true;
		
		//end
		isRunning = false;
	}
	
	public void receiveActivatable(Activatable activatable, Priority priority, boolean activate, Player source)
	{
		switch(priority)
		{
		case YIELD: yieldList.add(new ActivatableInstructions(activatable, activate, source));
		break;
		case PASS: passList.add(new ActivatableInstructions(activatable, activate, source));
		break;
		default: passList.add(new ActivatableInstructions(activatable, activate, source));
		break;
		}
	}
}