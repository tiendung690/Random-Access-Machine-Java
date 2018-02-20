/** File RandomAccessMachine */
package daa.practice1.randomaccessmachine;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.TreeMap;

import daa.practice1.randomaccessmachine.alu.ArithmeticLogicUnit;
import daa.practice1.randomaccessmachine.io.*;
import daa.practice1.randomaccessmachine.memory.*;
import daa.practice1.randomaccessmachine.memory.instruction.*;
import daa.practice1.randomaccessmachine.memory.register.*;

/**
 * The Random Access Machine is an abstract computational-machine identical to
 * the counter machine but adding the indirect addressing. The machine has a
 * Program Memory, Data Memory, Input Tape and OutputTape objects to execute a
 * program. Besides it uses the ArithmeticLogicUnit to do all the calculation.
 * 
 * @author Ángel Igareta
 * @version 1.0
 * @since 15-2-2018
 */
public class RandomAccessMachine {

	/** Integer that represents the index of the Instruction Pointer. */
	private Integer ipIndex;
	/** ProgramMemory object that represents a set of Program Register. */
	private ProgramMemory programMemory;
	/** DataMemory object that represents a set of Data Register. */
	private DataMemory dataMemory;
	/** FloatMemory object that represents a set of Float Register. */
	private FloatMemory floatMemory;
	/** InputTape that represents the buffer where the Machine will read from. */
	private InputTape inputTape;
	/** OutputTape that represents the buffer where the Machine will write to. */
	private OutputTape outputTape;
	/** Counter of the executed instructions. */
	private int instructionCounter;
	
	/** Private variable to indicate if the program has to show the process. */
	private boolean debug;
	
	/** ArrayList of Integer that represent the input tape. */
	private ArrayList<Double> inputArray;
	/** Index of inputArray. */
	private int inputIndex;
	
	/** ArrayList of Integer that represent the output tape. */
	private ArrayList<Double> outputArray;

	/**
	 * Constructor that initialize the private variables previously explained.
	 * 
	 * @param programFilename
	 * @param inputTapeFilename
	 * @param outputTapeFilename
	 * @throws Exception
	 *           If there was a problem initializing the Machine.
	 */
	public RandomAccessMachine(String programFilename, String inputTapeFilename, String outputTapeFilename)
			throws Exception {

		this.dataMemory = new DataMemory();
		this.floatMemory = new FloatMemory();
		this.programMemory = new ProgramMemory(programFilename);
		this.ipIndex = programMemory.getFirstRegister();
		
		try {
			this.inputTape = new InputTape(inputTapeFilename);
			this.outputTape = new OutputTape(outputTapeFilename);			
		}
		catch (IOException e) {
			if (debug) {
				System.out.println("*CLOSING TAPES*");				
			}
			
			inputTape.close();
			outputTape.close();
			
			throw new IOException("Error reading the tapes.");
		}
	}

	/**
	 * Method to start the Random Access Machine. While there is not an error or
	 * halt instruction it keeps executing the instructions in the Program Memory
	 * and showing the result if the debug option is enabled.
	 * 
	 * @param debug
	 *          Option to show all the process.
	 * @throws IOException
	 */
	public void start(boolean debug) throws IOException {
		this.debug = debug;
		if (debug) { 
			this.inputArray = inputTape.readInputTape();
			this.outputArray = new ArrayList<Double>();
		}
		
		try {
			while (ipIndex != null) {
				instructionCounter++;
				if (debug) { 
					showExecution();
				}
				executeInstruction();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage() == null) {
				System.out.println("ERROR in line " + ipIndex + ": " + e.getCause());
			}
			else {
				System.out.println("ERROR in line " + ipIndex + ": " + e.getMessage());
			}
		}
		
		if (debug) {
			outputTape.writeArray(outputArray);
		}
		
		if (debug) {
			System.out.println("*CLOSING TAPES*");				
		}
		else {
			System.out.println("*Number of instructions executed: " + instructionCounter);
		}
		
		inputTape.close();
		outputTape.close();
	}

	/**
	 * Method that moves the ipIndex to the one passed by argument.
	 * 
	 * @param nextIp
	 *          Next position of Ip Index.
	 */
	private void moveIP(Integer nextIp) {
		ipIndex = nextIp;
	}

	/**
	 * Method to execute the Instruction that is located where the ipIndex is
	 * pointing. For finding which method to call it analyze the instruction and
	 * call the method with the same name as the instructionType.
	 * 
	 * @throws Exception
	 *           If there is a runtime exception.
	 */
	private void executeInstruction() throws Exception {
		ProgramRegister currentInstruction = programMemory.getRegisterAt(ipIndex);
		InstructionType instructionType = currentInstruction.getInstructionType();

		Method method = this.getClass().getDeclaredMethod(instructionType.name(), Operating.class);
		method.invoke(this, currentInstruction.getOperating());
	}
	
	/**
	 * Show the Random Access Machine in a visual way.
	 * @throws Exception
	 */
	private void showExecution() throws Exception {
		System.out.println("---------------------");
		System.out.println("RANDOM ACCESS MACHINE");
		System.out.println("---------------------" + System.lineSeparator());
		
		System.out.println("*DATA REGISTERS*");
		showDataRegisters();
		System.out.println(" ");
		
		System.out.println("*FLOAT REGISTERS*");
		showFloatRegisters();
		System.out.println(" ");
		
		System.out.println("*PROGRAM REGISTERS*");
		showProgramRegisters();
		System.out.println(" ");
		
		System.out.println("*INPUT TAPE*");
		showInputTape();
		System.out.println(" ");
		
		System.out.println("*OUTPUT TAPE*");
		showOutputTape();
		System.out.println(" ");
		
		System.out.println("*Number of instructions executed: " + instructionCounter);
		System.out.println("*IP Index: " + ipIndex);
		System.out.println("*Current Instruction: " + programMemory.getRegisterAt(ipIndex).get());
		System.out.println(System.lineSeparator() +	System.lineSeparator());
	}

	/**
	 * Visual Representation of the Data Registers.
	 * 
	 * @throws Exception
	 */
	private void showDataRegisters() throws Exception {
		TreeMap<Integer, DataRegister> usedRegisters = dataMemory.getUsedRegisters();
		
		for (Integer index : usedRegisters.keySet()) {
			System.out.println("R[" + index + "]= " + dataMemory.getRegisterAt(index));
		}
		
		System.out.println("...");
	}
	
	/**
	 * Visual Representation of the Data Registers.
	 * 
	 * @throws Exception
	 */
	private void showFloatRegisters() throws Exception {
		TreeMap<Integer, FloatRegister> usedRegisters = floatMemory.getUsedRegisters();
		
		for (Integer index : usedRegisters.keySet()) {
			System.out.println("F[" + index + "]= " + floatMemory.getRegisterAt(index));
		}
		
		System.out.println("...");
	}
	
	/**
	 * Visual Representation of the Program Registers.
	 * 
	 * @throws Exception
	 */
	private void showProgramRegisters() throws Exception {
		Integer index = programMemory.getFirstRegister();
		
		while (index != null) {
			System.out.println("P[" + index + "]= " + programMemory.getRegisterAt(index).get());
			index = programMemory.getNextRegister(index);
		}
	}
	
	/**
	 * Visual Representation of the Input Tape.
	 * 
	 * @throws Exception
	 */
	private void showInputTape() throws Exception {
		for (int i = 0; i < inputArray.size(); ++i) {
			double inputValue = inputArray.get(i);
			
			if (i == inputIndex) {
				System.out.print("[*" + inputValue + "] ");	
			}
			else {
				System.out.print("[" + inputValue + "] ");				
			}
		}
		System.out.println(" ");
	}
	
	
	/**
	 * Visual Representation of the Output Tape.
	 * 
	 * @throws Exception
	 */
	private void showOutputTape() throws Exception {
		for (Double outputValue : outputArray) {
			System.out.print("[" + outputValue + "] ");
		}
		System.out.println(" ");
	}
	

	/**
	 * Method to resolve the Indirect addressing of the iRegister passed by
	 * argument. Remember that jValue = [R iValue]
	 * 
	 * @param iValue
	 * @return jValue
	 * @throws Exception
	 *           If there is an error reading the Register with index iValue.
	 */
	private int resolveIndirectAddressing(int iValue) throws Exception {
		return dataMemory.getRegisterAt(iValue).get();
	}

	/**
	 * The instruction load stores an operating in the ACC or Register with index 0.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */
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

	/**
	 * The instruction store stores the value of the ACC or Register with index 0 in
	 * the result value of the addressing type. Obviously it doesn't work with
	 * constant addressing. You can't assign a value to a constant.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */

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

	/**
	 * This instruction adds the value of the ACC or Register with index 0 with the
	 * result value of the addressing type.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */

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

	/**
	 * This instruction subtract the value of the ACC or Register with index 0 with
	 * the result value of the addressing type.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */
	private void sub(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				ArithmeticLogicUnit.subtract(dataMemory.getACC(), operating.getRegisterNumber());
				break;

			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				ArithmeticLogicUnit.subtract(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;

			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				ArithmeticLogicUnit.subtract(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;

			case "TAG":
				throw new Exception("Sub can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}

	/**
	 * This instruction multiplies the value of the ACC or Register with index 0
	 * with the result value of the addressing type.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */
	private void mul(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				ArithmeticLogicUnit.multiply(dataMemory.getACC(), operating.getRegisterNumber());
				break;

			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				ArithmeticLogicUnit.multiply(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;

			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				ArithmeticLogicUnit.multiply(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;

			case "TAG":
				throw new Exception("Mul can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}

	/**
	 * This instruction divides the value of the ACC or Register with index 0 with
	 * the result value of the addressing type. The ALU is concerned about the
	 * Arithmetical error of division by 0.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */
	private void div(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				ArithmeticLogicUnit.divide(dataMemory.getACC(), operating.getRegisterNumber());
				break;

			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();
				ArithmeticLogicUnit.divide(dataMemory.getACC(), dataMemory.getRegisterAt(iValue).get());
				break;

			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());
				ArithmeticLogicUnit.divide(dataMemory.getACC(), dataMemory.getRegisterAt(jValue).get());
				break;

			case "TAG":
				throw new Exception("Div can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}

	/**
	 * This instruction reads a value of the input Tape and stores it in the result
	 * value of the addressing type. It doesn't work with the constant addressing
	 * and neither if the result register is the ACC.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */
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
					if (debug) {
						ArithmeticLogicUnit.assign(dataMemory.getRegisterAt(iValue), inputArray.get(inputIndex++).intValue());
					}
					else {
						ArithmeticLogicUnit.assign(dataMemory.getRegisterAt(iValue), inputTape.read().intValue());
					}						
				}
				break;

			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());

				if (jValue == 0) {
					throw new Exception("The read value can't be assigned to the ACC.");
				}
				else {
					if (debug) {
						ArithmeticLogicUnit.assign(dataMemory.getRegisterAt(jValue), inputArray.get(inputIndex++).intValue());
					}
					else {
						ArithmeticLogicUnit.assign(dataMemory.getRegisterAt(jValue), inputTape.read().intValue());
					}					
				}
				break;

			case "TAG":
				throw new Exception("Read can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}

	/**
	 * This instruction reads the result value of the addressing type and stores it
	 * in the Output Tape. It doesn't work with the constant addressing and neither
	 * if the result register is the ACC.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */

	private void write(Operating operating) throws Exception {
		switch (operating.getOperatingName()) {
			case "CONSTANT_ADDRESSING":
				if (debug) {
					outputArray.add((double) operating.getRegisterNumber());
				}
				else {
					outputTape.write(operating.getRegisterNumber());					
				}
				break;

			case "DIRECT_ADDRESSING":
				int iValue = operating.getRegisterNumber();

				if (iValue == 0) {
					throw new Exception("The ACC value can't be assigned to the outputTape.");
				}
				else {
					if (debug) {
						outputArray.add((double) dataMemory.getRegisterAt(iValue).get());
					}
					else {
						outputTape.write(dataMemory.getRegisterAt(iValue).get());					
					}					
				}
				break;

			case "INDIRECT_ADDRESSING":
				int jValue = resolveIndirectAddressing(operating.getRegisterNumber());

				if (jValue == 0) {
					throw new Exception("The ACC value can't be assigned to the outputTape.");
				}
				else {					
					if (debug) {
						outputArray.add(dataMemory.getRegisterAt(jValue).get().doubleValue());
					}
					else {
						outputTape.write(dataMemory.getRegisterAt(jValue).get());					
					}	
				}

				break;
			case "TAG":
				throw new Exception("Write can't have a tag as parameter.");
		}
		moveIP(programMemory.getNextRegister(ipIndex));
	}

	/**
	 * This instruction moves the IpIndex to the line of the tag passed by argument.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */
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

	/**
	 * This instruction moves the IpIndex to the line of the tag passed by argument
	 * if the ACC is zero.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */
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

	/**
	 * This instruction moves the IpIndex to the line of the tag passed by argument
	 * if the ACC is greater than zero.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */
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

	/**
	 * This instruction stops the program, moving the IP Index to null.
	 * 
	 * @param operating
	 *          Operating that matches that instruction.
	 * @throws Exception
	 *           If there is a runtime error for the instruction with that
	 *           operating.
	 */
	private void halt(Operating operating) throws Exception {
		moveIP(null);
	}
}
