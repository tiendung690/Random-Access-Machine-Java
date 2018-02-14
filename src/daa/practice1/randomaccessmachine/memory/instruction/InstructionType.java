/** File InstructionType. */
package daa.practice1.randomaccessmachine.memory.instruction;

/**
 * InstructionType is an enum for all the instructions that our ram machine can have.
 * @author Ángel Igareta
 * @version 1.0
 * @since 14-2-2018
 */
public enum InstructionType {
	load,
	store,
	add,
	sub,
	mul,
	div,
	read,
	write,
	jump,
	jzero,
	jgtz,
	halt;
	
	private OperatorType operatorType;
	
	/**
	 * This method analyzes a raw operator and classify it's type using the
	 * OperatorType Enum.
	 * @param operatorName Raw operator.
	 */
	public void analyzeOperatorType(String operatorName) {
		if (operatorName.matches("[0-9]*[a-zA-Z]+[0-9]*")) { // For being a TAG it must contain at least 1 char.
			operatorType = OperatorType.TAG;
			operatorType.setTag(operatorName);
		}
		else {
			int registerNumber = Integer.parseInt(operatorName.replaceAll("[\\D]", ""));	
			
			if (operatorName.startsWith("=")) {
				operatorType = OperatorType.DIRECT_ADDRESSING;
				operatorType.setRegisterNumber(registerNumber);
			}
			else if (operatorName.startsWith("*")) {
				operatorType = OperatorType.INDIRECT_ADDRESSING;
				operatorType.setRegisterNumber(registerNumber);
				
			}
			else {
				operatorType = OperatorType.CONSTANT_ADDRESSING;
				operatorType.setRegisterNumber(registerNumber);
			}
		}
	}
	
	/**
	 * @return The OperatorType.
	 */
	public OperatorType getOperatorType() {
		return operatorType;
	}
}
