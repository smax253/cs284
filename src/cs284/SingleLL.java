package cs284;

public class SingleLL<E> {
	class Node<F> {
		// Data fields
		private F data;
		private Node<F> next;
		// Constructors
		Node() {
			data=null;
			next=null;
		}
		Node(F data){
			this.data=data;
			next=null;
		}
		Node(F data, Node<F> next) {
			this.data=data;
			this.next=next;
		}
		// Methods
		public F getData() {
			return data;
		}
		public Node<F> getNext() {
			return next;
		}
	}
	// Data fields
	private Node<E> head;
	private int size;
	// Constructor
	SingleLL() {
		head=null;
		size=0;
	}
	// Methods
	public boolean isEmpty() {
		return size==0;
	}
	public void addFirst(E item) {
		head = new Node<E>(item,head);
		size++;
	}
	public void addLast(E item) {
		if (head==null) {
			this.addFirst(item);
		} else {
			Node<E> current = head;
			while (current.next!=null) {
				current=current.next;
			}
			current.next = new Node<E>(item);
			size++;
		}
	}
	public E get(int index) {
		if (index<0 || index>size-1) {
			throw new IllegalArgumentException();
		}
		Node<E> current = head;
		for(int i=0; i<index; i++) {
			current = current.next;
		}
		return current.data;
	}
	/*
	public E removeFirst() {
	}
	public E removeLast() {
	}
	*/
	public E remove(int index) {
		if(index<0 || index>=size) {
			throw new IllegalArgumentException();
		}
		else {
			Node<E> current = head;
			for(int i = 0; i<index-1; i++) {
				current = current.next;
			}
			Node<E> temp;
			if(index != 0) {
				temp = current.next;
				if(index!=size-1) current.next = current.next.next;
				else current.next = null;
			}
			else {
				temp = head;
				head = temp.next;
			}
			
			size--;
			return temp.data;
		}
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		Node<E> current = head;
		while (current!=null) {
			s.append(current.data.toString()+";");
			current = current.next;
		}
		s.append("]");
		return s.toString();
	}
	public static void main(String[] args) {
		SingleLL<Integer> l = new SingleLL<Integer>();
		l.addFirst(3);
		l.addFirst(2);
		l.addFirst(1);
		l.addLast(4);
		System.out.println(l);
		l.remove(3);
		System.out.println(l);
		SingleLL<Integer> edge = new SingleLL<Integer>();
		edge.addFirst(1);
		System.out.println(edge);
		edge.remove(0);
		System.out.println(edge);
		
	}
}