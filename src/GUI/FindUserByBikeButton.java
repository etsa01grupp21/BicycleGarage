package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindUserByBikeButton extends JMenuItem implements ActionListener {

    private GarageGUI gui;

    public FindUserByBikeButton(GarageGUI gui) {
        super("Find user by bike");
        this.gui = gui;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String barcode = JOptionPane.showInputDialog("Enter name");
        if(barcode.matches("\\d{5}")){
            gui.findUserByBarcode(barcode);
        }else {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid barcode");
        }
    }
}