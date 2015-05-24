package Garage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private int id;
    private String pin;
    private String name;
    private boolean inside;
    private String phoneNbr;
    private List<Bicycle> bicycles;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public User(String name, int id, String phoneNbr) {
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

    public String getPhoneNbr() {
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
        return this.name + "    ("+ id +")" + "    PIN: " + pin;
    }

    public void removeBicycle(Bicycle bicycle) {
        bicycles.remove(bicycle);
    }
}