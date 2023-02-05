class InversionCounter {

    public static long countSwaps(int[] arr) {
        return mergeSort(arr, new int[arr.length], 0, arr.length - 1);
    }

    /**
     * Given an input array so that arr[left1] to arr[right1] is sorted and arr[left2] to arr[right2] is sorted
     * (also left2 = right1 + 1), merges the two so that arr[left1] to arr[right2] is sorted, and returns the
     * minimum amount of adjacent swaps needed to do so.
     */
    public static long mergeAndCount(int[] arr, int left1, int right1, int left2, int right2) {
        return merge(arr, new int[arr.length], left1, right1, left2, right2);
    }

    public static long mergeSort(int[] arr, int[] temp, int start, int end) {
        if(start >= end)
            return 0;
        long count = 0;
        int mid = start + (end - start)/2;

        count += mergeSort(arr, temp, start, mid);
        count += mergeSort(arr, temp,mid + 1, end);
        count += merge(arr, temp, start, mid, mid + 1, end);
        return count;
    }

    public static long merge(int[] arr, int[] temp, int left1, int right1, int left2, int right2) {
        long count = 0;
        int start = left1;
        int currIndex = start;
        while (left1 <= right1 && left2 <= right2) {
            if(arr[left1] <= arr[left2])
                temp[currIndex++] = arr[left1++];
            else {
                temp[currIndex++] = arr[left2++];
                count += (right1 - (left1 - 1));
            }
        }
        while(left1 <= right1)
            temp[currIndex++] = arr[left1++];
        while(left2 <= right2)
            temp[currIndex++] = arr[left2++];

        //copy back to original array
        if (right2 + 1 - start >= 0) System.arraycopy(temp, start, arr, start, right2 + 1 - start);
        return count;

    }



}
