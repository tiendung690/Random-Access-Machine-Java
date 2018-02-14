/** File ProgramRegister. */
package daa.practice1.randomaccessmachine.memory.register;

import daa.practice1.randomaccessmachine.memory.instruction.InstructionType;

/**
 * The class ProgramRegister contains the instruction in a register.
 * @author Ángel Igareta
 * @version 2.0
 * @since 12-2-2018
 */
public class ProgramRegister extends Register<String> {

	/**
	 * Enum that represent the Type of instruction the line contains.
	 */
	private InstructionType instructionType;
	
	/**
	 * The constructor analyze the raw instruction passed by argument and 
	 * classifies it thanks to the method analyzeInstructionType.
	 * @param data Raw instruction.
	 */
	public ProgramRegister(String data) {
		super(data);
		
		analyzeInstructionType(data.split("[\t ]+")[0]);
		
		if (instructionType != InstructionType.halt) {
			instructionType.analyzeOperatorType(data.split("[\t ]+")[1]);
		}
	}

	/**
	 * Method to classify the instruction depending on it's name.
	 * @param Represents the instruction Name.
	 */
	private void analyzeInstructionType(String instructionName) {
		switch(instructionName) {
			case "load":
			case "LOAD": instructionType = InstructionType.load; break;
			
			case "store":
			case "STORE": instructionType = InstructionType.store; break;
			
			case "add":
			case "ADD": instructionType = InstructionType.add; break;
			
			case "sub":
			case "SUB": instructionType = InstructionType.sub; break;
			
			case "mul":
			case "MUL": instructionType = InstructionType.mul; break;
			
			case "div":
			case "DIV": instructionType = InstructionType.div; break;
			
			case "read":
			case "READ": instructionType = InstructionType.read; break;
			
			case "write":
			case "WRITE": instructionType = InstructionType.write; break;
			
			case "jump":
			case "JUMP": instructionType = InstructionType.jump; break;
			
			case "jzero":
			case "JZERO": instructionType = InstructionType.jzero; break;
			
			case "jgtz":
			case "JGTZ": instructionType = InstructionType.jgtz; break;
			
			case "halt":
			case "HALT": instructionType = InstructionType.halt; break;
			
			default: // Throw custom exception
		}
	}	
	
	/**
	 * Getter of the instructionType.
	 * @return The instruction Type.
	 */
	public InstructionType getInstructionType() {
		return instructionType;
	}
}
