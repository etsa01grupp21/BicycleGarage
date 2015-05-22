package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveBikeButton extends JButton implements ActionListener {

    public RemoveBikeButton(GarageGUI garageGUI) {
        super("Remove Bike");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog("Enter name");
        String nbr = JOptionPane.showInputDialog("Enter Number");
    }
}

