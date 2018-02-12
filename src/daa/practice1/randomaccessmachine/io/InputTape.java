/**
 * 
 */
package daa.practice1.randomaccessmachine.io;

import java.io.*;

/**
 * @author angel
 *
 */
public class InputTape {

	private BufferedReader inputTape;
	
	public InputTape(String outputTapeFilename) throws FileNotFoundException {
		inputTape = new BufferedReader(new FileReader(outputTapeFilename));
	}
	
	public String read() throws IOException {
		return inputTape.readLine();
	}
	
	public void close() throws IOException {
		inputTape.close();
	}

}
