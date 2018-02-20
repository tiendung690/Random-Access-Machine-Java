/** File ArithmeticLogicUnit */
package daa.practice1.randomaccessmachine.alu;

import daa.practice1.randomaccessmachine.memory.register.DataRegister;
import daa.practice1.randomaccessmachine.memory.register.FloatRegister;

/**
 * Abstract class that represents the Arithmetic Logic Unit of the Random Access
 * Machine. It has all the necessary methods to assign, add, subtract, multiply
 * and divide registers with another registers or with constants.
 * 
 * @author Ángel Igareta
 * @version 1.0
 * @since 16-2-2018
 */
public abstract class ArithmeticLogicUnit {

	/*
	 * Method used to assign a value to a register passed by argument.
	 * 
	 * @param register1 
	 * @param value
	 */
	public static void assign(DataRegister register1, Integer value) {
		register1.set(value);
	}
	
	

	/**
	 * Method used to add the values of two registers and save the result in the
	 * first register passed by argument.
	 * 
	 * @param register1
	 * @param register2
	 */
	public static void add(DataRegister register1, DataRegister register2) {
		int sum = register1.get() + register2.get();
		register1.set(sum);
	}

	/**
	 * Method used to add the values of a register and a constant, saving the result
	 * in the first register passed by argument.
	 * 
	 * @param register1
	 * @param constant
	 */
	public static void add(DataRegister register1, int constant) {
		int sum = register1.get() + constant;
		register1.set(sum);
	}

	/**
	 * Method used to subtract the values of two registers and save the result in
	 * the first register passed by argument.
	 * 
	 * @param register1
	 * @param register2
	 */
	public static void subtract(DataRegister register1, DataRegister register2) {
		int sub = register1.get() - register2.get();
		register1.set(sub);
	}

	/**
	 * Method used to subtract the values of a register and a constant, saving the
	 * result in the first register passed by argument.
	 * 
	 * @param register1
	 * @param constant
	 */
	public static void subtract(DataRegister register1, int constant) {
		int sub = register1.get() - constant;
		register1.set(sub);
	}

	/**
	 * Method used to multiply the values of two registers and save the result in
	 * the first register passed by argument.
	 * 
	 * @param register1
	 * @param register2
	 */
	public static void multiply(DataRegister register1, DataRegister register2) {
		int mul = register1.get() * register2.get();
		register1.set(mul);
	}

	/**
	 * Method used to multiply the values of a register and a constant, saving the
	 * result in the first register passed by argument.
	 * 
	 * @param register1
	 * @param constant
	 */
	public static void multiply(DataRegister register1, int constant) {
		int mul = register1.get() * constant;
		register1.set(mul);
	}

	/**
	 * Method used to divide the values of two registers and save the result in the
	 * first register passed by argument.
	 * 
	 * @param register1
	 * @param register2
	 * @throws ArithmeticException
	 *           If the denominator of the division is 0.
	 */
	public static void divide(DataRegister register1, DataRegister register2) throws ArithmeticException {
		if (register2.get() == 0) {
			throw new ArithmeticException("The denominator of the division can't be 0.");
		}
		else {
			int div = register1.get() / register2.get();
			register1.set(div);
		}
	}

	/**
	 * Method used to divide the values of a register and a constant, saving the
	 * result in the first register passed by argument.
	 * 
	 * @param register1
	 * @param constant
	 * @throws ArithmeticException
	 *           If the denominator of the division is 0.
	 */
	public static void divide(DataRegister register1, int constant) throws ArithmeticException {
		if (constant == 0) {
			throw new ArithmeticException("The denominator of the division can't be 0.");
		}
		else {
			int div = register1.get() / constant;
			register1.set(div);
		}
	}
	
	/*
	 * Method used to assign a value to a register passed by argument.
	 * 
	 * @param register1 
	 * @param value
	 */
	public static void assign(FloatRegister register1, Double value) {
		register1.set(value);
	}
	
	

	/**
	 * Method used to add the values of two registers and save the result in the
	 * first register passed by argument.
	 * 
	 * @param register1
	 * @param register2
	 */
	public static void add(FloatRegister register1, FloatRegister register2) {
		double sum = register1.get() + register2.get();
		register1.set(sum);
	}

	/**
	 * Method used to add the values of a register and a constant, saving the result
	 * in the first register passed by argument.
	 * 
	 * @param register1
	 * @param constant
	 */
	public static void add(FloatRegister register1, double constant) {
		double sum = register1.get() + constant;
		register1.set(sum);
	}

	/**
	 * Method used to subtract the values of two registers and save the result in
	 * the first register passed by argument.
	 * 
	 * @param register1
	 * @param register2
	 */
	public static void subtract(FloatRegister register1, FloatRegister register2) {
		double sub = register1.get() - register2.get();
		register1.set(sub);
	}

	/**
	 * Method used to subtract the values of a register and a constant, saving the
	 * result in the first register passed by argument.
	 * 
	 * @param register1
	 * @param constant
	 */
	public static void subtract(FloatRegister register1, double constant) {
		double sub = register1.get() - constant;
		register1.set(sub);
	}

	/**
	 * Method used to multiply the values of two registers and save the result in
	 * the first register passed by argument.
	 * 
	 * @param register1
	 * @param register2
	 */
	public static void multiply(FloatRegister register1, FloatRegister register2) {
		double mul = register1.get() * register2.get();
		register1.set(mul);
	}

	/**
	 * Method used to multiply the values of a register and a constant, saving the
	 * result in the first register passed by argument.
	 * 
	 * @param register1
	 * @param constant
	 */
	public static void multiply(FloatRegister register1, double constant) {
		double mul = register1.get() * constant;
		register1.set(mul);
	}

	/**
	 * Method used to divide the values of two registers and save the result in the
	 * first register passed by argument.
	 * 
	 * @param register1
	 * @param register2
	 * @throws ArithmeticException
	 *           If the denominator of the division is 0.
	 */
	public static void divide(FloatRegister register1, FloatRegister register2) throws ArithmeticException {
		if (register2.get() == 0) {
			throw new ArithmeticException("The denominator of the division can't be 0.");
		}
		else {
			double div = register1.get() / register2.get();
			register1.set(div);
		}
	}

	/**
	 * Method used to divide the values of a register and a constant, saving the
	 * result in the first register passed by argument.
	 * 
	 * @param register1
	 * @param constant
	 * @throws ArithmeticException
	 *           If the denominator of the division is 0.
	 */
	public static void divide(FloatRegister register1, double constant) throws ArithmeticException {
		if (constant == 0) {
			throw new ArithmeticException("The denominator of the division can't be 0.");
		}
		else {
			double div = register1.get() / constant;
			register1.set(div);
		}
	}

}
