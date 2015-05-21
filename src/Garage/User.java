package Garage;

public class User {
    private String ID;
    private String name;
    private boolean inside;
    private int phoneNbr;

    public User(String ID, String name, int phoneNbr) {
        this.ID = ID;
        this.name = name;
        this.inside = false;
        this.phoneNbr = phoneNbr;
    }

    public String getID() {
        return ID;
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
}