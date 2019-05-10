package hw5;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class HuffmanTest {
    @Test
    void test1(){
        HuffmanTree tree = new HuffmanTree("this is a test string to encode");
        Boolean[] strencode = tree.encode("encodethis");
        Boolean[] strencode2 = tree.encode("encodethis");
        assertTrue(Arrays.equals(strencode, strencode2));
        assertEquals(tree.decode(strencode), "encodethis");
        Boolean[] testencoding = {true, false, true, true, false, false, false, false};
        assertEquals(tree.bitsToString(testencoding), "10110000");
    }
    @Test
    void test2(){
        HuffmanTree tree = new HuffmanTree("t");
        assertThrows(IllegalArgumentException.class, () -> tree.encode("dontdothis"));
        assertThrows(IllegalArgumentException.class, () -> tree.encode("cantdothiseither"));
        Boolean[] whoops = {true,true,false,false};
        assertThrows(IllegalArgumentException.class, () -> tree.decode(whoops));
        System.out.println(Arrays.toString(tree.encode("tt")));
        Boolean[] encoding = tree.encode("tttt");
        Boolean[] effencoding = tree.efficientEncode("tttt");
        assertTrue(Arrays.equals(encoding, effencoding));
        assertEquals(tree.decode(encoding), "tttt");
    }
}
