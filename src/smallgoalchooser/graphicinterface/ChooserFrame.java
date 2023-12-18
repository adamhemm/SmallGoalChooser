package smallgoalchooser.graphicinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

public class ChooserFrame implements ActionListener {
	
	private JFrame frame;
	private JButton addButton, saveButton, yesButton, noButton;
	private JTextField textField;
	private JTextArea inputTextArea;
	private JScrollPane inputScrollPane;
	private JTextArea listDisplayArea;
	private JScrollPane listDisplayPane;
	private JPanel panel;
	
	private Font myFont = new Font("Ink Free", Font.BOLD, 10);
	
	public ChooserFrame() {
		frame = new JFrame("Small Goal Helper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		
		/*textField = new JTextField();
		textField.setBounds(50,300,300,50);
		textField.setFont(myFont);
		textField.setText(null);
		textField.setToolTipText("Enter a small goal here");*/
		
		inputTextArea = new JTextArea("", 5, 50);
		inputTextArea.setBounds(50,300,300,50);
		inputTextArea.setLineWrap(true);
		//textArea.setEditable(false);
		//textArea.setText("hello");
		inputScrollPane = new JScrollPane(inputTextArea);
		inputScrollPane.setBounds(50,300,300,50);
		inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		/*panel = new JPanel();
		panel.setBounds(50, 10, 30, 50);
		panel.setBackground(Color.black);
		panel.setOpaque(true);
		panel.setVisible(true);*/
		
		addButton = new JButton("Add to list");
		addButton.setBounds(50, 400, 100, 50);
		saveButton = new JButton("Save list");
		saveButton.setBounds(250, 400, 100, 50);
		yesButton = new JButton("Yes");
		yesButton.setBounds(50, 400, 100, 50);
		yesButton.setVisible(false);
		noButton = new JButton("No");
		noButton.setBounds(250, 400, 100, 50);
		noButton.setVisible(false);
		
		//frame.add(textField);
		//frame.add(textArea);
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
		// TODO Auto-generated method stub
		
	}

}
