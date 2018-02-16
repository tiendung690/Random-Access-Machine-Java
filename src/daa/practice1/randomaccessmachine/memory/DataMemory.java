/** File DataMemory. */
package daa.practice1.randomaccessmachine.memory;

import daa.practice1.randomaccessmachine.memory.register.DataRegister;

/**
 * Class that contains an array of the Data Register that 
 * the Random Access Machine will use.
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class DataMemory extends InfiniteMemory<DataRegister> {
	
	/**
	 * Constructor that only calls it's superclass constructor.
	 */
	public DataMemory() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see daa.practice1.randomaccessmachine.memory.InfiniteMemory#setRegisterAt(int, java.lang.Object)
	 */
	@Override
	public void setRegisterAt(int index, DataRegister data) {
		memory.put(getPosition(index), data);
	}

	/* (non-Javadoc)
	 * @see daa.practice1.randomaccessmachine.memory.InfiniteMemory#getRegisterAt(int)
	 */
	@Override
	public DataRegister getRegisterAt(int index) {
		if (!memory.containsKey(index)) {
			setRegisterAt(index, new DataRegister());
		}
		return memory.get(getPosition(index));
	}
	
	public DataRegister getACC() {
		return getRegisterAt(0);
	}
	
}
