package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {
    private static class StringComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    public static Comparator<String> getStringComparator() {
        return new StringComparator();
    }

    @Test
    public void testStringMaxArrayDeque() {
        Comparator<String> s = getStringComparator();
        MaxArrayDeque<String> lld = new MaxArrayDeque<>(s);

        lld.addFirst("front");
        lld.addLast("middle");
        lld.addLast("back");

        assertEquals("middle",lld.max());

        Comparator<String> s1 = getStringComparator();
        assertEquals("middle",lld.max(s1));
    }

    private static class IntegerComparator implements Comparator<Integer> {
        public int compare(Integer t1, Integer t2) {
            return t1 - t2;
        }
    }

    public static Comparator<Integer> getIntegerComparator() {
        return new IntegerComparator();
    }

    @Test
    public void testIntegerMaxArrayDeque() {
        Comparator<Integer> s = getIntegerComparator();
        MaxArrayDeque<Integer> lld = new MaxArrayDeque<>(s);

        lld.addFirst(0);
        lld.addLast(1);
        lld.addLast(2);

        assertEquals(2, (int)lld.max());

        Comparator<Integer> s1 = getIntegerComparator();
        assertEquals(2, (int)lld.max(s1));
    }
}
