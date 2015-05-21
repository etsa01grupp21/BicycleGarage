package GUI;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.*;

public class btnRemoveUser extends JMenuItem implements ActionListener {
	
	private GarageGUI gui;
	
	public btnRemoveUser(GarageGUI gui) {
		super("Remove Garage.BicycleGarageManager.User");
	
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 String name = JOptionPane.showInputDialog("Enter name");
		 String nbr = JOptionPane.showInputDialog("Enter Number");
}
}

