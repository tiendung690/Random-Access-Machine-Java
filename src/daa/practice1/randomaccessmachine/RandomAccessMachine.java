/**
 * 
 */
package daa.practice1.randomaccessmachine;

import java.io.IOException;
import java.util.*;

import daa.practice1.randomaccessmachine.alu.ArithmeticLogicUnit;
import daa.practice1.randomaccessmachine.io.*;
import daa.practice1.randomaccessmachine.memory.*;

/**
 * @author angel
 *
 */
public class RandomAccessMachine {
	
	private Integer IpIndex;
	private ArithmeticLogicUnit alu;
	private ProgramMemory programMemory;
	private DataMemory dataMemory;
	private InputTape inputTape;	
	private OutputTape outputTape;
	
	public RandomAccessMachine(String programFilename, String inputTapeFilename, 
			String outputTapeFilename, boolean debug) throws IOException {
		
		programMemory = new ProgramMemory(programFilename);
		inputTape = new InputTape(inputTapeFilename);		
		outputTape = new OutputTape(outputTapeFilename);
		
		IpIndex = programMemory.getFirstRegister();
	}
	
	private void start() throws IOException {
		
		while (IpIndex != null) { // TODO: OR HALT
			
			
			//System.out.println(IpIndex);
			moveIP(programMemory.getNextRegister(IpIndex));
		}
		
		inputTape.close();
		outputTape.close();
	}
	
	private void moveIp() {
		IpIndex++;
	}
	
	private void moveIP(int nextIp) {
		IpIndex = nextIp;
	}
		
	private void executeInstruction() {
		
	}
		
	
	
	/**
	 * @return Help of the RandomAccessMachine program call.
	 */
	private static String showHelp() {
		return "This program recieves 3 arguments and 1 option: " + System.lineSeparator()
				+ "The first argument is the file that contains the program." + System.lineSeparator()
				+ "The second argument is the file that contains the inputfile." + System.lineSeparator()
				+ "The third argument is the file that contains the outputfile." + System.lineSeparator()
				+ "The fourth argument is the debug option. You can call it as --debug or -d";
	}

	/**
	 * Method that starts a RandomAccessMachine
	 * @param args The arguments are:
	 * 	1. The first argument is the file that contains the program.
	 * 	2. The second argument is the file that contains the inputfile.
	 * 	3. The third argument is the file that contains the outputfile.
	 * 	4. The fourth argument is the debug option.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, IOException { // TODO: Handle exceptions
		if ((args.length != 3) && (args.length != 4)) {
			throw new IllegalArgumentException(showHelp());
		}
		else {
			boolean debug = (args.length != 3) ? true : false;
			RandomAccessMachine ram = new RandomAccessMachine(args[0], args[1], args[2], debug);
			ram.start();
		}		
	}
}
