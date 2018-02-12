/**
 * 
 */
package daa.practice1.randomaccessmachine.memory;

/**
 * @author angel
 *
 */
public abstract class Register<T> {

	T data;
	
	public Register(T data) {
		this.data = data;
	}
	
	public T read() {
		return data;
	}
}
