/**
 * A recursive BinaryChop implementation.
 */
public class BinaryChopB implements BinaryChop {

    private static final int FAIL = -100000;

    public int chop(int target, int[] intArray) {
        return Math.max(-1, recursiveChop(target, intArray));
    }

    private int recursiveChop(int target, int[] intArray) {
        if (intArray.length == 0) {
            return FAIL;
        }
        int halfWay = intArray.length / 2;
        int newArrayLength = 0;
        if (target == intArray[halfWay]) {
            return halfWay;
        } else if (isEven(intArray.length) && target > intArray[halfWay]) {
            newArrayLength = halfWay - 1;
        } else {
            newArrayLength = halfWay;
        }
        int[] newArray = new int[newArrayLength];
        int offset = 0;
        for (int i = 0; i < newArrayLength; i++) {
            if (target > intArray[halfWay]) {
                newArray[i] = intArray[halfWay + i + 1];
                offset = halfWay + 1;
            } else {
                newArray[i] = intArray[i];
            }
        }
        return offset + recursiveChop(target, newArray);
    }

    private boolean isEven(int n) {
        return (n / 2 == (double) n / 2);
    }
}
