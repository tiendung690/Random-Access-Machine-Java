/**
 * 
 */
package daa.practice1.randomaccessmachine.memory;

/**
 * @author angel
 *
 */
public class DataMemory extends InfiniteMemory<DataRegister> {
	
	public DataMemory() {
		super();
		
		// R0
	}
	
	@Override
	public void setRegisterAt(int index, DataRegister data) {
		memory.set(getPosition(index), data);
	}

	@Override
	public DataRegister getRegisterAt(int index) {
		if (index < 0 || index >= memory.size()) {
			setRegisterAt(index, new DataRegister());
		}
		return memory.get(getPosition(index));
	}
}
