
package cs284;
import java.util.EmptyStackException;
public class StackSLL<E>  {
	// Data fields
	private SingleLL<E> stack;
	// Constructor
	StackSLL() {
		stack = new SingleLL<E>();
	}
	// Methods
	public E push(E item) {
		stack.addFirst(item);
		return item;
	}
	public E pop() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.removeFirst();
	}
	public E peek() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.get(0);
	}
	public boolean empty() {
		return stack.isEmpty();
	}
	public int size() {
		return stack.size();
	}
	public String toString() {
		return stack.toString();
	}
	public static void main(String[] args) {
		StackSLL<Integer> s = new StackSLL<Integer>();
		for (int i=0; i<10; i++) {
			s.push(i);
		}
		System.out.println(s);
		s.pop();
		System.out.println(s);
	}
}