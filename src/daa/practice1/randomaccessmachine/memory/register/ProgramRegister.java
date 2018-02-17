/** File ProgramRegister. */
package daa.practice1.randomaccessmachine.memory.register;

import daa.practice1.randomaccessmachine.memory.instruction.InstructionType;
import daa.practice1.randomaccessmachine.memory.instruction.Operating;

/**
 * The class ProgramRegister contains the instruction in a register.
 * 
 * @author Ángel Igareta
 * @version 2.0
 * @since 12-2-2018
 */
public class ProgramRegister extends Register<String> {

	/** String that represent the Type of instruction the line contains. */
	private InstructionType instructionType;
	private Operating operating;

	/**
	 * The constructor analyze the raw instruction passed by argument and classifies
	 * it thanks to the method analyzeInstructionType.
	 * 
	 * @param data
	 *          Raw instruction.
	 */
	public ProgramRegister(String data) throws Exception {
		super(data);
		
		// If there is something apart from the instruction and the operand
		if (data.split("[\t ]+").length > 2) {
			throw new Exception("The instruction '" + data + "' has more than 1 operand.");
		}

		analyzeInstructionType(data.split("[\t ]+")[0]);
		
		if (instructionType == null) {
			throw new Exception("The instruction '" + data + "' doesn't exist.");
		}

		if (instructionType != InstructionType.halt) {
			operating = new Operating(data.split("[\t ]+")[1]);
		}
	}

	/**
	 * Method to classify the instruction depending on it's name.
	 * 
	 * @param Represents
	 *          the instruction Name.
	 */
	private void analyzeInstructionType(String instructionName) {
		for (Enum instructionRealName : InstructionType.values()) {
			if (instructionName.toLowerCase().equals(instructionRealName.name())
					|| instructionName.toUpperCase().equals(instructionRealName.name())) {
				instructionType = InstructionType.valueOf(instructionRealName.name());
			}
		}
	}

	/**
	 * Getter of the instructionType.
	 * 
	 * @return The instruction Type.
	 */
	public InstructionType getInstructionType() {
		return instructionType;
	}

	/**
	 * @return The Operating.
	 */
	public Operating getOperating() {
		return operating;
	}

}
