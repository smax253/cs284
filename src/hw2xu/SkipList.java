
package hw2xu;
/**
 * SkipList class for Assignment 2.
 * <br>This is part 2 of the assignment, worth 50 points.
 * @author Max Shi
 *
 */
public class SkipList {
	/**
	 * Maximum amount of nodes in the Skip List
	 */
	public static final int MAX_NODES = 1000;
	/**
	 * Number of nodes in the skip list
	 */
	private int numberOfNodes;
	/**
	 * Maximum level of the skiplist. <br>
	 * This should be calculated as ceiling( log2(MAX_NODES) )
	 */
	private int maxLevel;
	/**
	 * Counter for visited nodes used in Part 3
	 */
	private int visitedNodes;
	/**
	 * The head of the Skip List
	 */
	private Node head;
	/**
	 * The maximum level of the list
	 */
	private int maxListLevel;
	/**
	 * The private inner Node class for SkipLists
	 *
	 */
	private static class Node {
		/**
		 * The data the Node holds
		 */
		public int item;
		/**
		 * The array of next Nodes that the SkipList Node references
		 */
		public Node[] next;
		/**
		 * Constructor for inner Node class
		 * @param level The maximum level that this node has
		 * @param data The data the node stores
		 */
		public Node(int level, Integer data) {
			next = new Node[level+1];
			item = data;
		}
	}
	/**
	 * Constructor for SkipList, should initialize all instance variables.
	 * Use -1 for the value of the first head node as a placeholder.
	 */
	public SkipList() {
		maxLevel = (int) Math.ceil(Math.log(MAX_NODES)/Math.log(2));
		head = new Node(maxLevel, -40);
		maxListLevel = 0;
		visitedNodes = 0;
		numberOfNodes = 0;
	}
	/**
	 * Prints the contents of a specific level of the SkipList
	 * @param level The level to be printed
	 * @throws IllegalArgumentException - If level is not between 0 and maxLevel
	 */
	public void print(int level) {
		if (level<0 || level> maxLevel) throw new IllegalArgumentException("Level must be between 0 and maxLevel");
		StringBuilder s = new StringBuilder();
		s.append(level+": ");
		Node current = head.next[level];
		while(current != null) {
			s.append(current.item+"->");
			current = current.next[level];
		}
		System.out.println(s.toString());
	}
	/**
	 * Prints all levels of the SkipList.
	 */
	public void print() {
		for(int i = maxLevel; i>=0; i--) {
			this.print(i);
		}
	}
	/**
	 * Find whether or not a value is stored in the SkipList
	 * @param value The value to search for
	 * @return A boolean representing whether or not the value was found: true if it was, false otherwise.
	 */
	public boolean find(int value) {
		int currentLevel = maxLevel;
		Node startingNode = head;
		while(currentLevel>=0) {
			Node search = startingNode.next[currentLevel];
			visitedNodes++;
			while(search != null && search.item<value) {
				startingNode = search;
				search = startingNode.next[currentLevel];
				visitedNodes++;
			}
			if (search != null && search.item == value) return true;
			else currentLevel--;
		}
		return false;
	}
	/**
	 * Insert a new value into the SkipList
	 * The maximum level of the Node holding the value is fixed, between 0 and maxLevel (both inclusive)
	 * The nth node goes up to level i if n % 2**i == 0.
	 * @param value The value to be inserted into the SkipList
	 * @return A boolean representing whether or not insertion was successful: true if it was inserted successfully, false otherwise.
	 */
	public boolean insert(int value) {
		if (numberOfNodes >= MAX_NODES) throw new AssertionError("Length of the SkipList cannot exceed MAX_NODES = "+ MAX_NODES);
		int insertLevel = 0;
		for (int i = maxLevel; i>=0; i--){
			if (numberOfNodes % Math.pow(2,i)==0) {
				insertLevel = i;
				break;
			}
		}
		if (insertLevel>maxListLevel) maxListLevel = insertLevel;
		Node[] nodesToUpdate = new Node[insertLevel+1];
		Node startingNode = head;
		for(int i = insertLevel; i>=0; i--) {
			Node search = startingNode.next[i];
			visitedNodes++;
			while(search != null && search.item < value) {
				startingNode = search;
				search = startingNode.next[i];
				visitedNodes++;
			}
			nodesToUpdate[i] = startingNode;
		}
		Node insert = new Node(insertLevel, value);
		for(int i = 0; i<nodesToUpdate.length; i++) {
			insert.next[i] = nodesToUpdate[i].next[i];
			nodesToUpdate[i].next[i] = insert;
		}
		numberOfNodes++;
		return true;
	}
	/**
	 * Delete the first occurrence of a value from the SkipList
	 * Your implementation should not call the find() method.
	 * @param value The value to be deleted from the SkipList
	 * @return A boolean representing whether or not the value was successfully deleted - true if it was, false otherwise
	 */
	public boolean delete(int value) {
		int newMaxLevel = 0;
		Node[] nodesToUpdate = new Node[maxLevel +1];
		Node startingNode = head;
		for (int i = maxLevel; i>=0; i--) {
			Node search = startingNode.next[i];
			visitedNodes++;
			while(search != null && search.item < value) {
				startingNode = search;
				search = startingNode.next[i];
				visitedNodes++;
			}
			if (search != null && search.item == value) nodesToUpdate[i] = startingNode;
		}
		boolean found = false;
		for(int i = 0; i<nodesToUpdate.length; i++) {
			if (nodesToUpdate[i] == null) continue;
			found = true;
			nodesToUpdate[i].next[i] = nodesToUpdate[i].next[i].next[i];
			if (nodesToUpdate[i] != head || nodesToUpdate[i].next[i] != null) newMaxLevel = i;
		}
		if (found){
			this.maxListLevel = newMaxLevel;
			numberOfNodes--;
		}

		return found;
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
	
	
}
