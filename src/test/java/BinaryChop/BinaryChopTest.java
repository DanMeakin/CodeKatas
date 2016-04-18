package BinaryChop;

import BinaryChop.*;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BinaryChopTest {

    public void chopTester(BinaryChop bc) {
        assertEquals(-1, bc.chop(3, new int[]{}));
        assertEquals(-1, bc.chop(3, new int[]{1}));
        assertEquals(0,  bc.chop(1, new int[]{1}));

        assertEquals(0,  bc.chop(1, new int[]{1, 3, 5}));
        assertEquals(1,  bc.chop(3, new int[]{1, 3, 5}));
        assertEquals(2,  bc.chop(5, new int[]{1, 3, 5}));
        assertEquals(-1, bc.chop(0, new int[]{1, 3, 5}));
        assertEquals(-1, bc.chop(2, new int[]{1, 3, 5}));
        assertEquals(-1, bc.chop(4, new int[]{1, 3, 5}));
        assertEquals(-1, bc.chop(6, new int[]{1, 3, 5}));

        assertEquals(0,  bc.chop(1, new int[]{1, 3, 5, 7}));
        assertEquals(1,  bc.chop(3, new int[]{1, 3, 5, 7}));
        assertEquals(2,  bc.chop(5, new int[]{1, 3, 5, 7}));
        assertEquals(3,  bc.chop(7, new int[]{1, 3, 5, 7}));
        assertEquals(-1, bc.chop(0, new int[]{1, 3, 5, 7}));
        assertEquals(-1, bc.chop(2, new int[]{1, 3, 5, 7}));
        assertEquals(-1, bc.chop(4, new int[]{1, 3, 5, 7}));
        assertEquals(-1, bc.chop(6, new int[]{1, 3, 5, 7}));
        assertEquals(-1, bc.chop(8, new int[]{1, 3, 5, 7}));

        // My own
        int myArr[] = new int[10000];
        for (int i=0; i < 10000; i++) {
            myArr[i] = i*i;
        }
        assertEquals(100, bc.chop(10000, myArr));
    }

    @Test
    public void testBinaryChopA() {
        chopTester(new BinaryChopA());
    }

    @Test
    public void testBinaryChopB() {
        chopTester(new BinaryChopB());
    }

    @Test
    public void testBinaryChopC() {
        chopTester(new BinaryChopC());
    }

    @Test
    public void testBinaryChopD() {
        chopTester(new BinaryChopD());
    }

    @Test
    public void testBinaryChopE() {
        chopTester(new BinaryChopE());
    }
}
