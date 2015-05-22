package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveUserButton extends JButton implements ActionListener {

    private GarageGUI gui;

    public RemoveUserButton(GarageGUI gui) {
        super("Remove user");

        this.gui = gui;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        gui.removeUser();
    }
}

