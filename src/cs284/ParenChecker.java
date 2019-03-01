package cs284;

public class ParenChecker {
	private static final String OPEN = "([{";
	private static final String CLOSE = ")]}";
	public static boolean isBalanced(String exp) {
		StackSLL<Character> s = new StackSLL<>();
		for(int i = 0; i<exp.length(); i++) {
			char current = exp.charAt(i);
			if(isOpen(current)) s.push(current);
			else if (isClose(current)) {
				try {
					char result = s.pop();
					if(OPEN.indexOf(result)!=CLOSE.indexOf(current)) return false;
				}catch(Exception e) {
					return false;
				}
			}
		}
		return s.size()==0;
	}
	private static boolean isOpen(char ch) {
		return OPEN.indexOf(ch)>-1;
	}
	private static boolean isClose(char ch) {
		return CLOSE.indexOf(ch)>-1;
	}
	public static void main(String[] args) {
		System.out.println(isBalanced("([{}])"));
		System.out.println(isBalanced("{{{}"));
		System.out.println(isBalanced("{]"));
		System.out.println(isBalanced("{}}}"));
		System.out.println(isBalanced(""));
	}
}
