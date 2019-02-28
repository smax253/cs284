package hw2;

import java.util.ArrayList;
/**
 * Homework 2: Double Linked Lists
 * @author Max Shi 2/27/19
 * I pledge my honor that I have abided by the Stevens Honor System
 */
public class IDLList<E> {
    class Node<F>{
        F data;
        Node<F> next;
        Node<F> prev;
        Node(F elem){
            data = elem;
        }
        Node(F elem, Node<F> prev, Node<F> next) {
            data = elem;
            this.prev = prev;
            this.next = next;
        }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    public IDLList(){
        head = null;
        tail = null;
        size = 0;

    }
    public boolean add(E elem){
        if (head == null){
            head = new Node<E>(elem);
            tail = head;

        }else{

            head = new Node<E>(elem, null, head);;
        }
        size++;
        indices.add(0,head);
        return true;
    }
    public boolean add(int index, E elem){
        if(index<0 || index>=size) throw new IndexOutOfBoundsException();
        else if(index == 0) add(elem);
        else if(index == size-1) append(elem);
        Node<E> next = indices.get(index);
        Node<E> prev = next.prev;
        next.prev = new Node<E>(elem, prev, next);
        prev.next = next.prev;
        indices.add(index, next.prev);
        return true;
    }
    public boolean append(E elem){
        if(size == 0) add(elem);
        else{
            tail = new Node<E>(elem,tail, null);
            size++;
            indices.add(tail);
        }
        return true;
    }
    public E get(int index){
        return indices.get(index).data;
    }
    public E getHead(){
        return head.data;
    }
    public E getLast(){
        return tail.data;
    }
    public int size(){
        return size;
    }
    public E remove(){
        if(size==0) throw new IllegalStateException();
        Node<E> temp = head;
        if(head.next!=null){
            head = head.next;
        }
        else{
            head = null;
        }
        size--;
        indices.remove(0);
        return temp.data;
    }
    public E removeLast(){
        if(size == 0) throw new IllegalStateException();
        else if (size == 1) return remove();
        else{
            Node<E> temp = tail;
            tail = tail.prev;
            indices.remove(indices.size()-1);
            return temp.data;
        }
    }
    public E removeAt(int index){
        if(index<0 || index>=size) throw new IndexOutOfBoundsException();
        else if (index == 0) return remove();
        else if (index == size-1) return removeLast();
        else{
            Node<E> temp = indices.get(index);
            temp.prev = temp.next;
            temp.next = temp.prev;
            indices.remove(index);
            return temp.data;
        }
    }
    public boolean remove(E elem){
        Node<E> temp = head;
        while(head != null){
            if (temp.data.equals(elem)) return true;
            temp = temp.next;
        }
        if(temp != null) indices.remove(temp);
        return false;
    }
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node<E> current = head;
        while(current != null){
            s.append(current.data+",");
        }
        s.setLength(s.length()-1);
        s.append("]");
        return s.toString();
    }
    public static void main(String[] args){
        ArrayList<Integer> test = new ArrayList<Integer>();
        for (int i = 0; i<5; i++){
            test.add(i);
        }
        System.out.println(test);
        test.remove(3);
        System.out.println(test);
    }
}
