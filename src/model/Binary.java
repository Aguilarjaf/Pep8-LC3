package model;

/**
 * This is the Binary model class of the simulator. This class allows us to work 
 * with the binary data type and provides several methods for performing
 * binary conversions.
 * 
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */

import java.math.BigInteger;
  
public class Binary extends Number {
	
	/**
	 * A string variable to store the binary value
	 * */
	private String binaryNumber;
	
	/**
	 * Class constructor for empty parameters. Initializes binaryNumber value to zero.
	 * */
	public Binary() {
		super();
		this.binaryNumber = "0";
	}
	
	/**
	 * Class constructor when an integer is passed to parameter.
	 * Initializes binaryNumber value to that integer.
	 * 
	 * @param number - A decimal value to be converted to binary
	 * */
	public Binary(final int number) {
		super();
		super.myNumber = Integer.toBinaryString(number);
		this.binaryNumber = super.myNumber;
	}
	
	/**
	 * Class constructor when an Binary object is passed to parameter.
	 * Initializes binaryNumber value to the value of that object.
	 * 
	 * @param number - A Binary object containing a binary value
	 * */
	public Binary(final Binary number) {
		super.myNumber = number.getValue();
		this.binaryNumber = super.myNumber;
	}
	
	/**
	 * Class constructor when an String representation of a decimal is passed
	 * to the parameter.
	 * Initializes binaryNumber value to the value of that decimal.
	 * 
	 * @param numberStr - A string representation of a decimal number
	 * */
	public Binary(final String numberStr) {
		int number = Integer.parseInt(numberStr);
		super.myNumber = Integer.toBinaryString(number);
		this.binaryNumber = super.myNumber;
	}

	/**
	 * Sets the value of the binaryNumber String to the value passed
	 * within the parameter.
	 * 
	 * @param theNumber - A decimal integer
	 * */
	@Override
	public void setValue(final int theNumber) {
		super.myNumber = Integer.toBinaryString(theNumber);
		this.binaryNumber = super.myNumber;
	}
	
	/**
	 * Returns the binary number stored in the binaryNumber String
	 * of the class.
	 * 
	 * @return value - A binary number.
	 * */
	public String getValue() {
		String value = "";
		value += this.binaryNumber;
		return value;
	}

	/**
	 * Compares two Binary objects with respect to their binary
	 * values. Returns 1 if the first object is greater than, 0 if they are 
	 * equal, or -1 if the first object is less than.
	 * 
	 * @param theOther - Another Binary object.
	 * @return result - A positive, zero, or negative integer.
	 * */
	public int compareTo(Binary theOther) {
		int result = 0;
		if(this.equals(theOther)) {
			result = 0;
		}
		if(super.myNumber.length() > theOther.getValue().length()) {
			result = 1;
		} else if(super.myNumber.length() < theOther.getValue().length()) {
			result = -1;
		} else {
			for(int i = 0; i < super.myNumber.length(); i++) {
				if(super.myNumber.charAt(i) == '0' && theOther.getValue().charAt(i) == '1') {
					result = -1;
					break;
				} else if(super.myNumber.charAt(i) == '1' && theOther.getValue().charAt(i) == '0') {
					result = 1;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * Checks if two Binary objects are equal.
	 * 
	 * @param theBinary - Another object of type Binary.
	 * @return A boolean value that indicates whether two Binary objects are equal
	 * */
	boolean equals(final Binary theBinary) {
		return super.myNumber.equals(theBinary.getValue());
	}
	
	/**
	 * Converts the binary value to a decimal value.
	 * 
	 * @return The decimal equivalent of the binary value.
	 * */
	public String toDecimal() {
		return Integer.toString(Integer.parseInt(super.myNumber, 2));
	}
	
	/**
	 * Converts the binary value to a hex value.
	 * 
	 * @return The hex equivalent of the binary value.
	 * */
	public String toHex() {
		return Integer.toHexString(Integer.parseInt(super.myNumber, 2));
	}
	
	/**
	 * The method below converts a hex code string into binary.
	 * 
	 * @param The hex code string.
	 * @return The converted binary string.
	 * */
	public String hexToBinary(String hexString) {
		return String.format("%4s", new BigInteger(hexString, 16).toString(2)).replace(' ', '0');
	}
	
	/**
	 * The method below converts an extended hex code string into binary.
	 * 
	 * @param The hex code string.
	 * @return The converted binary string.
	 * */
	public String extendedHexToBinary(String hexString) {
		
		String binaryString = String.format("%4s", new BigInteger(hexString, 16).toString(2)).replace(' ', '0');
		
		StringBuilder signExtend = new StringBuilder(binaryString);
		
		while (signExtend.length() % 8 != 0) signExtend.insert(0, "0");
		
		binaryString = signExtend.toString();
		
		return binaryString;
	}
	
	/**
	 * Converts an ASCII character into binary.
	 * 
	 * @param ASCII - An ASCII character.
	 * @return binaryString - A binary representation of the ASCII character.
	 * */
	public String asciiToBinary(char ASCII) {
		
		String binaryString = Integer.toBinaryString(ASCII);
		/* 
		 * The code for converting an ASCII character into binary was provided by
		 * user m0skit0 and Anthony Raimondo on stackoverflow. All credit for the 
		 * following line is attributed to both users.
		 * 
		 * You may access their solution at the link below: 
		 * https://stackoverflow.com/questions/15606740/converting-a-character-to-its-binary-value
		 * */
		
		StringBuilder signExtend = new StringBuilder(binaryString);
		
		// Adds extra zeros, until we have a binary string of 8 character length
		while (signExtend.length() < 8) signExtend.insert(0, "0");
		
		binaryString = signExtend.toString(); 
		
		return binaryString;
	}
	
	/**
	 * Converts a decimal value into binary.
	 * 
	 * @param value - A string representation of a decimal value.
	 * @return binaryString - A binary representation of the decimal value.
	 * */
	public String decimalToBinary(String value) {
		
		String binaryString = Integer.toBinaryString(Integer.valueOf(value));
		
		return binaryString;

	}
}