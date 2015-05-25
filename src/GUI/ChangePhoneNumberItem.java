package GUI;

import Garage.User;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ChangePhoneNumberItem extends JMenuItem implements ActionListener {

    private GarageGUI gui;

    public ChangePhoneNumberItem(GarageGUI gui) {
        super("Change phonenumber");
        this.gui = gui;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        User selectedUser = gui.getSelectedUser();
        if (selectedUser == null) {
            JOptionPane.showMessageDialog(null, "No user selected");
        } else {
            String nbr = JOptionPane
                    .showInputDialog("Enter new phonenumber");
            if (nbr != null) {
                if (!nbr.isEmpty()) {
                    selectedUser.setPhoneNumber(nbr);
                    gui.updateCurrentUserModel(selectedUser);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                }
            }
        }
    }
}