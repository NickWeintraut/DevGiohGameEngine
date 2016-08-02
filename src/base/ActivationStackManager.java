package base;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * StackManager has protocols for iterating through activatable instructions that allow simultaneous changes to be made in the same update tick
 * and abilities to chain before gameplay progresses.
 */
public class ActivationStackManager extends UpdaterGameObject {

	static final long serialVersionUID = 612;
	
	
	//private InputManager input; //Not currently making use of an InputManager

	private boolean isRunning;

	//is this update call at the top of the update hierarchy, or inside the initial update call?
	//important for triggeredEffects that trigger during phase actions and not state changes
	private boolean isTopUpdate;

	private List<ActivatableInstructions> passList; //List of instructions that want to operate before state changes are recorded

	private LinkedList<ActivatableInstructions> yieldList; //List of instructions which wait until state changes from the last effect are recorded

	/**
	 * Constructs an ActivationStackmanager that is currently not running and at the topUpdate
	 */
	public ActivationStackManager() {
		super();
		//this.input = Engine.getCurrentInputManager();
		isRunning = false;
		passList = new LinkedList<ActivatableInstructions>();
		yieldList = new LinkedList<ActivatableInstructions>();
		isTopUpdate = true;
	}
	
	/**
	 * Do we have any activatables available for operation?
	 * @return True if either passList or yieldList have elements
	 */
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
	
	/**
	 * Checks if a given list has Activatables
	 * @param priority Priority.YIELD or Priority.PASS
	 * @return True if the corresponding list has Activatables, False if empty
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
	
	/**
	 * 
	 * @return True if currently iterating through stack instructions
	 */
	public boolean getIsRunning()
	{
		return isRunning;
	}
	
	/**
	 * 
	 * @return ActivatableInstruction at beginning of yield list
	 */
	public ActivatableInstructions getTopYieldInstruction()
	{
		return yieldList.removeFirst();
	}
	
	/**
	 * 
	 * @return True if this update is the top of an update chain
	 */
	public boolean isTopUpdate()
	{
		return isTopUpdate;
	}
	
	/**
	 * Iterate through ActivatableInstructions according to priority and update game state
	 */
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
	
	/**
	 * Add an activatable to one of the instruction lists
	 * @param activatable The activatable to add
	 * @param priority The priority of this instruction
	 * @param activate True if this instruction should activate the activatable, false if should deactivate
	 * @param source The player this instruction is coming from
	 */
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