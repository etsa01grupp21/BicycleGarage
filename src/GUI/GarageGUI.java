package GUI;
import javax.swing.*;

import java.awt.*;
import java.util.*;

public class GarageGUI extends JFrame {
	private JTextArea messageArea1;
	private JTextArea messageArea2;
	//private Garage.BicycleGarageManager kebab;
	
	public GarageGUI(){
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Locale.setDefault(new Locale("en"));
		
		setLayout(new BorderLayout());
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu editMenu = new JMenu("Edit");
		menubar.add(editMenu);
		editMenu.add(new btnAddUser(this));
		editMenu.add(new btnRemoveUser(this));
		editMenu.add(new btnAddBike(this));
		editMenu.add(new btnRemoveBike(this));
		
		JMenu findMenu = new JMenu("Find");
		menubar.add(findMenu);
		findMenu.add(new btnFindUserByBike(this));
		findMenu.add(new btnFindUserById(this));
		
		
		JMenu viewMenu = new JMenu("View");
		menubar.add(viewMenu);
		viewMenu.add(new btnViewAllUsers(this));
		
		JPanel southPanel = new JPanel();
		messageArea1 = new JTextArea(15,25);
		messageArea1.setEditable(true);
		messageArea2 = new JTextArea(15,25);
		messageArea2.setEditable(true);
		southPanel.add(new JScrollPane(messageArea1));
		southPanel.add(new JScrollPane(messageArea2));
		add(southPanel,BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new GarageGUI();
	}




}
