
package cs284;
import java.util.Arrays;
public class MyList<E> {
	// Data fields
	private E[] data;
	private int free;
	// Constructor
	MyList(int length) {
		data = (E[]) new Object[length];
		free=0;
	}
	// Methods
	public int size() {
		return free;
	}
	public boolean isEmpty() {
		return free==0;
	}
	public boolean add(E item) {
		if (free==data.length) {
			throw new IllegalStateException();
		}
		data[free]=item;
		free++;
		return true;
	}
	private void resize() {
		data = Arrays.copyOf(data, data.length*2);
	}
	public boolean add(E item, int index) {
		if (free==data.length) {
			resize();
		}
		if (index<0 || index>free) {
			throw new IllegalArgumentException();
		}
		// complete code: shift then insert
		for (int i=free; i>index; i--) {
			data[i]=data[i-1];
		}
		data[index]=item;
		free++;
		return true;
	}
	
	public E get(int index) {
		if (index<0 || index>=free) {
			throw new IllegalArgumentException();
		}
		return data[index];
	}
	public boolean member(E item) {
		for(int i = 0; i<free; i++) {
			if (data[i].equals(item)) return true;
		}
		return false;
	}
	public String toString() {
		return Arrays.toString(data);
	}
	public static void main(String[] args) {
		MyList<Integer> l = new MyList<Integer>(20);
		for (int i=1; i<11; i++) {
			l.add(i);
		}
		l.add(12,3);
		System.out.println(l);
	}
}
