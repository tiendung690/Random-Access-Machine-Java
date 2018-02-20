/** File DataRegister. */
package daa.practice1.randomaccessmachine.memory.register;

/**
 * Data Register is a subclass of Register with the Integer data type. Besides
 * it implements a method to change the data of the register.
 * 
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class DataRegister extends Register<Integer> {

	/** Default constructor that initializes the register data with 0. */
	public DataRegister() {
		super(0);
	}

	/**
	 * Constructor that initializes the register data with the value passed by
	 * argument.
	 * 
	 * @param data
	 *          Integer Value of the Data Register.
	 */
	public DataRegister(Integer data) {
		super(data);
	}

	/**
	 * Setter of the data private variable of the parent class.
	 * 
	 * @param data
	 *          Value to be set in the register.
	 */
	public void set(Integer data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return data.toString();
	}
}
