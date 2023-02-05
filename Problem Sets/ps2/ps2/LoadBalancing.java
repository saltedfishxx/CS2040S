/**
 * Contains static routines for solving the problem of balancing m jobs on p processors
 * with the constraint that each processor can only perform consecutive jobs.
 */
public class LoadBalancing {

    /**
     * Checks if it is possible to assign the specified jobs to the specified number of processors such that no
     * processor's load is higher than the specified query load.
     *
     * @param jobSizes the sizes of the jobs to be performed
     * @param queryLoad the maximum load allowed for any processor
     * @param p the number of processors
     * @return true iff it is possible to assign the jobs to p processors so that no processor has more than queryLoad load.
     */
    public static boolean isFeasibleLoad(int[] jobSizes, int queryLoad, int p) {
        // TODO: Implement this
        int totalJobSize = 0;
        int i = 0;
        int end = jobSizes.length-1;
        if(jobSizes.length < 1 || queryLoad < 1 || p < 1) //invalid input
            return false;
        while(i <= end) {
            //if there exist a single job that is more than queryLoad, it is instantly impossible to assign
            if(jobSizes[i] > queryLoad) return false;
            else{
                //assume jobs are added in sequence to a processor, if assigned jobs > queryLoad, split and assign
                //remaining to next processor
                totalJobSize =+ totalJobSize + jobSizes[i];
                if(totalJobSize > queryLoad) {
                    p--;
                    totalJobSize = jobSizes[i];
                }
            }
            i++;
        }
        //if processors are not negative, it is feasible
        return p > 0;
    }

    /**
     * Returns the minimum achievable load given the specified jobs and number of processors.
     *
     * @param jobSizes the sizes of the jobs to be performed
     * @param p the number of processors
     * @return the maximum load for a job assignment that minimizes the maximum load
     */
    public static int findLoad(int[] jobSizes, int p) {
        // TODO: Implement this
        if(jobSizes.length < 1 || p < 1)
            return -1;
        int start = 0;
        //we are finding feasible load/job size as opposed to number of jobs assigned, hence total job size is used here
        int end = sumJobSize(jobSizes);
        while(start < end) {
            //using binary search approach, we find optimal load
            int mid = start + (end - start)/2;
            if(isFeasibleLoad(jobSizes, mid, p))
                end = mid; //if load is feasible, try if load can be further reduced
            else start = mid + 1; //else increase load until it is feasible
        }
       return start;
    }

    private static int sumJobSize(int[] jobSizes) {
        int sum = 0;
        for (int jobSize : jobSizes) {
            sum += jobSize;
        }
        return sum;
    }

    // These are some arbitrary testcases.
    public static int[][] testCases = {
            {1, 3, 5, 7, 9, 11, 10, 8, 6, 4},
            {67, 65, 43, 42, 23, 17, 9, 100},
            {4, 100, 80, 15, 20, 25, 30},
            {2, 3, 4, 5, 6, 7, 8, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89, 88, 87, 86, 85, 84, 83},
            {7}
    };

    /**
     * Some simple tests for the findLoad routine.
     */
    public static void main(String[] args) {
        for (int p = 1; p < 30; p++) {
            System.out.println("Processors: " + p);
            for (int[] testCase : testCases) {
                System.out.println(findLoad(testCase, p));
            }
        }
    }
}
