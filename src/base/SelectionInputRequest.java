package base;

import java.util.List;
//input request, yay
public class SelectionInputRequest extends InputRequest {
	
	List<GameObject> possibleSelections;
	List<GameObject> selected;
	int min;
	int max;

	public SelectionInputRequest(String message, Player owner, List<GameObject> possibleSelections, int min, int max) {
		super(message, owner);
		this.possibleSelections = possibleSelections;
		this.min = min;
		this.max = max;
	}

	public List<GameObject> getPossibleSelections()
	{
		return possibleSelections;
	}
	
	public void setSelections(List<GameObject> selected) throws InvalidSelectionException
	{
		if(possibleSelections.containsAll(selected))
		{
			System.out.println("It is in the possibilities");
			if(min <= selected.size() && selected.size() <= max)
				this.selected = selected;
			else
				throw new InvalidSelectionException(true, min, max);
		}
		else
			throw new InvalidSelectionException(false, min, max);
	}
	
	public List<GameObject> getSelections()
	{
		return this.selected;
	}
	
	@Override
	public boolean hasAnswer() {
		
		return (this.selected != null);
	}

}
