package GUI;

import Garage.BicycleGarageManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButton extends JButton implements ActionListener {

    private GarageGUI gui;
    private BicycleGarageManager manager;

    public SaveButton(GarageGUI garageGUI, BicycleGarageManager manager) {
        super("Save");
        this.gui = garageGUI;
        this.manager = manager;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        manager.fileWrite();
        JOptionPane.showMessageDialog(null, "Sparat");
    }
}