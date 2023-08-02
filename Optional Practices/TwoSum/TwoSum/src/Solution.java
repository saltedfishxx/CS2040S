import java.util.HashMap;

public class Solution {
    public static int solve(int[] arr, int target) {
        // TODO: Implement your solution here
        HashMap<Integer, Integer> seen = new HashMap<>();
        int pairs = 0;
        if(arr.length > 0) {
            for (int num : arr) {
                int diff = target - num; //get the difference from sum
                int diffValue = seen.get(diff) == null ? 0 : seen.get(diff);
                if (seen.containsKey(diff) && diffValue > 0) {
                    //if map contains complement/difference, and there is a unique value available, means we found a pair
                    pairs++;
                    seen.put(diff, diffValue-1);
                }else {
                    seen.put(num, seen.getOrDefault(num, 0) + 1);
                }
            }
        }
        return pairs;
    }
}
