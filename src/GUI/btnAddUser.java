package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class btnAddUser extends JMenuItem implements ActionListener {
	
	private GarageGUI gui;
	
	public btnAddUser(GarageGUI gui) {
		super("Add Garage.BicycleGarageManager.User");
		
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 String name = JOptionPane.showInputDialog("Enter name");
		 String nbr = JOptionPane.showInputDialog("Enter Number");
	 }
}