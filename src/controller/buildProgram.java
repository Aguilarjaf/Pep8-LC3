package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import model.Binary;
import model.Decimal;
import model.Hex;


/**
 * The class below conducts the action that results from pressing the build button on
 * our simulator GUI. This class assembles assembly language instructions by breaking
 * down to opcodes and operands of the instructions provided.
 *   
 * @author Jorge Aguilar, Nathan Stickler, Wynn Siripanich, Yangchen Zhong
 * @version October 25, 2020
 * */

public class buildProgram extends prgrmController implements ActionListener {
	
	/**
	 * A String that terminates machine language programs with "zz"
	 * */
	private String endCall;
	
	
	/**
	 * The Binary object below allows us to access some of the methods in the 
	 * Binary class.
	 * */
	private Binary binaryClass;
	
	/**
	 * The Hex object below allows us to access some of the methods in the 
	 * Hex class.
	 * */
	private Hex hexClass;
	
	/**
	 * The Decimal object below allows us to access some of the methods in the 
	 * Decimal class.
	 * */
	private Decimal decimalClass;
	
	private Mnemonics assemblyLanguage;
	
	/**
	 * The constructor for the buildProgram class that initializes the
	 * variables and objects of our class.
	 * */
	public buildProgram() {
		super();
		
		// Initializes endCall to an empty string
		this.endCall = "";
		
		// Initializes the objects from our helper classes
		binaryClass = new Binary();
		hexClass = new Hex();
		decimalClass = new Decimal();
		
	}
	
	/**
	 * The ActionEvent that is linked to the ActionListener of the build
	 * button.
	 * 
	 * @param ActionEvent instructions.
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		runAssemblyLanguageInst();
		
	}

	/**
	 * Initiates the assembly of the assembly language instructions.
	 * */
	private void runAssemblyLanguageInst() {
		
		// Gets the source code
		String sourceCode = prgrmController.theGUI.getSourceCodeTextArea();
		
		// Assembles the source code and writes the machine language programs
		prgrmController.theGUI.setObjectCodeTextArea(processSourceCode(sourceCode));
		
	}
	
	/**
	 * This method processes the assembly language instructions from the source code 
	 * text area.
	 * 
	 * @param Assembly language instructions.
	 * @return A Machine language program string.
	 * */
	public String processSourceCode(String sourceCode) {
	
		this.endCall = ""; // End call set to empty
		
		// Processes the assembly language instructions line by line
		String[] lines = sourceCode.split("\r?\n");
		StringBuilder binaryCode = new StringBuilder();
		StringBuilder hexCode = new StringBuilder();
		StringBuilder machineProgram = new StringBuilder();
	
		// Breaks down each assembly language instruction line to access its opcodes and operands
		for (int i = 0; i < lines.length; i++) {
			String[] assemblyCodeLine = lines[i].split(" ");
			binaryCode.append(verifyCodeLine(assemblyCodeLine[0], assemblyCodeLine));	
		}
			
		// Converts the binary string representation of assembly language instructions into hex
		for (int i = 0; i < binaryCode.length(); i+=4) {
			hexCode.append(hexClass.binaryToHex(binaryCode.substring(i,i+4)));
		}
		
		// Develops machine language programs from hex string
		for (int i = 0; i < hexCode.length(); i+=2) {
			machineProgram.append(hexCode.substring(i, i+2) + " ");
		}
		
		// Returns the machine language program as a string
		return machineProgram.toString().toUpperCase() + this.endCall;
		
		
	}
	
	/**
	 * The method below breaks down each line of our assembly language instructions
	 * and assigns the proper binary values to each opcode, operand, dot command, addressing mode, 
	 * and register fields.
	 * 
	 * @param firstCommand - The initial command, which may be an opcode or dot command.
	 * @param assemblyCodeLineArray - The assembly language instruction line.
	 * @return A binary string developed from the assembly language instruction line.
	 * */
	private String verifyCodeLine(String firstCommand, String[] assemblyCodeLineArray) {
		
		StringBuilder binaryAssemblyCode = new StringBuilder();
		if (assemblyCodeLineArray[0].equalsIgnoreCase("") || assemblyCodeLineArray[0].equalsIgnoreCase(";"))
			return "";
		
		// Processes dot commands
		if (firstCommand.charAt(0) == '.') {
			
			String dotCommand = firstCommand.substring(1, firstCommand.length());
			
			assemblyLanguage = Mnemonics.valueOf(dotCommand);
			
			if (assemblyLanguage.containsDotCommand(dotCommand)) {
				if (firstCommand.equalsIgnoreCase(".ASCII"))
					binaryAssemblyCode.append(asciiStringtoBinary(assemblyCodeLineArray[1]));
				
				if (firstCommand.equalsIgnoreCase(".END"))
					this.endCall = "zz";
				
				if (firstCommand.equals(".BLOCK")) {
					
					for (int i = 0; i < Integer.valueOf(assemblyCodeLineArray[1]); i++)
						binaryAssemblyCode.append("00000000");
				}
				
				if (firstCommand.equals(".WORD")) {
					binaryAssemblyCode.append("00000000");
					
					if (assemblyCodeLineArray[1].startsWith("0x"))
						binaryAssemblyCode.append(memoryAddress(assemblyCodeLineArray[1]));
					else
						binaryAssemblyCode.append(decimalClass
								.extendedDecimalToBinary(assemblyCodeLineArray[1]));
				}
				
				if (firstCommand.equals(".BYTE")) {
					if (assemblyCodeLineArray[1].startsWith("0x"))
						binaryAssemblyCode.append(binaryClass.extendedHexToBinary(assemblyCodeLineArray[1].substring(
								assemblyCodeLineArray[1].length()-2, assemblyCodeLineArray[1].length())));
					else 
						binaryAssemblyCode.append(asciiStringtoBinary(assemblyCodeLineArray[1]));
				}
				
			}
			
			return binaryAssemblyCode.toString(); // Returns a binary string from the instructions
		}
		
		assemblyLanguage = Mnemonics.valueOf(firstCommand);
		
		// Processes opcodes of U addressing modes
		if (assemblyLanguage.containsUMneumonics()) {
			assemblyLanguage = Mnemonics.valueOf(assemblyCodeLineArray[0]);
			binaryAssemblyCode.append(assemblyLanguage.getBinaryMnemonic());
		}
		
		// Processes opcodes with 'a' fields
		if (assemblyLanguage.containsAMneumonics()) {
			binaryAssemblyCode.append(assemblyLanguage.getBinaryMnemonic());
			binaryAssemblyCode.append("000000000");
			binaryAssemblyCode.append(memoryAddress(assemblyCodeLineArray[1]));
			
		}

		// Processes opcodes with 'r' fields
		if (assemblyLanguage.containsRMneumonics())
			binaryAssemblyCode.append(assemblyLanguage.getBinaryMnemonic());
		
		// Processes opcodes with 'aaa' fields
		if (assemblyLanguage.containsAAAMneumonics()) {
			binaryAssemblyCode.append(assemblyLanguage.getBinaryMnemonic()); // Opcode

			// Addressing Mode
			assemblyLanguage = Mnemonics.valueOf(assemblyCodeLineArray[2]);
			binaryAssemblyCode.append(assemblyLanguage.getBinaryMnemonic()); 
			binaryAssemblyCode.append("00000000"); // Filler
			
			if (assemblyCodeLineArray[2].contains("i")) { // Indirect addressing
				
				char c = assemblyCodeLineArray[1].charAt(1);
				
				binaryAssemblyCode.append(binaryClass.asciiToBinary(c));
			}
			
			if (assemblyCodeLineArray[2].contains("d")) // Direct addressing
				binaryAssemblyCode.append(memoryAddress(assemblyCodeLineArray[1]));
		}
	
		// Processes opcodes with 'raaa' fields
		if (assemblyLanguage.containsRAAAMneumonics()) {
			binaryAssemblyCode.append(assemblyLanguage.getBinaryMnemonic());
			
			String opcode = assemblyCodeLineArray[0];
			
			String registerField = opcode.substring(opcode.length()-1, opcode.length());
			
			assemblyLanguage = Mnemonics.valueOf(registerField);
			binaryAssemblyCode.append(assemblyLanguage.getBinaryMnemonic());
			
			// Addressing Mode
			assemblyLanguage = Mnemonics.valueOf(assemblyCodeLineArray[2]);
			binaryAssemblyCode.append(assemblyLanguage.getBinaryMnemonic()); 
			
			binaryAssemblyCode.append("00000000"); // Filler
			
			binaryAssemblyCode.append(memoryAddress(assemblyCodeLineArray[1]));
			
		}
		
		return binaryAssemblyCode.toString(); // Returns a binary string from the instructions
	}

	/**
	 * This method converts an ASCII string to a binary string.
	 * 
	 * @param asciiString - An ASCII string input
	 * @return binaryString - A binary string from ASCII
	 * */
	private String asciiStringtoBinary(String asciiString) {
		
		StringBuilder binaryString = new StringBuilder();
		
		// Removes quotes around ASCII values
		String asciiStringCopy = asciiString.substring(1, asciiString.length() - 1);
		
		// Converts ASCII data into binary
		for (int i = 0; i < asciiStringCopy.length(); i++) {
			binaryString.append(binaryClass.asciiToBinary(asciiStringCopy.charAt(i)));
		}
		
		return binaryString.toString();
		
	}

	/**
	 * A method that returns a binary representation of a memory address written in hex.
	 * This method is important for opcodes that require the access of memory registers.
	 * 
	 * @param hexAddress - A memory address in hex
	 * @return A binary representation of the memory address.
	 * */
	private String memoryAddress(String hexAddress) {
		
		String hexAddressCopy = "";
		
		// Processes hexAddresses of different syntax
		if (hexAddress.length() == 7)
			hexAddressCopy = hexAddress.substring(hexAddress.length() - 5, hexAddress.length() - 1);
		else if (hexAddress.length() == 2)
			hexAddressCopy = hexAddress.substring(0, hexAddress.length() - 1);
		else 
			hexAddressCopy = hexAddress.substring(hexAddress.length() - 4, hexAddress.length());
		
		return binaryClass.extendedHexToBinary(hexAddressCopy);
		
	}
	
}