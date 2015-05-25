package GUI;

import Garage.BicycleGarageManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadButton extends JButton implements ActionListener {

    private GarageGUI gui;
    private BicycleGarageManager manager;

    public LoadButton(GarageGUI garageGUI, BicycleGarageManager manager) {
        super("Load");
        this.gui = garageGUI;
        this.manager = manager;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        manager.fileRead();
        JOptionPane.showMessageDialog(null, "Laddat");
    }
}
