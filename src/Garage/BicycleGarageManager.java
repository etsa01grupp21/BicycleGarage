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

    private List<User> userList;
    private List<Bicycle> bikeList;
    private Queue<Character> pin;

    //HashMap for lookup user in constant time
    private Map<Integer, User> pinMap;

    /* Register hardware so that Garage.BicycleGarageManager
     * knows which drivers to access.
     */
    public BicycleGarageManager() {
        new HashMap<>();
        userList = new ArrayList<>();
        bikeList = new ArrayList<>();
    }


    public void addUser(User user) {
        userList.add(user);
    }

    public void addBike(Bicycle bikecycle) {
        bikeList.add(bikecycle);
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
        if (bikeList.contains(bicycleID)) {
            bikeList.get(Integer.parseInt(bicycleID)).setInside(true);
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
        if (bikeList.contains(id)) {
            if (bikeList.get(id).getOwner().getInside()) {
                exitLock.open(15);
                bikeList.get(id).getOwner().setInside(false);
                bikeList.get(id).setInside(false);
            }
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
        pin.add(c);
        if (pin.size() == 4) {
            StringBuilder sb = new StringBuilder();
            while (!pin.isEmpty()) sb.append(pin.poll());
            int pinCode = Integer.parseInt(sb.toString());
            User currentUser = pinMap.get(pinCode);
            if (currentUser == null) {
                terminal.lightLED(0, 3);

            } else {
                currentUser.setInside(true);
                entryLock.open(15);
            }
        }
    }
}
