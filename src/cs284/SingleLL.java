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
	public E removeFirst() {
		if (head==null) {
			throw new IllegalStateException();
		}
		E temp = head.data;
		head = head.next;
		size--;
		return temp;
	}
	public E removeLast() {
		if (size==0) { // empty list
			throw new IllegalStateException();
		}
		if (size==1) { // singleton list
			return this.removeFirst();
		}
		// list has two or more elements
		Node<E> current=head;
		while(current.next.next!=null) {
			current=current.next;
		}
		E temp = current.next.data;
		size--;
		current.next = null;
		return temp;
	}
	public E remove(int index) {
		if (index<0 || index>size-1) {
			throw new IllegalArgumentException();
		}
		if (size==1) {
			return this.removeFirst();
		} else {
			Node<E> current=head;
			Node<E> previous=head;
			for (int i=0; i<index; i++) {
				previous = current;
				current = current.next;
			}
			E temp = current.data;
			size--;
			previous.next = current.next;
			return temp;
		}
	}
	public boolean member(E item) {
		Node<E> current=head;
		while (current!=null && !current.data.equals(item)) {
			current = current.next;
		}
		return current!=null;
	}
	public SingleLL<E> take(int n) {
		SingleLL<E> l = new SingleLL<E>();
		int i = 0;
		Node<E> current = head;
		while (current!=null && i<n) {
			l.addLast(current.data);
			current = current.next;
			i++;
		}
		return l;
	}
	public SingleLL<E> take2(int n) {
		SingleLL<E> l = new SingleLL<E>();
		int i = 0;
		Node<E> current = head;
		Node<E> last = new Node<E>();
		Node<E> newHead = last;
		while (current!=null && i<n) {
			last.next = new Node<E>(current.data);
			last = last.next;
			current = current.next;
			i++;
		}
		l.head = newHead.next;
		l.size = i;
		return l;
	}
	public void take3(int n) {
		if (head==null || n<=0) { // list is empty or n==0
			head=null;
			size=0;
		} else { // n>0 and list is nonempty
			int i = 0;
			Node<E> current = head;
			while (current.next!=null && i<n-1) {
				current = current.next;
				i++;
			}
			current.next=null;
			size = i;
		}
	}
	class Pair<G,H>{
		private final G itemOne;
		private final H itemTwo;
		Pair(G first, H second){
			itemOne = first;
			itemTwo = second;
		}
		public G getFirst(){
			return itemOne;
		}
		public H getSecond() {
			return itemTwo;
		}
		public String toString() {
			return ("("+itemOne+","+itemTwo+")");
		}
	}
	public <F> SingleLL<Pair<E,F>> mergeLists(SingleLL<F> l){
		SingleLL<Pair<E,F>> result = new SingleLL<>();
		Node<E> currentOne = this.head;
		SingleLL<F>.Node<F> currentTwo = l.head;
		for(int i = 0; i<this.size && i<l.size; i++) {
			result.addFirst(new Pair<E,F>(currentOne.data, currentTwo.data));
			currentOne = currentOne.next;
			currentTwo = currentTwo.next;
		}
		result.reverse();
		return result;
	}
	public SingleLL<Pair<E,Integer>> compress(){
		SingleLL<Pair<E,Integer>> result = new SingleLL<>();
		Node<E> current = head;
		int counter = 1;
		while(current!=null) {
			if(current.next!=null && current.data.equals(current.next.data)) counter++;
			else {
				result.addFirst(new Pair<E, Integer>(current.data, counter));
				counter = 1;
			}
			current = current.next;
		}
		result.reverse();
		return result;
	}
	public boolean hasCycle() {
		Node<E> slow = head;
		Node<E> fast = head;
		do {
			//System.out.println(fast.data+" : "+slow.data);
			if(fast!=null&&fast.next!=null) fast= fast.next.next;
			else if(fast!=null) fast = fast.next;
			slow = slow.next;
		}
		while(slow!=null && slow!=fast); 
		//System.out.println(fast.data+" : "+slow.data);
		return slow!=null;
		
	}
	
	//
	//
	//    public SingleLL<E> drop(int n) {
	//
	//    }
	//
	//    public void removeAdjacentDuplicates() {
	//
	//    }
	//
	//    // requires Comparable
	//    public boolean isIncreasing() {
	//
	//    }
	//
	//    public SingleLL<E> reverse() {
	//
	//    }
	//
	//    public void reverse() {
	//
	//    }
	public void reverse() {
		if (head == null || head.next == null) return;
		Node<E> current = head;
		Node<E> next = head.next;
		head.next = null;
		while(next!=null) {
			Node<E> temp = next.next;
			next.next = current;
			current = next;
			next = temp;
		}
		head = current;
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
	//  build a list
		SingleLL <Integer > l = new  SingleLL <Integer >();
		for (int i=0;i<10;i++) {
		l.addLast(i);
		}
		//  create a loop
		//l.head.next.next.next.next = l.head;
		// test  your  code
		System.out.println(l.hasCycle());
		int[] entries = {4,4,4,2,3,3,2,2,2,1,1};
		SingleLL<Integer> compressTest = new SingleLL<>();
		for(int i:entries) {
			compressTest.addLast(i);
		}
		compressTest.addLast(9);
		System.out.println(compressTest.compress());
		System.out.println(compressTest.mergeLists(l));
		String[] entries2 = {"hi","there","this","is","a","test"};
		SingleLL<String> compress2 = new SingleLL<>();
		for(String s:entries2) {
			compress2.addLast(s);
		}
		System.out.println(compress2.mergeLists(l));
		l= new SingleLL<>();
		compressTest = new SingleLL<>();
		for(int i = 0; i<100000; i++){
			l.addFirst(i);
			compressTest.addFirst(i);
		}
		l.reverse();
		compressTest.reverse();
		System.out.println(l.toString().substring(0,100));
		System.out.println(l.mergeLists(compressTest));
	}
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
}