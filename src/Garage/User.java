package Garage;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String pin;
    private String name;
    private boolean inside;
    private int phoneNbr;
    private List<Bicycle> bicycles;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public User(String name, int id, int phoneNbr) {
        this.id = id;
        this.name = name;
        this.inside = false;
        this.phoneNbr = phoneNbr;
        this.pin = CodeGenerator.getPin();
        this.bicycles = new ArrayList<>();
    }

    public void addBicycle(Bicycle bicycle) {
        bicycles.add(bicycle);
    }

    public List<Bicycle> getBicycles() {
        return bicycles;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNbr() {
        return phoneNbr;
    }

    public void setInside(boolean newInside) {
        this.inside = newInside;
    }

    public boolean getInside() {
        return inside;
    }

    @Override
    public String toString() {
        return this.name + "    (" + id + ")" + "PIN: " + pin;
    }
}