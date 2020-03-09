/** File InstructionType. */
package daa.practice1.randomaccessmachine.memory.instruction;

/**
 * InstructionType is an enum for all the instructions that our ram machine can have.
 * 
 * @author Ángel Igareta
 * @version 1.0
 * @since 14-2-2018
 */
public enum InstructionType {
	load,
	store,
	add,
	sub,
	mul,
	div,
	read,
	write,
	jump,
	jzero,
	jgtz,
	halt,
	
	loadf,
	storef,
	addf,
	subf,
	mulf,
	divf,
	readf,
	writef,
	jzerof,
	jgtzf;
}
