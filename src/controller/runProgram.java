package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import model.Binary;
import model.Hex;

/**
 * This class processes machine language programs that are input into the
 * object code text area by the user. This is done by assigning functionality
 * to the hex opcodes within the machine language programs and using a memory
 * matrix to execute the action of those opcodes.
 *   
 * @author Jorge Aguilar, Nathan Stickler, Wynn Siripanich, Yangchen Zhong
 * @version October 25, 2020
 * */

public class runProgram implements ActionListener {
	
	/** The variable below stores the hexCode string that has been provided to 
	 * the object code text area panel (objCodeTextArea) 
	 * */
	private String hexCode; 
	
	/**
	 * A boolean flag that indicates whether or not the program is receiving
	 * input from the GUI
	 * */
	private Boolean guiInput = false;
	
	/**
	 * Store as a memory of the simulator. 
	 */
	private char[][] mMemory;
	
	/**
	 * Stores the instructions from opcodes
	 * */
	private char[][] mInstruction;

	/**
	 * Defines the max amount of memory spaces
	 */
	private final static int MAX_MEMORY = 512; // 2^9
	
	/**
	 * Defines the max amount of memory bits
	 * */
	private final static int MAX_MEMORY_BITS = 8;
	
	/**
	 * Defines the max amount of instruction spaces
	 * */
	private final static int MAX_INSTRUCTION = 20;
	
	/**
	 * Defines the max amount of memory bits
	 * */
	private final static int MAX_INSTRUCTION_BITS = 24;
	
	/**
	 * Decimal conversion of the CHARI opcode
	 * */
	private final static int OPC_CHARACTER_INPUT = 9;
	
	/**
	 * Decimal conversion of the CHARO opcode
	 * */
	private final static int OPC_CHARACTER_OUTPUT = 10;
	
	/**
	 * Decimal conversion of the ADD opcode
	 * */
	private final static int OPC_ADD = 14;
	
	/**
	 * Decimal conversion of the OR opcode
	 * */
	private final static int OPC_CONVERT = 20;
	
	/**
	 * Decimal conversion of the LD opcode
	 * */
	private final static int OPC_ACCUMULATE = 24;
	
	/**
	 * Decimal conversion of the ST opcode
	 * */
	private final static int OPC_STORE = 30;

	/**
	 * Variables for iterating through memory matrix
	 * */
	private int mCountMemory = 0;
	
	/**
	 * Variables for iterating through instruction matrix
	 * */
	private int mCountIns = 0;
	
	/**
	 * The Hex object below allows us to access some of the methods in the 
	 * Hex class.
	 * */
	private Hex hexClass;
	
	/**
	 * The Binary object below allows us to access some of the methods in the 
	 * Binary class.
	 * */
	private Binary theBinaryClass;
	
	/**
	 * A constructor that initializes the memory and instruction matrixes, initializes
	 * the objects of the Hex and Binary classes, and constructs an empty memory 
	 * dump which will be updated throughout the program.
	 * */
	public runProgram() {
		this.mMemory = new char[MAX_MEMORY][MAX_MEMORY_BITS];
		this.mInstruction = new char[MAX_INSTRUCTION][MAX_INSTRUCTION_BITS];
		
		hexClass = new Hex();
		theBinaryClass = new Binary();
		
		// Constructs memory dump
		String zeros = "00000000";
		for (int i = 0; i < this.mMemory.length; i++) {
			for (int j = 0; j < this.mMemory.length; j++) {
				this.mMemory[i] = Arrays.copyOf(zeros.toString().toCharArray(), zeros.toString().toCharArray().length);
			}
		}
	}
	
	/**
	 * A constructor that initializes the memory and instruction matrixes,
	 * initializes the objects of the helper classes, constructs an empty memory 
	 * dump, and defines the hexCode string of the machine language programs.
	 * 
	 * @param hexInstructions - A machine language program as a hex string.
	 * */
	public runProgram(String hexInstructions) {
		this.mMemory = new char[MAX_MEMORY][MAX_MEMORY_BITS];
		this.mInstruction = new char[MAX_INSTRUCTION][MAX_INSTRUCTION_BITS];
		
		hexClass = new Hex();
		theBinaryClass = new Binary();
		
		this.hexCode = hexInstructions; // Stores machine language programs
		
		// Constructs memory dump
		String zeros = "00000000";
		for (int i = 0; i < this.mMemory.length; i++) {
			for (int j = 0; j < this.mMemory.length; j++) {
				this.mMemory[i] = Arrays.copyOf(zeros.toString().toCharArray(), zeros.toString().toCharArray().length);
			}
		}
	}

	/**
	 * Runs the machine language programs whenever the run button
	 * has been pressed.
	 * 
	 * @param e - ActionListener button instructions.
	 * */
	public void actionPerformed(ActionEvent e) {
		if (e != null) this.guiInput = true;
		runMachineLanguageInstructions();
	}

	/**
	 * Instructs the program to run machine language programs whenever the
	 * run button has been clicked. This method iterates through and processes
	 * machine language programs and stores its data in memory. This method also
	 * reports the state of the program to the CPU panel to present any critical
	 * information about the system.
	 */
	public void runMachineLanguageInstructions() {
		mCountMemory = 0;
		mCountIns = 0;
		
		if (this.guiInput == true) {
			prgrmController.theGUI.setOutputTextArea("");
			prgrmController.theGUI.setNTextBox("0");
			prgrmController.theGUI.setZTextBox("0");
			prgrmController.theGUI.setVTextBox("0");
			prgrmController.theGUI.setCTextBox("0");
			
			this.hexCode = prgrmController.theGUI.getObjCodeTextArea();
		}
		
		// Returns if input is empty. Prevents program from crashing.
		if (this.hexCode.compareTo("") == 0) {
			return;
		}
		
		boolean terminateSignal = false;
		String result; // Stores result
		
		try {
			// Translates hexCode into binary to be stored in memory
			StringBuilder binaryMemory = new StringBuilder(); 
			
			// Translates hexCode into binary to be stored in instsructions
			StringBuilder binaryIns = new StringBuilder();
			
			int iCountInsPart = 0;
			
			boolean bLoadIns = true;
			
			// The for loop iterates through the hexCode
			for (int i = 0; i < hexCode.length(); i += 3) {
				
			    String hexPair = hexCode.substring(i, i + 2);
			    
			    if (hexPair.compareTo("zz") == 0) {
			    	terminateSignal = true;
			    	break;
			    }
			    
			    iCountInsPart++;
			    
			    if (iCountInsPart > 3) {
			    	iCountInsPart = 1;
				    binaryIns.setLength(0);
				    if (bLoadIns) {
					    mCountIns++;
				    }
			    }

			    if (iCountInsPart == 1 && hexPair.compareTo("00") == 0) {
			    	bLoadIns = false;
			    }
			    
			    binaryIns.append(theBinaryClass.hexToBinary(hexPair.substring(0, 1)));
			    binaryIns.append(theBinaryClass.hexToBinary(hexPair.substring(1, 2)));
			    
		    	binaryMemory.setLength(0);
		
		    	binaryMemory.append(theBinaryClass.hexToBinary(hexPair.substring(0, 1)));
		    	binaryMemory.append(theBinaryClass.hexToBinary(hexPair.substring(1, 2)));
			    
		    	// Stores hexCode in memory at each step of the machine language programs
				mMemory[mCountMemory] = Arrays.copyOf(binaryMemory.toString().toCharArray(),
						binaryMemory.toString().toCharArray().length);
				
				// Stores hexCode in instructions
				if (bLoadIns) {
					mInstruction[mCountIns] = Arrays.copyOf(binaryIns.toString().toCharArray(),
							binaryIns.toString().toCharArray().length);
				}
				
				mCountMemory++; // Increments to access the next memory space
			}
			
			result = execute(); // Executes opcode
			
			// Error message for incomplete programs and invalid input
			if (!terminateSignal) {
				JOptionPane.showMessageDialog(null, "There is no terminated \"zz\" signal!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// Outputs ASCII result to the output text area
			if (this.guiInput == true)
				prgrmController.theGUI.setOutputTextArea(result);
			
		} catch (Exception ex) {
			// Error message for invalid input
			JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
			System.out.println(ex.toString());
		}
		
		// Prints the memory dump of the program in Hex
		StringBuilder machineProgram = new StringBuilder();
		
		// Builds new memory dump for the GUI memory dump panel
		for (int i = 1; i <= this.mMemory.length; i++) {
			
			StringBuilder memDumpBinaryString = new StringBuilder();
			String hexDump = "";
			
			for (int j = 0; j < this.mMemory[i-1].length; j++) {
				
				memDumpBinaryString.append(this.mMemory[i-1][j]);
				
				hexDump = hexClass.extendedBinaryToHex(memDumpBinaryString.toString());
				
			}
			
			for (int k = 0; k < hexDump.length(); k+=2) {
				machineProgram.append(hexDump.substring(k, k+2) + " ");
			}
			
			machineProgram.append(" ");
			
			if (i % 8 == 0 && i > 0) machineProgram.append("\n");
		}
		
		// Sends memory dump to the GUI memory dump panel
		if (this.guiInput == true)
			prgrmController.theGUI.setMemDumpText(machineProgram.toString().toUpperCase());
		
		wipeMemory(); // Clears memory
		
		// Prints the given program's binary instructions
		System.out.println("\nInstructions:");
		for (int i = 1; i <= this.mInstruction.length; i++) {
			
			for (int j = 0; j < this.mInstruction[i-1].length; j++) System.out.print(this.mInstruction[i-1][j]);
			
			System.out.print(" ");
			
			if (i % 3 == 0 && i > 0) System.out.println("\n");
		}
	}
	
	/**
	 * The method below clears the memory from the memory dump by
	 * zeroing out all memory spaces. This allows the program to reflect
	 * the memory of the next program after execution.
	 * */
	private void wipeMemory() {
		
		String zeros = "00000000";
		
		for (int i = 0; i < this.mMemory.length; i++) {
			for (int j = 0; j < this.mMemory.length; j++) {
				this.mMemory[i] = Arrays.copyOf(zeros.toString().toCharArray(), zeros.toString().toCharArray().length);
			}
		}
		
	}

	/**
	 * Execute all the instructions in memory.
	 * Handle all the instructions by opcode and operands.
	 * 
	 * @return String output of the instructions.
	 */
	private String execute() {
		StringBuilder sb = new StringBuilder();
		
		int location = 0;
		int iCountInput = 0;
		int accumulator = 0;
		char asciiCharacter = '0';
		
		String strInput = "";
		
		if (this.guiInput == true) {
			strInput = prgrmController.theGUI.getInputTextArea();
		}
		
		int j = 0;
		while (j < mCountIns) {
			char[] ins = mInstruction[j];
			int opCode = getOpcode(ins, 0, 4);
			
			String opCodeString = new String(ins, 0, 8); // Accesses opcode from instruction array
			
			// Reports critical data to the cpu panel
			if (this.guiInput == true) {
				prgrmController.theGUI.setInstSpcfrText(opCodeString);
	
				prgrmController.theGUI.setAcmltrTextOne("0x" + 
						String.format("%6s", Integer.toHexString(accumulator).toUpperCase()).replace(' ', '0'));
				prgrmController.theGUI.setAcmltrTextTwo(String.valueOf(accumulator));
	
				prgrmController.theGUI.setPrgrmCntrTextOne("0x" + 
						String.format("%6s", Integer.toHexString(j * 3).toUpperCase()).replace(' ', '0'));
				prgrmController.theGUI.setPrgrmCntrTextTwo(String.valueOf(j * 3));
			}

			location = getValue(ins, 8, 24); // Accesses operand from instruction matrix
			
			// Reports critical data to the cpu panel
			if (this.guiInput == true) {
				prgrmController.theGUI.setOprndSpcfrTextOne("0x" + String.format("%6s",
						Integer.toHexString(location).toUpperCase()).replace(' ', '0'));
				prgrmController.theGUI.setOprndSpcfrTextTwo(String.valueOf(location));
				
				prgrmController.theGUI.setOprndTextOne("0x" + String.format("%6s",
						Integer.toHexString(getValue(mMemory[location], 0, MAX_MEMORY_BITS - 1)).toUpperCase())
							.replace(' ', '0'));
				prgrmController.theGUI.setOprndTextTwo(String.valueOf(getValue(mMemory[location], 0, MAX_MEMORY_BITS - 1)));
			}
			
			// Handles the opcode of the machine language programs
			switch (opCode) {
			case OPC_CHARACTER_INPUT:
				int ascii = 0;
				if (strInput.length() > iCountInput) {
					char character = strInput.charAt(iCountInput++);
					ascii = (int) character;
				}
				setValue(mMemory[location], ascii);
				break;

			case OPC_CHARACTER_OUTPUT:
			    asciiCharacter = (char) getValue(mMemory[location], 0, MAX_MEMORY_BITS);
				sb.append(asciiCharacter);
				break;

			case OPC_ADD:
				accumulator += getValue(mMemory[++location], 0, MAX_MEMORY_BITS); // Increments with .BLOCK
				break;

			case OPC_CONVERT:
			    accumulator += getValue(mMemory[++location], 0, MAX_MEMORY_BITS); // Increments with .BLOCK
				break;

			case OPC_ACCUMULATE:
				accumulator = getValue(mMemory[++location], 0, MAX_MEMORY_BITS); // Increments with .BLOCK
				break;

			case OPC_STORE:
				setValue(mMemory[location], accumulator);
				break;
			}
			j++;
		}
		return sb.toString();
	}
	
	/**
	 * Gets the value of the opcode from the instructions array
	 * 
	 * @param input - An array of program instructions.
	 * @param start - Starting index of the opcode
	 * @param end - Ending index of the opcode
	 * @return value - A binary representation of the opcode.
	 * */
	private int getOpcode(char[] input, int start, int end) {
		if (input == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		int value = 0;
		for (int i = start; i <= end; i++) {
			value *= 2;
			value += input[i] == '1' ? 1 : 0;
		}
		return value;
	}

	/**
	 * Get the value of array input by list indices.
	 *  
	 * @param input A array of character.
	 * @param start Begin index of array.
	 * @param end Last index of array which want to get value.
	 * @return The value of array input.
	 */
	public int getValue(char[] input, int start, int end) {
		if (input == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		int value = 0;
		for (int i = start; i < end; i++) {
			value *= 2;
			value += input[i] == '1' ? 1 : 0;
		}
		return value;
	}

	/**
	 * Set value of array input by value n.
	 * 
	 * @param input A array of character which need to be assigned.
	 * @param n The value need to assign.
	 */
	public void setValue(char[] input, int n) {
		if (input == null) {
			throw new IllegalArgumentException("Bit String must be set first.");
		}
		int iLength = input.length;
		for (int i = iLength - 1; i >= 0; i--) {
			input[i] = (n % 2 == 0) ? '0' : '1';
			n /= 2;
		}
	}
	

}
