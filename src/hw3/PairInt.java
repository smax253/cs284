package hw3;

/**
 * Class to represent a pair of Integers
 * @author Max Shi
 */
public class PairInt{
	private int x;
	private int y;
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean equals(Object p){
		PairInt other = null;
		try {
			other = (PairInt) p;
		}catch(Exception e) {
			return false;
		}
		if(other == null) return false;
		return x==other.getX() && y==other.getY();
	}
	public String toString() {
		return "("+x+","+y+")";
	}
	public PairInt copy() {
		return new PairInt(x, y);
	}
	
}