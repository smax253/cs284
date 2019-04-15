package cs284;

import java.util.Arrays;

public class Heap<E extends Comparable<E>>{
	private E[] data;
	private int last;
	
	Heap(int size){
		data = (E[]) new Comparable[size];
		last = 0;
	}
	private void swap(int i, int j) {
		E temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	public void add(E item) {
		data[last] = item;
		int current = last;
		int parent = (current-1)/2;
		while(parent >= 0 && data[current].compareTo(data[parent])<0 ) {
			swap(current, parent);
			current = parent;
			parent = (current-1)/2;
		}
		last++;
	}
	public E remove() {
		E temp = data[0];
		data[0] = data[last-1];
		int parent = 0;
		int minChild;
		while(true) {
			int leftChild = 2*parent + 1;
			int rightChild = leftChild + 1;
			if(leftChild>=last) break;
			minChild = data[leftChild].compareTo(data[rightChild])<0 ? leftChild : rightChild;
			if(data[minChild].compareTo(data[parent])>0) break;
			
		}
		last--;
		return temp;
	}
	public String toString() {
		return Arrays.toString(data);
	}
	public static void main(String[] args) {
		Heap<Integer> heap = new Heap<Integer>(20);
		for(int i = 10; i>0; i--) {
			heap.add(i);
		}
		System.out.println(heap);
	}
}
