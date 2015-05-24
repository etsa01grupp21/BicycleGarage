package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindUserByIdItem extends JMenuItem implements ActionListener {

	private GarageGUI gui;

	public FindUserByIdItem(GarageGUI gui) {
		super("Find user by ID");
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String id = (JOptionPane
				.showInputDialog("Enter ID-number"));
		if (id != null) {
			if (id.matches("[0-9]{1,}")) {
				gui.findUserById(id);
			} else {
				JOptionPane.showMessageDialog(null,
						"Please enter a valid number");
			}
		}
	}
}