package Lecture4;

import static org.junit.Assert.*;
import org.junit.Test;
public class intListTest {
    /**test for intList.dincrList*/
    @Test
    public void testdincrList(){
        intList input = new intList(15, null);
        input = new intList(10, input);
        input = new intList(5, input);

        intList expected = new intList(17, null);
        expected = new intList(12, expected);
        expected = new intList(7, expected);

        intList output = intList.dincrList(input, 2);

        for (int i = 0; i < input.size(); ++i){
            assertEquals(output.get(i), expected.get(i));
        }
    }

    /**test for intList.incrList*/
    @Test
    public void testincrList(){
        intList input = new intList(15, null);
        input = new intList(10, input);
        input = new intList(5, input);

        intList expected = new intList(17, null);
        expected = new intList(12, expected);
        expected = new intList(7, expected);

        intList output = intList.incrList(input, 2);

        for (int i = 0; i < input.size(); ++i){
            assertEquals(output.get(i), expected.get(i));
        }
    }
}
