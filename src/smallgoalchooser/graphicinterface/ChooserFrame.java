package smallgoalchooser.graphicinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import smallgoalchooser.listhandler.ListHandler;
import smallgoalchooser.savefile.FileManipulator;

public class ChooserFrame implements ActionListener {
	
	private JFrame frame;
	private JButton addButton, saveButton, yesButton, noButton, closeButton;
	//private JTextField textField;
	private JTextArea inputTextArea;
	private JScrollPane inputScrollPane;
	private JTextArea listDisplayArea;
	private JScrollPane listDisplayPane;
	private JLabel helperLabel;
	private ListHandler listMaker;
	private JPanel panel;
	private FileManipulator saveHandler;
	private boolean listFinished = false;
	
	//private Font myFont = new Font("Ink Free", Font.BOLD, 10);
	
	public ChooserFrame() throws IOException {
		listMaker = new ListHandler();
		saveHandler = new FileManipulator();
		frame = new JFrame("Small Goal Helper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		//panel.setBounds(50, 10, 30, 50);
		//panel.setBackground(Color.black);
		//panel.setOpaque(false);
		panel.setVisible(true);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0.1;
		gbc.gridwidth = 2;
		
		/*textField = new JTextField();
		textField.setBounds(50,300,300,50);
		textField.setFont(myFont);
		textField.setText(null);
		textField.setToolTipText("Enter a small goal here");*/
		
		helperLabel = new JLabel("Enter a small goal and press Add to List");
		//helperLabel.setBounds(50, 50, 300, 50);
		panel.add(helperLabel, gbc);
		gbc.gridy++;
		
		inputTextArea = new JTextArea("", 5, 50);
		//inputTextArea.setBounds(50,250,300,100);
		inputTextArea.setSize(300, 100);
		inputTextArea.setLineWrap(true);
		//textArea.setEditable(false);
		//textArea.setText("hello");
		inputScrollPane = new JScrollPane(inputTextArea);
		//inputScrollPane.setBounds(50,250,300,100);
		inputScrollPane.setSize(300, 100);
		inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(inputScrollPane, gbc);
		gbc.gridy++;
		
		
		listDisplayArea = new JTextArea("", 5, 50);
		//listDisplayArea.setBounds(50,100,300,100);
		listDisplayArea.setSize(300, 100);
		listDisplayArea.setLineWrap(true);
		listDisplayArea.setEditable(false);
		listDisplayPane = new JScrollPane(listDisplayArea);
		//listDisplayPane.setBounds(50, 100, 300, 100);
		listDisplayPane.setSize(300, 100);
		listDisplayPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(listDisplayPane, gbc);
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		
		
		addButton = new JButton("Add to List");
		//addButton.setBounds(50, 400, 100, 50);
		addButton.addActionListener(this);
		panel.add(addButton, gbc);
		
		yesButton = new JButton("Finished");
		//yesButton.setBounds(50, 400, 100, 50);
		yesButton.addActionListener(this);
		panel.add(yesButton, gbc);
		gbc.gridx++;
		gbc.anchor = GridBagConstraints.LINE_END;
		
		saveButton = new JButton("Save List");
		//saveButton.setBounds(250, 400, 100, 50);
		saveButton.addActionListener(this);
		panel.add(saveButton, gbc);
		
		noButton = new JButton("Not Yet");
		//noButton.setBounds(250, 400, 100, 50);
		noButton.addActionListener(this);
		panel.add(noButton, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		
		closeButton = new JButton("Close");
		closeButton.addActionListener(this);
		panel.add(closeButton, gbc);
		closeButton.setVisible(false);
		
		if(saveHandler.getFileIsThere()) {
			yesButton.setVisible(true);
			noButton.setVisible(true);
			addButton.setVisible(false);
			saveButton.setVisible(false);
			inputScrollPane.setVisible(false);
			listDisplayPane.setVisible(false);
			
			listMaker.loadFromSaveFile(saveHandler.getGoalListSaved());
			helperLabel.setText("Your Current Goal is: " + listMaker.getCurrentGoal());
		}
		else {
			yesButton.setVisible(false);
			noButton.setVisible(false);
			addButton.setVisible(true);
			saveButton.setVisible(true);
			inputScrollPane.setVisible(true);
			listDisplayPane.setVisible(true);
		}
		//frame.add(textField);
		//frame.add(textArea);
		/*frame.add(helperLabel);
		frame.add(listDisplayPane);
		frame.add(inputScrollPane);
		frame.add(addButton);
		frame.add(saveButton);
		frame.add(yesButton);
		frame.add(noButton);*/
		frame.getContentPane().add(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addButton) {
			if(inputTextArea.getText().trim().length() > 0) {
				if(listDisplayArea.getText().length() > 0) {
					String tempTextHolder;
					tempTextHolder = listDisplayArea.getText() + ",\n" + inputTextArea.getText();
					listDisplayArea.setText(tempTextHolder);
				}
				else {
					listDisplayArea.setText(inputTextArea.getText());
				}
			}
			inputTextArea.setText(null);
		}
		else if(e.getSource() == saveButton) {
			listMaker.loadListArray(listDisplayArea.getText());
			String currentGoal = listMaker.chooseCurrentGoal();
			helperLabel.setText("Your current goal is: " + currentGoal);
			try {
				saveHandler.saveGoalList(listMaker.createListToBeSaved());
			} catch (IOException e1) {
				System.out.println("Failed to save list of goals");
				e1.printStackTrace();
			}
			closeButton.setVisible(true);
			addButton.setVisible(false);
			saveButton.setVisible(false);
			inputScrollPane.setVisible(false);
			listDisplayPane.setVisible(false);
		}
		else if(e.getSource() == yesButton) {
			listMaker.currentGoalCompleted();
			String currentGoal = listMaker.chooseCurrentGoal();
			if(currentGoal.isEmpty()) {
				helperLabel.setText("You have completed all of your goals. Congratulations!");
				listFinished = true;
			}
			else {
				helperLabel.setText("Your current goal is: " + currentGoal);
				try {
					saveHandler.saveGoalList(listMaker.createListToBeSaved());
				} catch (IOException e1) {
					System.out.println("Failed to save list of goals");
					e1.printStackTrace();
				}
			}
			if(listFinished) {
				closeButton.setVisible(true);
				yesButton.setVisible(false);
				noButton.setVisible(false);
			}
		}
		else if(e.getSource() == noButton) {
			if(!listMaker.getCurrentGoal().isEmpty()) {
				try {
					saveHandler.saveGoalList(listMaker.createListToBeSaved());
				} catch (IOException e1) {
					System.out.println("Failed to save list of goals");
					e1.printStackTrace();
				}
			}
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		else if(e.getSource() == closeButton) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	
}
