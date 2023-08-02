import java.util.*;

public class MedianFinder {

    // TODO: Include your data structures here
    PriorityQueue<Integer> leftSet;
    PriorityQueue<Integer> rightSet;

    public MedianFinder() {
        // TODO: Construct/Initialise your data structures here
        leftSet = new PriorityQueue<>(Collections.reverseOrder());
        rightSet = new PriorityQueue<>();
    }

    public void insert(int x) {
        // TODO: Implement your insertion operation here
        //if number is less than left set or no numbers in set, add to left half
        if(leftSet.isEmpty() || x <= leftSet.peek()) {
            leftSet.add(x);
        } else if(rightSet.isEmpty() || x >= rightSet.peek()) {
            rightSet.add(x);
        } else {
            leftSet.add(x);
        }

        //balance the numbers such that first element of right half is always the median
        rebalance();
    }

    public void rebalance() {
        while(leftSet.size() > rightSet.size() + 1) {
            int n = leftSet.poll();
            rightSet.add(n);
        }
        while(rightSet.size() > leftSet.size() + 1) {
            int n = rightSet.poll();
            leftSet.add(n);
        }
    }

    public int getMedian() {
        // TODO: Implement your getMedian operation here
        int median;
        if(leftSet.size() <= rightSet.size()) {
            median = rightSet.poll();
        }
        else {
            median = leftSet.poll();
        }

        rebalance();
        return median;
    }


}
