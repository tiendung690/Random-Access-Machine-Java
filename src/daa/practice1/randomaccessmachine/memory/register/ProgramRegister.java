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
	/** Operating object that represents the operating of the instruction. */
	private Operating operating;

	/**
	 * The constructor analyze the raw instruction passed by argument and classifies
	 * it thanks to the method analyzeInstructionType.
	 * 
	 * @param data
	 *          Raw instruction.
	 * 
	 * @throws Exception
	 *           Throws exception if the instruction doesn't exist, if it has more
	 *           than one operand and it's not a comment or if the operand is
	 *           invalid.
	 * 
	 */
	public ProgramRegister(String data) throws Exception {
		super(data);
		String[] splittedData = data.split("[\t ]+");

		analyzeInstructionType(splittedData[0]);

		if (instructionType == null) {
			throw new Exception("The instruction '" + data + "' doesn't exist.");
		}
		else if ((instructionType == InstructionType.halt) && (splittedData.length > 1)
				&& !splittedData[1].startsWith("#")) {
			throw new Exception("The instruction '" + data + "' can't have an operand.");
		}
		else if (instructionType != InstructionType.halt) {
			if ((splittedData.length > 2) && !splittedData[2].startsWith("#")) {
				throw new Exception("The instruction '" + data + "' has more than 1 operand.");
			}
			else if (splittedData.length > 1) {
				try {
					operating = new Operating(splittedData[1]);
				}
				catch (NumberFormatException e) {
					throw new Exception("The instruction '" + data + "' has an invalid operand.");
				}
			}
			else {
				throw new Exception("The instruction '" + data + "' doesn't have an operand.");
			}
		}
	}

	/**
	 * Method to classify the instruction depending on it's name. It iterates from
	 * the Enum InstructionType and see if a instruction matches. It is case
	 * insensitive.
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
	 * Getter of the Operating.
	 * 
	 * @return The Operating.
	 */
	public Operating getOperating() {
		return operating;
	}

}
