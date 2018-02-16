/**
 * 
 */
package daa.practice1.randomaccessmachine;

import java.io.IOException;
import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import daa.practice1.randomaccessmachine.alu.ArithmeticLogicUnit;
import daa.practice1.randomaccessmachine.io.*;
import daa.practice1.randomaccessmachine.memory.*;
import daa.practice1.randomaccessmachine.memory.instruction.*;
import daa.practice1.randomaccessmachine.memory.register.*;

/**
 * @author angel
 *
 */
public class RandomAccessMachine {
	
	private Integer IpIndex;
	private ArithmeticLogicUnit alu;
	private ProgramMemory programMemory;
	private DataMemory dataMemory;
	private InputTape inputTape;	
	private OutputTape outputTape;
	
	public RandomAccessMachine(String programFilename, String inputTapeFilename, 
			String outputTapeFilename, boolean debug) throws IOException {
		
		programMemory = new ProgramMemory(programFilename);
		inputTape = new InputTape(inputTapeFilename);		
		outputTape = new OutputTape(outputTapeFilename);
		
		IpIndex = programMemory.getFirstRegister();
	}
	
	private void start() throws IOException {
		
		while (IpIndex != null) {
			executeInstruction(programMemory.getRegisterAt(IpIndex));
			moveIP(programMemory.getNextRegister(IpIndex));
		}
		
		inputTape.close();
		outputTape.close();
	}
	
	private void moveIP(Integer nextIp) {
		IpIndex = nextIp;
	}
		
	private void executeInstruction(ProgramRegister currentInstruction) {
		InstructionType instructionType = currentInstruction.getInstructionType();
		
		// Look for the method
		try {
			Method method = this.getClass().getDeclaredMethod(instructionType.name(), OperatorType.class);
			method.invoke(this, instructionType.getOperatorType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void load(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":

			case "DIRECT_ADDRESSING":
				
				break;
			case "INDIRECT_ADDRESSING":
				
				break;
			case "TAG":
				throw new Exception("Load can't have a tag as parameter.");
		}
	}
	
	private void store(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":
				
				break;
			case "DIRECT_ADDRESSING":
				
				break;
			case "INDIRECT_ADDRESSING":
				
				break;
			case "TAG":
				throw new Exception("Store can't have a tag as parameter.");
		}	
	}
	
	private void add(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":
				
				break;
			case "DIRECT_ADDRESSING":
				
				break;
			case "INDIRECT_ADDRESSING":
				
				break;
			case "TAG":
				throw new Exception("Add can't have a tag as parameter.");
		}
	}

	private void sub(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":
				
				break;
			case "DIRECT_ADDRESSING":
				
				break;
			case "INDIRECT_ADDRESSING":
				
				break;
			case "TAG":
				throw new Exception("Sub can't have a tag as parameter.");
		}	
	}
	
	private void mul(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":
				
				break;
			case "DIRECT_ADDRESSING":
				
				break;
			case "INDIRECT_ADDRESSING":
				
				break;
			case "TAG":
				throw new Exception("Mul can't have a tag as parameter.");
		}	
	}
	
	private void div(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":
				
				break;
			case "DIRECT_ADDRESSING":
				
				break;
			case "INDIRECT_ADDRESSING":
				
				break;
			case "TAG":
				throw new Exception("Div can't have a tag as parameter.");
		}	
	}
	
	private void read(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":
				
				break;
			case "DIRECT_ADDRESSING":
				
				break;
			case "INDIRECT_ADDRESSING":
				
				break;
			case "TAG":
				throw new Exception("Read can't have a tag as parameter.");
		}	
	}
	
	private void write(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":
				
				break;
			case "DIRECT_ADDRESSING":
				
				break;
			case "INDIRECT_ADDRESSING":
				
				break;
			case "TAG":
				throw new Exception("Write can't have a tag as parameter.");
		}	
	}
	
	private void jump(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":
				throw new Exception("Jump can't have an CONSTANT_ADDRESSING as parameter.");
			case "DIRECT_ADDRESSING":
				throw new Exception("Jump can't have an DIRECT_ADDRESSING as parameter.");
			case "INDIRECT_ADDRESSING":
				throw new Exception("Jump can't have an INDIRECT_ADDRESSING as parameter.");
			case "TAG":
				String tagForJump = operatorType.getTag();
				moveIP(programMemory.getLineOfTag(tagForJump));
		}	
	}
	
	private void jzero(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":
				throw new Exception("Jzero can't have an CONSTANT_ADDRESSING as parameter.");
			case "DIRECT_ADDRESSING":
				throw new Exception("Jzero can't have an DIRECT_ADDRESSING as parameter.");
			case "INDIRECT_ADDRESSING":
				throw new Exception("Jzero can't have an INDIRECT_ADDRESSING as parameter.");
			case "TAG":
				String tagForJump = operatorType.getTag();
				if (dataMemory.getACC().read() == 0) {
					moveIP(programMemory.getLineOfTag(tagForJump));					
				}
		}	
	}
	
	private void jgtz(OperatorType operatorType) throws Exception {
		switch (operatorType.name()) {
			case "CONSTANT_ADDRESSING":
				throw new Exception("Jgtz can't have an CONSTANT_ADDRESSING as parameter.");
			case "DIRECT_ADDRESSING":
				throw new Exception("Jgtz can't have an DIRECT_ADDRESSING as parameter.");
			case "INDIRECT_ADDRESSING":
				throw new Exception("Jgtz can't have an INDIRECT_ADDRESSING as parameter.");
			case "TAG":
				String tagForJump = operatorType.getTag();
				if (dataMemory.getACC().read() > 0) {
					moveIP(programMemory.getLineOfTag(tagForJump));					
				}
		}	
	}
	
	private void halt(OperatorType operatorType) throws Exception {
		moveIP(programMemory.getLastRegister());
	}	
	
	
	/**
	 * @return Help of the RandomAccessMachine program call.
	 */
	private static String showHelp() {
		return "This program recieves 3 arguments and 1 option: " + System.lineSeparator()
				+ "The first argument is the file that contains the program." + System.lineSeparator()
				+ "The second argument is the file that contains the inputfile." + System.lineSeparator()
				+ "The third argument is the file that contains the outputfile." + System.lineSeparator()
				+ "The fourth argument is the debug option. You can call it as --debug or -d";
	}

	/**
	 * Method that starts a RandomAccessMachine
	 * @param args The arguments are:
	 * 	1. The first argument is the file that contains the program.
	 * 	2. The second argument is the file that contains the inputfile.
	 * 	3. The third argument is the file that contains the outputfile.
	 * 	4. The fourth argument is the debug option.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, IOException { // TODO: Handle exceptions
		if ((args.length != 3) && (args.length != 4)) {
			throw new IllegalArgumentException(showHelp());
		}
		else {
			boolean debug = (args.length != 3) ? true : false;
			RandomAccessMachine ram = new RandomAccessMachine(args[0], args[1], args[2], debug);
			ram.start();
		}		
	}
}
