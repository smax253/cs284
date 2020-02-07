package hw1new;
//import java.util.Arrays;

/**
 * Implements a BinaryNumber class using big-endian format.
 * @author Alex Schlumpf
 * @version 1.0.0.0
 */
public class BinaryNumber {
    private int data[];
    private int length;

    /**
     * Constructor that creates a 0-filled BinaryNumber with the given length.
     * @param length of BinaryNumber
     */
    public BinaryNumber(int length) {
        this.data = new int[length];
        this.length = length;
    } 

    /**
     * Constructor that creates a BinaryNumber corresponding to the String encoding.
     * @param number String encoded for a BinaryNumber
     * @throws IllegalArgumentException if String is not a proper encoding
     */
    public BinaryNumber(String number) {
        int i, val;
        final int len = number.length();
        this.data = new int[len];
        this.length = len;
        for (i = 0; i < len; i++) {
            val = Character.getNumericValue(number.charAt(i));
            if (val == 0 || val == 1) {
                data[i] = val;
            }
            else {
                throw new IllegalArgumentException("String must encode a binary number.");
            }
        }
    }

    /**
     * Returns the length of the BinaryNumber
     * @return length
     */
    public int getLength() {
        return this.length;
    }

    public int getDigit(int i) {
        if (i < 0 || i >= this.length) {
            throw new IllegalArgumentException("Index out of bounds.");
        }
        return this.data[i];
    }

    /**
     * Returns the int[] encoding of the BinaryNumber
     * @return data
     */
    public int[] innerArray() {
        return this.data;
    }

    /**
     * Shifts a BinaryNumber left or right by a specified number of bits.
     * @param dir The direction that the BinaryNumber is being shifted. 1 shifts right, -1 shifts left.
     * @param amount The amount of bits being shift
     * @throws IllegalArgumentException if dir is not 1 or -1
     * @throws IllegalArgumentException if amount is a negative int
     */
    public void bitShift(int dir, int amount) {
        if (amount >= 0) {
            int i, buffer[];
            if (dir == 1) {
                final int cutoff = length - amount;
                if (cutoff <= 0) {
                    this.data = new int[] {0};
                    this.length = 1;
                    return;
                }
                buffer = new int[cutoff];
                for (i = 0; i < cutoff; i++) {
                    buffer[i] = data[i];
                }
                this.length = cutoff;
            }
            else if (dir == -1) {
                final int cutoff = length + amount;
                buffer = new int[cutoff];
                for (i = 0; i < cutoff; i++) {
                    buffer[i] = (i < this.length) ? data[i] : 0;
                }
                this.length = cutoff;
            }
            else {
                throw new IllegalArgumentException("Expected -1 or 1 for direction.");
            }
            this.data = buffer;
        }
        else {
            throw new IllegalArgumentException("Amount must be a nonnegative int.");
        }
    }

    /**
     * Static method that prepends 0s to an int[]
     * @param arry The int[]
     * @param amount The amount of 0s being prepended to arry
     * @return The result of prepending amt 0s to arry.
     */
    private static int[] prepend(int[] arry, int amount) {
        int i, resLength = arry.length + amount;
        int[] buffer = new int[resLength];
        for (i = 0; i < resLength; i++) {
            buffer[i] = (i < amount) ? 0 : arry[i - amount];
        }
        return buffer;
    }

    /**
     * A static method that computes the bitwise or of two BinaryNumbers
     * @param bn1 A BinaryNumber
     * @param bn2 A BinaryNumber
     * @throws IllegalArgumentException if the BinaryNumbers differ in length
     * @return An int[] that represents the result of taking the bitwise or of bn1 and bn2
     */
    public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
        if (bn1.getLength() != bn2.getLength()) {
            throw new IllegalArgumentException("BinaryNumbers must have the same length.");
        }
        int i, len = bn1.getLength();
        int n1[] = bn1.innerArray(), n2[] = bn2.innerArray(), result[] = new int[len];
        for (i = 0; i < len; i++) {
            result[i] = (n1[i] == 0 && n2[i] == 0) ? 0 : 1;
        }
        return result;
    }

    /**
     * A static method that computes the bitwise and of two BinaryNumbers
     * @param bn1 A BinaryNumber
     * @param bn2 A BinaryNumber
     * @throws IllegalArgumentException if the BinaryNumbers differ in length
     * @return An int[] that represents the result of taking the bitwise and of bn1 and bn2
     */
    public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
        if (bn1.getLength() != bn2.getLength()) {
            throw new IllegalArgumentException("BinaryNumbers must have the same length.");
        }
        int i, len = bn1.getLength();
        int n1[] = bn1.innerArray(), n2[] = bn2.innerArray(), result[] = new int[len];
        for (i = 0; i < len; i++) {
            result[i] = (n1[i] == 1 && n2[i] == 1) ? 1 : 0;
        }
        return result;
    }

    /**
     * Returns the BinaryNumber in digits as an int
     * @return The digit in int format
     */
    public int toDecimal() {
        int i, res = 0, pw = 0;
        for (i = this.length - 1; i >= 0; i--, pw++) {
            res += data[i] * Math.pow(2, pw);
        }
        return res;
    }

    /**
     * Adds an instance of another BinaryNumber to the current instance.
     * Prepends 0s to the shorter number when lengths differ.
     * Accounts for possible overflow when adding.
     * @param other The other BinaryNumber
     */
    public void add(BinaryNumber other) {
        final int local[], outside[], diff = this.length - other.getLength();
        int i, sum, carry = 0, len = Math.max(this.length, other.getLength());
        boolean overflow = false;
        int result[] = new int[len];
        if (diff > 0) {
            outside = BinaryNumber.prepend(other.data, diff);
            local = this.data;
        }
        else if (diff < 0) {
            local = BinaryNumber.prepend(this.data, diff * -1);
            outside = other.innerArray();
        }
        else {
            local = this.data;
            outside = other.innerArray();
        }
        for (i = len - 1; i >= 0; i--) {
            sum = local[i] + outside[i] + carry;
            switch (sum) {
                case (0):
                    carry = 0;
                    overflow = false;
                    break;
                case (1):
                    result[i] = 1;
                    carry = 0;
                    overflow = false;
                    break;
                case (2):
                    result[i] = 0;
                    carry = 1;
                    overflow = true;
                    break;
                case (3):
                    result[i] =1;
                    carry = 1;
                    overflow = true;
                    break;
            }
        }
        if (overflow) {
            result = BinaryNumber.prepend(result, 1);
            result[0] = 1;
            len++;
        }
        this.data = result;
        this.length = len;
    }

    /**
     * Returns the BinaryNumber as an encoded String.
     * @return The encoded String.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Integer i : data) {
            sb.append(i.toString());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BinaryNumber bn = new BinaryNumber("0111");
    }
}