package smallgoalchooser.listhandler;

import java.util.ArrayList;
import java.util.Random;

public class ListHandler {
	private String[] goalsToDo;
	private ArrayList<String> modifiableGoalsToDo;
	private ArrayList<String> modifiableGoalsDone;
	private String currentGoal;
	private int currentGoalIndex;
	
	public void loadListArray(String unsplitList) {
		goalsToDo = unsplitList.split(",\n");
		for (String goal: goalsToDo) {
			System.out.println(goal);
			modifiableGoalsToDo.add(goal);
		}
	}
	
	public String chooseCurrentGoal() {
		currentGoalIndex = new Random().nextInt(modifiableGoalsToDo.size());
		currentGoal = modifiableGoalsToDo.get(currentGoalIndex);
		modifiableGoalsToDo.remove(currentGoalIndex);
		return currentGoal;
	}

}
