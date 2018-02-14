/**
 * 
 */
package daa.practice1.randomaccessmachine.memory.instruction;

/**
 * [DESCRIPTION]
 * @author Ángel Igareta
 * @version 1.0
 * @since X-X-2018
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
	
	public void analyzeOperatorType(String operatorName) {
		if (operatorName.matches("[0-9]*[a-zA-Z]+[0-9]*")) {
			operatorType = OperatorType.TAG;
			operatorType.setTag(operatorName);
		}
		else {
			int registerNumber = Integer.parseInt(operatorName.replaceAll("[\\D]", "")); // TODO: Throws error if there is a string		
			
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
	
	public OperatorType getOperatorType() {
		return operatorType;
	}
}
