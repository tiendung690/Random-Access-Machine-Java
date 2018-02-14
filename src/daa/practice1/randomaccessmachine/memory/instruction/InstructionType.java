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
		int registerNumber = Integer.parseInt(operatorName.replaceAll("[\\D]", ""));
		
		if (operatorName.startsWith("=")) {
			operatorType = OperatorType.DIRECT_ADDRESSING;
			operatorType.setRegisterNumber(registerNumber);
		}
		else if (operatorName.startsWith("*")) {
			operatorType = OperatorType.INDIRECT_ADDRESSING;
			operatorType.setRegisterNumber(registerNumber);
			
		}
		else if (operatorName.endsWith(":")) {
			operatorType = OperatorType.TAG;
		}
		else {
			operatorType.setRegisterNumber(registerNumber);
		}
	}
	
	public OperatorType getOperatorType() {
		return operatorType;
	}
}
