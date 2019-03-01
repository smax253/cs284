package hw2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {

	@Test
	void test1() { //normal functionalities
		IDLList<Integer> list = new IDLList<Integer>();
		IDLList<Integer> list1 = new IDLList<Integer>();
		for(int i = 0; i<15; i++) {
			list.add(i);
			list1.add(i);
			System.out.println(i);
		}
		System.out.println(list);
		int temp = list.getHead();
		assertTrue(list.remove()==temp);
	}
	void test2() { //edge cases
		assertTrue(false);
	}
	void test3() { //throw all the errors
		assertTrue(false);
	}
	void test4() { //non-primitive functionalities
		
	}
	void test5() { //more specific functionalities
		
	}

}
