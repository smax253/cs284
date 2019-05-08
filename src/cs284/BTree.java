package cs284;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class BTree<E> {

	class Node<F> {
		private F data;
		private Node<F> left;
		private Node<F> right;
		// Constructors
		
		public Node(F data, BTree<E>.Node<F> left, BTree<E>.Node<F> right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		public Node(F data) {
			super();
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
	}
	// Data fields
	private Node<E> root;
	
	// Constructor
	
	public BTree() {
		super();
		root=null;
	}
	
	public BTree(E data) {
		super();
		root=new Node<E>(data);
	}
	
	public BTree(E data, BTree<E> left, BTree<E> right) {
		super();
		root = new Node<E>(data,left.root,right.root);
	}
	
	private String toString(int l, Node<E> current) {
		StringBuilder s = new StringBuilder();
		
		for (int i=0; i<l; i++) {
			s.append("--");
		}
		
		if (current==null) {
			s.append("null\n");
		} else {
			s.append(current.data.toString()+"\n");


			s.append(toString(l+1,current.right));
			s.append(toString(l+1,current.left));
		}
		return s.toString();
		
	}
	
	public String toString() {
		return toString(0,root);
	}
	
	private int height(Node<E> current) {
		if (current==null) {
			return 0;
		} else {
			return Math.max(height(current.left), height(current.right))+1;
		}
	}
	
	public int height() {
		return height(root);
	}
	
	private Node<E> mirror(Node<E> current) {
		if (current==null) {
			return current;
		} else {
			Node<E> temp = current.left;
			current.left = mirror(current.right);
			current.right = mirror(temp);
			return current;
		}
	}
	
	public void mirror() {
		root = mirror(root);
	}

	private int no_of_nodes(Node<E> current) {
		if (current==null) {
			return 0;
		} else {
			return 1 + no_of_nodes(current.left)+no_of_nodes(current.right);
		}
	}
	
	public int no_of_nodes() {
		return no_of_nodes(root);
	}
	
	/*
	 * Takes a level l and clips off the tree at that level 
	 */
	private Node<E> clip(int l, Node<E> current) {
		if(l==0 || current == null) return null;
		else {
			current.left = clip(l-1, current.left);
			current.right = clip(l-1, current.right);
			return current;
		}
	}
	public ArrayList<ArrayList<Integer>> paths(Node<E> localroot){
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		ArrayList<Integer> currentpath = new ArrayList<>();
		result.add(currentpath);

		if(localroot.left != null) {
			ArrayList<ArrayList<Integer>> left = paths(localroot.left);
			left.forEach((n)->n.add(0,1));
			result.addAll(left);
		}
		if(localroot.right != null){
			ArrayList<ArrayList<Integer>> right = paths(localroot.right);
			right.forEach((n)->n.add(0,0));
			result.addAll(right);
		}
		return result;
	}

	public ArrayList<E> leveltraversal(){
		ArrayList<E> result = new ArrayList<>();
		Stack<Node<E>> level = new Stack<>();
		Stack<Node<E>> nextLevel = new Stack<>();
		level.add(root);
		while(!level.isEmpty()){
			Node<E> current = level.pop();
			result.add(current.data);
			if(current.left != null) nextLevel.add(current.left);
			if(current.right != null) nextLevel.add(current.right);
			if(level.isEmpty()){
				level.addAll(nextLevel);
				nextLevel.clear();
			}
		}
		return result;
	}
	public ArrayList<E> leveltraversal2(){
		ArrayList<E> result = new ArrayList<>();
		LinkedList<Node<E>> list = new LinkedList<>();
		list.add(root);
		int levelsize = list.size();
		for(int i = 0; i<levelsize; i++){
			Node<E> current = list.removeLast();
			result.add(current.data);
			if(current.left != null) list.addFirst(current.left);
			if(current.right != null) list.addFirst(current.right);
		}
		return result;
	}
	
	public void clip(int l) {
		root=clip(l,root);
	}
//	public void clip(int l) {
//		
//	}
	
	public static void main(String[] args) {
		BTree<Integer> leaf7 = new BTree<>(7);
		BTree<Integer> leaf24 = new BTree<>(24);
		BTree<Integer> leaf51 = new BTree<>(51,new BTree<>(32), new BTree<>(90));
		BTree<Integer> t = new BTree<>(12,leaf7,new BTree<>(43,leaf24,leaf51));
	
		System.out.println(t);
		System.out.println(t.paths(t.root).toString());
	}
	
}
