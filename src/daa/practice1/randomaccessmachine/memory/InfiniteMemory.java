/**
 * 
 */
package daa.practice1.randomaccessmachine.memory;

import java.util.ArrayList;

/**
 * @author angel
 *
 */
public abstract class InfiniteMemory<T> {

	ArrayList<T> memory;
	Integer maxCapacity;

	public InfiniteMemory() {
		maxCapacity = new Double(Math.pow(10, 7)).intValue(); // TODO: Worth it?
		memory = new ArrayList<T>(maxCapacity);
	}
	
	protected int getPosition(int index) {
		return index % maxCapacity;
	}
	
	protected abstract T getRegisterAt(int index);
	protected abstract void setRegisterAt(int index, T data);
}
