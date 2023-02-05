import java.util.Arrays;

/**
 * The Optimization class contains a static routine to find the maximum in an array that changes direction at most once.
 */
public class Optimization {

    /**
     * A set of test cases.
     */
    static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, -100, -80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83}
    };


    /**
     * Returns the maximum item in the specified array of integers which changes direction at most once.
     *
     * @param dataArray an array of integers which changes direction at most once.
     * @return the maximum item in data Array
     */
    public static int searchMax(int[] dataArray) {
        // TODO: Implement this
        int size = dataArray.length;
        int start = 0;
        int end = dataArray.length-1;

        if(size == 0) //invalid array
            return 0;
        if(size == 1)
            return dataArray[0];
        //if value in array decreases then increases, just compare the max of both edge of the array
        if(dataArray[0] > dataArray[1])
            return Math.max(dataArray[0], dataArray[size-1]);
        //if value in array increases then decrease, use binary and recurse to find max element
        else {
            while (start < end) {
                int mid = start + (end - start) / 2;
                //if right element is bigger than mid, search right side of element
                if (dataArray[mid] < dataArray[mid + 1])
                    start = mid + 1;
                else //else search left side of element
                    end = mid;
            }
        }
        return dataArray[start];
    }



    /**
     * A routine to test the searchMax routine.
     */
    public static void main(String[] args) {
        for (int[] testCase : testCases) {
            System.out.println(searchMax(testCase));
        }
    }
}
