package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> s = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> a = new ArrayDequeSolution<>();

        int N = 50000;
        String methodCalls = "";

        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 7);
            switch (operationNumber) {
                case 0:
                    // addLast
                    int randVal = StdRandom.uniform(0, 100);
                    s.addLast(randVal);
                    a.addLast(randVal);
                    methodCalls = methodCalls + "\naddLast(" + randVal + ")";
                    break;
                case 1:
                    // addFirst
                    int randVal1 = StdRandom.uniform(0, 100);
                    s.addFirst(randVal1);
                    a.addFirst(randVal1);
                    methodCalls = methodCalls + "\naddFirst(" + randVal1 + ")";
                    break;
                case 2:
                    // removeLast
                    if (s.isEmpty() || a.isEmpty()) {
                        break;
                    }
                    Integer sLast = s.removeLast();
                    Integer aLast = a.removeLast();
                    methodCalls = methodCalls + "\nremoveLast()";
                    assertEquals(methodCalls, aLast, sLast);
                    break;
                case 3:
                    // removeFirst
                    if (s.isEmpty() || a.isEmpty()) {
                        break;
                    }
                    Integer sFirst = s.removeFirst();
                    Integer aFirst = a.removeFirst();
                    methodCalls = methodCalls + "\nremoveFirst()";
                    assertEquals(methodCalls, aFirst, sFirst);
                    break;
                case 4:
                    // get
                    if (s.isEmpty() || a.isEmpty()) {
                        break;
                    }
                    int maxIndex = Math.min(a.size(), s.size());
                    int index = StdRandom.uniform(0, maxIndex);
                    methodCalls = methodCalls + "\nget(" + index + ")";
                    assertEquals(methodCalls, a.get(index), s.get(index));
                    break;
                case 5:
                    //empty
                    methodCalls = methodCalls + "\nisEmpty()";
                    assertEquals(methodCalls, a.isEmpty(), s.isEmpty());
                    break;
                case 6:
                    //size
                    methodCalls = methodCalls + "\nsize()";
                    assertEquals(methodCalls, a.size(), s.size());
                    break;
            }
        }
    }
}
