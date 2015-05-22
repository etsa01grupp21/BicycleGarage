package Garage;


import java.util.ArrayDeque;
import java.util.Deque;

public class CodeGenerator {
    private static int pinCounter = -1;
    private static Deque<String> availablePins = new ArrayDeque<>();

    public static String getPin(){
        if(availablePins.isEmpty()){
            pinCounter++;
            return String.format("%04d", pinCounter);
        }
        else return String.format("%04d", Integer.parseInt(availablePins.pop()));
    }

    public static void addPin(String pin){
        System.out.println(pin);
        availablePins.offer(pin);
    }
}
