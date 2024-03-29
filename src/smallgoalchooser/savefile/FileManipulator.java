package smallgoalchooser.savefile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class FileManipulator {
	
	private FileWriter writer;
	private FileReader reader;
	private boolean fileIsThere = true;
	private String goalListSaved = "";
	private String goalListToSave;
	
	public FileManipulator() throws IOException {
		try {
			reader = new FileReader("goal list.txt");
		}
		catch(FileNotFoundException fe) {
			fileIsThere = false;
			
		}
		if(fileIsThere) {
			int characterPosition;
			while ((characterPosition=reader.read())!=-1) {
				goalListSaved = goalListSaved + (char)characterPosition;
			}
			reader.close();
		}
	}
	
	public void saveGoalList(String goalList) throws IOException {
		deleteGoalListFile();
		goalListToSave = goalList;
		writer = new FileWriter("goal list.txt");
		for (int i = 0; i < goalListToSave.length(); i++) {
			writer.write(goalListToSave.charAt(i));
		}
		writer.close();
	}
	
	public String getGoalListSaved() {
		return goalListSaved;
	}
	
	public boolean getFileIsThere() {
		return fileIsThere;
	}
	
	public void deleteGoalListFile() {
		File saveFile = new File("goal list.txt");
		if(saveFile.exists()) {
			if(saveFile.delete()) {
				System.out.println("Save file deleted successfully");
			}
			else {
				System.out.println("Failed to delete the save file");
			}
		}
	}

}
