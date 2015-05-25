package Garage;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class CodeGenerator {
    private static int barcodeCounter = -1;
    private static Set<String> takenBarcodes = new HashSet<>();
    private static Deque<String> availableBarcodes = new ArrayDeque<>();
    private static Set<String> takenPins = new HashSet<>();

    public static String generatePin() {
    	String pin = String.format("%04d", (int) (Math.random()*10000));
    	if (takenPins.contains(pin)) {
    		return generatePin();
    	}else {
    		takenPins.add(pin);
        	return pin;
    	}
    }

    public static void addPin(String pin){
        takenPins.add(pin);
    }

    public static boolean freePin(String pin) {
        return takenPins.remove(pin);
    }

    public static void addTakenBarcode(String barcode){
        takenBarcodes.add(barcode);
    }

    public static String getBarcode(){
        if(availableBarcodes.isEmpty()){
            barcodeCounter++;
            String barcode = String.format("%05d", barcodeCounter);
            while (takenBarcodes.contains(barcode)){
                barcodeCounter++;
                barcode = String.format("%05d", barcodeCounter);
            }
            takenBarcodes.add(barcode);
            return barcode;
        }
        else return String.format("%05d", Integer.parseInt(availableBarcodes.pop()));
    }

    public static void addBarcode(String barcode){
        takenBarcodes.remove(barcode);
        availableBarcodes.offer(barcode);
    }

    @SuppressWarnings("resource")
	public static void saveCodeGenerator(){
        List<Object> saveList = new ArrayList<>();
        saveList.add(availableBarcodes);
        saveList.add(takenPins);
        saveList.add(barcodeCounter);
        saveList.add(takenBarcodes);
        try{
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream("codegenerator.ser"));
            out.writeObject(saveList);
        }catch( Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    @SuppressWarnings({ "resource", "unchecked" })
	public static void readCodeGenerator(){
        List<Object> readList = new ArrayList<>();
        try{
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream("codegenerator.ser"));
            readList = (List<Object>) in.readObject();
            availableBarcodes = (Deque<String>) readList.get(0);
            takenPins = (Set<String>) readList.get(1);
            barcodeCounter = (int) readList.get(2);
            takenBarcodes = (Set<String>) readList.get(3);
        }catch( Exception e){
            e.printStackTrace();
        }
    }
}
