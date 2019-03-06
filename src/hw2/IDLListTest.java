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
		list.add(6, 100);
		assertTrue(list.get(6)==100);
		list.append(-100);
		assertTrue(list.removeLast()==-100);
		assertTrue(list1.remove(13));
	}
	@Test
	void test2() { //edge cases
		assertTrue(false);
	}
	@Test
	void test3() { //throw all the errors
		assertTrue(false);
	}
	@Test
	void test4() { //non-primitive functionalities
		
	}
	@Test
	void test5() { //more specific functionalities
		
	}

}
