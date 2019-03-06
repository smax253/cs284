package hw2;

import java.util.ArrayList;
/**
 * Homework 2: Double Linked Lists
 * @author Max Shi 2/27/19
 * I pledge my honor that I have abided by the Stevens Honor System
 */
public class IDLList<E> {
    /**
     * The Node class to store each element of the linked list
     */
    class Node<F>{
        F data;
        Node<F> next;
        Node<F> prev;
        /**
         * Constructors for creating nodes with element or with all data fields
         */
        Node(F elem){
            data = elem;
        }
        Node(F elem, Node<F> prev, Node<F> next) {
            data = elem;
            this.prev = prev;
            this.next = next;
        }
    }
    //instance variables
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;
    /**
     * Create an empty double linked list
     */
    public IDLList(){
        head = null;
        tail = null;
        size = 0;
        indices = new ArrayList<Node<E>>();
    }
    /**
     * Adds an element to the front of the list
     * @param elem The element to add to the list
     * @return true if addition succeeded
     */
    public boolean add(E elem){
        if (head == null){
            head = new Node<E>(elem);
            tail = head;

        }else{
            head = new Node<E>(elem, null, head);
            head.next.prev= head;
        }
        size++;
        indices.add(0,head);
        return true;
    }
    /**
     * Inserts an element to a certain index of the list
     * @param index the index to insert the element
     * @param elem the element to add
     * @return true if insertion succeeded
     */
    public boolean add(int index, E elem){
        if(index<0 || index>size) throw new IndexOutOfBoundsException();
        else if(index == 0) return add(elem);
        else if(index == size) return append(elem);
    	Node<E> next = indices.get(index);
        Node<E> prev = next.prev;
        next.prev = new Node<E>(elem, prev, next);
        prev.next = next.prev;
        indices.add(index, next.prev);
        size++;
        return true;
        
    }
    /**
     * Adds an element to the back of the list
     */
    public boolean append(E elem){
        if(size == 0) return add(elem);
        else{
            tail = new Node<E>(elem,tail, null);
            tail.prev.next = tail;
            size++;
            indices.add(tail);
            return true;
        }
    }
    /**
     * Gets the data of the node at the given index
     */
    public E get(int index){
        return indices.get(index).data;
    }
    /**
     * Gets the data at the head of the list
     */
    public E getHead(){
        if(head == null) throw new IllegalStateException();
        return head.data;
    }
    /**
     * Gets the data at the tail of the list
     */
    public E getLast(){
        if(tail == null) throw new IllegalStateException();
        return tail.data;
    }
    /**
     * Returns the size of the linked list
     */
    public int size(){
        return size;
    }
    /**
     * Removes the first element of the list and returns it
     * @return The first element of the list
     */
    public E remove(){
        if(size==0) throw new IllegalStateException();
        Node<E> temp = head;
        if(head.next!=null){
            head = head.next;
            head.prev = null;
        }
        else{
            head = null;
        }
        size--;
        indices.remove(0);
        return temp.data;
    }
    /**
     * Removes the last element of the list
     * @return The last element of the list
     */
    public E removeLast(){
        if(size == 0) throw new IllegalStateException();
        else if (size == 1) return remove();
        else{
            Node<E> temp = tail;
            tail = tail.prev;
            tail.next = null;
            indices.remove(indices.size()-1);
            size--;
            return temp.data;
        }
    }
    /**
     * Removes the element at given index in the list and returns it
     * @return The element at the given index
     */
    public E removeAt(int index){
        if(index<0 || index>=size) throw new IndexOutOfBoundsException();
        else if (index == 0) return remove();
        else if (index == size-1) return removeLast();
        else{
            Node<E> temp = indices.get(index);
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            indices.remove(index);
            size--;
            return temp.data;
            
        }
    }
    /**
     * Remove the first instance of an element from the list
     * @return true if element in list, false if not
     */
    public boolean remove(E elem){
        Node<E> temp = head;
        boolean found = false;
        int index = 0;
        while(temp != null && !found){
            if (temp.data.equals(elem)) found=true;
            else {
            	index++;
            	temp = temp.next;
            }
        }
        if(found) {
        	return elem.equals(this.removeAt(indices.indexOf(temp)));
        }
        else return false;
        
    }
    /**
     * Returns a string representation of the list
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node<E> current = head;
        while(current != null){
            s.append(current.data.toString()+",");
            current = current.next;
        }
        if(s.length()>2) s.setLength(s.length()-1);
        s.append("]");
        return s.toString();
    }
    
}
