/**
 * 
 */
package daa.practice1.randomaccessmachine.memory;

/**
 * @author angel
 *
 */
public class ProgramRegister extends Register<String> {

	int instructionType;
	
	public ProgramRegister(String data) {
		super(data);
		analyzeInstructionType();
	}

	private void analyzeInstructionType() {
		;
	}	
	
}
