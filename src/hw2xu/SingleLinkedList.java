package original;
/**
 * Single Linked List Class for Assignment 2.<br>
 * This is Part 1 of the assignment, worth 20 points.
 * @author Max Shi
 *
 */
public class SingleLinkedList {
	/**
	 * Private inner class Node to store Linked List objects.
	 *
	 */
	private static class Node{
		/**
		 * The next Node in the linked list
		 */
		public Node next;
		/**
		 * The data stored in this node of the linked list
		 */
		public int item;
		/**
		 * Constructor for inner Node class
		 * @param data The integer stored in the Node
		 */
		public Node(int data) {
			this.item = data;
		}
		/**
		 * Constructor for inner Node class **unused**
		 * @param data The integer stored in the Node
		 * @param next The next node following this node in the linked list
		 */
		public Node(int data, Node next){
			this.item = data;
			this.next = next;
		}
	}
	/**
	 * The head of the linked list
	 */
	private Node head;
	private int visitedNodes = 0;
	/**
	 * Constructor for the linked list. Should initialize the list.
	 */
	public SingleLinkedList() {
		head = null;
		visitedNodes = 0;
	}
	/**
	 * Directly prints from this method, does not return a string.
	 * Example format (must be exact): {@code 1->2->3->4->}
	 */
	public void print() {
		Node current = head;
		StringBuilder s = new StringBuilder();
		while (current != null) {
			s.append(current.item+"->");
			current = current.next;
		}
		System.out.println(s.toString());
	}
	/**
	 * Inserts the given value at the end of the linked list
	 * @param value The value to be inserted
	 * @return A boolean representing success of insertion: true if successful, false if unsuccessful
	 */
	public boolean insert(int value) {
		if (head == null) {
			head = new Node(value);
			return true;
		}
		Node current = head;
		visitedNodes++;
		while(current.next != null) {
			current = current.next;
			visitedNodes++;
		}
		current.next = new Node(value);
		return true;
	}
	/**
	 * Deletes the first instance of the given value in the linked list
	 * @param value The value to be deleted from the list
	 * @return A boolean representing success of deletion: true if element found and deleted, false if element not found.
	 */
	public boolean delete(int value) {
		if (head == null) return false;
		if (head.item == value) {
			head = head.next;
			return true;
		}
		Node prev = head;
		Node current = head.next;
		visitedNodes++;
		while(current != null && current.item != value) {
			prev = current;
			current = current.next;
			visitedNodes++;
		}
		if (current == null) return false;
		prev.next = current.next;
		return true;
	}
	/**
	 * Find whether or not a value exists in the linked list.
	 * @param value The value to search for in the list
	 * @return A boolean representing whether or not the value was found - true if it was, false otherwise
	 */
	public boolean find(int value) {
		if (head == null) return false;
		Node current = head;
		visitedNodes++;
		while(current != null && current.item != value) {
			current = current.next;
			visitedNodes++;
		}
		if (current == null) return false;
		return true;
	}
	/**
	 * @return the visitedNodes
	 */
	public int getVisitedNodes() {
		return visitedNodes;
	}
	/**
	 * Set visitedNodes to 0;
	 */
	public void resetVisitedNodes() {
		visitedNodes = 0;
	}
	public static void main(String[] args){
		SingleLinkedList sll = new SingleLinkedList();
		for(int i = 0; i<30; i++){
			sll.insert(i);
		}
		for(int i = 0; i<10; i++){
			if (!sll.find(i)) System.out.println("not found");
		}
		for(int i = 0; i<15; i++){
			sll.delete(i);
		}
		sll.print();


	}
}
