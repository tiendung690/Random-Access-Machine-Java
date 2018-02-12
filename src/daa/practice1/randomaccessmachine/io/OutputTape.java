/** File OutputTape, */
package daa.practice1.randomaccessmachine.io;

/** Import the IOPackage necessary for FileWriter and BufferedWriter. */
import java.io.*;


/**
 * Class that does all the necessary operations with the outputTape. This includes
 * creating the buffer, writing in it and closing it.
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class OutputTape {
	
	/** OutputTape is a Buffer of the outputTape. */
	private BufferedWriter outputTape;

	/**
	 * Constructor of the OutputTape class. It initializes the outputTape to a BufferedWriter
	 * that can write text to a character-output stream.
	 * @param outputTapeFilename Name of the file that contains the outputTape.
	 * @throws FileNotFoundException
	 */
	public OutputTape(String outputTapeFilename) throws IOException {
		outputTape = new BufferedWriter(new FileWriter(outputTapeFilename));
	}
	
	/**
	 * Writes a line to the buffer that contains text from the outputTape.
	 * @param lineToWrite String that contains the line that is going to be written.
	 * @throws IOException
	 */
	public void write(String lineToWrite) throws IOException {
		outputTape.write(lineToWrite);
	}
	
	/**
	 * Close the buffer where input was writing.
	 * @throws IOException
	 */
	public void close() throws IOException {
		outputTape.close();
	}
}
