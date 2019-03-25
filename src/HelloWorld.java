public class HelloWorld {
	public static double ffibloop(int n) {
		double old = 1;
		double current = 1;
		
		while(n>1) {
			current = old+current;
			old = current-old;
			n--;
		}
		return current;
	}
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println(ffibloop(200));
	}
	
}
