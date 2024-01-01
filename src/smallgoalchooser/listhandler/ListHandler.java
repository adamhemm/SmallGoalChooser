package smallgoalchooser.listhandler;

import java.util.ArrayList;
import java.util.Random;

public class ListHandler {
	private String[] goalsToDo;
	private String[] goalsDone;
	private String[] splitSaveFile;
	private ArrayList<String> modifiableGoalsToDo = new ArrayList<String>();
	private ArrayList<String> modifiableGoalsDone = new ArrayList<String>();
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
		splitSaveFile = saveFile.split("=");
		currentGoal = splitSaveFile[0];
		if(splitSaveFile[1].trim().length() > 0) {
			goalsToDo = splitSaveFile[1].split("+");
			for (String goal: goalsToDo) {
				modifiableGoalsToDo.add(goal);
			}
		}
		if(splitSaveFile[2].trim().length() > 0) {
			goalsDone = splitSaveFile[2].split("+");
			for (String goal: goalsDone) {
				modifiableGoalsDone.add(goal);
			}
		}
	}
	
	public String chooseCurrentGoal() {
		currentGoalIndex = new Random().nextInt(modifiableGoalsToDo.size());
		currentGoal = modifiableGoalsToDo.get(currentGoalIndex);
		modifiableGoalsToDo.remove(currentGoalIndex);
		return currentGoal;
	}
	
	public void currentGoalCompleted() {
		modifiableGoalsDone.add(currentGoal);
	}
	
	public String createListToBeSaved() {
		/*creates a formatted list to be saved,
		 *  consisting of goals done, current goal,
		 *  and goals to do*/
		goalsToBeSaved = currentGoal;
		goalsToBeSaved = goalsToBeSaved + " = ";
		if(!modifiableGoalsToDo.isEmpty()) {
			for(int i = 0; i<modifiableGoalsToDo.size(); i++) {
				if((i+1)== modifiableGoalsToDo.size()) {
					goalsToBeSaved = goalsToBeSaved + modifiableGoalsToDo.get(i);
				}
				else {
					goalsToBeSaved = goalsToBeSaved + modifiableGoalsToDo.get(i) + "+";
				}
			}
		}
		goalsToBeSaved = goalsToBeSaved + " = ";
		if(!modifiableGoalsDone.isEmpty()) {
			for(int i = 0; i<modifiableGoalsDone.size(); i++) {
				if((i+1)== modifiableGoalsDone.size()) {
					goalsToBeSaved = goalsToBeSaved + modifiableGoalsDone.get(i);
				}
				else {
					goalsToBeSaved = goalsToBeSaved + modifiableGoalsDone.get(i) + "+";
				}
			}
		}
		return goalsToBeSaved;
	}

}
