package jUnit5;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Decimal;

/**
 * This is the JUnit test class of the Decimal model class. The Decimal class allows us
 * to access data in Decimal and contains methods to make appropriate Decimal conversions. 
 * This JUnit class will be ensuring full coverage of the Decimal class.
 * 
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */

class DecimalTest {
	
	/**
	 * Creates an object of type Decimal
	 * */
	static Decimal C0;
	
	/**
	 * Creates an object of type Decimal
	 * */
	static Decimal C1;
	
	/**
	 * Creates an object of type Decimal
	 * */
	static Decimal C2;
	
	/**
	 * Creates an object of type Decimal
	 * */
	static Decimal C3;

	/**
	 * Initializes all Decimal objects
	 * */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		C0 = new Decimal();
		assertEquals("Decimal default constructor failed", "0", C0.getValue());
		
		C1 = new Decimal(41);
		assertEquals("Decimal default constructor failed", "41", C1.getValue());
		
		C2 = new Decimal("553");
		assertEquals("Decimal default constructor failed", "553", C2.getValue());
		
		C3 = new Decimal(C1);
		assertEquals("Decimal default constructor failed", "41", C1.getValue());
	}

	/**
	 * Returns memory to CPU by setting all objects to null
	 * */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		C0 = null;
		
		C1 = null;
		
		C2 = null;;
		
		C3 = null;
	}

	/**
	 * Tests the set method of the Decimal class
	 * */
	@Test
	void setTest() {
		C1 = new Decimal(41);
		C1.setValue(101);
		assertEquals("Decimal setValue failed", "101", C1.getValue());
	}
	
	/**
	 * Tests the compereTo method of the Decimal class
	 * */
	@Test
	void compareToTest() {
		C1 = new Decimal(41);
		
		C2 = new Decimal(553);
		assertEquals("Decimal compareTo failed", 1, C2.compareTo(C1));
		
		C1.setValue(555);
		assertEquals("Decimal compareTo failed", -1, C2.compareTo(C1));
		
		C1.setValue(553);
		assertEquals("Decimal compareTo failed", 0, C2.compareTo(C1));
	}
	
	/**
	 * Tests the equals method of the Decimal class
	 * */
	@Test
	void equalsTest() {
		Decimal C1 = new Decimal(66);
		Decimal C2 = new Decimal(66);
		assertTrue("Decimal equals failed", C1.equals(C2));
	}
	
	/**
	 * Tests the toHex method of the Decimal class
	 * */
	@Test
	void toHexTest() {
		Decimal C1 = new Decimal(41);
		assertEquals("Decimal toHex failed", "29", C1.toHex());
		Decimal C2 = new Decimal(553);
		assertEquals("Decimal toHex failed", "229", C2.toHex());
	}
	
	/**
	 * Tests the toBinary method of the Decimal class
	 * */
	@Test
	void toBinaryTest() {
		Decimal C1 = new Decimal(41);
		Decimal C2 = new Decimal(553);
		assertEquals("Decimal toBinary failed", "101001", C1.toBinary());
		assertEquals("Decimal toBinary failed", "1000101001", C2.toBinary());
	}
	
	/**
	 * Tests the conversion methods of the Decimal class (from decimal to
	 * binary and form decimal to ASCII)
	 * */
	@Test
	void conversionsTest() {
		C1.extendedDecimalToBinary("79");
		C1.decimalToAscii("79");
	}

}
