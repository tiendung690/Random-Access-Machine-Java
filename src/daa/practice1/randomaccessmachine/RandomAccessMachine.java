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


public class RandomAccessMachine {
	
	private Integer ipIndex;
	private ArithmeticLogicUnit alu;
	
	private ProgramMemory programMemory;
	private DataMemory dataMemory;
	
	private InputTape inputTape;	
	private OutputTape outputTape;
	
	private boolean debug;
	
	public RandomAccessMachine(String programFilename, String inputTapeFilename, 
			String outputTapeFilename, boolean debug) throws IOException {
		
		this.alu = new ArithmeticLogicUnit();
		this.dataMemory = new DataMemory();
		this.programMemory = new ProgramMemory(programFilename);
		this.inputTape = new InputTape(inputTapeFilename);		
		this.outputTape = new OutputTape(outputTapeFilename);
		
		this.ipIndex = programMemory.getFirstRegister();
		this.debug = debug;
	}
	
	public void start() throws IOException {
		
		while (ipIndex != null) {
			executeInstruction(programMemory.getRegisterAt(ipIndex));
			moveIP(programMemory.getNextRegister(ipIndex));
		}
		
		inputTape.close();
		outputTape.close();
	}
	
	private void moveIP(Integer nextIp) {
		ipIndex = nextIp;
	}
		
	private void executeInstruction(ProgramRegister currentInstruction) {
		InstructionType instructionType = currentInstruction.getInstructionType();
		
		// Look for the method
		try {
			Method method = this.getClass().getDeclaredMethod(instructionType.name(), Operating.class);
			method.invoke(this, currentInstruction.getOperating());
			
			if (debug) {
				System.out.println("IP: " + ipIndex);
				System.out.println("Instruction: " + programMemory.getRegisterAt(ipIndex).get());
				showRegisters();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showRegisters() {
		for (int i = 0; i < 10; ++i) {
			System.out.print("R" + i + ": "+ dataMemory.getRegisterAt(i) + " | ");
		}
		System.out.println(" ");
		System.out.println(" ");
	}
	
	private int resolveIndirectAddressing(int iValue) {
		return dataMemory.getRegisterAt(iValue).get();
	}

	private void load(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				alu.assign(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
				
			case "TAG":
				throw new Exception("Load can't have a tag as parameter.");
		}
	}
	
	private void store(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				throw new Exception("Store can't have a constant addressing.");
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				alu.assign(dataMemory.getRegisterAt(iValue), dataMemory.getACC().get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				alu.assign(dataMemory.getRegisterAt(jValue), dataMemory.getACC().get());
				break;
			case "TAG":
				throw new Exception("Store can't have a tag as parameter.");
		}	
	}
	
	private void add(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				alu.assign(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
			case "TAG":
				throw new Exception("Add can't have a tag as parameter.");
		}
	}

	private void sub(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				alu.assign(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
			case "TAG":
				throw new Exception("Sub can't have a tag as parameter.");
		}	
	}
	
	private void mul(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				alu.assign(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
			case "TAG":
				throw new Exception("Mul can't have a tag as parameter.");
		}	
	}
	
	private void div(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				alu.assign(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
			case "TAG":
				throw new Exception("Div can't have a tag as parameter.");
		}	
	}
	
	private void read(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				alu.assign(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
			case "TAG":
				throw new Exception("Read can't have a tag as parameter.");
		}	
	}
	
	private void write(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				alu.assign(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				alu.assign(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
			case "TAG":
				throw new Exception("Write can't have a tag as parameter.");
		}	
	}
	
	private void jump(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				throw new Exception("Jump can't have an CONSTANT_ADDRESSING as parameter.");
			case "DIRECT_ADDRESSING":
				throw new Exception("Jump can't have an DIRECT_ADDRESSING as parameter.");
			case "INDIRECT_ADDRESSING":
				throw new Exception("Jump can't have an INDIRECT_ADDRESSING as parameter.");
			case "TAG":
				String tagForJump = operating.getTag();
				moveIP(programMemory.getLineOfTag(tagForJump));
		}	
	}
	
	private void jzero(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				throw new Exception("Jzero can't have an CONSTANT_ADDRESSING as parameter.");
			case "DIRECT_ADDRESSING":
				throw new Exception("Jzero can't have an DIRECT_ADDRESSING as parameter.");
			case "INDIRECT_ADDRESSING":
				throw new Exception("Jzero can't have an INDIRECT_ADDRESSING as parameter.");
			case "TAG":
				String tagForJump = operating.getTag();
				if (dataMemory.getACC().get() == 0) {
					moveIP(programMemory.getLineOfTag(tagForJump));					
				}
		}	
	}
	
	private void jgtz(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				throw new Exception("Jgtz can't have an CONSTANT_ADDRESSING as parameter.");
			case "DIRECT_ADDRESSING":
				throw new Exception("Jgtz can't have an DIRECT_ADDRESSING as parameter.");
			case "INDIRECT_ADDRESSING":
				throw new Exception("Jgtz can't have an INDIRECT_ADDRESSING as parameter.");
			case "TAG":
				String tagForJump = operating.getTag();
				if (dataMemory.getACC().get() > 0) {
					moveIP(programMemory.getLineOfTag(tagForJump));					
				}
		}	
	}
	
	private void halt(Operating operating) throws Exception {
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
