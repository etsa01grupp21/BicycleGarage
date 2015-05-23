package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveBikeButton extends JButton implements ActionListener {

    private GarageGUI gui;

    public RemoveBikeButton(GarageGUI garageGUI) {
        super("Remove Bike");
        this.gui = garageGUI;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (gui.getSelectedBicycle() == null) {
            JOptionPane.showMessageDialog(null,
                    "No bicycle selected");
        } else {
            gui.removeBicycle();
        }
    }
}

