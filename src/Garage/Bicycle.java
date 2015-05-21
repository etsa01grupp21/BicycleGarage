package Garage;

public class Bicycle {
    private String name;
    private String ID;
    private long lastUsed;
    private User owner;
    private boolean inside;

    public Bicycle(String ID, String name, User owner) {
        this.name = name;
        this.ID = ID;
        this.owner = owner;
        this.inside = false;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public long getLastUsed() {
        return lastUsed;
    }

    public User getOwner() {
        return owner;
    }

    public void setInside(boolean newInside) {
        this.inside = newInside;
        this.lastUsed = System.currentTimeMillis();
    }

    public boolean getInside() {
        return inside;
    }
}