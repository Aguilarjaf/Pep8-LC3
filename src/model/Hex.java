package model;

/**
 * This is the Hex model class of the simulator. This class allows us to work 
 * with the hex data type and provides several methods for performing
 * hex conversions.
 * 
 * @author Jorge Aguilar, Nathan Stickler, Wynn Siripanich, Yangchen Zhong
 * @version October 25, 2020
 * */
  
public class Hex extends Number {
	
	/**
	 * A string variable to store the hex value
	 * */
	private String hexNumber;
	
	/**
	 * Class constructor for empty parameters. Initializes hexNumber value to zero.
	 * */
	public Hex() {
		super();
		this.hexNumber = "0";
	}
	
	/**
	 * Class constructor when a decimal integer is passed through the parameter.
	 * Initializes hexNumber value to that integer.
	 * 
	 * @param number - A decimal value to be converted to hex
	 * */
	public Hex(final int number) {
		super();
		super.myNumber = Integer.toHexString(number);
		this.hexNumber = super.myNumber;
	}
	
	/**
	 * Class constructor when an Hex object is passed to parameter.
	 * Initializes hexNumber value to the value of that object.
	 * 
	 * @param number - A Hex object containing a hex value
	 * */
	public Hex(final Hex number) {
		super.myNumber = number.getValue();
		this.hexNumber = super.myNumber;
	}
	
	/**
	 * Class constructor when an String representation of a decimal is passed
	 * to the parameter.
	 * Initializes hexNumber value to the value of that decimal.
	 * 
	 * @param numberStr - A string representation of a decimal number
	 * */
	public Hex(final String numberStr) {
		int hexVal = Integer.parseInt(numberStr);
		super.myNumber = Integer.toHexString(hexVal);
		this.hexNumber = super.myNumber;
	}
	
	/**
	 * Sets the value of the hexNumber String to the value passed
	 * within the parameter.
	 * 
	 * @param number - A decimal integer
	 * */
	@Override
	public void setValue(final int number) {
		super.myNumber = Integer.toHexString(number);
		this.hexNumber = super.myNumber;
	}
	
	/**
	 * Returns the hex number stored in the hexNumber String
	 * of the class.
	 * 
	 * @return value - A hex number.
	 * */
	public String getValue() {
		String value = "";
		value += this.hexNumber;
		return value;
	}

	/**
	 * Compares two Hex objects with respect to their hex
	 * values. Returns 1 if the first object is greater than, 0 if they are 
	 * equal, or -1 if the first object is less than.
	 * 
	 * @param theOther - Another Hex object.
	 * @return result - A positive, zero, or negative integer.
	 * */
	public int compareTo(Hex theOther) {
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
				if(Integer.valueOf(super.myNumber.charAt(i)) > Integer.valueOf(theOther.getValue().charAt(i))) {
					result = 1;
				} else if(Integer.valueOf(super.myNumber.charAt(i)) < Integer.valueOf(theOther.getValue().charAt(i))) {
					result = -1;
				}
			}
		}
		return result;
	}
	
	/**
	 * Checks if two Hex objects are equal.
	 * 
	 * @param theHex - Another object of type Hex
	 * @return A boolean value that indicates whether two Hex objects are equal
	 * */
	public boolean equals(final Hex theHex) {
		return super.myNumber.equals(theHex.getValue());
	}

	/**
	 * Converts the hex value to a binary value.
	 * 
	 * @return The binary equivalent of the hex value.
	 * */
	public String toBinary() {
		return Integer.toBinaryString(Integer.parseInt(super.myNumber, 16));
	}
	
	/**
	 * Converts the hex value to a decimal value.
	 * 
	 * @return The decimal equivalent of the hex value.
	 * */
	public String toDecimal() {
		return Integer.toString(Integer.parseInt(super.myNumber, 16));
	}
	
	/**
	 * Converts a binary value to a hex value.
	 * 
	 * @param binaryString - A string representation of a binary value.
	 * @return hexString - The hex equivalent of the binary value.
	 * */
	public String binaryToHex(String binaryString) {
		
		/**
		 * The code for converting a binary string to hex was provided by user 
		 * Eran on stackoverflow. All credit for the following two lines is
		 * attributed to user Eran.
		 * 
		 * You may access their solution at the link below:
		 * https://stackoverflow.com/questions/25592084/converting-binary-string-to-a-hexadecimal-string-java
		 * */
		int decimalVal = Integer.parseInt(binaryString, 2);
		String hexString = Integer.toString(decimalVal, 16);
		
		return hexString;
	}
	
	/**
	 * Converts a sign extended binary value to a hex value.
	 * 
	 * @param binaryString - A string representation of a sign extended binary value.
	 * @return hexString - The hex equivalent of the binary value.
	 * */
	public String extendedBinaryToHex(String binaryString) {
		
		int decimalVal = Integer.parseInt(binaryString, 2);
		StringBuilder hexString = new StringBuilder(Integer.toString(decimalVal, 16));
		
		// Sign extend
		while (hexString.length() % 2 != 0) hexString.insert(0, "0");
		
		return hexString.toString();
	}

}