package jUnit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.buildProgram;

/**
 * This is the JUnit test class for the buildProgram.java class in the controller
 * package. The buildProgram.java class is responsible for assembling any
 * assembly language instructions provided by the user. This JUnit test class will
 * ensure full coverage of the buildProgram.java class.
 * 
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */

class BuildTest {
	
	/**
	 * An object of type buildProgram
	 * */
	static buildProgram builder;
	
	/**
	 * A string representation of the "output" assembly language instructions
	 * */
	static String outputProgram;
	
	/**
	 * A string representation of the "reverse" assembly language instructions
	 * */
	static String reverseProgram;
	
	/**
	 * A string representation of the "sum" assembly language instructions
	 * */
	static String sumProgram;

	/**
	 * Initializes the buildProgram object and assigns value
	 * to the assembly language instruction strings.
	 * */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		builder = new buildProgram();
		
		outputProgram = "CHARO 0x0007, d\n"
				+ "CHARO 0x0008, d\n"
				+ "STOP\n"
				+ ".ASCII \"No\"\n"
				+ ".END";
		
		reverseProgram = "CHARI 0x0007, d ;Input 1st character\n"
				+ "CHARI 0x0008, d\n"
				+ "CHARI 0x0009, d\n"
				+ "CHARI 0x000A, d\n"
				+ "CHARI 0x000B, d\n"
				+ "CHARI 0x000C, d\n"
				+ "CHARI 0x000D, d ;Input 7th character\n"
				+ "\n"
				+ "CHARO 0x000D, d ;Output 7th character\n"
				+ "CHARO 0x000C, d\n"
				+ "CHARO 0x000B, d\n"
				+ "CHARO 0x000A, d\n"
				+ "CHARO 0x0009, d\n"
				+ "CHARO 0x0008, d\n"
				+ "CHARO 0x0007, d ;Output 1st character\n"
				+ "\n"
				+ "STOP\n"
				+ "\n"
				+ ".BLOCK 1 ;Storage for 1st character\n"
				+ ".BLOCK 1 ;Storage for 2nd character\n"
				+ ".BLOCK 1 ;Storage for 3rd character\n"
				+ ".BLOCK 1 ;Storage for 4th character\n"
				+ ".BLOCK 1 ;Storage for 5th character\n"
				+ ".BLOCK 1 ;Storage for 6th character\n"
				+ ".BLOCK 1 ;Storage for 7th character\n"
				+ "\n"
				+ ".END";
		
		sumProgram = "LDA 0x0011, d ;A <- first number\n"
				+ "ADDA 0x0013, d ;Add the two numbers\n"
				+ "ORA 0x0015, d ;Convert sum to character\n"
				+ "STBYTEA 0x0010, d ;Store the character\n"
				+ "CHARO 0x0010, d ;Output the character\n"
				+ "STOP\n"
				+ ".BLOCK 1 ;Character to output\n"
				+ ".WORD 5 ;Decimal 5\n"
				+ ".WORD 3 ;Decimal 3\n"
				+ ".WORD 0x0030 ;Mask for ASCII char\n"
				+ ".END";
	}

	/**
	 * Restores memory to CPU by setting all objects to null
	 * */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		builder = null;
		outputProgram = null;
		reverseProgram = null;
		sumProgram = null;
	}

	/**
	 * Tests the buildProgram.java class by inputing different assembly language instructions
	 * into the class, then processing each instruction.
	 * */
	@Test
	void test() {
		builder = new buildProgram();
		builder.processSourceCode(outputProgram);
		builder.processSourceCode(reverseProgram);
		builder.processSourceCode(sumProgram);
	}

}
