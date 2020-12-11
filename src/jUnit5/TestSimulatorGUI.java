package jUnit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import view.SimulatorGUI;

/**
 * This is the JUnit test class that checks for full coverage of our SimulatorGUI.java
 * class. The SimulatorGUI.java class develops the GUI of our simulator. This class
 * will be testing its ActionListeners, get methods, set methods, and the methods 
 * that make up the GUI's view.
 * 
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */

class TestSimulatorGUI {
	
	/**
	 * Creates an object of type SimulatorGUI
	 * */
	static SimulatorGUI gui;

	/**
	 * Initializes the SmulatorGUI object and ensures that all
	 * GUI panels communicate with one another.
	 * */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gui = new SimulatorGUI();
	}

	/**
	 * Returns memory to CPU by setting the SimulatorGUI object to null
	 * */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		gui = null;
	}

	/**
	 * Conducts a test on the GUIs ActionListeners
	 * */
	@Test
	void testListeners() {
		gui.addRunButtonListener(null);
		gui.addBuildButtonListener(null);
		gui.addConversionDocListener(null);
	}
	
	/**
	 * Conducts a test on the GUIs get methods
	 * */
	@Test 
	void testGetMethods() {
		gui.getObjCodeTextArea();
		gui.getSourceCodeTextArea();
		gui.getInputTextArea();
		gui.getDecimalField();
	}
	
	/**
	 * Conducts a test on the GUIs set methods
	 * */
	@Test 
	void testSetMethods() {
		String text = "";
		
		gui.setObjectCodeTextArea(text);
		gui.setMemDumpText(text);
		gui.setOutputTextArea(text);
		gui.setHexField(text);
		gui.setBinaryField(text);
		gui.setAsciiField(text);
		gui.setOpCodeField(text);
		gui.setInstSpcfrText(text);
		gui.setAcmltrTextOne(text);
		gui.setAcmltrTextTwo(text);
		gui.setPrgrmCntrTextOne(text);
		gui.setPrgrmCntrTextTwo(text);
		gui.setOprndSpcfrTextOne(text);
		gui.setOprndSpcfrTextTwo(text);
		gui.setOprndTextOne(text);
		gui.setOprndTextTwo(text);
		
		gui.setNTextBox(text);
		gui.setZTextBox(text);
		gui.setVTextBox(text);
		gui.setCTextBox(text);
	}

}
