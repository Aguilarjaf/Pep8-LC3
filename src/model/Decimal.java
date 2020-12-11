package model;

/**
 * This is the Decimal model class of the simulator. This class allows us to work 
 * with the decimal data type and provides several methods for performing
 * decimal conversions.
 * 
 * @author Jorge Aguilar, Nathan Stickler, Wynn Siripanich, Yangchen Zhong
 * @version October 25, 2020
 * */
  
public class Decimal extends Number {
	
	/**
	 * The Binary object below allows us to access some of the methods in the 
	 * Binary class.
	 * */
	private Binary binaryClass = new Binary();
	
	/**
	 * A string variable to store the decimal value
	 * */
	private String decimalNumber;

	/**
	 * Class constructor for empty parameters. Initializes decimalNumber value to zero.
	 * */
	public Decimal() {
		super();
		this.decimalNumber = "0";
	}
	
	/**
	 * Class constructor when a decimal integer is passed through the parameter.
	 * Initializes decimalNumber value to that integer.
	 * 
	 * @param number - A decimal value to be stored
	 * */
	public Decimal(final int number) {
		super();
		super.myNumber = Integer.toString(number);
		this.decimalNumber = super.myNumber;
	}
	
	/**
	 * Class constructor when an Decimal object is passed to parameter.
	 * Initializes decimalNumber value to the value of that object.
	 * 
	 * @param number - A Decimal object containing a decimal value
	 * */
	public Decimal(final Decimal number) {
		super.myNumber = number.getValue();
		this.decimalNumber = super.myNumber;
	}
	
	/**
	 * Class constructor when an String representation of a decimal is passed
	 * to the parameter.
	 * Initializes decimalNumber value to the value of that decimal.
	 * 
	 * @param numberStr - A string representation of a decimal number
	 * */
	public Decimal(final String numberStr) {
		super.myNumber = numberStr;
		this.decimalNumber = super.myNumber;
	}
		
	/**
	 * Sets the value of the decimalNumber String to the value passed
	 * within the parameter.
	 * 
	 * @param theNumber - A decimal integer
	 * */
	@Override
	public void setValue(final int theNumber) {
		super.myNumber = Integer.toString(theNumber);
		this.decimalNumber = super.myNumber;
	}
	
	/**
	 * Returns the decimal number stored in the decimalNumber String
	 * of the class.
	 * 
	 * @return value - A decimal number.
	 * */
	public String getValue() {
		String value = "";
		value += this.decimalNumber;
		return value;
	}

	/**
	 * Compares two Decimal objects with respect to their decimal
	 * values. Returns 1 if the first object is greater than, 0 if they are 
	 * equal, or -1 if the first object is less than.
	 * 
	 * @param theOther - Another Decimal object.
	 * @return result - A positive, zero, or negative integer.
	 * */
	public int compareTo(Decimal theOther) {
		int result = 0;
		if(Integer.valueOf(this.decimalNumber) > Integer.valueOf(theOther.getValue())) {
			result = 1;
		} else if(Integer.valueOf(this.decimalNumber) < Integer.valueOf(theOther.getValue())) {
			result = -1;
		}
		return result;
	}
	
	/**
	 * Checks if two Decimal objects are equal.
	 * 
	 * @param theDecimal - Another object of type Decimal
	 * @return A boolean value that indicates whether two Decimal objects are equal
	 * */
	public boolean equals(final Decimal theDecimal) {
		return this.decimalNumber.equals(theDecimal.getValue());
	}
	
	/**
	 * Converts the decimal value to a hex value.
	 * 
	 * @return The hex equivalent of the decimal value.
	 * */
	public String toHex() {
		return Integer.toHexString(Integer.valueOf(super.myNumber));
	}
	
	/**
	 * Converts the decimal value to a binary value.
	 * 
	 * @return The binary equivalent of the decimal value.
	 * */
	public String toBinary() {
		return Integer.toBinaryString(Integer.valueOf(super.myNumber));
	}
	
	/**
	 * Converts the decimal value to a sign extended binary value.
	 * 
	 * @return A sign extended binary equivalent of the decimal value.
	 * */
	public String extendedDecimalToBinary(String value) {
		
		StringBuilder decimalToBinary = new StringBuilder();
		
		decimalToBinary.append(binaryClass.decimalToBinary(value));
		
		while (decimalToBinary.length() % 8 != 0) decimalToBinary.insert(0, "0"); // Sign extendd
		
		return decimalToBinary.toString();
	}
	
	/**
	 * Converts the decimal value to an ASCII value.
	 * 
	 * @return The ASCII equivalent of the decimal value.
	 * */
	public String decimalToAscii(String value) {
		
		/*
		 * The code for converting an integer into ASCII was provided by the user
		 * Sean Patrick Floyd on stackoverflow. All credit for the following two lines
		 * are attributed to user Sean Patrick Floyd.
		 * 
		 * You may access their solution at: 
		 * https://stackoverflow.com/questions/5328996/java-change-int-to-ascii
		 * */
		int decimalVal = Integer.parseInt(value);
		
		char c = (char) decimalVal;
		
		StringBuilder ascii = new StringBuilder();
		
		ascii.append(c);
		
		return ascii.toString();
	}

}
