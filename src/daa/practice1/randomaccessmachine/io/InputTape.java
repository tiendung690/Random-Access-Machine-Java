/** File InputTape, */
package daa.practice1.randomaccessmachine.io;

/** Import the IOPackage necessary for FileReader and BufferedReader. */
import java.io.*;


/**
 * Class that does all the necessary operations with the inputTape. This includes
 * creating the buffer, reading from it and closing it.
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class InputTape {

	/** InputTape is a Buffer of the inputTape. */
	private BufferedReader inputTape;
	
	/**
	 * Constructor of the InputTape class. It initializes the inputTape to a BufferedReader
	 * that contains text from a character-input stream.
	 * @param inputTapeFilename Name of the file that contains the inputTape.
	 * @throws FileNotFoundException
	 */
	public InputTape(String inputTapeFilename) throws FileNotFoundException {
		inputTape = new BufferedReader(new FileReader(inputTapeFilename));
	}
	
	/**
	 * Read a line from the buffer that contains text from the inputTape.
	 * @return String read in the file that contains the inputTape.
	 * @throws IOException
	 */
	public String read() throws IOException {
		return inputTape.readLine();
	}
	
	/**
	 * Close the buffer where inputTape was reading.
	 * @throws IOException
	 */
	public void close() throws IOException {
		inputTape.close();
	}
}
