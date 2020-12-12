package model;

/**
 * The class below is our abstract number class that supports the
 * basic operations of data types. These basic operations
 * check for equality, comparison, and return the value in a 
 * printable format.
 * 
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */
  
public abstract class Number {

	protected String myNumber;
	
	/**
	 * Constructor for the abstract Number class. Initializes our
	 * number to an empty string.
	 * */
	public Number() { 
		myNumber = "";
	}
	
	/**
	 * Returns the number saved in the abstract Number class.
	 * 
	 * @return myNumber - A string representation of the number stored.
	 * */
	public String getValue() {
		return myNumber;
	}
	
	/**
	 * Sets the value of the number stored in the abstract Number class.
	 * 
	 * @param theNumber - An integer representation of the number to be stored.
	 * */
	void setValue(final int theNumber) {
		myNumber += theNumber;
	}
	
	/**
	 * The method below checks if two data types are equal.
	 * 
	 * @param Two different data types.
	 * @return A boolean value that determines whether both data types are equal.
	 * */
	public boolean equals(Object other) {
		
		if (myNumber.toString().equals(other.toString())) return true;
		else return false;
	}
	
	/**
	 * The method below checks if two data types are comparable.
	 * 
	 * */
	public int compareTo(Object other) {
		
		String numberString = this.myNumber.toString();
		
		// Positive numbers mean numberString is greater than other
		if (numberString.compareTo(other.toString()) > 0) return 1;
		
		// 0 means both objects are equal
		else if (numberString.compareTo(other.toString()) == 0) return 0;
		
		// Negative numbers mean numberString is less than other
		else return -1;
	}
	
	/**
	 * The method below returns a string representation of the data type.
	 * 
	 * @param A data type.
	 * @return A string representation of that data type.
	 * */
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.getClass().getName());
		result.append("number: ");
		result.append(myNumber);
		result.append("\n");
		return result.toString();
	}
}
