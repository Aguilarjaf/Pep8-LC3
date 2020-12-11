package jUnit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.prgrmController;
import view.SimulatorGUI;

/**
 * This is the JUnit test class of the prgrmController.java class in the controller 
 * package. The prgrmController.java class is responsible for communicating between the 
 * GUI, the classes in the model package, and other helper controller classes, to 
 * provide functionality to the GUI. This JUnit class will be testing the coverage
 * of the prgrmController.java class's constructors.
 * 
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */

class PrgrmControllerTest {
	
	/**
	 * Creates an object of type prgrmController
	 * */
	prgrmController controller;
	
	/**
	 * Creates an object of type SimulatorGUI (to be used within the prgrmController object)
	 * */
	SimulatorGUI newGUI;
	
	/**
	 * Initializes our prgrmController and SimulatorGUI objects
	 * */
	@BeforeEach
	void setUp() throws Exception {
		controller = new prgrmController();
		newGUI = new SimulatorGUI();
	}

	/**
	 * Returns memory to CPU by setting all objects to null
	 * */
	@AfterEach
	void tearDown() throws Exception {
		controller = null;
		newGUI = null;
	}

	/**
	 * Tests the constructor of the prgrmController.java class
	 * */
	@Test
	void testConstructor() {
		controller = new prgrmController(newGUI);
	}

}
