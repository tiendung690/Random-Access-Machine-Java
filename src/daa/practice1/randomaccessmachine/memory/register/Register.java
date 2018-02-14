/** File Register. */
package daa.practice1.randomaccessmachine.memory.register;


/**
 * Abstract class that represents a generic type of register and contains
 * a data and a method to read it.
 * @author Ángel Igareta
 * @version 1.0
 * @since X-X-2018
 */
public abstract class Register<T> {

	/** Generic data that the Register contains. */
	protected T data;
	
	/**
	 * Constructor that sets the data passed by argument to the Register data.
	 * @param data Data to set .
	 */
	public Register(T data) {
		this.data = data;
	}
	
	/**
	 * Method to read the data from the Register.
	 * @return Data Data of the register.
	 */
	public T read() {
		return data;
	}
}
