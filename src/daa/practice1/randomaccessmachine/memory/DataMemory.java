/** File DataMemory. */
package daa.practice1.randomaccessmachine.memory;

import daa.practice1.randomaccessmachine.memory.register.DataRegister;

/**
 * Class that contains an array of the Data Register that the Random Access
 * Machine will use.
 * 
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class DataMemory extends InfiniteMemory<DataRegister> {

	/**
	 * Constructor that only calls it's superclass constructor to initialize the
	 * memory.
	 */
	public DataMemory() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * daa.practice1.randomaccessmachine.memory.InfiniteMemory#setRegisterAt(int,
	 * java.lang.Object)
	 * 
	 * @throws Exception Throws an exception if the index that is being accesed. is
	 * negative.
	 */
	@Override
	public void setRegisterAt(int index, DataRegister data) throws Exception {
		if (index < 0) {
			throw new Exception("Can't access a negative register.");
		}

		memory.put(getMemoryPosition(index), data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * daa.practice1.randomaccessmachine.memory.InfiniteMemory#getRegisterAt(int)
	 * 
	 * @throws Exception Throws an exception if the index that is being accesed. is
	 * negative.
	 */
	@Override
	public DataRegister getRegisterAt(int index) throws Exception {
		if (index < 0) {
			throw new Exception("Can't access a negative register.");
		}

		if (!memory.containsKey(index)) {
			setRegisterAt(index, new DataRegister());
		}

		return memory.get(getMemoryPosition(index));
	}

	/**
	 * Method that returns the ACC or First Register.
	 * 
	 * @return The ACC or First Register.
	 * @throws Exception
	 *           Throws an exception if the index that is being accesed is negative.
	 */
	public DataRegister getACC() throws Exception {
		return getRegisterAt(0);
	}

}
