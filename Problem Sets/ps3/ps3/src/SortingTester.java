import java.util.Random;

public class SortingTester {
    static boolean debugging = true; //debugging to show print statements
    static Watch w = new Watch();
    public static boolean checkSort(ISort sorter, int size) {
        // TODO: implement this
        KeyValuePair[] test = createRandomArr(size);
        boolean result = true;
        sorter.sort(test);
        for(int i = 0; i < size-1; i++){
            //if left element is bigger than right element, array is not sorted
            if(test[i].getKey() > test[i+1].getKey()) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isStable(ISort sorter, int size) {
        // TODO: implement this
        KeyValuePair [] arr = createDupArr(size);
        sorter.sort(arr);
        boolean result = true;
        //given the same keys, if value of left key is bigger than right key, sorter is not stable
        for (int i = 0; i < size - 1; i++) {
                if (arr[i].getValue() > arr[i + 1].getValue()) {
                result = false;
                break;
            }
        }
        return result;
    }

    ////////////Helper methods///////////////
    public static void testHelper(ISort sorter, int size, KeyValuePair[] randomArr, KeyValuePair[] ascArr, KeyValuePair[] descArr) {
        long cost = 0;
        if(debugging) System.out.println("TEST 1");
        //TEST 1
        boolean isSorted = checkSort(sorter, size);
        //if random array is sorted correctly by comparing key order
        if(debugging) System.out.println("Array sorted: " + isSorted);

        //TEST 2
        //Check time elapsed for random array
        w.start();
        cost = sorter.sort(randomArr);
        w.stop();
        if(debugging) System.out.println("TEST 2\nTime taken to sort random keys with size " + size + ": "
                + w.getTime() + " and cost of " + cost + " units.");

        //Check time elapsed for ascending random array
        w.reset();
        w.start();
        cost = sorter.sort(ascArr);
        w.stop();
        if(debugging) System.out.println("Time taken to sort ascending keys with size " + size + ": "
                + w.getTime() + " and cost of " + cost + " units.");

        //Check time elapsed for descending random array
        w.reset();
        w.start();
        cost = sorter.sort(descArr);
        w.stop();
        if(debugging) System.out.println("Time taken to sort descending keys with size " + size + ": "
                + w.getTime() + " and cost of " + cost + " units.");

        //TEST 3
        //Check if sort is stable
        boolean isStable = isStable(sorter, size);
        if(debugging) System.out.println("TEST 3\nSort is stable: " +  isStable);

        //TEST 4
        //Check if running sort and stable 50 times will return 1 false statement
        //if sort returns 1 false, sort is Dr Evil, else if stable returns 1 false, sort is Quick Sort or Selection Sort
        boolean sortable = true;
        for (int i = 0; i < 50; i++) {
            if (!(checkSort(sorter, 10000))) {
                sortable = false;
                break;
            }
        }
        boolean stable = true;
        for (int i = 0; i < 50; i++) {
            if (!(isStable(sorter, 1000))) {
                stable = false;
                break;
            }
        }
        if(debugging) {
            System.out.println("TEST 4\nRunning sort 50 times, sortable result is: " + sortable);
            System.out.println("Running sort 50 times, stable result is: " + stable);
        }


    }

    public static KeyValuePair[] createDupArr(int length) {
        KeyValuePair[] arr = new KeyValuePair[length];
        for(int i = 0; i < length - 2; i++){
            arr[i] = new KeyValuePair(2, i + 2);
        }
        arr[length-2] = new KeyValuePair(1, 1);
        arr[length-1] = new KeyValuePair(1, 2);

        return  arr;
    }

    public static KeyValuePair[] createRandomArr(int length) {
        Random r = new Random(1);
        KeyValuePair[] arr = new KeyValuePair[length];
        for(int i = 0; i < length; i++) {
            arr[i] = new KeyValuePair(r.nextInt(), r.nextInt());
        }
        return arr;
    }

    public static KeyValuePair[] createAscArr (int length) {
        KeyValuePair[] arr = new KeyValuePair[length];
        for(int i = 0; i < length; i++){
            arr[i] = new KeyValuePair(i,i);
        }
        return arr;
    }

    public static KeyValuePair[] createDescArr (int length) {
        KeyValuePair[] arr = new KeyValuePair[length];
        for(int i = length-1; i >= 0; i--){
            arr[i] = new KeyValuePair(i,i);
        }
        return arr;
    }

    /////////////MAIN METHOD//////////////////
    public static void main(String[] args) {
        // TODO: implement this
        ISort [] sorters = new ISort[] {new SorterC()};
        //ISort [] sorters = new ISort[] {new SorterA(), new SorterB(), new SorterD(), new SorterE(), new SorterF()};
        int[] sizes = new int[]{100, 1000, 10000};

        for(ISort sorter : sorters){
            if(debugging) System.out.println("============ Begin testing for " + sorter + " ==============");
            for(int n: sizes) {

                testHelper(sorter, n, createRandomArr(n), createAscArr(n), createDescArr(n));
            }
        }
    }
}
