package Garage;

import java.io.Serializable;

public class Bicycle implements Serializable {
    private String name;
    private String barcode;
    private User owner;
    private boolean inside;

    public Bicycle(String name, User owner) {
        this.name = name;
        this.barcode = CodeGenerator.getBarcode();
        this.owner = owner;
        this.inside = false;
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
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
        return this.name + "    (" + this.barcode + ")";
    }

    public void delete() {
        this.owner.removeBicycle(this);
    }
}
