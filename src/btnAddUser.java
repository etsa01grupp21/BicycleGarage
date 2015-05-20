package GUI;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.*;

public class btnAddUser extends JMenuItem implements ActionListener {
	
	private GarageGUI gui;
	
	public btnAddUser(GarageGUI gui) {
		super("Add User");
		
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 String name = JOptionPane.showInputDialog("Enter name");
		 String nbr = JOptionPane.showInputDialog("Enter Number")
		 BicycleGarageManage.addUser(new User(ID, name));
	 }
}