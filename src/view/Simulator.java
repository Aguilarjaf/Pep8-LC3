package view;
import java.awt.EventQueue;


import javax.swing.JFrame;

import controller.prgrmController;

/**
 * This is the main class of the program. This class launches the GUI
 * and displays it onto the user's screen. The GUI is the interface of
 * our Pep/8 simulator.
 *   
 * @author Jorge Aguilar, Nathan Stickler, Wynn Siripanich, Yangchen Zhong
 * @version October 25, 2020
 */
public class Simulator {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(() ->
		{
			var simulatorFrame = new SimulatorGUI();
			simulatorFrame.setTitle("Pep8 Simulator");
			simulatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// Creates the controller class for the program
			@SuppressWarnings("unused")
			prgrmController theController = new prgrmController(simulatorFrame);
			
			simulatorFrame.setVisible(true);
			simulatorFrame.pack();
		});

	}

}
