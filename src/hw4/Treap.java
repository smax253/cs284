package hw4;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {
	private class Pair<F,G>{
		private F first;
		private G second;
		public Pair(F a, G b) {
			first = a;
			second = b;
		}
		public F getFirst() {
			return first;
		}
		public G getSecond() {
			return second;
		}
		public void setFirst(F a) {
			first = a;
		}
		public void setSecond(G b) {
			second = b;
		}
	}
	
    private class Node<F> {
        public F data;
        public int priority;
        public Node<F> left;
        public Node<F> right;
        public Node(F data, int priority){
            this.data = data;
            this.priority = priority;
        }
        public Node<F> rotateRight(){
            Node<F> newHead = left;
            Node<F> temp = newHead.right;
            newHead.right = this;
            this.left = temp;
            return newHead;
        }
        public Node<F> rotateLeft(){
            Node<F> newHead = right;
            Node<F> temp = newHead.left;
            newHead.left = this;
            this.right = temp;
            return newHead;
        }
        public boolean equals(Object other){
            Node<F> otherNode = null;
            try{
                otherNode = (Node<F>) other;
            }catch(Exception e){
                return false;
            }
            if(otherNode == null) return false;
            else return data.equals(otherNode.data) && priority == otherNode.priority;
        }
        public String toString(){
            return String.format("(key=%s, priority=%d)", data.toString(), priority);
        }
    }
    private Random priorityGenerator;
    private Node<E> root;
    public Treap(){
        priorityGenerator = new Random();
    }
    public Treap(long seed){
        priorityGenerator = new Random(seed);
    }
    public boolean add(E key){
        return add(key, priorityGenerator.nextInt());
    }
    public boolean add(E key, int priority){
        Stack<Node<E>> prevNodes = new Stack<>();
        Node<E> current = root;
        Node<E> addNode = new Node<>(key, priority);
        boolean dir = false;
        while(current != null){
            prevNodes.push(current);
            if (key.equals(current.data)) throw new IllegalArgumentException();
            if(key.compareTo(current.data)>0){
                current = current.right;
                dir = true;
            }
            else {
                current = current.left;
                dir = false;
            }
        }
        Node<E> previous = prevNodes.empty() ? null : prevNodes.peek();
        if(previous == null) root = addNode;
        else if (dir) previous.right = addNode;
        else previous.left = addNode;
        reheap(prevNodes);
        return true;
    }
    private Node<E> makeRotation(Node<E> current){
        if (current.left != null && current.left.priority>current.priority){
            current = current.rotateRight();
        }
        else if (current.right != null && current.right.priority > current.priority){
            current = current.rotateLeft();
        }
        return current;
    }
    private void reheap(Stack<Node<E>> nodeStack){
        if(nodeStack.empty()) return;
        else if(nodeStack.size() == 1){
            Node<E> current = nodeStack.pop();
            current = makeRotation(current);
            root = current;
        }
        else{
            Node<E> current = nodeStack.pop();
            Node<E> parent = nodeStack.peek();
            boolean parentdir = false;
            if(parent.right != null && parent.right.equals(current)) parentdir = true;
            current = makeRotation(current);
            if(parentdir) parent.right = current;
            else parent.left = current;
        }
        reheap(nodeStack);
    }
    public boolean delete(E key){
        Node<E> prev = null;
        Node<E> current = root;
        boolean dir = false;
        while(current != null && !key.equals(current.data)){
            prev = current;
            if (key.compareTo(current.data)>0) {
            	current = current.right;
            	dir = true;
            }
            else if(key.compareTo(current.data)<0 ) {
            	current = current.left;
            	dir = false;
            }
        }
        System.out.println("found");
        if(current==null) return false;
        while(current.left != null && current.right != null) {
        	if(prev == null) prev = root;
    		boolean rotateDir = false;
    		if(current.right == null) {
    			rotateDir = true;
    		}else if(current.left == null) rotateDir = false;
    		else if(current.right.priority<current.left.priority) rotateDir = false;
    		else rotateDir = true;
    		if(rotateDir) {
    			if (dir) {
    				prev.right = current.rotateRight();
    				current = prev.right;
    			}else {
    				prev.left = current.rotateRight();
    				current = prev.left;
    			}
    		}else {
    			if (dir) {
    				prev.right = current.rotateLeft();
    				current = prev.right;
    			}else {
    				prev.left = current.rotateLeft();
    				current = prev.left;
    			}
    		} 
    		System.out.println(current.data);
        }
        if(dir) prev.right = null;
        else prev.left = null;
        return true;
        
    }
    private boolean find(Node<E> root, E key){
        return false;
    }
    public boolean find(E key){
        return false;
    }
    private String traverse(Node<E> node, int level){
        String leftString= "", rightString="";
        String indent = "";
        for (int i = 0; i < level; i++) {
            indent += "  ";
        }
        if(node != null) {

            leftString = traverse(node.left, level + 1);
            rightString = traverse(node.right, level + 1);
            return indent + node.toString() + "\n" + leftString + rightString;
        }
        else{
            return indent + "null" + "\n";
        }


    }
    public String toString(){
        return traverse(root, 0);
    }
    public static void main(String[] args){
        Treap<Integer> testTree = new Treap<>();
//        Random ints = new Random();
//        for (int i = 0; i<10; i++){
//            try {
//                treap.add(ints.nextInt(15));
//            }
//            catch(IllegalArgumentException e){
//                i--;
//            }
//        }
        testTree.add(4 ,19);
        testTree.add(2 ,31);
        testTree.add (6 ,70);
        testTree.add (1 ,84);
        testTree.add (3 ,12);
        testTree.add (5 ,83);
        testTree.add (7 ,26);
        System.out.println(testTree.toString());
        testTree.delete(5);
        System.out.println(testTree.toString());
//        treap.add(6);
//        treap.add(7);
//        treap.add(3);
//        treap.add(5);
//        treap.add(2);
//        System.out.println(treap.toString());
//        treap.root = treap.root.rotateRight();
//        System.out.println(treap.toString());
//        treap.root = treap.root.rotateLeft();
//        System.out.println(treap.toString());
//        Treap<Integer> treap2 = new Treap<>();
//        treap2.add(2);
//        treap2.add(3);
//        treap2.add(1);
//        System.out.println(treap2.toString());
//        treap2.root = treap2.root.rotateLeft();
//        System.out.println(treap2.toString());

    }
}
