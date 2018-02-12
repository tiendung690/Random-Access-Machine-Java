/**
 * 
 */
package daa.practice1.randomaccessmachine.io;

import java.io.*;

/**
 * @author angel
 *
 */
public class OutputTape {
	
	BufferedWriter outputTape;

	public OutputTape(String outputTapeFilename) throws IOException {
		outputTape = new BufferedWriter(new FileWriter(outputTapeFilename));
	}
	
	public void write(String lineToWrite) throws IOException {
		outputTape.write(lineToWrite);
	}
	
	public void dispose() throws IOException {
		System.out.println("Closing Output Tape");
		outputTape.close();
	}
}
