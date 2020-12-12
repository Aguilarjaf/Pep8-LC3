package view;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.DocumentListener;

import model.Hex;

/**
 * The class below builds the GUI of our pep8 simulator by extending
 * the JFrame class and importing Java's Swing library. The purpose of 
 * this class is to develop the GUI for our Pep/8 simulator. The GUI
 * is composed of different panels and buttons which the user can
 * interact with to input their assembly language instructions or 
 * machine language programs.
 *   
 * @author Jorge Aguilar, RJ Alabado, Dung Tran, Tiarnan Marsten
 * @version December 8, 2020
 * */
public class SimulatorGUI extends JFrame {
	
	/**
	 * serialVersion of the Frame.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The variable below sets the default width of the GUI
	 * */
	private static final int DEFAULT_WIDTH = 1165;
	
	/**
	 * The variable below sets the default height of the GUI
	 * */
	private static final int DEFAULT_HEIGHT = 800;
	
	/**
	 * The line below creates a border that can be used to outline
	 * some items in our GUI (e.g., JTextArea)
	 * */
	private Border textAreaBorder = BorderFactory.createLineBorder(Color.BLACK, 1); 
	/* I learned how to create a border for a JTextArea using the code found on: 
	 * https://examples.javacodegeeks.com/desktop-java/swing/jtextfield/create-jtextfield-with-border/
	 * 
	 * I give full credit of this piece of code to Byron Kiourtzoglou, who was kind
	 * enough to demonstrate how a border is created.
	 * */
	
	
	/**
	 * The text area variable below realizes the Object Code text area that the user
	 * can interact with to input their machine language programs.
	 * */
	private JTextArea objCodeTextArea;
	
	/**
	 * The text area variable below realizes the Output text area that portrays an
	 * ASCII representation from machine language programs.
	 * */
	private JTextArea outputTextArea;
	
	/**
	 * The text area variable below realizes the Input text area that accepts a 
	 * string that can be used for certain machine language programs (such as the 
	 * reverse program).
	 * */
	private JTextArea inputTextArea;
	
	/**
	 * The text are variable below realizes the Source Code text area that the
	 * user can interact with to input their assembly language instructions.
	 * */
	private JTextArea sourceCodeText;
	
	/**
	 * The variable below realizes the text area for our memory dump.
	 * */
	private JTextArea memTextArea;

	/**
	 * The variables below represent the NZVC text fields of our simulator. 
	 * These text fields allow us to report the state of the program.
	 * */
	private JTextField ntextBox;
	private JTextField ztextBox;
	private JTextField vtextBox;
	private JTextField ctextBox;
	
	/**
	 * The variables below realize the accumulator text fields of our GUI
	 * to report the accumulator state of our program.
	 * */
	private JTextField acmltrTextOne;
	private JTextField acmltrTextTwo;
	
	/**
	 * The variables below realize the program counter text fields of our GUI
	 * to report the program counter state of our program.
	 * */
	private JTextField prgrmCntrTextOne;
	private JTextField prgrmCntrTextTwo;
	
	/**
	 * The variables below realize the instruction specifier text fields of our GUI
	 * to report the instruction specifier state of our program.
	 * */
	private JTextField instSpcfrText;
	
	/**
	 * The variables below realize the operand text fields of our GUI
	 * to report the operand state of our program.
	 * */
	private JTextField oprndTextOne;
	private JTextField oprndTextTwo;
	
	/**
	 * The variables below realize the operand specifier text fields of our GUI
	 * to report the operand specifier state of our program.
	 * */
	private JTextField oprndSpcfrTextOne;
	private JTextField oprndSpcfrTextTwo;
	
	/**
	 * The variable below realizes the decimal text field in the top section
	 * of our GUI. This allows us to convert between different data types.
	 * */
	private JTextField decimalField;
	
	/**
	 * The variable below realizes the hex text field in the top section
	 * of our GUI.
	 * */
	private JTextField hexField;
	
	/**
	 * The variable below realizes the binary text field in the top section
	 * of our GUI.
	 * */
	private JTextField binaryField;
	
	/**
	 * The variable below realizes the ASCII text field in the top section
	 * of our GUI.
	 * */
	private JTextField asciiField;
	
	/**
	 * The variable below realizes the opcode text field in the top section
	 * of our GUI.
	 * */
	private JLabel opCodeField;
	
	/**
	 * The variable below initializes the runButton button, which will
	 * be used to run the program whenever machine language programs
	 * are provided.
	 * */
	private JButton runButton;
	
	/**
	 * The variable below initializes the buildButton button, which will
	 * be used when assembling machine language instructions
	 * */
	private JButton buildButton;
	
	
	/**
	 * The Hex variable below allows us to access some of the methods in the 
	 * Hex class.
	 * */
	private Hex hexClass = new Hex();

	/**
	 * The method below develops the simulator's GUI by assembling the
	 * different panels that compose the many sections of the GUI.
	 * The design of the GUI is inspired by the interface of the Pep/8 program.
	 * */
	public SimulatorGUI() {
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT); // Sets the size of the window
		
		// Initializes the west and east panels
		var westPanel = new JPanel(new BorderLayout());
		var eastPanel = new JPanel(new BorderLayout());
		
		// Develops west panel
		westPanel.add(centerPanelMethod(), BorderLayout.CENTER);
		westPanel.add(southPanelMethod(), BorderLayout.SOUTH);
		
		// Develops east panel
		eastPanel.add(memoryDump(), BorderLayout.EAST);
		
		// Adds all panels to the main GUI
		add(northPanelMethod(), BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		add(eastPanel, BorderLayout.EAST);
		
	}
	
	/**
	 * The method below develops the memory dump panel of our simulator.
	 * This panel allows the user to see the state of the program's memory 
	 * when running machine language instructions.
	 * 
	 * @return Memory dump panel.
	 * */
	private JPanel memoryDump() {
		var memPanel = new JPanel(new BorderLayout());
		var memLabel = new JLabel("Memory Dump", SwingConstants.CENTER);
		
		memTextArea = new JTextArea(fillerMemory(), 30, 17);
		
		memTextArea.setBorder(textAreaBorder);
		memTextArea.setLineWrap(true); // Prevents text area from expanding
		
		// Initializes the scroll to look throw memory dump
		var scroller = new JScrollPane(memTextArea); 
		
		memPanel.add(memLabel, BorderLayout.NORTH);
		memPanel.add(scroller, BorderLayout.CENTER);
		
		return memPanel;
	}
	
	/**
	 * The method below provides the memory dump panel with an empty
	 * memory dump, made up of all zeros.
	 * 
	 * @return A string representation of the a zeroed out memory dump.
	 * */
	private String fillerMemory() {
		char[][] aestheticDump = new char[512][8]; // Allocates memory dump space
		
		String zeros = "00000000";
		// Fills the memory dump
		for (int i = 0; i < aestheticDump.length; i++) {
			for (int j = 0; j < aestheticDump.length; j++) {
				aestheticDump[i] = Arrays.copyOf(zeros.toString().toCharArray(), 
						zeros.toString().toCharArray().length);
			}
		}
		
		// StringBuilder to print the memory dump of the program in Hex
		StringBuilder machineProgram = new StringBuilder();
		
		// Iterates through the memory dump
		for (int i = 1; i <= aestheticDump.length; i++) {
			
			StringBuilder memDumpBinaryString = new StringBuilder();
			String hexDump = "";
			
			for (int j = 0; j < aestheticDump[i-1].length; j++) {
				
				memDumpBinaryString.append(aestheticDump[i-1][j]);
				
				hexDump = hexClass.extendedBinaryToHex(memDumpBinaryString.toString());
				
			}
			
			for (int k = 0; k < hexDump.length(); k+=2) {
				machineProgram.append(hexDump.substring(k, k+2) + " ");
			}
			
			machineProgram.append(" ");
			
			if (i % 8 == 0 && i > 0) machineProgram.append("\n");
		}
		
		return machineProgram.toString().toUpperCase();
	}
	
	/**
	 * The method below creates the north panel that holds all the buttons 
	 * presented in the pep/8 program. Most buttons do not have functionality,
	 * except for the run button that helps us execute the machine language programs
	 * and the build button that assembles the assembly language instructions. 
	 * The north panel also has a conversion tool that allows us to convert between
	 * decimal, binary, hex, and ASCII data types.
	 * 
	 * @return A complete north panel with buttons and conversion tool.
	 * */
	private JPanel northPanelMethod() {
		// The lines below create the action buttons on left side of north panel
		Icon newIcon = new ImageIcon("Icons/newicon.png");
		var newButton = new JButton(newIcon);
		
		Icon folderIcon = new ImageIcon("Icons/folder.png");
		var openButton = new JButton(folderIcon);
		
		Icon saveIcon = new ImageIcon("Icons/save.png");
		var saveButton = new JButton(saveIcon);

		Icon undoIcon = new ImageIcon("Icons/undo.png");
		var undoButton = new JButton(undoIcon);
		
		Icon redoIcon = new ImageIcon("Icons/redo.png");
		var redoButton = new JButton(redoIcon);
		
		Icon helpIcon = new ImageIcon("Icons/float.png");
		var helpButton = new JButton(helpIcon);
		
		Icon assembleIcon = new ImageIcon("Icons/hammer.png");
		buildButton = new JButton(assembleIcon);
		
		Icon runIcon = new ImageIcon("Icons/play.png");
		runButton = new JButton(runIcon);
		
		Icon debugIcon = new ImageIcon("Icons/debug.png");
		var startDebugButton = new JButton(debugIcon);
		
		Icon stopIcon = new ImageIcon("Icons/stop.png");		
		var stopDebugButton = new JButton(stopIcon);
		
		Icon codeIcon = new ImageIcon("Icons/code.png");
		var codeButton = new JButton(codeIcon);

		Icon cpuIcon = new ImageIcon("Icons/cpu.png");
		var codecpuButton = new JButton(cpuIcon);
		
		Icon memoryIcon = new ImageIcon("Icons/memory.png");
		var codecpumemoryButton = new JButton(memoryIcon);
		
		// Group all action buttons
		var actionIconsGrid = new JPanel(new GridLayout(1, 13));
		actionIconsGrid.add(newButton);
		actionIconsGrid.add(openButton);
		actionIconsGrid.add(saveButton);
		actionIconsGrid.add(undoButton);
		actionIconsGrid.add(redoButton);
		actionIconsGrid.add(helpButton);
		actionIconsGrid.add(buildButton);
		actionIconsGrid.add(runButton);
		actionIconsGrid.add(startDebugButton);
		actionIconsGrid.add(stopDebugButton);
		actionIconsGrid.add(codeButton);
		actionIconsGrid.add(codecpuButton);
		actionIconsGrid.add(codecpumemoryButton);
		
		// The lines below develop the text fields for the conversion tool
		decimalField = new JTextField("97", 5);
		hexField = new JTextField("0x0061", 5);
		binaryField = new JTextField("01100001", 6);
		asciiField = new JTextField("a", 5);
		opCodeField = new JLabel("ADDSP, d");
		
		// Group all tool text fields
		var textFieldGrid = new JPanel(new FlowLayout(FlowLayout.LEFT));
		textFieldGrid.add(decimalField);
		textFieldGrid.add(hexField);
		textFieldGrid.add(binaryField);
		textFieldGrid.add(asciiField);
		textFieldGrid.add(opCodeField);
		
		// Create the north panel
		var northLeftPanel = new JPanel(new BorderLayout());
		var northRightPanel = new JPanel(new BorderLayout());
		var northPanel = new JPanel(new GridLayout(1, 2));
		
		// Add all elements onto north panel
		northLeftPanel.add(actionIconsGrid, BorderLayout.CENTER);
		northRightPanel.add(textFieldGrid);
		
		northPanel.add(actionIconsGrid);
		northPanel.add(northRightPanel);
		
		return northPanel; 
	}	
	
	/**
	 * The method below creates the center panel of the GUI. This center panel
	 * holds the source code text area, the CPU information area that reports the
	 * state of the program, and the input text area to accept user input strings.
	 * 
	 * @return A complete center panel with all text areas and information fields.
	 * */
	private JPanel centerPanelMethod() {
		
		// The variable below will store the left components of the center panel
		var centerLeftPanel = new JPanel(new BorderLayout());
		var sourceCodeLabel = new JLabel("Source Code - untitled.pep", SwingConstants.CENTER);
		
		// The lines below create the source code text area
		sourceCodeLabel.setBorder(textAreaBorder);
		
		sourceCodeText = new JTextArea("", 30, 50); 
		sourceCodeText.setBorder(textAreaBorder);
		sourceCodeText.setLineWrap(true); // Prevents text area from expanding
		
		centerLeftPanel.add(sourceCodeLabel, BorderLayout.NORTH);
		centerLeftPanel.add(sourceCodeText, BorderLayout.CENTER);
		
		// The variable below will store the right components of the center panel
		var centerRightPanel = new JPanel(new BorderLayout());
		
		// The lines below create the input text area
		var inputPanel = new JPanel(new BorderLayout());
		var inputLabel = new JLabel("Input", SwingConstants.CENTER);
		inputLabel.setBorder(textAreaBorder);
		
		inputTextArea = new JTextArea(""); // Auto resizes to fit entire area
		inputTextArea.setBorder(textAreaBorder);
		inputTextArea.setLineWrap(true); // Prevents text area from expanding
		
		inputPanel.add(inputLabel, BorderLayout.NORTH);
		inputPanel.add(inputTextArea, BorderLayout.CENTER);
		
		var centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(centerLeftPanel, BorderLayout.WEST);
		centerPanel.add(centerRightPanel, BorderLayout.CENTER);
		
		// The lines below create the CPU panel and all elements within it
		var cpuInfoPanel = new JPanel(new GridLayout(0, 1));

		var cpuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var cpuLabel = new JLabel("CPU");
		cpuPanel.add(cpuLabel);		
		cpuInfoPanel.add(cpuPanel);	
		
		var nzvcPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var nLabel = new JLabel("N");
		ntextBox = new JTextField("", 2);
		var zLabel = new JLabel("Z");
		ztextBox = new JTextField("", 2);
		var vLabel = new JLabel("V");
		vtextBox = new JTextField("", 2);
		var cLabel = new JLabel("C");
		ctextBox = new JTextField("", 2);
		nzvcPanel.add(nLabel);	
		nzvcPanel.add(ntextBox);	
		nzvcPanel.add(zLabel);	
		nzvcPanel.add(ztextBox);	
		nzvcPanel.add(vLabel);	
		nzvcPanel.add(vtextBox);	
		nzvcPanel.add(cLabel);	
		nzvcPanel.add(ctextBox);	
		cpuInfoPanel.add(nzvcPanel);	
		
		var assumulatorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var accumulatorLabel = new JLabel("Accumulator");
		acmltrTextOne = new JTextField("", 6);
		acmltrTextTwo = new JTextField("", 5);
		assumulatorPanel.add(accumulatorLabel);
		assumulatorPanel.add(acmltrTextOne);
		assumulatorPanel.add(acmltrTextTwo);
		cpuInfoPanel.add(assumulatorPanel);
		
		var indexRegPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var indexRegLabel = new JLabel("Index Register");
		var idxRegTextOne = new JTextField("", 5);
		var idxRegTextTwo = new JTextField("", 5);
		indexRegPanel.add(indexRegLabel);
		indexRegPanel.add(idxRegTextOne);
		indexRegPanel.add(idxRegTextTwo);
		cpuInfoPanel.add(indexRegPanel);
		
		var stackPointerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var stackPointerLabel = new JLabel("Stack Pointer");
		var stckPntrTextOne = new JTextField("", 5);
		var stckPntrTextTwo = new JTextField("", 5);
		stackPointerPanel.add(stackPointerLabel);
		stackPointerPanel.add(stckPntrTextOne);
		stackPointerPanel.add(stckPntrTextTwo);
		cpuInfoPanel.add(stackPointerPanel);
		
		var programCntrPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var programCntrLabel = new JLabel("Program Counter");
		prgrmCntrTextOne = new JTextField("", 6);
		prgrmCntrTextTwo = new JTextField("", 5);
		programCntrPanel.add(programCntrLabel);
		programCntrPanel.add(prgrmCntrTextOne);
		programCntrPanel.add(prgrmCntrTextTwo);
		cpuInfoPanel.add(programCntrPanel);
		
		var instSpcfrPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var instSpecifierLabel = new JLabel("Instruction Specifier");
		instSpcfrText = new JTextField("", 8);
		instSpcfrPanel.add(instSpecifierLabel);
		instSpcfrPanel.add(instSpcfrText);
		cpuInfoPanel.add(instSpcfrPanel);
		
		var oprndSpcfrPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var oprndSpecifierLabel = new JLabel("Operand Specifier");
		oprndSpcfrPanel.add(oprndSpecifierLabel);
		oprndSpcfrTextOne = new JTextField("", 6);
		oprndSpcfrTextTwo = new JTextField("", 5);
		oprndSpcfrPanel.add(oprndSpcfrTextOne);
		oprndSpcfrPanel.add(oprndSpcfrTextTwo);
		cpuInfoPanel.add(oprndSpcfrPanel);
		
		var oprndPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var oprndLabel = new JLabel("(Operand)");
		oprndTextOne = new JTextField("", 6);
		oprndTextTwo = new JTextField("", 5);
		oprndPanel.add(oprndLabel);
		oprndPanel.add(oprndTextOne);
		oprndPanel.add(oprndTextTwo);
		cpuInfoPanel.add(oprndPanel);
		
		var trcTrpsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		var rdioBtn = new JCheckBox();
		var trcTrpsLabel = new JLabel("Trace Traps");
		var singleStepBtn = new JButton("Single Step");
		var resumeBtn = new JButton("Resume");
		trcTrpsPanel.add(rdioBtn);	
		trcTrpsPanel.add(trcTrpsLabel);	
		trcTrpsPanel.add(singleStepBtn);	
		trcTrpsPanel.add(resumeBtn);		
		cpuInfoPanel.add(trcTrpsPanel);	
		
		centerRightPanel.add(cpuInfoPanel, BorderLayout.NORTH);
		centerRightPanel.add(inputPanel, BorderLayout.CENTER);
		
		return centerPanel; // Returns the complete Center panel 
	}
	
	/**
	 * The method below develops the south panel of the GUI.
	 * The south panel stores the object code text area, the assembler listing
	 * text area, and the output text area.
	 * 
	 * @return A complete south panel with all text areas.
	 * */
	private JPanel southPanelMethod() {
		// The southPanel below helps us construct our pep8 simulator to match the
		// layout of the pep8 program
		var southPanel = new JPanel(new BorderLayout());

		// The lines below build our object code text area and our assembler listing text area
		var objAndAsmblrPanel = new JPanel(new BorderLayout());
		var objCodeLabel = new JLabel("Object Code", SwingConstants.CENTER);
		
		objCodeLabel.setBorder(textAreaBorder);
		
		objCodeTextArea = new JTextArea("", 5, 50);
		objCodeTextArea.setBorder(textAreaBorder);
		objCodeTextArea.setLineWrap(true); // Prevents text area from expanding
		
		var asmblrLtngLabel = new JLabel("Assembler Listing", SwingConstants.CENTER);
		var asmblrTextArea = new JTextArea("", 5, 50);
		
		asmblrLtngLabel.setBorder(textAreaBorder);
		
		asmblrTextArea.setBorder(textAreaBorder);
		asmblrTextArea.setLineWrap(true); // Prevents text area from expanding
		objAndAsmblrPanel.add(objCodeLabel, BorderLayout.NORTH);
		objAndAsmblrPanel.add(objCodeTextArea, BorderLayout.CENTER);

		var objAndAsmblrPanelTwo = new JPanel(new BorderLayout());
		objAndAsmblrPanelTwo.add(asmblrLtngLabel, BorderLayout.NORTH);
		objAndAsmblrPanelTwo.add(asmblrTextArea, BorderLayout.CENTER);
		
		var objAndAsmblrPanelThree = new JPanel(new BorderLayout());
		objAndAsmblrPanelThree.add(objAndAsmblrPanel, BorderLayout.NORTH);
		objAndAsmblrPanelThree.add(objAndAsmblrPanelTwo, BorderLayout.CENTER);
		
		// Add object code and assembler listing text areas to South panel
		southPanel.add(objAndAsmblrPanelThree, BorderLayout.WEST); 

		// The lines below create the output panel text area
		var outputPanel = new JPanel(new BorderLayout());
		var outputLabel = new JLabel("Output", SwingConstants.CENTER);
		
		outputLabel.setBorder(textAreaBorder);
		
		outputTextArea = new JTextArea(""); // Auto resizes to fit entire area
		outputTextArea.setBorder(textAreaBorder);
		outputPanel.add(outputLabel, BorderLayout.NORTH);
		outputPanel.add(outputTextArea, BorderLayout.CENTER);
		
		southPanel.add(outputPanel, BorderLayout.CENTER); // Adds output text area to South panel
		
		return southPanel;
	}
	
	/**
	 * Adds an ActionListener to the run button so that the simulator can 
	 * execute machine language programs when it is clicked.
	 * 
	 * @param ActionListener instructions.
	 * */
	public void addRunButtonListener(ActionListener listenForRunButton) {
		runButton.addActionListener(listenForRunButton);
	}
	
	/**
	 * Adds an ActionListener to the build button so that the simulator can
	 * assemble the assembly language instructions when it is clicked.
	 * 
	 * @param ActionListener instructions.
	 * */
	public void addBuildButtonListener(ActionListener listenForBuildButton) {
		buildButton.addActionListener(listenForBuildButton);
	}
	
	/**
	 * Adds a DocumentListener to the decimalField text area in the north panel
	 * where the user can provide a decimal value to be converted into binary,
	 * hex, and ASCII.
	 * 
	 * @param DocumentListener instructions.
	 * */
	public void addConversionDocListener(DocumentListener listenForDocChanges) {
		decimalField.getDocument().addDocumentListener(listenForDocChanges);
	}
	
	// Getter methods below:
	
	/**
	 * The method below returns a string representation of the information stored 
	 * in the object code text area.
	 * 
	 * @return A string representation of the data in the object code text area.
	 * */
	public String getObjCodeTextArea() {
		return objCodeTextArea.getText();
	}
	
	/**
	 * The method below returns a string representation of the information 
	 * stored in the source code text area
	 * 
	 * @return A string representation of the data in the source code text area.
	 * */
	public String getSourceCodeTextArea() {
		return sourceCodeText.getText();
	}
	
	/**
	 * The method below returns a string representation of the information 
	 * stored in the input text area.
	 * 
	 * @return A string representation of the data in the input text area.
	 * */
	public String getInputTextArea() {
		return inputTextArea.getText().toString();
	}
	
	/**
	 * The method below returns a string representation of the information stored 
	 * in the decimalField text field.
	 * 
	 * @return A string representation of the data in the decimalField text field.
	 * */
	public String getDecimalField() {
		return decimalField.getText().toString();
	}
	
	// Setter methods below:
	
	/**
	 * The method below sets the data in the object code text area.
	 * 
	 * @param A string representation of machine language programs.
	 * */
	public void setObjectCodeTextArea(String text) {
		objCodeTextArea.setText(text);
	}
	
	/**
	 * The method below sets the data in the memory dump text area.
	 * 
	 * @param Memory dump data.
	 * */
	public void setMemDumpText(String text) {
		memTextArea.setText(text);
	}
	
	/**
	 * The method below sets the data in the output text area.
	 * 
	 * @param An ASCII representation of the machine language programs compiled.
	 * */
	public void setOutputTextArea(String text) {
		outputTextArea.setText(text);
	}
	
	/**
	 * The method below sets the data within the hexField text field.
	 * This text field holds a hex conversion of decimal user input.
	 * 
	 * @param A hex string.
	 * */
	public void setHexField(String text) { // Conversion tool
		hexField.setText(text);
	}
	
	/**
	 * The method below sets the data within the binaryField text field.
	 * This text field holds a binary conversion of decimal user input.
	 * 
	 * @param A binary string.
	 * */
	public void setBinaryField(String text) { // Conversion tool
		binaryField.setText(text);
	}
	
	/**
	 * The method below sets the data within the asciiField text field.
	 * This text field holds an ASCII conversion of decimal user input.
	 * 
	 * @param An ASCII string.
	 * */
	public void setAsciiField(String text) { // Conversion tool
		asciiField.setText(text);
	}
	
	/**
	 * The method below sets the data within the opCodeField label.
	 * This label presents the opcode within the decimal, hex, binary, and
	 * ASCII text field, if an opcode exists.
	 * 
	 * @param A string representation of an opcode.
	 * */
	public void setOpCodeField(String text) { // Conversion tool
		opCodeField.setText(text);
	}
	
	/**
	 * The method below sets the data within the instruction specifier
	 * text field. This data presents the state of the program with
	 * regards to the instruction specifier.
	 * 
	 * @param A string representation of instruction specifier data.
	 * */
	public void setInstSpcfrText(String text) {
		instSpcfrText.setText(text);
	}
	
	/**
	 * The methods below set the data within the accumulator 
	 * text fields. This data presents the state of the program with
	 * regards to the accumulator.
	 * 
	 * @param A string representation of accumulator data.
	 * */
	public void setAcmltrTextOne(String text) {
		acmltrTextOne.setText(text);
	}
	
	public void setAcmltrTextTwo(String text) {
		acmltrTextTwo.setText(text);
	}
	
	/**
	 * The methods below set the data within the program counter 
	 * text fields. This data presents the state of the program with
	 * regards to the program counter.
	 * 
	 * @param A string representation of the program counter data.
	 * */
	public void setPrgrmCntrTextOne(String text) {
		prgrmCntrTextOne.setText(text);
	}
	
	public void setPrgrmCntrTextTwo(String text) {
		prgrmCntrTextTwo.setText(text);
	}
	
	/**
	 * The methods below set the data within the operand specifier 
	 * text fields. This data presents the state of the program with
	 * regards to the operand specifier.
	 * 
	 * @param A string representation of the operand specifier data.
	 * */
	public void setOprndSpcfrTextOne(String text) {
		oprndSpcfrTextOne.setText(text);
	}
	
	public void setOprndSpcfrTextTwo(String text) {
		oprndSpcfrTextTwo.setText(text);
	}
	
	/**
	 * The methods below set the data within the operand
	 * text fields. This data presents the state of the program with
	 * regards to the operand.
	 * 
	 * @param A string representation of the operand data.
	 * */
	public void setOprndTextOne(String text) {
		oprndTextOne.setText(text);
	}
	
	public void setOprndTextTwo(String text) {
		oprndTextTwo.setText(text);
	}
	
	/**
	 * The method below sets the data within the N text field.
	 * This data presents some state of the program.
	 * 
	 * @param A string representation of N data.
	 * */
	public void setNTextBox(String text) {
		ntextBox.setText(text);
	}
	
	/**
	 * The method below sets the data within the Z text field.
	 * This data presents some state of the program.
	 * 
	 * @param A string representation of Z data.
	 * */
	public void setZTextBox(String text) {
		ztextBox.setText(text);
	}

	/**
	 * The method below sets the data within the V text field.
	 * This data presents some state of the program.
	 * 
	 * @param A string representation of V data.
	 * */
	public void setVTextBox(String text) {
		vtextBox.setText(text);
	}
	
	/**
	 * The method below sets the data within the C text field.
	 * This data presents some state of the program.
	 * 
	 * @param A string representation of C data.
	 * */
	public void setCTextBox(String text) {
		ctextBox.setText(text);
	}
}
