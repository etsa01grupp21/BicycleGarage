package GUI;

import Garage.Bicycle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ViewStatisticsItem extends JMenuItem implements ActionListener {

    private GarageGUI gui;

    public ViewStatisticsItem(GarageGUI garageGUI) {
        super("View statistics for bicycle");
        this.gui = garageGUI;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gui.getSelectedBicycle() == null) {
            JOptionPane.showMessageDialog(null,
                    "No bike selected");
        } else {
            JOptionPane op = new JOptionPane(new StatisticsTable(), JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.DEFAULT_OPTION);
            JDialog dlg = op.createDialog(null, "Bicycle statistics");
            dlg.setVisible(true);
        }
    }

    private class StatisticsTable extends JPanel {

        public StatisticsTable() {
            add(new JScrollPane(getTimeStamps()));
        }

        private Component getTimeStamps() {
            Bicycle bike = gui.getSelectedBicycle();
            DefaultListModel<Bicycle.Stamp> listModel = new DefaultListModel<>();
            for (Bicycle.Stamp stamp : bike.getTimeStamps()) {
                listModel.addElement(stamp);
            }
            return new JList<>(listModel);
        }
    }
}
