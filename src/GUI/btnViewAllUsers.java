package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JMenuItem;

public class btnViewAllUsers extends JMenuItem implements ActionListener {

	private GarageGUI gui;
	
	public btnViewAllUsers(GarageGUI gui) {
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

