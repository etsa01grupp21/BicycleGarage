package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateNewPinItem extends JMenuItem implements ActionListener {

    private GarageGUI gui;

    public GenerateNewPinItem(GarageGUI gui) {
        super("Generate new pin");
        this.gui = gui;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        gui.generateNewPin();
    }
}