/**
 * A while-looping implementation.
 */
public class BinaryChopA implements BinaryChop {

    public int chop(int target, int[] intArray) {
        int startIdx = 0;
        int endIdx = intArray.length;
        while (true) {
            if (endIdx - startIdx == 0) {
                return -1;
            }
            int halfWay = (endIdx - startIdx) / 2 + startIdx;
            if (intArray[halfWay] == target) {
                return halfWay;
            } else if (halfWay == startIdx) {
                return -1;
            } else if (target > intArray[halfWay]) {
                startIdx = halfWay;
            } else if (target < intArray[halfWay]) {
                endIdx = halfWay;
            }
        }
    }

}
