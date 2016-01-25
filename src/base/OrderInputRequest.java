package base;

//basic input request that asks to order a selection of gameobjects, meant for use in activation stack if the code that
//asks for ordering of triggers is uncommented and used, not implemented in UI due to time constraints though
public class OrderInputRequest extends InputRequest {
	
	private GameObject[] inputOrder;
	private GameObject[] outputOrder;
	
	public OrderInputRequest(GameObject[] inputOrder, String message, Player owner) {
		super(message, owner);
		this.inputOrder = inputOrder;
	}
	
	
	public GameObject[] getInputOrder()
	{
		return inputOrder;
	}
	
	public void setOutputOrder(GameObject[] outputOrder)
	{
		this.outputOrder = outputOrder;
	}
	
	public GameObject[] getOutputOrder()
	{
		return outputOrder;
	}
	
	public boolean hasAnswer()
	{
		if(outputOrder != null)
			return true;
		else
			return false;
	}

}
