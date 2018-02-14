/** File ProgramRegister. */
package daa.practice1.randomaccessmachine.memory.register;

import daa.practice1.randomaccessmachine.memory.instruction.InstructionType;

/**
 * [DESCRIPTION]
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class ProgramRegister extends Register<String> {

	private InstructionType instructionType;
	
	public ProgramRegister(String data) {
		super(data);
		
		// TODO -> Start with TAG
		// if (instructionName.matches("[a-zA-Z0-9]*:")) {
		//	instructionType
		//}
		
		analyzeInstructionType(data.split(" ")[0]);
		
		if (instructionType != InstructionType.halt) {
			instructionType.analyzeOperatorType(data.split(" ")[1]);
		}
	}

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
		}
	}	
	
	public InstructionType getInstructionType() {
		return instructionType;
	}
}
