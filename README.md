# TCSS360-PRJ3
Final Pep/8 Simulator.

This is a README document for Group 1:
Jordge Aguilar
RJ Alabado
Tiarnan Marsten
Dung Tran

Our software is a simulator for the Pep/8 virtual machine, written in JAVA. Our program retains similar appearance to that of Pep/8, as well as some of the functionality.

We were able to replicate Pep/8 using Swing, able to make them look relatively similar. We implemented buttons and text boxes to type in input. However, while there are many buttons, not all of them are functional. The main functional button is the green “run” button located at the top. You are also able to type into all the available boxes, however, only the “Object Code”, “Input”, and “Output” boxes that are able to function well.

Because of these limitations, when compared to the Pep/8 virtual machine, we believe our program lacks sophistication. It’s just not as advanced and therefore has finite functionality. Within these confines, our simulator is only capable of printing an ASCII representation of object code, printing the same thing but in reverse, and is only able to sum two single digit integers.

To run each of the simulator’s capabilities, please follow these steps:

-Out Instruction:
	Input to “Object Code”: 51 00 07 51 00 08 00 48 69 zz
	Expected Output: Hi

-In and out:
	(Only capable of flipping two-letter words)

	Input to “Object Code”: 49 00 0D 49 00 0E 51 00 0E 51 00 0D 00 zz
	Input to “Input”: up
	Expected Output in “Output Box”: pu

	(Only capable of flipping four-letter words)

	Input to “Object Code”: 49 00 15 49 00 16 49 00 17 49 00 18 51 00 18 51 00 17 51 00 15 51 00 16 00 zz
       Input to “Input”: IOup
	Expected Output in “Output Box”: puIO

-Add:
       Input to “Object Code”: C1 00 11 71 00 13 A1 00 15 F1 00 10 51 00 10 00 00 02 00 04 00 30 zz
       Expected Output in “Output Box”: 6
       
       Input to “Object Code”: C1 00 11 71 00 13 A1 00 15 F1 00 10 51 00 10 00 00 05 00 03 00 30 zz
       Expected Output in “Output Box”: 8
