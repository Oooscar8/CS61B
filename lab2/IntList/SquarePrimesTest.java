package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {
    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes1() {
        IntList lst1 = IntList.of(10, 12, 14, 21, 25);
        boolean changed1 = IntListExercises.squarePrimes(lst1);
        assertEquals("10 -> 12 -> 14 -> 21 -> 25", lst1.toString());
        assertFalse(changed1);
    }

    @Test
    public void testSquarePrimesSimple2() {
        IntList lst2 = IntList.of(5, 7, 13, 3, 6);
        boolean changed2 = IntListExercises.squarePrimes(lst2);
        assertEquals("25 -> 49 -> 169 -> 9 -> 6", lst2.toString());
        assertTrue(changed2);
    }
}

