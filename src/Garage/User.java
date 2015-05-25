package Garage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class User implements Serializable {
    private String id;
    private String pin;
    private String name;
    private boolean inside;
    private String phoneNbr;
    private List<Bicycle> bicycles;

    public User(String name, String id, String phoneNbr) {
        this.id = id;
        this.name = name;
        this.inside = false;
        this.phoneNbr = phoneNbr;
        this.pin = CodeGenerator.generatePin();
        this.bicycles = new ArrayList<>();
    }

    public User(String name, String id, String phoneNbr, String pin) {
        this.id = id;
        this.name = name;
        this.inside = false;
        this.phoneNbr = phoneNbr;
        this.pin = pin;
        CodeGenerator.addPin(pin);
        this.bicycles = new ArrayList<>();
    }

    public void addBicycle(Bicycle bicycle) {
        bicycles.add(bicycle);
    }

    public List<Bicycle> getBicycles() {
        return bicycles;
    }

    public String getId() {
        return id;
    }
    
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNbr = phoneNumber;
    }

    public boolean isInside() {
        return inside;
    }
    
    public void setInside(boolean newInside) {
        this.inside = newInside;
    }

    @Override
    public String toString() {
        return this.name + "    ID:"+ id +  "    PIN: " + pin + "    Phone: " + phoneNbr;
    }

    public void removeBicycle(Bicycle bicycle) {
        bicycles.remove(bicycle);
    }
}