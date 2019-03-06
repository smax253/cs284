package hw2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {

	@Test
	void test1() { //normal functionalities
		IDLList<Integer> list = new IDLList<Integer>();
		IDLList<Integer> list1 = new IDLList<Integer>();
		for(int i = 0; i<15; i++) { //construct two lists 14 to 0
			list.add(i);
			list1.add(i);
		}
		int temp = list.getHead();
		assertTrue(temp == 14); //make sure head is head
		assertTrue(list.getLast() == 0); //make sure tail is tail
		assertTrue(list.remove()==temp); //remove head
		assertTrue(list.add(6, 100)); //insert element
		assertTrue(list.get(6)==100); //make sure element inserted in correct index
		assertTrue(list.append(-100)); //append element to end
		assertTrue(list.removeLast()==-100); //make sure correct element removed from end
		assertTrue(list.toString().equals("[13,12,11,10,9,8,100,7,6,5,4,3,2,1,0]")); //make sure element actually removed
		assertTrue(list.append(700)); 
		assertTrue(list.getLast() == 700); //test getLast()
		assertTrue(list1.remove(13)); //remove specific element
		assertTrue(list1.size() == 14); //make sure size updated
	}
	@Test
	void test2() { //edge cases
		IDLList<Integer> list = new IDLList<>();
		assertTrue(list.add(0,1)); //add first element with index
		assertTrue(list.add(1,10)); //add last element with index
		list.append(40);
		list.append(30);
		assertTrue(!list.remove(50)); //remove nonexistent element
		assertTrue(list.remove(1)); //remove the first element by directly referring to it
		list.removeLast();
		assertTrue(list.removeAt(1)==40); //remove the last element by referring to index
		assertTrue(list.removeLast()==10); //remove the last and only element
		assertTrue(list.size() == 0);
		assertTrue(list.toString().equals("[]")); //check if actually empty
		list.append(100);
		assertTrue(list.remove()==100); //remove the first and only element
		assertTrue(list.size() == 0);
		assertTrue(list.toString().equals("[]")); //check if actually empty
	}
	@Test
	void test3() { //throw all the errors
		IDLList<Integer> list = new IDLList<>();
		try {
			list.add(100,1); //add out of bounds
		}catch(IndexOutOfBoundsException e) {
			assertTrue(e!=null);
		}
		try {
			list.add(-10,1); //add out of bounds
		}catch(IndexOutOfBoundsException e) {
			assertTrue(e!=null);
		}
		try {
			list.getHead(); //get head on empty list
;		}catch(IllegalStateException e) {
			assertTrue(e!=null);
		}
		try {
			list.getLast(); //get tail on empty list
		}catch(IllegalStateException e) {
			assertTrue(e!=null);
		}
		try {
			list.remove(); //remove head element from empty list
		}catch(IllegalStateException e) {
			assertTrue(e!=null);
		}
		try {
			list.removeLast();  //remove tail from empty list
		}catch(IllegalStateException e) {
			assertTrue(e!=null);
		}
		try {
			list.removeAt(100); //remove from index from empty list
		}catch(IndexOutOfBoundsException e) {
			assertTrue(e!=null);
		}
		try {
			list.removeAt(-100); //same as above
		}catch(IndexOutOfBoundsException e) {
			assertTrue(e!=null);
		}
	}
	@Test
	void test4() { //Test string representation
		String [] strs = {"hello","this","is","a","test"};
		IDLList<String> list = new IDLList<>();
		for(String s : strs) {
			list.append(s);
		}
		assertTrue(list.toString().equals("[hello,this,is,a,test]"));
	}

}
