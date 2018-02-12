/**
 * 
 */
package daa.practice1.randomaccessmachine.memory;

import java.io.*;

/**
 * @author angel
 *
 */
public class ProgramMemory extends InfiniteMemory<ProgramRegister> {

	public ProgramMemory(String programFilename) throws IOException {
		super();
		
		BufferedReader reader = new BufferedReader(new FileReader(programFilename));
		int i = 0;
		
		while (reader.ready()) {
			setRegisterAt(i++, new ProgramRegister(reader.readLine()));
		}
		
		reader.close();
	}

	@Override
	public void setRegisterAt(int index, ProgramRegister data) {
		memory.set(getPosition(index), data);
	}

	@Override
	public ProgramRegister getRegisterAt(int index) {
		if (index < 0 || index >= memory.size()) {
			setRegisterAt(index, new ProgramRegister(""));
		}
		return memory.get(getPosition(index));
	}
}
