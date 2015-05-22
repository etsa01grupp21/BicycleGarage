package Garage;

import Interfaces.BarcodePrinter;
import Interfaces.ElectronicLock;
import Interfaces.PinCodeTerminal;

import java.util.*;

public class BicycleGarageManager {
    BarcodePrinter printer;
    PinCodeTerminal terminal;
    ElectronicLock entryLock;
    ElectronicLock exitLock;

    private Map<String, User> pinToUser;
    private Map<Integer, User> idToUser;
    private Map<Integer, Bicycle> barcodeToBicycle;
    private List<User> users;
    private StringBuilder pin;

    /* Register hardware so that Garage.BicycleGarageManager
     * knows which drivers to access.
     */
    public BicycleGarageManager() {
        pin = new StringBuilder();
        pinToUser = new HashMap<>();
        idToUser = new HashMap<>();
        barcodeToBicycle = new HashMap<>();
        users = new ArrayList<>();
    }


    public boolean addUser(User user) {
        if(idToUser.get(user.getId()) != null) return false;
        else {
            idToUser.put(user.getId(), user);
            pinToUser.put(user.getPin(), user);
            users.add(user);
        }
        return true;
    }

    public boolean removeUser(User user) {
        User u = idToUser.get(user.getId());
        if(u == null) return false;
        else {
            idToUser.remove(u.getId());
            pinToUser.remove(u.getPin());
            CodeGenerator.addPin(u.getPin());
            users.remove(u);
        }
        return true;
    }

    public void registerHardwareDrivers(BarcodePrinter printer,
                                        ElectronicLock entryLock, ElectronicLock exitLock,
                                        PinCodeTerminal terminal) {
        this.printer = printer;
        this.entryLock = entryLock;
        this.exitLock = exitLock;
        this.terminal = terminal;
    }

    /* Will be called when a user has used the bar code
     * reader at the entry door. Garage.BicycleGarageManager.Bicycle ID should be a
     * string of 5 characters, where every character
     * can be '0', '1',... '9'. */
    public void entryBarcode(String bicycleID) {
        if (barcodeToBicycle.get(Integer.parseInt(bicycleID)) != null) {
            entryLock.open(15);
        }
        /*
         * Registrera cykeln som inne i garaget
		 * Lås upp dörren
		 */
    }

    /* Will be called when a user has used the bar code
     * reader at the exit door. Garage.BicycleGarageManager.Bicycle ID should be a
     * string of 5 characters, where every
     * character can be '0', '1',... '9'. */
    public void exitBarcode(String bicycleID) {
        int id = Integer.parseInt(bicycleID);
        Bicycle bike = barcodeToBicycle.get(id);
        if (bike != null && bike.isInside()) {
                exitLock.open(15);
                bike.getOwner().setInside(false);
                bike.setInside(false);
        }
        /*
         * Om (ägaren av cykeln är i garaget) {
		 * 		Lås upp dörren && registrera ägaren som utanför
		 * } annars {
		 * 		Gör inget (?)
		 * }
		 */
    }

    /* Will be called when a user has pressed a key at the
     * keypad at the entry door. The following characters could be
     * pressed: '0', '1',... '9', '*', '#'. */
    public void entryCharacter(char c) {
        if (pin.length() > 3) {
            User currentUser = pinToUser.get(pin.toString());
            if (currentUser == null) {
                terminal.lightLED(0, 3);
            } else {
                currentUser.setInside(true);
                entryLock.open(15);
            }
            pin.setLength(0);
        } else {
            pin.append(c);
        }
    }

    public void populateUsers() {
        Random rand = new Random();
        for(int i = 0; i<10; i++){
            String s = String.valueOf(i);
            User u = new User("YOLOSWAG #" + s, i, i);
            for(int b = 0; b<5; b++){
                Bicycle bike = new Bicycle("ID:" + rand.nextInt(100), "NAME:" + b, u);
                u.addBicycle(bike);
            }
            idToUser.put(i, u);
            pinToUser.put(u.getPin(), u);
            users.add(u);
        }
    }

    public List<User> getUsers() {
        return users;
    }
}