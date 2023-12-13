package smallgoalchooser.graphicinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

public class ChooserFrame implements ActionListener {
	
	JFrame frame;
	JButton addButton, saveButton;
	JTextField textField;
	JPanel panel;
	
	Font myFont = new Font("Ink Free", Font.BOLD, 30);
	
	public ChooserFrame() {
		frame = new JFrame("Small Goal Helper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		
		textField = new JTextField();
		textField.setBounds(50,25,300,50);
		textField.setFont(myFont);
		
		addButton = new JButton("Add to list");
		saveButton = new JButton("Save list");
		
		frame.add(textField);
		frame.add(addButton);
		frame.add(saveButton);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
