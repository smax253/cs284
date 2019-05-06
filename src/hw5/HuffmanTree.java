package hw5;


import java.util.*;
/*
*  Instructions:
*  First: Read through the assignment specification, make sure you
understand what the assignment is asking for.
*  Second: There are number of  instructions within this code, make
sure to complete all of them fully.
*  Third: Test you code.
*/
// Pledge: I pledge my honor that I have abided by the Stevens Honor System
// Name: Max Shi

/**
 * HW4 CS284 Spring 2019
 * Implements a Huffman encoding tree.
 * The included code has been commented for the student's benefit, feel
 * free to read through it.
 */
public class HuffmanTree {
// ******************** Start of Stub Code ******************** //
// ************************************************************ //

    /**
     * Node<E> is an inner class and it is abstract.
     * There will be two kinds
     * of Node, one for leaves and one for internal nodes.
     */
    abstract static class Node implements Comparable<Node> {
        /**
         * The frequency of all the items below this node
         */
        protected int frequency;

        public Node(int freq) {
            this.frequency = freq;
        }

        /**
         * Needed for the Minimum Heap used later in this stub.
         */
        public int compareTo(Node other) {
            return this.frequency - other.frequency;
        }
    }

    /**
     * Leaves of a Huffman tree contain the data items
     */
    protected static class LeafNode extends Node {
// Data Fields
        /**
         * The data in the node
         */
        protected char data;

        /**
         * Constructor to create a leaf node (i.e. no children)
         */
        public LeafNode(char data, int freq) {
            super(freq);
            this.data = data;
        }

        /**
         * toString method
         */
        public String toString() {
            return "[value= " + this.data + ",freq= " + frequency + "]";
        }
    }

    /**
     * Internal nodes contain no data,
     * just references to left and right subtrees
     */
    protected static class InternalNode extends Node {
        /**
         * A reference to the left child
         */
        protected Node left;
        /**
         * A reference to the right child
         */
        protected Node right;

        /**
         * Constructor to create an internal node
         */
        public InternalNode(Node leftC, Node rightC) {
            super(leftC.frequency + rightC.frequency);
            left = leftC;
            right = rightC;
        }

        public String toString() {
            return "(freq= " + frequency + ")";
        }
    }

    // Enough space to encode all "extended ascii" values
// This size is probably overkill (since many of the values are not "printable" in the usual sense)
    private static final int codex_size = 256;
    /* Data Fields for Huffman Tree */
    private Node root;

    public HuffmanTree(String s) {
        root = buildHuffmanTree(s);
    }

    /**
     * Returns the frequencies of all characters in s.
     *
     * @param s
     * @return
     */
    public static int[] frequency(String s) {
        int[] freq = new int[codex_size];
        for (char c : s.toCharArray()) {
            freq[c]++;
        }
        return freq;
    }

    /**
     * Builds the actual Huffman tree for that particular string.
     *
     * @param s
     * @return
     */
    private static Node buildHuffmanTree(String s) {
        int[] freq = frequency(s);
// Create a minimum heap for creating the Huffman Tree
// Note to students: You probably won't know what this data structure
// is yet, and that is okay.
        PriorityQueue<Node> min_heap = new PriorityQueue<Node>();
// Go through and create all the nodes we need
// as in, all the nodes that actually appear in our string (have a frequency greater then 0)
        for (int i = 0; i < codex_size; i++) {
            if (freq[i] > 0) {
// Add a new node (for that character) to the min_heap, notice we have to cast our int i into a char.
                min_heap.add(new LeafNode((char) i,
                        freq[i]));
            }
        }
// Edge case (string was empty)
        if (min_heap.isEmpty()) {
            throw new NullPointerException("Cannot encode an empty String");
        }
// Now to create the actual Huffman Tree
// NOTE: this algorithm is a bit beyond what we cover in cs284,
// you'll see this in depth in cs385
// Merge smallest subtrees together
        while (min_heap.size() > 1) {
            Node left = min_heap.poll();
            Node right = min_heap.poll();
            Node merged_tree = new InternalNode(left,
                    right);
            min_heap.add(merged_tree);
        }
// Return our structured Huffman Tree
        return min_heap.poll();
    }

    // ******************** End of Stub Code ******************** //
// ********************************************************** //
    public String bitsToString(Boolean[] encoding) {
        StringBuilder s = new StringBuilder();
        for (Boolean b : encoding){
            s.append(b ? "1" : "0");
        }
        return s.toString();
    }
    private String toStringHelper(Node top, int level){
        StringBuilder s = new StringBuilder();
        if (level != 0) s.append("\n");
        for(int i = 0; i<level; i++){
            s.append("  ");
        }
        s.append(top.toString());
        if (top instanceof InternalNode) {
            InternalNode inter = (InternalNode) top;
            s.append(toStringHelper(inter.right, level+1));
            s.append(toStringHelper(inter.left, level+1));
        }
        return s.toString();
    }
    public String toString() {
        return toStringHelper(root, 0);
    }

    public String decode(Boolean[] coding) {
        StringBuilder s = new StringBuilder();
        Node current = root;
        for (int i = 0; i<coding.length; i++){
            if (current == null) throw new IllegalArgumentException();
            if (root instanceof LeafNode && coding[i]) throw new IllegalArgumentException();
            else if(current instanceof InternalNode){
                InternalNode temp = (InternalNode) current;
                current = coding[i] ? temp.right : temp.left;
            }
            if(current instanceof LeafNode) {
                LeafNode temp = (LeafNode) current;
                s.append(temp.data);
                current = root;
            }
        }
        return s.toString();
    }
    private void lookup(Node current, char c, ArrayList<Boolean> result, Stack<Boolean> stack){
        if (current == null) return;
        else if(current instanceof InternalNode){
            InternalNode temp = (InternalNode) current;
            stack.push(true);
            lookup(temp.right, c, result, stack);
            stack.pop();
            stack.push(false);
            lookup(temp.left, c, result, stack);
            stack.pop();
        }
        else if (current instanceof LeafNode){
            LeafNode temp = (LeafNode) current;
            if(temp.data == c) {
                result.addAll(stack);
            }
        }
    }
    public Boolean[] encode(String inputText) {
        ArrayList<Boolean> bools = new ArrayList<>();
        ArrayList<Boolean> currentChar = new ArrayList<>();
        for(int i = 0; i<inputText.length(); i++){
        	if (root instanceof LeafNode && ((LeafNode)root).data == inputText.charAt(i)) bools.add(false);
        	else{
	        	lookup(root, inputText.charAt(i), currentChar, new Stack<Boolean>());
	            if (currentChar.size()==0) throw new IllegalArgumentException();
	            bools.addAll(currentChar);
	            currentChar.clear();
        	}
        }
        Boolean[] result = new Boolean[0];
        return bools.toArray(result);
    }

    public Boolean[] efficientEncode(String inputText) {
// NOTE: Should only go through the tree once.
        ArrayList<Boolean> bools = new ArrayList<>();
        HashMap<Character, ArrayList<Boolean>> hash = new HashMap<>();
        for(int i = 0; i<inputText.length(); i++){
            Character c = inputText.charAt(i);
            if(hash.containsKey(c)) bools.addAll(hash.get(c));
            else{
            	if (root instanceof LeafNode && ((LeafNode)root).data == inputText.charAt(i)) bools.add(false);
            	else {
	            	ArrayList<Boolean> currentChar = new ArrayList<>(); 
	                lookup(root, c, currentChar, new Stack<Boolean>());
	                if (currentChar.size() == 0) throw new IllegalArgumentException();
	                bools.addAll(currentChar);
	                hash.put(c, currentChar);
	                currentChar.clear();
            	}
            }
        }
        Boolean[] result = new Boolean[0];
        return bools.toArray(result);
    }

    public static void main(String[] args) {
// Code to see if stuff works...
        String s = "s";
        HuffmanTree t = new HuffmanTree(s); // Creates specific Huffman Tree for "s"
        Boolean[] bools = {false, false};
        System.out.println(t.bitsToString(bools));
        System.out.println(t.toString());
        System.out.println(t.decode(bools));
        System.out.println(Arrays.toString(t.encode("ssss")));
        Boolean[] encode = t.efficientEncode("ssss");
        System.out.println(Arrays.toString(encode));
        System.out.println(t.decode(encode));

// Now you can use encode, decode, and toString to interact with your specific Huffman Tree
    }
}
