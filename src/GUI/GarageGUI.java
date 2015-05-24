package GUI;

import Garage.Bicycle;
import Garage.BicycleGarageManager;
import Garage.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GarageGUI {
    private BicycleGarageManager manager;
    private JList<User> users;
    private JList<Bicycle> bicycles;
    private User selectedUser;
    private Bicycle selectedBicycle;

    public GarageGUI(BicycleGarageManager manager) {
        this.manager = manager;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Bicyclegarage");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initializeSaveOnExit(frame);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void initializeSaveOnExit(JFrame frame) {
        frame.addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                manager.fileWrite();
            }
        });
    }

    private void addComponentsToPane(Container contentPane) {
        contentPane.add(getMenu(), BorderLayout.NORTH);
        contentPane.add(getMainPanel(), BorderLayout.CENTER);
        contentPane.add(addUserButton(), BorderLayout.SOUTH);
    }

    private Component addUserButton() {
        JPanel buttons = new JPanel(new GridLayout(0, 4));

        JButton addUserButton = new AddUserButton(this);
        buttons.add(addUserButton);

        JButton removeUserButton = new RemoveUserButton(this);
        buttons.add(removeUserButton);

        JButton addBikeButton = new AddBikeButton(this);
        buttons.add(addBikeButton);

        JButton removeBikeButton = new RemoveBikeButton(this);
        buttons.add(removeBikeButton);

        return buttons;
    }

    private Component getMainPanel() {
        JPanel main = new JPanel(new GridLayout(0, 2));
        JScrollPane userPane = new JScrollPane(createUsers());
        main.add(userPane);
        JScrollPane bicyclesPane = new JScrollPane(createBicycles());
        main.add(bicyclesPane);
        return main;
    }

    private Component getMenu() {
        JMenuBar menubar = new JMenuBar();

        JMenu findMenu = new JMenu("Find");
        findMenu.add(new FindUserByBikeButton(this));
        findMenu.add(new FindUserByIdButton(this));
        menubar.add(findMenu);

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new GenerateNewPinItem(this));
        menubar.add(editMenu);
        return menubar;
    }

    private JList<Bicycle> createBicycles() {
        DefaultListModel<Bicycle> listModel = new DefaultListModel<>();
        bicycles = new JList<>(listModel);
        bicycles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    Bicycle selectedBike = bicycles.getSelectedValue();
                    updatedSelectedBicycle(selectedBike);
                }
            }
        });
        return bicycles;
    }

    private JList<User> createUsers() {
        if(!manager.fileRead()) manager.populateUsers();
        DefaultListModel<User> listModel = new DefaultListModel<>();
        for (User u : manager.getUsers()) {
            listModel.addElement(u);
        }

        users = new JList<>(listModel);
        users.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    User selectedUser = users.getSelectedValue();
                    updatedSelectedUser(selectedUser);
                    updateBicyclesModel(selectedUser);
                }
            }
        });
        return users;
    }

    private void updatedSelectedUser(User user) {
        this.selectedUser = user;
    }

    private void updatedSelectedBicycle(Bicycle bicycle) {
        this.selectedBicycle = bicycle;
    }

    private void updateBicyclesModel(User user) {
        DefaultListModel<Bicycle> listModel = (DefaultListModel<Bicycle>) bicycles.getModel();
        listModel.clear();
        if (user != null) {
            for (Bicycle bike : user.getBicycles()) {
                listModel.addElement(bike);
            }
        }
        bicycles.setModel(listModel);
    }

    private void updateUsersModel(User user, boolean addUser) {
        DefaultListModel<User> listModel = (DefaultListModel<User>) users.getModel();
        if (addUser) listModel.addElement(user);
        else {
            listModel.removeElement(user);
            if (listModel.isEmpty()) updateBicyclesModel(null);
        }
        users.setModel(listModel);
    }

    public void addUser(String name, int id, String nbr) {
        User u = new User(name, id, nbr);
        if (manager.addUser(u)) updateUsersModel(u, true);
        else {
            JOptionPane.showMessageDialog(null,
                    "There already exits a user with that social security number!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeUser() {
        manager.removeUser(selectedUser);
        updateUsersModel(selectedUser, false);
        selectedUser = null;
    }

    public void addBike(String bikeName) {
        User user = getSelectedUser();
        Bicycle bike = new Bicycle(bikeName, user);
        user.addBicycle(bike);
        manager.addBicycle(bike);
        updateBicyclesModel(user);
    }

    public void removeBicycle() {
        manager.removeBicycle(selectedBicycle);
        selectedBicycle.delete();
        updateBicyclesModel(selectedBicycle.getOwner());
        selectedBicycle = null;
    }

    public User getSelectedUser() {
        return this.selectedUser;
    }

    public Bicycle getSelectedBicycle() {
        return this.selectedBicycle;
    }

    public void findUserByBarcode(String barcode) {
        Bicycle bicycle = manager.getBicycle(barcode);
        if (bicycle == null) JOptionPane.showMessageDialog(null, "No user found");
        else {
            User user = bicycle.getOwner();
            users.setSelectedValue(user, true);
            updateBicyclesModel(user);
            bicycles.setSelectedValue(bicycle, true);
            this.selectedUser = user;
            this.selectedBicycle = bicycle;
        }
    }

    public void findUserById(int i) {
        User user = manager.getUser(i);
        if (user == null) JOptionPane.showMessageDialog(null, "No user found");
        else {
            users.setSelectedValue(user, true);
            updateBicyclesModel(user);
            this.selectedUser = user;
        }
    }

    public void generateNewPin() {
        selectedUser.generateNewPin();
        updateCurrentUserModel(selectedUser, users.getSelectedIndex());
    }

    private void updateCurrentUserModel(User user, int selectedIndex) {
        DefaultListModel<User> listModel = (DefaultListModel<User>) users.getModel();
        listModel.setElementAt(user, selectedIndex);
    }
}