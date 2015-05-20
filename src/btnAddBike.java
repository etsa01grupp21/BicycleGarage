package GUI;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.*;

public class btnAddBike extends JMenuItem implements ActionListener {
	
	private GarageGUI gui;
	
	public btnAddBike(GarageGUI gui) {
		super("Add Bike");
		
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 String name = JOptionPane.showInputDialog("Enter name");
		 String nbr = JOptionPane.showInputDialog("Enter Number");
}
}