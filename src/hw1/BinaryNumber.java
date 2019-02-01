package hw1;
public class BinaryNumber {
	private int[] data;
	private boolean overflow;
	public BinaryNumber(int length){
		if(length<1) {
			System.out.println("Invalid input.");
			overflow = true;
		}
		else {
			data = new int[length];
			overflow = false;
		}
	}
	public BinaryNumber(String str) {
		boolean invalid = false;
		data = new int[str.length()];
		for(int i = 0; i<str.length(); i++) {
			data[i] =  Character.getNumericValue(str.charAt(i));
			if (data[i]<0 || data[i]>1) {
				invalid = true;
			}
		}
		if (invalid) {
			System.out.println("Invalid Input");
			data = null;
			overflow = true;
		}
	}
	public int getLength() {
		return data.length;
	}
	public int getDigit(int index) {
		if(index>0 && index<data.length-1)
			return data[index];
		else
			return -1;
	}
	public void shiftR(int amount) {
		
	}
	public void add(BinaryNumber aBinaryNumber) {
		
	}
	public String toString() {
		String s = "";
		for(int digit:data) {
			s += digit;
		}
		return s;
	}
	public int toDecimal() {
		int number = 0;
		for(int i = 0; i<data.length; i++) {
			number += data[i]*Math.pow(2, i);
		}
		return number;
	}
	public void clearOverflow() {
		overflow = false;
	}
}
