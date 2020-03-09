/**
 * 
 */
package daa.practice1.randomaccessmachine.memory;

import java.util.TreeMap;

import daa.practice1.randomaccessmachine.memory.register.FloatRegister;

/**
 * @author alu0100967111
 *
 */
public class FloatMemory extends InfiniteMemory<FloatRegister> {
	
	/** Log of used Registers */
	private TreeMap<Integer, FloatRegister> usedRegisters;
	
	/**
	 * Constructor that only calls it's superclass constructor to initialize the
	 * memory.
	 */
	public FloatMemory() {
		super();
		usedRegisters = new TreeMap<Integer, FloatRegister>();
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
	public void setRegisterAt(int index, FloatRegister data) throws Exception {
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
	public TreeMap<Integer, FloatRegister> getUsedRegisters() {
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
	public FloatRegister getRegisterAt(int index) throws Exception {
		if (index < 0) {
			throw new Exception("Can't access a negative register.");
		}

		if (!memory.containsKey(index)) {
			setRegisterAt(index, new FloatRegister());
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
	public FloatRegister getACC() throws Exception {
		return getRegisterAt(0);
	}
}
