package jUnit5;

import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.runProgram;

/**
 * This is the JUnit test class for the runProgram.java class in the controller 
 * package. The runProgram.java class executes the system's machine language programs
 * and returns a memory dump from those programs. This JUnit class will be testing
 * how well the runProgram.java class covers a piece of machine language instructions.
 * 
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */

class RunTest {

	/**
	 * Creates an object of type runProgram
	 * */
	static runProgram runnerTest;
	
	/**
	 * A string for our "output" machine language program
	 * */
	static String outputProgram;
	
	/**
	 * A string for our "reverse" machine language program
	 * */
	static String reverseProgram;
	
	/**
	 * A string for our "sum" machine language program
	 * */
	static String sumProgram;
	
	/**
	 * The method below initializes the runProgram object and stores 
	 * machine language programs into our String program variables.
	 * */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		runnerTest = new runProgram();
		
		outputProgram = "51 00 07 51 00 08 00 48 69 zz";
		reverseProgram = "49 00 15 49 00 16 49 00 17 49 00 18 51 00 18 51 00 17 51 00 15 51 00 16 00 zz";
		sumProgram = "C1 00 11 71 00 13 A1 00 15 F1 00 10 51 00 10 00 00 00 05 00 03 00 30 zz";
	}

	/**
	 * Returns memory to CPU by setting all objects and variables to null
	 * */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		runnerTest = null;
		
		outputProgram = null;
		reverseProgram = null;
		sumProgram = null;
	}

	/**
	 * Tests the runProgram.java class by inputing different machine language programs
	 * into the class, then running each machine language program.
	 * */
	@Test
	void test() {
		runnerTest = new runProgram();
		
		runnerTest = new runProgram(outputProgram);
		runnerTest.runMachineLanguageInstructions();
		
		runnerTest = new runProgram(reverseProgram);
		runnerTest.runMachineLanguageInstructions();
		
		runnerTest = new runProgram(sumProgram);
		runnerTest.runMachineLanguageInstructions();
	}

}
