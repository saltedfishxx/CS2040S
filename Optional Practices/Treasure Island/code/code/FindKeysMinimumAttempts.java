import java.util.Arrays;

public class FindKeysMinimumAttempts implements IFindKeys {

    //an array that contains indexes of current identified keys, used to test unlock the chest
    private int[] keysFound;
    @Override
    public int[] findKeys(int N, int k, ITreasureExtractor treasureExtractor) {
        // TODO: Problem 2 -- Implement strategy to find correct keys with minimum cost (least number of keys used in total across all attempts)
        this.keysFound = new int[k];
        Arrays.fill(this.keysFound, -1);
        //final array of keys containing the correct set of keys to open the chest
        int[] finalKeyMap = new int[N];
        int start = 0;
        int end = N-1;
        //upper and lower bound depending on whether keys were found
        int endPointer;
        int startPointer = start;
        int found = 0;

        //keep looping until all correct key indexes are found
        while(this.keysFound[this.keysFound.length-1] == -1) {
            if(found > 0) {
                //if 1st key has been found, change end pointer to search from left side of found key first
                end = startPointer-1;
            }
            endPointer = end;
            startPointer = start;
            //binary search to find a correct key, starting from upper bound to lower bound
            while (startPointer < endPointer) {
                int mid = startPointer + (endPointer - startPointer)/2;
                //test range is always from 0-mid as if keys are found using 0-mid + existing found key locations,
                //upper bound is lowered to verify left side of the array for exact key location
                if(testKeys(treasureExtractor, N, start, mid))
                    endPointer = mid;
                else
                    startPointer = mid+1;
            }
            this.keysFound[found] = startPointer;
            finalKeyMap[startPointer] = 1;
            found++;
        }
        return finalKeyMap;
    }

    /**
     * Tests the set of keys in the lower and upper bound along with any identified keys
     * @param test
     * @param n
     * @param start
     * @param end
     * @param k
     * @return boolean test
     */
    public boolean testKeys(ITreasureExtractor test, int n, int start, int end) {
        int[] keysTest = new int[n]; //prepare new array of size N
        for(int i = start; i <= end; i++) {
            keysTest[i] = 1; //assign indexes sending out to test as 1 from specified start/end range
        }
        //check if previously found keys happened, assign them to array as well
        for(int i: this.keysFound) {
            if(i != -1) {
                keysTest[i] = 1;
            }
        }
        return test.tryUnlockChest(keysTest);
    }
}
