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
public enum OperatorType {
	CONSTANT_ADDRESSING,
	DIRECT_ADDRESSING,
	INDIRECT_ADDRESSING,
	TAG;
	
	private int registerNumber;
	private String tagName;
	
	public void setRegisterNumber(int registerNumber) {
		this.registerNumber = registerNumber;
	}
	
	public int getRegisterNumber() {
		return registerNumber;
	}

	public void setTag(String tagName) {
		this.tagName = tagName;
	}
	
	public String getTag() {
		return tagName;
	}
}
