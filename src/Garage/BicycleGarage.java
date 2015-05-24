package Garage;

import GUI.GarageGUI;
import Interfaces.BarcodePrinter;
import Interfaces.BarcodeReader;
import Interfaces.ElectronicLock;
import Interfaces.PinCodeTerminal;
import TestDrivers.*;

public class BicycleGarage {
    public BicycleGarage() {
        BicycleGarageManager manager = new BicycleGarageManager();
        new GarageGUI(manager);
        ElectronicLock entryLock = new ElectronicLockTestDriver("Entry lock");
        ElectronicLock exitLock = new ElectronicLockTestDriver("Exit lock");
        BarcodePrinter printer = new BarcodePrinterTestDriver();
        PinCodeTerminal terminal = new PinCodeTerminalTestDriver();
        manager.registerHardwareDrivers(printer, entryLock, exitLock, terminal);
        terminal.register(manager);
        BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
        BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
        readerEntry.register(manager);
        readerExit.register(manager);
    }
    public static void main(String[] args) {
        new BicycleGarage();
    }
}