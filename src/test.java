
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 5;
		int y = 6;
		if(x++==y && ++y > x) {
			return;
		}
		else if(++x == y 
				|| ++y>x) {
			System.out.println("java sucks");
		}
		System.out.println(x+":"+y);
	}

}
