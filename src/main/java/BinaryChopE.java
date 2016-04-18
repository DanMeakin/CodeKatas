import java.util.ArrayList;
import java.util.List;

/**
 * A recursive, tuple-based implementation.
 */
public class BinaryChopE implements BinaryChop {

    class Tuple<T> {
        private final T a;
        private final T b;

        public Tuple(T a, T b) {
            this.a = a;
            this.b = b;
        }

        /**
         * Gets the first value in the tuple.
         *
         * @return the first value in the tuple
         */
        public T fst() {
            return a;
        }

        /**
         * Gets the second value in the tuple.
         *
         * @return the second value in the tuple
         */
        public T snd() {
            return b;
        }
    }

    public int chop(int target, int[] intArray) {
        List<Tuple<Integer>> ts = makeTupleList(intArray);
        return findTargetInTuples(target, ts);
    }

    private int findTargetInTuples(Integer target, List<Tuple<Integer>> ts) {
        int midIndex = ts.size() / 2;
        if (ts.size() == 0) {
            return -1;
        }

        Tuple<Integer> midTuple = ts.get(midIndex);
        List<Tuple<Integer>> sublist = new ArrayList<>();
        if (target.equals(midTuple.snd())) {
            return midTuple.fst();
        } else if (target > midTuple.snd()) {
            sublist = ts.subList(midIndex+1, ts.size());
        } else if (target < midTuple.snd()) {
            sublist = ts.subList(0, midIndex);
        }
        return findTargetInTuples(target, sublist);
    }

    /**
     * Creates a list of Tuples from an array of ints.
     *
     * In each tuple, the nth entry in the list consists of a pair of numbers,
     * where the second value is the nth entry in the passed int array, and
     * the first value is the original index of the array from which the second
     * value is extracted.
     *
     * @param arr int array from which to create list of tuples
     * @return list of tuples
     */
    private List<Tuple<Integer>> makeTupleList(int[] arr) {
        List<Tuple<Integer>> tupleList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            Tuple<Integer> t = new Tuple<>(i, arr[i]);
            tupleList.add(t);
        }
        return tupleList;
    }
}
