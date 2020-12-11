package jUnit5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Hex;

/**
 * This is the JUnit test class of the Hex model class. The Hex class allows us
 * to access data in Hex and contains methods to make appropriate Hex conversions. 
 * This JUnit class will ensure full coverage of the Hex class.
 * 
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */

class HexTest {

	/**
	 * Creates an object of type Hex
	 * */
	Hex H0;
	
	/**
	 * Creates an object of type Hex
	 * */
	Hex H1;
	
	/**
	 * Creates an object of type Hex
	 * */
	Hex H2;
	
	/**
	 * Creates an object of type Hex
	 * */
	Hex H3;
	
	/**
	 * Initializes all Hex objects
	 * */
	@BeforeEach
	void setUp() throws Exception {
		H0 = new Hex();
		H1 = new Hex(41);
		H2 = new Hex("553");
		H3 = new Hex(H1);
	}

	/**
	 * Restores memory to CPU by setting all objects to null
	 * */
	@AfterEach
	void tearDown() throws Exception {
		H0 = null;
		H1 = null;
		H2 = null;
		H3 = null;
	}

	/**
	 * Tests the constructors of the Hex class
	 * */
	@Test
	void constructorsTest() {
		H0 = new Hex();
		assertEquals("Hex default constructor failed", "0", H0.getValue());
		H1 = new Hex(41);
		assertEquals("Hex integer constructor failed", "29", H1.getValue());
		H2 = new Hex("553");
		assertEquals("Hex string constructor failed", "229", H2.getValue());
		H3 = new Hex(H1);
		assertEquals("Hex copy constructor failed", "29", H1.getValue());
	}
	
	/**
	 * Tests the set method of the Hex class
	 * */
	@Test
	void setTest() {
		H1 = new Hex(55);
		H1.setValue(176);
		assertEquals("Hex setValue failed", "B0", H1.getValue().toUpperCase());
	}
	
	/**
	 * Tests the compareTo method of the Hex class
	 * */
	@Test
	void compareToTest() {
		H1 = new Hex(41);
		H2 = new Hex(553);
		assertEquals("Hex compareTo failed", 1, H2.compareTo(H1));
		H1.setValue(555);
		assertEquals("Hex compareTo failed", -1, H2.compareTo(H1));
		H1.setValue(553);
		assertEquals("Hex compareTo failed", 0, H2.compareTo(H1));
	}
	
	/**
	 * Tests the equals method of the Hex class
	 * */
	@Test
	void equalsTest() {
		H1 = new Hex(66);
		H2 = new Hex(66);
		assertTrue("Hex equals failed", H1.equals(H2));
	}
	
	/**
	 * Tests the toDecimal method of the Hex class
	 * */
	@Test
	void toDecimalTest() {
		H1 = new Hex(41);
		assertEquals("Hex toDecimal failed", "41", H1.toDecimal());
		H2 = new Hex(553);
		assertEquals("Hex toDecimal failed", "553", H2.toDecimal());
	}
	
	/**
	 * Tests the toBinary method of the Hex class
	 * */
	@Test
	void toBinaryTest() {
		H1 = new Hex(41);
		H2 = new Hex(553);
		assertEquals("Hex toBinary failed", "101001", H1.toBinary());
		assertEquals("Hex toBinary failed", "1000101001", H2.toBinary());
	}
	
	/**
	 * Tests the binaryToHex method of the Hex class
	 * */
	@Test 
	void binaryToHex() {
		assertEquals("Binary to Hex failed!", "2", H1.binaryToHex("10"));
		assertEquals("Extended Binary to Hex failed!", "02", H1.extendedBinaryToHex("00000010"));
	}
}
