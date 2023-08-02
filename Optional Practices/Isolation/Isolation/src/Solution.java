import java.util.HashMap;
import java.util.Hashtable;

public class Solution {
    // TODO: Implement your solution here
    public static int solve(int[] arr) {
        //hashmap to keep track of the last index at which each bottle type was seen
        HashMap<Integer, Integer> intervals = new HashMap<>();
        int currentInterval = 0; //start index of current interval of unique bottles
        int maxLength = 0;

        for(int i = 0; i < arr.length; i++) {
            int bottle = arr[i];
            if(intervals.containsKey(bottle) && intervals.get(bottle) >= currentInterval) {
                //bottle type already seen in current interval, start new interval with current bottle
                currentInterval = intervals.get(bottle) + 1;
            }
            intervals.put(bottle, i);

            maxLength = Math.max(maxLength, i - currentInterval + 1);
        }

        return maxLength;
    }
}