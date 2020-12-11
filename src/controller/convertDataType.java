package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Decimal;
import model.Hex;

/**
 * The class below provides functionality to the conversion tool in the 
 * north panel of our simulator GUI. This class makes it possible for the 
 * user to provide decimal input and immediately produces the binary, hex,
 * ASCII, and opcode equivalent of that input.
 *   
 * @author Jorge Aguilar, Nathan Stickler, Wynn Siripanich, Yangchen Zhong
 * @version October 25, 2020
 * */

public class convertDataType extends prgrmController implements DocumentListener {
	
	/**
	 * The Hex variable below allows us to access some of the methods in the 
	 * Hex class.
	 * */
	private Hex hexClass;
	
	/**
	 * The Decimal variable below allows us to access some of the methods in the 
	 * Decimal class.
	 * */
	private Decimal decimalClass;
	
	/**
	 * The variable below stores the decimal value input from the user.
	 * */
	private String decimalVal;
	
	/**
	 * The variable below is a boolean flag that detects whether or not the
	 * program is receiving data from the GUI and its ActionListener(s)
	 * */
	private Boolean guiInput = false;
	
	/**
	 * The constructor below initializes our Hex, Binary, and Decimal objects. 
	 * This constructor also assigns value to our decimalVal variable so that
	 * we can process its conversions in the later methods of this class.
	 * */
	public convertDataType() {
		super();
		hexClass = new Hex();
		decimalClass = new Decimal();
		
		this.decimalVal = prgrmController.theGUI.getDecimalField();
		
		convert(this.decimalVal); // Calls convert method
	}
	
	/**
	 * The constructor below initializes our Hex, Binary, and Decimal objects. 
	 * This constructor also assigns value to our decimalVal variable so that
	 * we can process its conversions in the later methods of this class.
	 * 
	 * @param A string representation of a decimal value (provided manually)
	 * */
	public convertDataType(String value) {
		super();
		hexClass = new Hex();
		decimalClass = new Decimal();
		
		this.decimalVal = value;
		
		convert(this.decimalVal); // Calls convert method
	}

	/**
	 * The method below updates the data type conversions whenever a
	 * value is inserted into the decimal field.
	 * 
	 * @param DocumentEvent instructions.
	 * */
	@Override
	public void insertUpdate(DocumentEvent e) {
		if (e == null) {
			System.out.println("Error: There is no GUI input");
			return;
		}
		else {
			this.guiInput = true;
			convert(prgrmController.theGUI.getDecimalField());
		}
	}

	/**
	 * The method below updates the data type conversions whenever a 
	 * value is removed from the decimal field.
	 * 
	 * @param DocumentEvent instructions.
	 * */
	@Override
	public void removeUpdate(DocumentEvent e) {
		if (e == null) {
			System.out.println("Error: There is no GUI input");
			return;
		}
		else {
			this.guiInput = true;
			convert(prgrmController.theGUI.getDecimalField());
		}
	}
	
	/**
	 * The method below updates the data type conversions whenever the
	 * decimal field is changed.
	 * 
	 * @param DocumentEvent instructions.
	 * */
	@Override
	public void changedUpdate(DocumentEvent e) {
		if (e == null) {
			System.out.println("Error: There is no GUI input");
			return;
		}
		else {
			this.guiInput = true;
			convert(prgrmController.theGUI.getDecimalField());
		}
	}
	
	/**
	 * The method below handles the conversion process between data types.
	 * 
	 * @param A string representation of a decimal value. 
	 * */
	public void convert(String decimalVal) {
		
		this.decimalVal = decimalVal;
		
		// Variable for hex data type
		StringBuilder extendedHex = new StringBuilder();
		
		// Calls blank method if there is no user input
		if (decimalVal.equalsIgnoreCase("") && this.guiInput == true) {
			blank();
			return;
		}
		
		// Variable for binary data type
		String binaryConversion = decimalClass.extendedDecimalToBinary(decimalVal);
		
		extendedHex.append(hexClass.extendedBinaryToHex(binaryConversion));
		
		// Variable for ASCII data type
		String asciiConversion = decimalClass.decimalToAscii(decimalVal);	
		
		while (extendedHex.length() % 4 != 0) extendedHex.insert(0, "0");
		
		extendedHex.insert(0, "0x"); // Formats hex string
		
		// Updates binary, hex, and ASCII text fields
		if (this.guiInput == true)
			updateConverter(binaryConversion, extendedHex.toString().toUpperCase(), asciiConversion);
	}
	
	/**
	 * The method below calls the setter methods of the simulator's GUI so that
	 * the converted data types can be reflected in the conversion tool's text 
	 * fields. This allows the user to see real time conversions.
	 * 
	 * @param A binary, hex, and ASCII conversion string.
	 * */
	public void updateConverter(String binary, String hex, String ascii) {
		prgrmController.theGUI.setBinaryField(binary);
		prgrmController.theGUI.setHexField(hex);
		prgrmController.theGUI.setAsciiField(ascii);
		prgrmController.theGUI.setOpCodeField(" ");
	}
	
	/**
	 * The method below handles the behavior of the converter tool when no data is
	 * provided by the user. This ensures that the simulator does not crash.
	 * */
	public void blank() {
		prgrmController.theGUI.setBinaryField(" ");
		prgrmController.theGUI.setHexField(" ");
		prgrmController.theGUI.setAsciiField(" ");
	}

}
