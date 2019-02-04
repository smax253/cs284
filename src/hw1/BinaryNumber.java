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
		if(index>=0 && index<data.length)
			return data[index];
		else
			return -1;
	}
	public void shiftR(int amount) {
		
	}
	public void add(BinaryNumber aBinaryNumber) {
		if (this.getLength() != aBinaryNumber.getLength()) System.out.println("Number length does not match.");
		else {
			int length = this.getLength();
			int carry = 0;
			for(int i = 0; i<length; i++) {
				int sum = getDigit(i)+aBinaryNumber.getDigit(i)+carry;
				aBinaryNumber.data[i] = sum%2;
				carry = sum/2;
			}
			if (carry==1) overflow = true; 
		}
	}
	public String toString() {
		if(overflow) return "Overflow";
		
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
	public static void main(String[] args) {
		BinaryNumber b1 = new BinaryNumber("01101");
		BinaryNumber b2 = new BinaryNumber("11000");
		b1.add(b2);
		System.out.println(b2);
		System.out.println(b2.toDecimal());
	}
}
