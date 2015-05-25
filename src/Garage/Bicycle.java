package Garage;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Bicycle implements Serializable {
    private String name;
    private String barcode;
    private User owner;
    private boolean inside;
    private List<Stamp> timeStamps;

    public Bicycle(String name, User owner) {
        this.name = name;
        this.barcode = CodeGenerator.getBarcode();
        this.owner = owner;
        this.inside = false;
        timeStamps = new ArrayList<>();
    }

    public Bicycle(String name, User owner, String barcode) {
        this.name = name;
        this.barcode = barcode;
        CodeGenerator.addTakenBarcode(barcode);
        this.owner = owner;
        this.inside = false;
        timeStamps = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getBarcode() {
        return barcode;
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
            	
                timeStamps.add(new Stamp());
            } else {
                timeStamps.get(timeStamps.size() - 1).stampOut();
            }
        }
    }

    public boolean isInside() {
        return this.inside;
    }

    @Override
    public String toString() {
        return this.name + "    Barcode: " + this.barcode;
    }

    public void delete() {
        this.owner.removeBicycle(this);
    }

    public List<Stamp> getTimeStamps() {
        return this.timeStamps;
    }

    public class Stamp implements Serializable {

        private Timestamp in, out;

        private Stamp() {
            in = new Timestamp(System.currentTimeMillis());
        }

        private void stampOut() {
            out = new Timestamp(System.currentTimeMillis());
        }

        @Override
        public String toString() {
            if (out == null) return "In: " + in.toString();
            else return "In: " + in.toString() + "    Out: " + out.toString();
        }
    }
}
