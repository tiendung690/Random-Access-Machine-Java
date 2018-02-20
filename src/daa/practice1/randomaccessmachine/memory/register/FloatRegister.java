/**
 * 
 */
package daa.practice1.randomaccessmachine.memory.register;

/**
 * @author alu0100967111
 *
 */
public class FloatRegister extends Register<Double> {
	
	/** Default constructor that initializes the register data with 0. */
	public FloatRegister() {
		super(0.0);
	}

	/**
	 * Constructor that initializes the register data with the value passed by
	 * argument.
	 * 
	 * @param data
	 *          Integer Value of the Data Register.
	 */
	public FloatRegister(Double data) {
		super(new Double(data));
	}

	/**
	 * Setter of the data private variable of the parent class.
	 * 
	 * @param data
	 *          Value to be set in the register.
	 */
	public void set(Double data) {
		this.data = new Double(data);
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
