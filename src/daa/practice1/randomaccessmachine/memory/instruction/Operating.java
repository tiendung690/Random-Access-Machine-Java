/** File OperatorType. */
package daa.practice1.randomaccessmachine.memory.instruction;

/**
 * OperatorType is an enum for all the operating that our ram machine can have.
 * 
 * @author Ángel Igareta
 * @version 1.0
 * @since 14-2-2018
 */
public class Operating {

	/** If it's an operating, the operatingName represents it's type. */
	private String operatingName;
	/**
	 * If it's an operating, the registerNumber represents the number of the
	 * register.
	 */
	private int registerNumber;
	/** If it's a tag, the tagName represents the name of that Tag. */
	private String tagName;

	/**
	 * The constructor calls analyzeOperatingType to initialize the private
	 * variables. of the Class.
	 * 
	 * @param operatingName
	 */
	public Operating(String operatingName) {
		analyzeOperatingType(operatingName);
	}

	/**
	 * This method analyzes a raw operating and classify it's type using the
	 * OperatorType Enum. For being a TAG it must contain at least 1 non-digit.
	 * 
	 * @param operatingName
	 *          Raw operating.
	 * @throws NumberFormatException
	 *           Throws an exception if the Integer.parseInt method throws error.
	 */
	public void analyzeOperatingType(String operatingName) throws NumberFormatException {
		if (operatingName.matches("[0-9]*[a-zA-Z | !\"$%&.,?@~]{1}" + "[\\w | !\"$%&.,?@~]*")) {
			this.operatingName = "TAG";
			this.tagName = operatingName;
		}
		else {
			if (operatingName.startsWith("=")) {
				this.operatingName = "CONSTANT_ADDRESSING";
				this.registerNumber = Integer.parseInt(operatingName.replaceFirst("\\=", ""));
			}
			else if (operatingName.startsWith("*")) {
				this.operatingName = "INDIRECT_ADDRESSING";
				this.registerNumber = Integer.parseInt(operatingName.replaceFirst("\\*", ""));
			}
			else {
				this.operatingName = "DIRECT_ADDRESSING";
				this.registerNumber = Integer.parseInt(operatingName);
			}
		}
	}

	/**
	 * Getter of the operatingName private variable.
	 * @return Operating Name.
	 */
	public String getOperatingName() {
		return operatingName;
	}

	/**
	 * Getter of the registerNUmber private variable.
	 * @return Number of the register.
	 */
	public int getRegisterNumber() {
		return registerNumber;
	}

	/**
	 * Getter of the tagName private variable.
	 * @return Name of the tag.
	 */
	public String getTag() {
		return tagName;
	}
}
