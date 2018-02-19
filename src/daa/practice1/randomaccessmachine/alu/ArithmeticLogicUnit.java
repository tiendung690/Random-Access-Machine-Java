package daa.practice1.randomaccessmachine.alu;

import daa.practice1.randomaccessmachine.memory.register.DataRegister;

/**
 * [DESCRIPTION]
 * @author Ángel Igareta
 * @version 1.0
 * @since X-X-2018
 */
public abstract class ArithmeticLogicUnit {
	
	public static void assign(DataRegister register1, Integer value) {
		register1.set(value);
	}
	
	public static void add(DataRegister register1, DataRegister register2) {
		int sum = register1.get() + register2.get();
		register1.set(sum);
	}
	
	public static void add(DataRegister register1, int constant) {
		int sum = register1.get() + constant;
		register1.set(sum);
	}
	
	public static void sub(DataRegister register1, DataRegister register2) {
		int sub = register1.get() - register2.get();
		register1.set(sub);
	}
	
	public static void sub(DataRegister register1, int constant) {
		int sub = register1.get() - constant;
		register1.set(sub);
	}
	
	public static void mul(DataRegister register1, DataRegister register2) {
		int mul = register1.get() * register2.get();
		register1.set(mul);
	}
	
	public static void mul(DataRegister register1, int constant) {
		int mul = register1.get() * constant;
		register1.set(mul);
	}
	
	public static void div(DataRegister register1, DataRegister register2) throws ArithmeticException {
		if (register2.get() == 0) {
			throw new ArithmeticException("The denominator of the division can't be 0.");
		}
		else {
			int div = register1.get() / register2.get();
			register1.set(div);			
		}
	}
	
	public static void div(DataRegister register1, int constant) throws ArithmeticException {
		if (constant == 0) {
			throw new ArithmeticException("The denominator of the division can't be 0.");
		}
		else {
			int div = register1.get() / constant;
			register1.set(div);			
		}
	}

}
