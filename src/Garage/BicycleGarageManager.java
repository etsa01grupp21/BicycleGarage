package Garage;

import Interfaces.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class BicycleGarageManager {
	BarcodePrinter printer;
	PinCodeTerminal terminal;
	ElectronicLock entryLock;
	ElectronicLock exitLock;

	private Map<String, User> pinToUser;
	private Map<String, User> idToUser;
	private Map<String, Bicycle> barcodeToBicycle;
	private List<User> users;
	private StringBuilder pin;
	private long lastPIN;
	
	private Queue<Long> lockoutQueue;
	boolean locked;

	public BicycleGarageManager() {
		pin = new StringBuilder();
		pinToUser = new HashMap<>();
		idToUser = new HashMap<>();
		barcodeToBicycle = new HashMap<>();
		users = new ArrayList<>();
		lockoutQueue = new LinkedList<Long>();
		lockoutQueue.offer(System.currentTimeMillis());
		locked = false;
	}

	/*
	 * Register hardware so that Garage.BicycleGarageManager knows which drivers
	 * to access.
	 */
	public void registerHardwareDrivers(BarcodePrinter printer,
			ElectronicLock entryLock, ElectronicLock exitLock,
			PinCodeTerminal terminal) {
		this.printer = printer;
		this.entryLock = entryLock;
		this.exitLock = exitLock;
		this.terminal = terminal;
	}

	public boolean addUser(User user) {
		if (idToUser.get(user.getId()) != null)
			return false;
		else {
			idToUser.put(user.getId(), user);
			pinToUser.put(user.getPin(), user);
			users.add(user);
			fileWrite();
		}
		return true;
	}

	public boolean removeUser(User user) {
		User u = idToUser.get(user.getId());
		if (u == null)
			return false;
		else {
			for (Bicycle bike : u.getBicycles()) {
				removeBicycle(bike);
			}
			idToUser.remove(u.getId());
			pinToUser.remove(u.getPin());
			CodeGenerator.freePin(u.getPin());
			users.remove(u);
		}
		fileWrite();
		return true;
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUser(String id) {
		return idToUser.get(id);
	}

	public void addBicycle(Bicycle bike) {
		barcodeToBicycle.put(bike.getBarcode(), bike);
		printer.printBarcode(bike.getBarcode());
		fileWrite();
	}

	public void removeBicycle(Bicycle bike) {
		barcodeToBicycle.remove(bike.getBarcode());
		CodeGenerator.addBarcode(bike.getBarcode());
		fileWrite();
	}

	public void updatePin(User user) {
		String oldPin = user.getPin();
		pinToUser.remove(oldPin);
		String newPin = CodeGenerator.generatePin();
		pinToUser.put(newPin, user);
		user.setPin(newPin);
		fileWrite();
	}

	public Bicycle getBicycle(String barcode) {
		return barcodeToBicycle.get(barcode);
	}

	/*
	 * Will be called when a user has used the bar code reader at the entry
	 * door. Garage.BicycleGarageManager.Bicycle ID should be a string of 5
	 * characters, where every character can be '0', '1',... '9'.
	 */
	public void entryBarcode(String bicycleID) {
		if (barcodeToBicycle.get(bicycleID) != null) {
			barcodeToBicycle.get(bicycleID).setInside(true);
			fileWrite();
			entryLock.open(15);
		}
	}

	/*
	 * Will be called when a user has used the bar code reader at the exit door.
	 * Garage.BicycleGarageManager.Bicycle ID should be a string of 5
	 * characters, where every character can be '0', '1',... '9'.
	 */
	public void exitBarcode(String bicycleID) {
		Bicycle bike = barcodeToBicycle.get(bicycleID);
		if (bike != null && bike.getOwner().isInside()) {
			bike.getOwner().setInside(false);
			fileWrite();
			bike.setInside(false);
			exitLock.open(15);
		}
	}

	/*
	 * Will be called when a user has pressed a key at the keypad at the entry
	 * door. The following characters could be pressed: '0', '1',... '9', '*',
	 * '#'.
	 */
	public void entryCharacter(char c) {
		if (System.currentTimeMillis() - lastPIN > 3000) {
			pin.setLength(0);
		}
		pin.append(c);
		lastPIN = System.currentTimeMillis();
		if (pin.length() > 3) {
			if (System.currentTimeMillis() - lockoutQueue.peek() >= 30000) {
				locked = false;
			}
			User currentUser = pinToUser.get(pin.toString());
			if (currentUser == null) {
				if (!locked) {
					terminal.lightLED(0, 3);
					lockout();
				}
			} else if (!locked) {
				for (int i = 0; i < currentUser.getBicycles().size(); i++) {
					if (currentUser.getBicycles().get(i).isInside()) {
						currentUser.setInside(true);
						entryLock.open(15);
					}
				}
				if (!currentUser.isInside()) {
					terminal.lightLED(0, 3);
					terminal.lightLED(1, 3);
				} else {
					terminal.lightLED(1, 3);
				}
			}
			pin.setLength(0);
		}
	}

	private void lockout() {
		if (!locked) {
			if (lockoutQueue.size() < 5) {
				if (System.currentTimeMillis() - lockoutQueue.peek() < 30000) {
					lockoutQueue.offer(System.currentTimeMillis());
				} else {
					lockoutQueue.poll();
					lockoutQueue.offer(System.currentTimeMillis());
				}
			} else {
				terminal.lightLED(0, 30);
				while (lockoutQueue.size() > 1) {
					lockoutQueue.poll();
				}
				locked = true;
			}
		}
	}

//	public void populateUsers() {
//		Random rand = new Random();
//		for (int i = 0; i < 10; i++) {
//			String s = String.valueOf(i);
//			User u = new User("YOLOSWAG#" + s, s, s);
//			int nbrBikes = rand.nextInt(10) + 1;
//			for (int b = 0; b < nbrBikes; b++) {
//				Bicycle bike = new Bicycle("NAME:" + b, u);
//				barcodeToBicycle.put(bike.getBarcode(), bike);
//				u.addBicycle(bike);
//				int nbrStamps = rand.nextInt(10) + 1;
//				for (int j = 0; j < nbrStamps; j++) {
//					bike.setInside(true);
//					try {
//						Thread.sleep(5);
//					} catch (InterruptedException ex) {
//						Thread.currentThread().interrupt();
//					}
//					bike.setInside(false);
//				}
//
//			}
//			addUser(u);
//		}
//	}

	public void addTestUsers() {
		User u1 = new User("Bertil Gunnarsson", "190010102020", "0720573294",
				"0010");
		Bicycle b1 = new Bicycle("Big One", u1, "00050");
		u1.addBicycle(b1);
		addUser(u1);
		addBicycle(b1);

		User u2 = new User("Greger Hjortklo", "200010102020", "0738676359",
				"0100");
		Bicycle b2 = new Bicycle("Sketen", u2, "00030");
		u2.addBicycle(b2);
		addUser(u2);
		addBicycle(b2);
	}

	@SuppressWarnings("resource")
	private void fileWrite() {
		List<Object> saveList = new ArrayList<>();
		saveList.add(users);
		saveList.add(pinToUser);
		saveList.add(idToUser);
		saveList.add(barcodeToBicycle);
		CodeGenerator.saveCodeGenerator();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("save.ser"));
			out.writeObject(saveList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public boolean fileRead() {
		List<Object> readList;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"save.ser"));
			readList = (List<Object>) in.readObject();
			users = (List<User>) readList.get(0);
			pinToUser = (Map<String, User>) readList.get(1);
			idToUser = (Map<String, User>) readList.get(2);
			barcodeToBicycle = (Map<String, Bicycle>) readList.get(3);
			CodeGenerator.readCodeGenerator();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}