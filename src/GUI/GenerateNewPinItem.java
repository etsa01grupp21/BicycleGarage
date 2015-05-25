package GUI;

import Garage.User;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GenerateNewPinItem extends JMenuItem implements ActionListener {

    private GarageGUI gui;

    public GenerateNewPinItem(GarageGUI gui) {
        super("Generate new pin");
        this.gui = gui;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        User selectedUser = gui.getSelectedUser();
        if (selectedUser != null) {
            gui.generateNewPin();
        } else {
            JOptionPane.showMessageDialog(null, "No user selected");
        }
    }
}