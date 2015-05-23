package Garage;


import java.util.ArrayDeque;
import java.util.Deque;

public class CodeGenerator {
    private static int pinCounter = -1;
    private static int barcodeCounter = -1;
    private static Deque<String> availablePins = new ArrayDeque<>();
    private static Deque<String> availableBarcode = new ArrayDeque<>();

    public static String getPin(){
        if(availablePins.isEmpty()){
            pinCounter++;
            return String.format("%04d", pinCounter);
        }
        else return String.format("%04d", Integer.parseInt(availablePins.pop()));
    }

    public static void addPin(String pin){
        availablePins.offer(pin);
    }

    public static String getBarcode(){
        if(availableBarcode.isEmpty()){
            barcodeCounter++;
            return String.format("%05d", barcodeCounter);
        }
        else return String.format("%05d", Integer.parseInt(availableBarcode.pop()));
    }

    public static void addBarcode(String barcode){
        availableBarcode.offer(barcode);
    }

}
