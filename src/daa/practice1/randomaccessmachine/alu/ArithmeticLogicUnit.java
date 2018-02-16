package daa.practice1.randomaccessmachine.alu;

import daa.practice1.randomaccessmachine.memory.DataMemory;
import daa.practice1.randomaccessmachine.memory.register.DataRegister;

/**
 * [DESCRIPTION]
 * @author Ángel Igareta
 * @version 1.0
 * @since X-X-2018
 */
public class ArithmeticLogicUnit {

	public ArithmeticLogicUnit() {
		
	}
	
	public void assign(DataRegister register1, Integer value) {
		register1.set(value);
	}
	
	public void add(DataRegister register1, DataRegister register2) {
		int sum = register1.get() + register2.get();
		register1.set(sum);
	}
	
	public void add(DataRegister register1, int constant) {
		int sum = register1.get() + constant;
		register1.set(sum);
	}
	
	public void sub(DataRegister register1, DataRegister register2) {
		int sub = register1.get() - register2.get();
		register1.set(sub);
	}
	
	public void sub(DataRegister register1, int constant) {
		int sub = register1.get() - constant;
		register1.set(sub);
	}
	
	public void mul(DataRegister register1, DataRegister register2) {
		int mul = register1.get() * register2.get();
		register1.set(mul);
	}
	
	public void mul(DataRegister register1, int constant) {
		int mul = register1.get() * constant;
		register1.set(mul);
	}
	
	public void div(DataRegister register1, DataRegister register2) {
		int div = register1.get() / register2.get();
		register1.set(div);
	}
	
	public void div(DataRegister register1, int constant) {
		int div = register1.get() / constant;
		register1.set(div);
	}

}
