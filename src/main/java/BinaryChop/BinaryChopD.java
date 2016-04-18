package BinaryChop;

import java.util.*;

/**
 * An iterator-based implementation
 */
public class BinaryChopD implements BinaryChop {

    private int[] intArray;
    private int target;

    /**
     * Carries out the binary chop.
     *
     * @param target the target value
     * @param intArray the array to search/chop
     * @return the index of target within intArray, or -1 if not present
     */
    public int chop(int target, int[] intArray) {
        this.intArray = intArray;
        this.target = target;
        return getEndPoint();

    }

    /**
     * Finds the final index for the target within intArray.
     *
     * This method returns an integer representing the index of target within
     * the intArray. If target is not found, this method returns -1.
     *
     * @return index of target within intArray, or -1 if not found
     */
    private int getEndPoint() {
        List<Integer> indices = new ArrayList<>();
        Iterator<List<Integer>> iterator = listIterator();

        while (iterator.hasNext()) {
            indices = iterator.next();
        }
        boolean notFound = indices.size() == 0
                || indices.get(0) >= intArray.length
                || target != intArray[indices.get(0)];
        if (notFound) {
            return -1;
        } else {
            return indices.get(0);
        }
    }

    /**
     * Creates an iterator which operates over a list of indices to values in
     * intArray, iteratively carrying-out a binary search for the index of the
     * target value.
     *
     * @return iterator which iterates through the list of indices towards the
     *         target value
     */
    private Iterator<List<Integer>> listIterator() {
        List<Integer> l = intArrayToList();

        return new Iterator<List<Integer>>() {
            private List<Integer> currSubList = l;
            private boolean hasNextEntry = l.size() > 0;

            @Override
            public List<Integer> next() {
                // Get the middle index of the current sub-list
                int midPoint = currSubList.size() / 2;
                // Get the index of the intArray represented by the value at the
                // current middle index.
                int midIndex = currSubList.get(midPoint);
                // Get the value of the intArray at midIndex
                int midValue = intArray[midIndex];

                if (target == midValue) {
                    currSubList = Arrays.asList(new Integer[]{midIndex});
                } else if (target < midValue) {
                    currSubList = currSubList.subList(0, midPoint);
                } else if (target > midValue) {
                    if (currSubList.size() <= midPoint + 1) {
                        currSubList = new ArrayList<>();
                    } else {
                        currSubList = currSubList.subList(midPoint + 1, currSubList.size());
                    }
                }
                if (currSubList.size() <= 1) {
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

    /**
     * Converts the intArray to a list of Integers.
     *
     * @return intArray as list of Integers
     */
    private List<Integer> intArrayToList() {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < intArray.length; i++) {
            l.add(i);
        }
        return l;
    }
}
