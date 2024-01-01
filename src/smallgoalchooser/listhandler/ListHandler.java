package smallgoalchooser.listhandler;

import java.util.ArrayList;
import java.util.Random;

public class ListHandler {
	private String[] goalsToDo;
	private ArrayList<String> modifiableGoalsToDo = new ArrayList<String>();
	private ArrayList<String> modifiableGoalsDone;
	private String currentGoal;
	private int currentGoalIndex;
	private String goalsToBeSaved;
	
	public String getCurrentGoal() {
		return currentGoal;
	}
	
	public void loadListArray(String unsplitList) {//this is called when the list is first made by the user
		goalsToDo = unsplitList.split(",\n");
		for (String goal: goalsToDo) {
			//System.out.println(goal);
			modifiableGoalsToDo.add(goal);
		}
	}
	
	public void loadFromSaveFile(String saveFile) {
		
	}
	
	public String chooseCurrentGoal() {
		currentGoalIndex = new Random().nextInt(modifiableGoalsToDo.size());
		currentGoal = modifiableGoalsToDo.get(currentGoalIndex);
		modifiableGoalsToDo.remove(currentGoalIndex);
		return currentGoal;
	}
	
	public String createListToBeSaved() {
		/*creates a formatted list to be saved,
		 *  consisting of goals done, current goal,
		 *  and goals to do*/
		return goalsToBeSaved;
	}

}
