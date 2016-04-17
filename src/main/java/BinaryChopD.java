import java.util.*;

/**
 * An iterator-based implementation
 */
public class BinaryChopD implements BinaryChop {

    private int[] intArray;
    private int target;

    public int chop(int target, int[] intArray) {
        this.intArray = intArray;
        this.target = target;
        List<Integer> ep = getEndPoint();
        if (ep.size() == 0 || ep.get(0) >= intArray.length) {
            return -1;
        } else if (target == intArray[ep.get(0)]){
            return ep.get(0);
        } else {
            return -1;
        }
    }

    private List<Integer> getEndPoint() {
        List<Integer> indices = new ArrayList<>();
        Iterator<List<Integer>> iterator = listIterator();
        while (iterator.hasNext()) {
            indices = iterator.next();
        }
        return indices;
    }

    private Iterator<List<Integer>> listIterator() {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < intArray.length; i++) {
            l.add(i);
        }
        System.out.println(l.toString());
        return new Iterator<List<Integer>>() {
            private List<Integer> currSubList = l;
            private boolean hasNextEntry = true;

            @Override
            public List<Integer> next() {
                if (currSubList.size() == 0) {
                    hasNextEntry = false;
                    return currSubList;
                }
                // Get the middle index of the current sub-list
                int midPoint = currSubList.size() / 2;
                // Get the index of the intArray represented by the value at the
                // current middle index.
                int midIndex = currSubList.get(midPoint);
                // Get the value of the intArray at midIndex
                int midValue = intArray[midIndex];

                if (target == midValue) {
                    currSubList = Arrays.asList(new Integer[]{midIndex});
                    hasNextEntry = false;
                } else if (target < midValue) {
                    currSubList = currSubList.subList(0, midPoint);
                } else if (target > midValue) {
                    if (currSubList.size() <= midPoint + 1) {
                        hasNextEntry = false;
                        currSubList = new ArrayList<>();
                    } else {
                        currSubList = currSubList.subList(midPoint + 1, currSubList.size());
                    }
                } else {
                    hasNextEntry = false;
                }
                return currSubList;
            }

            @Override
            public boolean hasNext() {
                return hasNextEntry;
            }
        };
    }
}
