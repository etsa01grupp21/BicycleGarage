package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBikeButton extends JButton implements ActionListener {


    public AddBikeButton(GarageGUI garageGUI) {
        super("Add bicycle");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog("Enter name");
        String nbr = JOptionPane.showInputDialog("Enter Number");
    }
}