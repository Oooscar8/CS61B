package flik;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void testFlik() {
        assertTrue("-1 != -1", Flik.isSameNumber(-1, -1));
        assertTrue("0 != 0", Flik.isSameNumber(0, 0));
        assertTrue("127 != 127", Flik.isSameNumber(127, 127));
        assertTrue("128 != 128", Flik.isSameNumber(128, 128));
        assertTrue("129 != 129", Flik.isSameNumber(129, 129));
        assertTrue("99999 != 99999", Flik.isSameNumber(99999, 99999));
    }
}
