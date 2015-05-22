package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAllUsersButton extends JMenuItem implements ActionListener {

	private GarageGUI gui;
	
	public ViewAllUsersButton(GarageGUI gui) {
		super("View All Users");
		this.gui = gui;
		addActionListener((ActionListener) this);
	}
	public void actionPerformed(ActionEvent e) {
		/*Set<String> s =  phoneBook.names();
		String t= "";
		Iterator<String> it = s.iterator();
	    while(it.hasNext()) {
	    	t+=it.next()+"\n";
	        }gui.setText(t);
	   */ }
	}

