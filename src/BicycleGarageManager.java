public class BicycleGarageManager {
	BarcodePrinter printer;
	ElectronicLock entryLock;
	ElectronicLock exitLock;
	PinCodeTerminal terminal;
	
	/* Register hardware so that BicycleGarageManager
	 * knows which drivers to access. 
	 */
	public void registerHardwareDrivers(BarcodePrinter printer,
	     ElectronicLock entryLock, ElectronicLock exitLock,
	     PinCodeTerminal terminal) {
		this.printer = printer;
		this.entryLock = entryLock;
		this.exitLock = exitLock;
		this.terminal = terminal;
	}

	/* Will be called when a user has used the bar code
	 * reader at the entry door. Bicycle ID should be a
	 * string of 5 characters, where every character
	 * can be '0', '1',... '9'. */
	public void entryBarcode(String bicycleID) {
		/*
		 * Registrera ägaren av cykeln som inne i garaget
		 * Lås upp dörren
		 */
	}

	/* Will be called when a user has used the bar code
	 * reader at the exit door. Bicycle ID should be a
	 * string of 5 characters, where every
	 * character can be '0', '1',... '9'. */
	public void exitBarcode(String bicycleID) {
		/*
		 * Om (ägaren av cykeln är i garaget) {
		 * 		Lås upp dörren && registrera ägaren som utanför
		 * } annars {
		 * 		Gör inget (?)
		 * }
		 */
	}

	/* Will be called when a user has pressed a key at the
	 * keypad at the entry door. The following characters could be
	 * pressed: '0', '1',... '9', '*', '#'. */
	public void entryCharacter(char c) {
		/*
		 * 
		 */
	}

}
