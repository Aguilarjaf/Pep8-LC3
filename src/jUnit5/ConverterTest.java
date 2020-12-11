package jUnit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.convertDataType;

/**
 * This is the JUnit test class for the converDataType.java class in the controller package. 
 * The convertDataType.java class provides functionality to the conversion tool
 * at the top of our GUI. This allows the user to input decimal values and provides
 * immediate Binary, Hex, and ASCII conversions. The convertDataType.java class is also
 * set up to represent the opcode related to the value that the user has input. This
 * JUnit test class will ensure full coverage of the convertDataType.java class.
 * 
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */

class ConverterTest {
	
	/**
	 * An object of type convertDataType
	 * */
	static convertDataType converterOne;
	
	/**
	 * An object of type convertDataType
	 * */
	static convertDataType converterTwo;
	
	/**
	 * An object of type convertDataType
	 * */
	static convertDataType converterThree;
	
	/**
	 * An object of type convertDataType
	 * */
	static convertDataType converterFour;
	
	/**
	 * A string representation of a decimal value
	 * */
	static String decimalValOne;
	
	/**
	 * A string representation of a decimal value
	 * */
	static String decimalValTwo;
	
	/**
	 * A string representation of a decimal value
	 * */
	static String decimalValThree;
	
	/**
	 * A string representation of a decimal value
	 * */
	static String decimalValFour;

	/**
	 * Initializes all objects and assigns values to strings
	 * */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		decimalValOne = "93";
		decimalValTwo = "100";
		decimalValThree = "79";
		decimalValFour = "88";
		
		converterOne = new convertDataType(decimalValOne);
		converterTwo = new convertDataType(decimalValTwo);
		converterThree = new convertDataType(decimalValThree);
		converterFour = new convertDataType(decimalValFour);
		
	}

	/**
	 * Restores memory to CPU by setting all objects and variables to null
	 * */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		converterOne = null;
		converterTwo = null;
		converterThree = null;
		converterFour = null;
		
		decimalValOne = null;
		decimalValTwo = null;
		decimalValThree = null;
		decimalValFour = null;
	}

	/**
	 * Tests the conversion tool
	 * */
	@Test
	void test() {
		
		converterOne = new convertDataType(decimalValOne);
		converterTwo = new convertDataType(decimalValTwo);
		converterThree = new convertDataType(decimalValThree);
		converterFour = new convertDataType(decimalValFour);
		
		converterTwo.insertUpdate(null);
		converterTwo.removeUpdate(null);
		converterTwo.changedUpdate(null);
		
		converterThree.insertUpdate(null);
		converterThree.removeUpdate(null);
		converterThree.changedUpdate(null);
		
		converterFour.insertUpdate(null);
		converterFour.removeUpdate(null);
		converterFour.changedUpdate(null);
	}

}
