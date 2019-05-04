package hw4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreapTest {

    @Test
    void test1() { //normal behavior
        Treap<Integer> testTree = new Treap<>();
        testTree.add(4, 19);
        testTree.add(2, 31);
        testTree.add(6, 70);
        testTree.add(1, 84);
        testTree.add(3, 12);
        testTree.add(5, 83);
        testTree.add(7, 26);
        assertTrue(testTree.delete(5));
        assertTrue(testTree.find(6));
        assertFalse(testTree.find(100));
        assertFalse(testTree.find(5));

        assertTrue(testTree.delete(1));
        System.out.println(testTree);
        assertTrue(testTree.find(6));
    }

    @Test
    void test2() { //edge cases
        Treap<Integer> testTree = new Treap<>();
        assertFalse(testTree.find(5));
        assertEquals(testTree.toString(),"null");
        assertFalse(testTree.delete(50));
        assertFalse(testTree.find(40));
        testTree.add(40);

        assertThrows(IllegalArgumentException.class, () -> testTree.add(40));
        assertTrue(testTree.find(40));
        assertTrue(testTree.delete(40));
        assertEquals(testTree.toString(),"null");

    }

    @Test
    void test3() {  //now can it do it with objects?
        Treap<String> testTree = new Treap<>();
        testTree.add("hello", 40);
        testTree.add("my",20);
        testTree.add("name", 15);
        testTree.add("is",60);
        testTree.add("max", 1000);
        testTree.add("shi",9001);
        assertTrue(testTree.find("hello"));
        assertTrue(testTree.delete("name"));
        assertFalse(testTree.find("nothere"));
        assertFalse(testTree.find("name"));
    }


}