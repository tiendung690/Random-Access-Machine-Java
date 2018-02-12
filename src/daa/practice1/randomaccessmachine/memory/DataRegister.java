/**
 * 
 */
package daa.practice1.randomaccessmachine.memory;

/**
 * @author angel
 *
 */
public class DataRegister extends Register<Integer> {
	
	public DataRegister() {
		super(0);
	}

	public DataRegister(Integer data) {
		super(data);
	}

	public void write(Integer data) {
		this.data = data;
	}	
}
