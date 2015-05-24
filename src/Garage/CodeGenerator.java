package Garage;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class CodeGenerator {
    private static int barcodeCounter = -1;
    private static Deque<String> availableBarcode = new ArrayDeque<>();
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

    public static boolean freePin(String pin) {
    	return takenPins.remove(pin);
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
        saveList.add(takenPins);
        saveList.add(barcodeCounter);
        try{
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream("codegenerator.ser"));
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
                    new ObjectInputStream(new FileInputStream("codegenerator.ser"));
            readList = (List<Object>) in.readObject();
            availableBarcode = (Deque<String>) readList.get(0);
            takenPins = (Set<String>) readList.get(1);
            barcodeCounter = (int) readList.get(2);
        }catch( Exception e){
            e.printStackTrace();
        }

    }

}
