/** File InputTape, */
package daa.practice1.randomaccessmachine.io;

/** Import the IOPackage necessary for FileReader and BufferedReader. */
import java.io.*;

/**
 * Class that does all the necessary operations with the inputTape. This
 * includes creating the buffer, reading from it and closing it.
 * 
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class InputTape {

	/** inputTape is a Buffer of the Input File. */
	private BufferedReader inputTape;

	/**
	 * Constructor of the InputTape class. It initializes the inputTape to a
	 * BufferedReader that contains text from a character-input stream.
	 * 
	 * @param inputTapeFilename
	 *          Name of the file that contains the inputTape.
	 * @throws IOException
	 *           If the file is not found.
	 */
	public InputTape(String inputTapeFilename) throws IOException {
		inputTape = new BufferedReader(new FileReader(inputTapeFilename));
	}

	/**
	 * Read a line from the buffer that contains text from the inputTape. This line
	 * must be an integer. If it isn't read() throws an error.
	 * 
	 * @return String read in the file that contains the inputTape.
	 * @throws Exception
	 *           Throws an error if the line is not an integer or there was an error
	 *           reading it.
	 */
	public Integer read() throws IOException {
		String lineRead;
		int returnValue;

		try {
			lineRead = inputTape.readLine();
			returnValue = Integer.parseInt(lineRead);
		}
		catch (IOException ioException) {
			throw new IOException("There was a problem reading the number.");
		}
		catch (NumberFormatException numberException) {
			throw new IOException("The read value is not a number.");
		}

		return returnValue;
	}

	/**
	 * Close the buffer where inputTape was reading.
	 * 
	 * @throws IOException
	 *           Throws an error if there was a problem closing the buffer.
	 */
	public void close() throws IOException {
		inputTape.close();
	}
}
