/** File OutputTape, */
package daa.practice1.randomaccessmachine.io;

/** Import the IOPackage necessary for FileWriter and BufferedWriter. */
import java.io.*;
import java.util.ArrayList;

/**
 * Class that does all the necessary operations with the outputTape. This
 * includes creating the buffer, writing in it and closing it.
 * 
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class OutputTape {

	/** outputTape is a Buffer of the Output File. */
	private BufferedWriter outputTape;

	/**
	 * Constructor of the OutputTape class. It initializes the outputTape to a
	 * BufferedWriter that can write text to a character-output stream.
	 * 
	 * @param outputTapeFilename
	 *          Name of the file that contains the outputTape.
	 * @throws IOException
	 *           Throws an error if the file was not found.
	 */
	public OutputTape(String outputTapeFilename) throws IOException {
		outputTape = new BufferedWriter(new FileWriter(outputTapeFilename));
	}

	/**
	 * Writes a line to the buffer that contains text from the outputTape.
	 * 
	 * @param numberToWrite
	 *          String that contains the line that is going to be written.
	 * @throws IOException
	 *           Throws an error if there was a problem writing the number.
	 */
	public void write(double numberToWrite) throws IOException {
		outputTape.write(String.valueOf(numberToWrite));
		outputTape.newLine();
	}
	
	public void writeArray(ArrayList<Double> outputArray) throws IOException {
		for (Double numberToWrite : outputArray) {
			this.write(numberToWrite);
		}
	}

	/**
	 * Close the buffer where the input was writing.
	 * 
	 * @throws IOException
	 *           Throws an error if there was a problem closing the buffer.
	 */
	public void close() throws IOException {
		outputTape.close();
	}
}
