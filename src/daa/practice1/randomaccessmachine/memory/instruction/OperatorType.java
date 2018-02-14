/** File OperatorType. */
package daa.practice1.randomaccessmachine.memory.instruction;

/**
 * OperatorType is an enum for all the operators that our ram machine can have.
 * @author Ángel Igareta
 * @version 1.0
 * @since 14-2-2018
 */
public enum OperatorType {
	CONSTANT_ADDRESSING,
	DIRECT_ADDRESSING,
	INDIRECT_ADDRESSING,
	TAG;
	
	/** If it's an operator, the registerNumber represents the number of the register. */
	private int registerNumber;
	/** If it's a tag, the tagName represents the name of that Tag. */
	private String tagName;
	
	/**
	 * @param registerNumber Register number to set.
	 */
	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}
	
	/**
	 * @return Number of the register.
	 */
	public int getRegisterNumber() {
		return registerNumber;
	}

	/**
	 * @param tagName Tag Name to be set.
	 */
	public void setTag(String tagName) {
		this.tagName = tagName;
	}
	
	/**
	 * @return Name of the tag.
	 */
	public String getTag() {
		return tagName;
	}
}
