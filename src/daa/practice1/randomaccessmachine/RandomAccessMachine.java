/**
 * 
 */
package daa.practice1.randomaccessmachine;

import java.io.IOException;
import java.lang.reflect.Method;

import daa.practice1.randomaccessmachine.alu.ArithmeticLogicUnit;
import daa.practice1.randomaccessmachine.io.*;
import daa.practice1.randomaccessmachine.memory.*;
import daa.practice1.randomaccessmachine.memory.instruction.*;
import daa.practice1.randomaccessmachine.memory.register.*;


public class RandomAccessMachine {
	
	private Integer ipIndex;
	
	private ProgramMemory programMemory;
	private DataMemory dataMemory;
	
	private InputTape inputTape;	
	private OutputTape outputTape;
	
	private boolean debug;
	
	public RandomAccessMachine(String programFilename, String inputTapeFilename, 
			String outputTapeFilename, boolean debug) throws Exception {
		
		this.dataMemory = new DataMemory();		
		this.programMemory = new ProgramMemory(programFilename);
		this.inputTape = new InputTape(inputTapeFilename);		
		this.outputTape = new OutputTape(outputTapeFilename);
		
		this.debug = debug;
		this.ipIndex = programMemory.getFirstRegister();
	}
	
	public void start() throws IOException {
		try {			
			while (ipIndex != null) {
				executeInstruction(programMemory.getRegisterAt(ipIndex));
			}
		}
		catch (Exception e) {
			if (e.getMessage() == null) {
				System.out.println("ERROR in line " + ipIndex + ": " + e.getCause());				
			}
			else {
				System.out.println("ERROR in line " + ipIndex + ": " + e.getMessage());
			}
		}
		
		inputTape.close();
		outputTape.close();
	}
	
	private void moveIP(Integer nextIp) {
		ipIndex = nextIp;
	}
		
	private void executeInstruction(ProgramRegister currentInstruction) throws Exception {
		InstructionType instructionType = currentInstruction.getInstructionType();
		
		if (debug) {
			System.out.println("IP: " + ipIndex);
			System.out.println("Instruction: " + programMemory.getRegisterAt(ipIndex).get());			
		}
		
		Method method = this.getClass().getDeclaredMethod(instructionType.name(), Operating.class);
		method.invoke(this, currentInstruction.getOperating());
		
		if (debug) {
			showRegisters();
		}
	}
	
	private void showRegisters() throws Exception {
		for (int i = 0; i < 10; ++i) {
			System.out.print("R" + i + ": "+ dataMemory.getRegisterAt(i) + " | ");
		}
		System.out.println(" ");
		System.out.println(" ");
	}
	
	private int resolveIndirectAddressing(int iValue) throws Exception {
		return dataMemory.getRegisterAt(iValue).get();
	}

	private void load(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				ArithmeticLogicUnit.assign(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				ArithmeticLogicUnit.assign(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				ArithmeticLogicUnit.assign(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
				
			case "TAG":
				throw new Exception("Load can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}
	
	private void store(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				throw new Exception("Store can't have a constant addressing.");
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				ArithmeticLogicUnit.assign(dataMemory.getRegisterAt(iValue), dataMemory.getACC().get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				ArithmeticLogicUnit.assign(dataMemory.getRegisterAt(jValue), dataMemory.getACC().get());
				break;
			case "TAG":
				throw new Exception("Store can't have a tag as parameter.");
		}	
		moveIP(programMemory.getNextRegister(ipIndex));
	}
	
	private void add(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				ArithmeticLogicUnit.add(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				ArithmeticLogicUnit.add(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				ArithmeticLogicUnit.add(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
			case "TAG":
				throw new Exception("Add can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}

	private void sub(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				ArithmeticLogicUnit.sub(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				ArithmeticLogicUnit.sub(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				ArithmeticLogicUnit.sub(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
			case "TAG":
				throw new Exception("Sub can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}
	
	private void mul(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				ArithmeticLogicUnit.mul(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				ArithmeticLogicUnit.mul(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				ArithmeticLogicUnit.mul(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
			case "TAG":
				throw new Exception("Mul can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}
	
	private void div(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				ArithmeticLogicUnit.div(dataMemory.getACC(), operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				ArithmeticLogicUnit.div(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				ArithmeticLogicUnit.div(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;
			case "TAG":
				throw new Exception("Div can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}
	
	private void read(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				throw new Exception("The read value can't be assigned to a constant.");
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				
				if (iValue == 0) {
					throw new Exception("The read value can't be assigned to the ACC.");
				}
				else {
					ArithmeticLogicUnit.assign(dataMemory.getRegisterAt(iValue), inputTape.read());					
				}
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				if (jValue == 0) {
					throw new Exception("The read value can't be assigned to the ACC.");
				}
				else {
					ArithmeticLogicUnit.assign(dataMemory.getRegisterAt(jValue), inputTape.read());			
				}				
				break;
			case "TAG":
				throw new Exception("Read can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}
	
	private void write(Operating operating) throws Exception {		
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				outputTape.write(operating.getRegisterNumber());
				break;
				
			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				
				if (iValue == 0) {
					throw new Exception("The ACC value can't be assigned to the outputTape.");
				}
				else {			
					outputTape.write(dataMemory.getRegisterAt(iValue).get());
				}				
				break;
				
			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				
				if (jValue == 0) {
					throw new Exception("The ACC value can't be assigned to the outputTape.");
				}
				else {		
					outputTape.write(dataMemory.getRegisterAt(jValue).get());
				}	
				
				break;
			case "TAG":
				throw new Exception("Write can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
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
				else {
					moveIP(programMemory.getNextRegister(ipIndex));
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
				else {
					moveIP(programMemory.getNextRegister(ipIndex));
				}
		}	
	}
	
	private void halt(Operating operating) throws Exception {
		moveIP(null);
	}	
	
	
	/**
	 * @return Help of the RandomAccessMachine program call.
	 */
	private static void showHelp() {
		System.out.print("This program recieves 3 arguments and 1 option: " + System.lineSeparator()
				+ "The first argument is the file that contains the program." + System.lineSeparator()
				+ "The second argument is the file that contains the inputfile." + System.lineSeparator()
				+ "The third argument is the file that contains the outputfile." + System.lineSeparator()
				+ "The fourth argument is the debug option. You can call it as --debug or -d");
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
	public static void main(String[] args) {
		if ((args.length != 3) && (args.length != 4)) {
			System.out.println("ERROR: Invalid number of arguments");
			showHelp();
		}
		else {
			boolean debug = ((args.length != 3) && (args[3].equals("debug"))) ? true : false;
			
			try {
				RandomAccessMachine ram = new RandomAccessMachine(args[0], args[1], args[2], debug);
				ram.start();				
			}
			catch (IOException e) {
				System.out.println("IOException reading the files:" + e.getMessage());
			}
			catch (Exception e) {
				System.out.println("Error reading the Program file: " + e.getMessage());
			}		
		}
	}
}
