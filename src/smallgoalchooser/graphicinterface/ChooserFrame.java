package smallgoalchooser.graphicinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import smallgoalchooser.listhandler.ListHandler;
import smallgoalchooser.savefile.FileManipulator;

public class ChooserFrame implements ActionListener {
	
	private JFrame frame;
	private JButton addButton, saveButton, yesButton, noButton;
	//private JTextField textField;
	private JTextArea inputTextArea;
	private JScrollPane inputScrollPane;
	private JTextArea listDisplayArea;
	private JScrollPane listDisplayPane;
	private JLabel helperLabel;
	private ListHandler listMaker;
	//private JPanel panel;
	private FileManipulator saveHandler;
	
	//private Font myFont = new Font("Ink Free", Font.BOLD, 10);
	
	public ChooserFrame() throws IOException {
		listMaker = new ListHandler();
		saveHandler = new FileManipulator();
		frame = new JFrame("Small Goal Helper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		
		/*textField = new JTextField();
		textField.setBounds(50,300,300,50);
		textField.setFont(myFont);
		textField.setText(null);
		textField.setToolTipText("Enter a small goal here");*/
		
		inputTextArea = new JTextArea("", 5, 50);
		inputTextArea.setBounds(50,250,300,100);
		inputTextArea.setLineWrap(true);
		//textArea.setEditable(false);
		//textArea.setText("hello");
		inputScrollPane = new JScrollPane(inputTextArea);
		inputScrollPane.setBounds(50,250,300,100);
		inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		listDisplayArea = new JTextArea("", 5, 50);
		listDisplayArea.setBounds(50,100,300,100);
		listDisplayArea.setLineWrap(true);
		listDisplayArea.setEditable(false);
		listDisplayPane = new JScrollPane(listDisplayArea);
		listDisplayPane.setBounds(50, 100, 300, 100);
		listDisplayPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		helperLabel = new JLabel("Enter a small goal and press Add to List");
		helperLabel.setBounds(50, 50, 300, 50);
		/*panel = new JPanel();
		panel.setBounds(50, 10, 30, 50);
		panel.setBackground(Color.black);
		panel.setOpaque(true);
		panel.setVisible(true);*/
		
		addButton = new JButton("Add to List");
		addButton.setBounds(50, 400, 100, 50);
		addButton.addActionListener(this);
		saveButton = new JButton("Save List");
		saveButton.setBounds(250, 400, 100, 50);
		saveButton.addActionListener(this);
		yesButton = new JButton("Yes");
		yesButton.setBounds(50, 400, 100, 50);
		yesButton.addActionListener(this);
		yesButton.setVisible(false);
		noButton = new JButton("No");
		noButton.setBounds(250, 400, 100, 50);
		noButton.addActionListener(this);
		noButton.setVisible(false);
		
		//frame.add(textField);
		//frame.add(textArea);
		frame.add(helperLabel);
		frame.add(listDisplayPane);
		frame.add(inputScrollPane);
		frame.add(addButton);
		frame.add(saveButton);
		frame.add(yesButton);
		frame.add(noButton);
		//frame.add(panel);
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
		}
		else if(e.getSource() == yesButton) {
			
		}
		else if(e.getSource() == noButton) {
			
		}
	}

}
