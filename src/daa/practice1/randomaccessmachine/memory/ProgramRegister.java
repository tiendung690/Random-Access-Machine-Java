/** File ProgramRegister. */
package daa.practice1.randomaccessmachine.memory;


/**
 * [DESCRIPTION]
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class ProgramRegister extends Register<String> {

	private int instructionType;
	
	public ProgramRegister(String data) {
		super(data);
		analyzeInstructionType();
	}

	private void analyzeInstructionType() {
		;
	}	
	
}
