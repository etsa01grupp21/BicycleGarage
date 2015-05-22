package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindUserByBikeButton extends JMenuItem implements ActionListener {
	
	private GarageGUI gui;
	
	public FindUserByBikeButton(GarageGUI gui) {
		super("Search user by bike");
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 String name = JOptionPane.showInputDialog("Enter name");
		 String nbr = JOptionPane.showInputDialog("Enter Number");
}
}