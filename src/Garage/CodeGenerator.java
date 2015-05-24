package Garage;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

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

    public static void saveCodeGenerator(){
        List<Object> saveList = new ArrayList<>();
        saveList.add(availableBarcode);
        saveList.add(availablePins);
        saveList.add(barcodeCounter);
        saveList.add(pinCounter);
        try{
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream("codegenerator"));
            out.writeObject(saveList);
        }catch( Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void readCodeGenerator(){
        List<Object> readList = new ArrayList<>();
        try{
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream("codegenerator"));
            readList = (List<Object>) in.readObject();
            availableBarcode = (Deque<String>) readList.get(0);
            availablePins = (Deque<String>) readList.get(1);
            barcodeCounter = (int) readList.get(2);
            pinCounter = (int) readList.get(3);
        }catch( Exception e){
            e.printStackTrace();
        }

    }

}
