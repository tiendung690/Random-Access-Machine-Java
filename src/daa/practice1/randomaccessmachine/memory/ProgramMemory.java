/** File ProgramMemory. */
package daa.practice1.randomaccessmachine.memory;

import java.io.*;
import java.util.*;

import daa.practice1.randomaccessmachine.memory.register.ProgramRegister;

/**
 * The class ProgramMemory contains the Hash of lines-instruction that
 * represents the program the machine is running.
 * 
 * @author Ángel Igareta
 * @version 1.0
 * @since 12-2-2018
 */
public class ProgramMemory extends InfiniteMemory<ProgramRegister> {

	/**
	 * Hashtable<String,Integer> tagHash stores all the tags of the program memory
	 * and it's index.
	 */
	private Hashtable<String, Integer> tagHash;

	/**
	 * Constructor of the Program Memory. It reads all the lines in the program file
	 * and charges them in the Hash Table of ProgramRegister. Besides, it inserts
	 * the tags in the tagHash. At the time of reading it omit comments or blank
	 * lines.
	 * 
	 * @param programFilename
	 *          String that contains the name of the program file.
	 * @throws Exception
	 *           If there was an error reading the file.
	 */
	public ProgramMemory(String programFilename) throws Exception {
		super();
		tagHash = new Hashtable<String, Integer>();

		BufferedReader reader = new BufferedReader(new FileReader(programFilename));
		int i = 1;

		while (reader.ready()) {
			String newLine = reader.readLine().trim();

			if (!newLine.startsWith("#") && !newLine.isEmpty()) {
				String possibleTag = newLine.split("[\\s]+")[0];

				if (possibleTag.endsWith(":")) { // Check for tags
					tagHash.put(possibleTag.replace(":", ""), i);
					newLine = newLine.replaceFirst(possibleTag, "").trim();
				}

				try {
					this.setRegisterAt(i, new ProgramRegister(newLine));
				}
				catch (Exception e) {
					reader.close();
					throw new Exception("In line " + i + ": " + e.getMessage());
				}
			}
			i++;
		}
		
		reader.close();
		
		if (this.memory.size() == 0) {			
			throw new Exception("Program File empty.");
		}
	}

	/**
	 * Method that returns the line in the Program Memory associated with the tag
	 * passed by argument.
	 * 
	 * @param tagName
	 *          Name of the tag.
	 * @return Line of the tag passed by argument.
	 * @throws Exceptiion
	 *           If the tag doesn't exist.
	 */
	public int getLineOfTag(String tagName) throws Exception {
		if (!tagHash.containsKey(tagName)) {
			throw new Exception("Tag " + tagName + " doesn't exist.");
		}

		return tagHash.get(tagName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * daa.practice1.randomaccessmachine.memory.InfiniteMemory#setRegisterAt(int,
	 * java.lang.Object)
	 */
	@Override
	public void setRegisterAt(int index, ProgramRegister data) {
		memory.put(getMemoryPosition(index), data);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * daa.practice1.randomaccessmachine.memory.InfiniteMemory#getRegisterAt(int)
	 */
	@Override
	public ProgramRegister getRegisterAt(int index) {
		return memory.get(getMemoryPosition(index));
	}

	/**
	 * Method that returns the first Program Register in the Program Memory.
	 * 
	 * @return The first Program Register.
	 */
	public Integer getFirstRegister() {
		return memory.firstKey();
	}

	/**
	 * Method that returns the last Program Register in the Program Memory.
	 * 
	 * @return The last Program Register.
	 */
	public Integer getLastRegister() {
		return memory.lastKey();
	}

	/**
	 * Method that returns the next Program Register of the Program Memory to the
	 * one passed by argument.
	 * 
	 * @return The next Program Register to the one passed by argument.
	 */
	public Integer getNextRegister(int index) {
		return memory.higherKey(index);
	}

}
