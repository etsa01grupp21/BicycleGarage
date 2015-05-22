package Garage;

public class Bicycle {
    private String name;
    private String id;
    private User owner;
    private boolean inside;

    public Bicycle(String id, String name, User owner) {
        this.name = name;
        this.id = id;
        this.owner = owner;
        this.inside = false;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLastUsed() {
        return null;
    }

    public User getOwner() {
        return owner;
    }

    public void setInside(boolean inside) {
        if (this.inside == inside) {
            //Bike is already inside/outside
        } else {
            this.inside = inside;
            if (inside) {
                //create timestamp
            } else {
                //add stampout
            }
        }
    }

    public boolean isInside() {
        return this.inside;
    }

    @Override
    public String toString() {
        return this.name + "    (" + this.id + ")";
    }

}
