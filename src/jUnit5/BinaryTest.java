package jUnit5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Binary;

/**
 * This is the JUnit test class of the Binary model class. The Binary class allows us
 * to access data in Binary and contains methods to make appropriate Binary conversions. 
 * This JUnit class will ensure full coverage of the Binary class.
 * 
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */
class BinaryTest {
	
	/**
	 * Creates an object of type Binary
	 * */
	Binary B0;
	
	/**
	 * Creates an object of type Binary
	 * */
	Binary B1;
	
	/**
	 * Creates an object of type Binary
	 * */
	Binary B2;
	
	/**
	 * Creates an object of type Binary
	 * */
	Binary B3;
	
	/**
	 * Initializes all Binary objects
	 * */
	@BeforeEach
	void setUp() throws Exception {
		B0 = new Binary();
		B1 = new Binary();
		B2 = new Binary();
		B3 = new Binary();
	}

	/**
	 * Restores memory to CPU by setting all objects to null
	 * */
	@AfterEach
	void tearDown() throws Exception {
		B0 = null;
		B1 = null;
		B2 = null;
		B3 = null;
	}

	/**
	 * Tests the constructors of the Binary class
	 * */
	@Test
	void constructorsTest() {
		B0 = new Binary();
		assertEquals("Binary default constructor failed", "0", B0.getValue());
		
		B1 = new Binary(44);
		assertEquals("Binary integer constructor failed", "101100", B1.getValue());
		
		B2 = new Binary("554");
		assertEquals("Binary string constructor failed", "1000101010", B2.getValue());
		
		B3 = new Binary(B1);
		assertEquals("Binary copy constructor failed", "101100", B1.getValue());
	}
	
	/**
	 * Tests the set method of the Binary class
	 * */
	@Test
	void setTest() {
		Binary B1 = new Binary(77);
		B1.setValue(11);
		assertEquals("Binary setValue failed", "1011", B1.getValue());
	}
	
	/**
	 * Tests the compareTo method of the Binary class
	 * */
	@Test
	void compareToTest() {
		Binary B1 = new Binary(41);
		Binary B2 = new Binary(553);
		assertEquals("Binary compareTo failed", 1, B2.compareTo(B1));
		B1.setValue(555);
		assertEquals("Binary compareTo failed", -1, B2.compareTo(B1));
		B1.setValue(553);
		assertEquals("Binary compareTo failed", 0, B2.compareTo(B1));
	}
	
	/**
	 * Tests the equals method of the Binary class
	 * */
	@Test
	void equalsTest() {
		Binary B1 = new Binary(66);
		Binary B2 = new Binary(66);
		assertTrue("Binary equals failed", B1.getValue().equals(B2.getValue()));
	}
	
	/**
	 * Tests the toDecimal method of the Binary class
	 * */
	@Test
	void toDecimalTest() {
		Binary B1 = new Binary(41);
		assertEquals("Binary toDecimal failed", "41", B1.toDecimal());
		Binary B2 = new Binary(553);
		assertEquals("Binary toDecimal failed", "553", B2.toDecimal());
	}
	
	/**
	 * Tests the toHex method of the Binary class
	 * */
	@Test
	void toHexTest() {
		Binary B1 = new Binary(41);
		Binary B2 = new Binary(553);
		assertEquals("Binary toHex failed", "29", B1.toHex());
		assertEquals("Binary toHex failed", "229", B2.toHex());
	}
	
	/**
	 * Tests the conversion methods of the Binary class (such as hex to binary,
	 * ASCII to binary, and decimal to binary)
	 * */
	@Test
	void testConversions() {
		assertEquals("Decimal to Binary failed!", "1001", B1.hexToBinary("0009"));
		assertEquals("Decimal to Binary failed!", "00001001", B1.extendedHexToBinary("0009"));
		assertEquals("Decimal to Binary failed!", "01000001", B1.asciiToBinary('A'));
		assertEquals("Decimal to Binary failed!", "10", B1.decimalToBinary("2"));
	}

}
