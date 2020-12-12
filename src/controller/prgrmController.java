package controller;

import view.SimulatorGUI;

/**
 * The class below is responsible for the controller part of this program. 
 * The controller is responsible for communicating between the simulator GUI 
 * and the classes in the model package. This class allows the program to 
 * assemble assembly language instructions and run machine language programs by
 * accessing the data from the classes in the model package and other helper 
 * classes in the controller package.
 *   
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */


public class prgrmController {
	
	/**
	 * The variable below initializes an object of type SimulatorGUI.
	 * */
	protected static SimulatorGUI theGUI;
	
	/**
	 * The method below is an empty constructor for this class.
	 * */
	public prgrmController() {}
	
	/**
	 * The method below is another constructor of this class. This 
	 * constructor accepts a SimulatorGUI object and calls on
	 * the appropriate helper classes to assemble assembly language 
	 * instructions and run machine language programs.
	 * 
	 * @param An object of type SimulatorGUI
	 * */
	public prgrmController(SimulatorGUI newGUI) {
		
		// Initializes the prgrmController.theGUI variable to the object in the parameter
		if (newGUI != null) prgrmController.theGUI = newGUI;
		
		// Initializes the prgrmController.theGUI variable to a new object of type SimulatorGUI
		else prgrmController.theGUI = new SimulatorGUI();
		
		// Calls on the appropriate controller classes to provide functionality to the simulator
		prgrmController.theGUI.addRunButtonListener(new runProgram());
		prgrmController.theGUI.addBuildButtonListener(new buildProgram());
		prgrmController.theGUI.addConversionDocListener(new convertDataType());
	}

}

