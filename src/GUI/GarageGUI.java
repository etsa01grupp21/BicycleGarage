package GUI;

import Garage.Bicycle;
import Garage.BicycleGarageManager;
import Garage.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class GarageGUI extends JFrame {
    private BicycleGarageManager manager;
    private JList<User> users;
    private JList<Bicycle> bicycles;
    private User selectedUser;

    public GarageGUI(BicycleGarageManager manager) {
        this.manager = manager;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Bicyclegarage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        setLayout(new BorderLayout());
        frame.pack();
        frame.setVisible(true);
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

        JMenu viewMenu = new JMenu("View");
        viewMenu.add(new ViewAllUsersButton(this));
        menubar.add(viewMenu);
        return menubar;
    }

    private JList<Bicycle> createBicycles() {
        DefaultListModel<Bicycle> listModel = new DefaultListModel<>();
        bicycles = new JList<>(listModel);
        return bicycles;
    }

    private JList<User> createUsers() {
        manager.populateUsers();
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

    private void updateBicyclesModel(User user) {
        DefaultListModel<Bicycle> listModel = (DefaultListModel<Bicycle>) bicycles.getModel();
        listModel.clear();
        for (Bicycle bike : user.getBicycles()) {
            listModel.addElement(bike);
        }
        bicycles.setModel(listModel);
    }

    private void updateUsersModel(User user, boolean add) {
        DefaultListModel<User> listModel = (DefaultListModel<User>) users.getModel();
        if(add) listModel.addElement(user);
        else listModel.removeElement(user);
        users.setModel(listModel);
    }

    public void addUser(String name, int id, int nbr) {
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
        if(selectedUser == null){
            JOptionPane.showMessageDialog(null,
                    "No user selected");
        }else {
            manager.removeUser(selectedUser);
            updateUsersModel(selectedUser, false);
            selectedUser = null;
        }

    }
}