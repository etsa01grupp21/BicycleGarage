package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserButton extends JButton implements ActionListener {
	
	private GarageGUI gui;
	
	public AddUserButton(GarageGUI gui) {
		super("Add user");
		
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 AddUserPanel panel = new AddUserPanel();
		 int result = JOptionPane.showConfirmDialog(null, panel,
				 "Please fill in the details", JOptionPane.OK_CANCEL_OPTION);
		 if (result == JOptionPane.OK_OPTION) {
			 if(panel.name.getText().isEmpty() || panel.ssn.getText().isEmpty() || panel.nbr.getText().isEmpty()){
				 JOptionPane.showMessageDialog(null,
						 "Wrong Input!",
						 "Error",
						 JOptionPane.ERROR_MESSAGE);
			 }else{
				 gui.addUser(panel.name.getText(), Integer.parseInt(panel.ssn.getText()), panel.nbr.getText());
			 }
		 }
	 }

	private class AddUserPanel extends JPanel {

		private JTextField name, ssn, nbr;

		public AddUserPanel(){
			super(new GridLayout(3, 0, 0, 10));
			buildPanel();
		}

		private void buildPanel() {
			add(new JLabel("Name:"));
			name = new JTextField();
			add(name);

			add(new JLabel("SSN:"));
			ssn = new JTextField();
			add(ssn);

			add(new JLabel("Phone number:"));
			nbr = new JTextField();
			add(nbr);
		}
	}

}