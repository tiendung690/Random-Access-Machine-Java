/** File OperatorType. */
package daa.practice1.randomaccessmachine.memory.instruction;

/**
 * OperatorType is an enum for all the operators that our ram machine can have.
 * @author Ángel Igareta
 * @version 1.0
 * @since 14-2-2018
 */
public class Operating {
	
	/** If it's an operator, the registerNumber represents the number of the register. */
	private int registerNumber;
	/** If it's a tag, the tagName represents the name of that Tag. */
	private String tagName;
	
	private String operatingName;
	
	public Operating(String operatingName) {
		analyzeOperatorType(operatingName);
	}
	
	/**
	 * This method analyzes a raw operator and classify it's type using the
	 * OperatorType Enum.
	 * @param operatorName Raw operator.
	 */
	public void analyzeOperatorType(String operatingName) {
		if (operatingName.matches("[0-9]*[a-zA-Z]+[0-9]*")) { // For being a TAG it must contain at least 1 char.
			this.operatingName = "TAG";
			this.tagName = operatingName;
		}
		else {
			int registerNumber = Integer.parseInt(operatingName.replaceAll("[\\D]", ""));	
			
			if (operatingName.startsWith("=")) {
				this.operatingName = "CONSTANT_ADDRESSING";
				this.registerNumber = registerNumber;
			}
			else if (operatingName.startsWith("*")) {
				this.operatingName = "INDIRECT_ADDRESSING";
				this.registerNumber = registerNumber;				
			}
			else {
				this.operatingName = "DIRECT_ADDRESSING";
				this.registerNumber = registerNumber;
			}
		}
	}
	
	public String getOperatingName() {
		return operatingName;
	}
	
	/**
	 * @return Number of the register.
	 */
	public int getRegisterNumber() {
		return registerNumber;
	}
	
	/**
	 * @return Name of the tag.
	 */
	public String getTag() {
		return tagName;
	}
}
