package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBikeButton extends JButton implements ActionListener {

	private GarageGUI gui;

	public AddBikeButton(GarageGUI garageGUI) {
		super("Add bike");
		this.gui = garageGUI;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (gui.getSelectedUser() == null) {
			JOptionPane.showMessageDialog(null, "No user selected");
		} else {
			String bikeName = JOptionPane
					.showInputDialog("Enter name for bike");
			if (bikeName != null) {
				if (!bikeName.isEmpty()) {
					gui.addBike(bikeName);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid input");
				}
			}
		}
	}
}