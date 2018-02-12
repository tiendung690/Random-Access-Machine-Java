/** File ProgramMemory. */
package daa.practice1.randomaccessmachine.memory;

/** Import the necessary classes for FileReader and BufferedReader.*/
import java.io.*;


/**
 * Class that contains an array of the Program Register that the Random Access Machine
 * will use.
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class ProgramMemory extends InfiniteMemory<ProgramRegister> {

	/**
	 * Constructor of the Program Memory. It reads all the lines in the program file and
	 * charges them in the Array List of ProgramRegister.
	 * @param programFilename String that contains the name of the program file.
	 * @throws IOException
	 */
	public ProgramMemory(String programFilename) throws IOException {
		super();
		
		BufferedReader reader = new BufferedReader(new FileReader(programFilename));
		int i = 0;
		
		while (reader.ready()) {
			setRegisterAt(i++, new ProgramRegister(reader.readLine()));
		}
		
		reader.close();
	}

	/* (non-Javadoc)
	 * @see daa.practice1.randomaccessmachine.memory.InfiniteMemory#setRegisterAt(int, java.lang.Object)
	 */
	@Override
	public void setRegisterAt(int index, ProgramRegister data) {
		memory.set(getPosition(index), data);
	}

	/* (non-Javadoc)
	 * @see daa.practice1.randomaccessmachine.memory.InfiniteMemory#getRegisterAt(int)
	 */
	@Override
	public ProgramRegister getRegisterAt(int index) {
		if (index < 0 || index >= memory.size()) {
			setRegisterAt(index, new ProgramRegister(""));
		}
		return memory.get(getPosition(index));
	}
}
