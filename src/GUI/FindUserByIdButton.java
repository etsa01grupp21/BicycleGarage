package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindUserByIdButton extends JMenuItem implements ActionListener {

    private GarageGUI gui;

    public FindUserByIdButton(GarageGUI gui) {
        super("Find user by id");
        this.gui = gui;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String ssn = (JOptionPane.showInputDialog("Enter social security number"));
        if (ssn.matches("[0-9]{1,}")) {
            gui.findUserById(Integer.parseInt(ssn));
        } else {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid number");
        }
    }
}