package cs284;

import java.util.Arrays;

public class Sort<E> {
	public static int[] merge(int[] a, int[] b){
		int[] result = new int[a.length + b.length];
		int indexA = 0;
		int indexB = 0;
		int indexR = 0;
		while(indexA<a.length || indexB<b.length) {
			if(indexB>=b.length || (indexA<a.length && a[indexA]<b[indexB]) ) {
				result[indexR] = a[indexA];
				indexA++;
			}
			else {
				result[indexR] = b[indexB];
				indexB++;
			}
			indexR++;
		}
		
		return result;
	}
	public static int[] mergeSort(int[] a) {
		return mergeSort(Arrays.copyOf(a, a.length/2), Arrays.copyOfRange(a, a.length/2, a.length));
	}
	private static int[] mergeSort(int[] a, int[] b) {
		if(a.length <= 1 && b.length <= 1) return merge(a,b);
		else {
			return merge(mergeSort(Arrays.copyOf(a, a.length/2), Arrays.copyOfRange(a, a.length/2, a.length)),
					mergeSort(Arrays.copyOf(b,b.length/2), Arrays.copyOfRange(b, b.length/2, b.length)));
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] first = {50,60,90,30};
		int[] second = {45,20,80,15,33};
		int[] merged = mergeSort(first, second);
		System.out.println(Arrays.toString(merged));
	}

}
