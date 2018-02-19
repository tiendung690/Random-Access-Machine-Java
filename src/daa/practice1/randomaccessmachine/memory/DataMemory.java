/** File DataMemory. */
package daa.practice1.randomaccessmachine.memory;

import java.util.TreeMap;

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

	/** Log of used Registers */
	private TreeMap<Integer, DataRegister> usedRegisters;
	
	/**
	 * Constructor that only calls it's superclass constructor to initialize the
	 * memory.
	 */
	public DataMemory() {
		super();
		usedRegisters = new TreeMap<Integer, DataRegister>();
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
		
		if (!usedRegisters.containsKey(index)) {
			usedRegisters.put(index, data);			
		}
		
		memory.put(getMemoryPosition(index), data);
	}

	/**
	 * @return the usedRegisters
	 */
	public TreeMap<Integer, DataRegister> getUsedRegisters() {
		return usedRegisters;
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
