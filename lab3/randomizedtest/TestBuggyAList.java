package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> NoResizingAList = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        NoResizingAList.addLast(4);
        NoResizingAList.addLast(5);
        NoResizingAList.addLast(6);
        buggyAList.addLast(4);
        buggyAList.addLast(5);
        buggyAList.addLast(6);

        assertEquals(NoResizingAList.removeLast(), buggyAList.removeLast());
        assertEquals(NoResizingAList.removeLast(), buggyAList.removeLast());
        assertEquals(NoResizingAList.removeLast(), buggyAList.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            switch(operationNumber) {
                case 0:
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    L.addLast(randVal);
                    B.addLast(randVal);
                    break;
                case 1:
                    // size
                    int sizeL = L.size();
                    int sizeB = B.size();
                    assertEquals(sizeL, sizeB);
                    break;
                case 2:
                    if (L.size() > 0){
                        int LastNumberL = L.getLast();
                        int LastNumberB = B.getLast();
                        assertEquals(LastNumberL, LastNumberB);
                    }
                    break;
                case 3:
                    if (L.size() > 0){
                        int DeletedNumberL = L.removeLast();
                        int DeletedNumberB = B.removeLast();
                        assertEquals(DeletedNumberL, DeletedNumberB);
                    }
                    break;
            }
        }
    }
}
