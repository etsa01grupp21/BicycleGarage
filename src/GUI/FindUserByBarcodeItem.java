package GUI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class FindUserByBarcodeItem extends JMenuItem implements ActionListener {

	private GarageGUI gui;

	public FindUserByBarcodeItem(GarageGUI gui) {
		super("Find user by Barcode");
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String barcode = JOptionPane.showInputDialog("Enter Barcode");
		if (barcode != null) {
			if (barcode.matches("\\d{5}")) {
				gui.findUserByBarcode(barcode);
			} else {
				JOptionPane.showMessageDialog(null,
						"Please enter a valid barcode");
			}
		}
	}
}