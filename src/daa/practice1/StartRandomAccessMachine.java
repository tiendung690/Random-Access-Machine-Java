/** File: StartRandomAccessMachine.java */
package daa.practice1;

import java.io.IOException;
import daa.practice1.randomaccessmachine.RandomAccessMachine;

/**
 * Class that starts a Random AccessMachine depending on the arguments passed to
 * main method.
 * 
 * @author Ángel Igareta
 * @version 1.0
 * @since 19-2-2018
 */
public class StartRandomAccessMachine {

	/**
	 * @return Help of the RandomAccessMachine program call.
	 */
	private static void showHelp() {
		System.out.print("This program recieves 3 arguments and 1 option: " + System.lineSeparator()
				+ "The first argument is the file that contains the program." + System.lineSeparator()
				+ "The second argument is the file that contains the inputfile." + System.lineSeparator()
				+ "The third argument is the file that contains the outputfile." + System.lineSeparator()
				+ "The fourth argument is the debug option. You can call it as --debug or -d");
	}

	/**
	 * Method that starts a RandomAccessMachine
	 * 
	 * @param args
	 *          The arguments are: 1. The first argument is the file that contains
	 *          the program. 2. The second argument is the file that contains the
	 *          inputfile. 3. The third argument is the file that contains the
	 *          outputfile. 4. The fourth argument is the debug option.
	 * @throws IOException
	 */
	public static void main(String[] args) {
		if ((args.length != 3) && (args.length != 4)) {
			System.out.println("ERROR: Invalid number of arguments");
			showHelp();
		}
		else {
			boolean debug = ((args.length != 3) && (args[3].equals("debug"))) ? true : false;

			try {
				RandomAccessMachine ram = new RandomAccessMachine(args[0], args[1], args[2]);
				ram.start(debug);
			}
			catch (IOException e) {
				System.out.println("IOException:" + e.getMessage());
			}
			catch (Exception e) {
				System.out.println("Error reading the Program file: " + e.getMessage());
			}
		}
	}

}
