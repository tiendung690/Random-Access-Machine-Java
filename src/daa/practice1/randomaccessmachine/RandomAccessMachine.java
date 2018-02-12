/**
 * 
 */
package daa.practice1.randomaccessmachine;

import java.io.IOException;

import daa.practice1.randomaccessmachine.alu.ArithmeticLogicControlUnit;
import daa.practice1.randomaccessmachine.io.OutputTape;
import daa.practice1.randomaccessmachine.memory.*;

/**
 * @author angel
 *
 */
public class RandomAccessMachine {
	
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
	 */
	public static void main(String[] args) throws IllegalArgumentException, IOException {
		if ((args.length != 3) &&  (args.length != 4)) {
			throw new IllegalArgumentException(showHelp());
		}
		
		ProgramMemory programMemory = new ProgramMemory(args[0]);
		DataMemory dataMemory = new DataMemory();

		OutputTape outputTape = new OutputTape(args[2]);

		outputTape.close();
	}
}
