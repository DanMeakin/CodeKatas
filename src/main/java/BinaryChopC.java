import java.util.ArrayList;
import java.util.List;

/**
 * A super-Java implementation (horribly inefficient)
 */
public class BinaryChopC implements BinaryChop {

    class ChopSlice<T extends Comparable<T>> {

        private List<T> slice;
        private T targetValue;

        /* The offset from the original array of this slice */
        private int initialOffset;

        public ChopSlice(List<T> slice, T target, int offset) {
            this.slice = slice;
            this.initialOffset = offset;
            this.targetValue = target;
        }

        public ChopSlice(List<T> slice, T target) {
            this.slice = slice;
            this.initialOffset = 0;
            this.targetValue = target;
        }

        public ChopSlice<T> nextSlice() {
            int offset = initialOffset + getNextSliceOffset();
            List<T> slice = getSlice().subList(
                    getNextSliceStart(),
                    getNextSliceEnd()
            );
            return new ChopSlice(slice, targetValue, offset);
        }

        public boolean hasNextSlice() {
            return !(
                    getSliceSize() <= 1 ||
                    hasFoundTarget() ||
                    getNextSliceStart() == getNextSliceEnd()
            );
        }

        public int finalResult() {
            if (getSliceSize() == 0) {
                return -1;
            } else if (hasFoundTarget()) {
                return this.initialOffset + this.getMidIndex();
            } else {
                return -1;
            }
        }

        private boolean hasFoundTarget() {
            return (targetValue.equals(getMidValue()));
        }

        private int getNextSliceStart() {
            if (isHeadNext()) {
                return 0;
            } else if (isTailNext()) {
                if (isEven(getSliceSize())) {
                    return getMidIndex() + 1;
                } else {
                    return getMidIndex() + 1;
                }
            }
            String msg = "no further slice to get!";
            throw new UnsupportedOperationException(msg);
        }

        private int getNextSliceEnd() {
            if (isTailNext()) {
                return getSliceSize();
            } else if (isHeadNext()) {
                return getMidIndex();
            }
            String msg = "no further slice to get!";
            throw new UnsupportedOperationException(msg);
        }

        private boolean isHeadNext() {
            if (getTargetValue().compareTo(getMidValue()) < 0) {
                return true;
            } else {
                return false;
            }
        }

        private boolean isTailNext() {
            if (getTargetValue().compareTo(getMidValue()) > 0) {
                return true;
            } else {
                return false;
            }
        }

        private T getMidValue(){
            return getSlice().get(getMidIndex());
        }

        private int getMidIndex() {
            return getSlice().size() / 2;
        }

        private List<T> getSlice() {
            return slice;
        }

        private int getSliceSize() {
            return getSlice().size();
        }

        private T getTargetValue() {
            return targetValue;
        }

        private int getNextSliceOffset() {
            if (isHeadNext()) {
                return 0;
            } else if (isTailNext()) {
                return getMidIndex() + 1;
            } else {
                String msg = "no further slice to take";
                throw new UnsupportedOperationException(msg);
            }
        }

        private boolean isEven(int n) {
            return n / 2 == (double) n / 2;
        }
    }

    public int chop(int target, int[] intArray){
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < intArray.length; i++) {
            intList.add(intArray[i]);
        }
        ChopSlice<Integer> cs = new ChopSlice<>(intList, target);
        while (cs.hasNextSlice()) {
            cs = cs.nextSlice();
        }
        return cs.finalResult();
    }

}
