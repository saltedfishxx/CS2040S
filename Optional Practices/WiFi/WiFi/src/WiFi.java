import java.util.Arrays;

class WiFi {

    /**
     * Implement your solution here
     */
    public static double computeDistance(int[] houses, int numOfAccessPoints) {
        Arrays.sort(houses);
        double start = 0;
        //maximum radius r to cover
        double end = houses[houses.length-1];

        //binary search to find the minimum radius to cover the houses
        while(end-start > 0.5) {
            double mid = start + (end - start)/2;
            if(coverable(houses, numOfAccessPoints, mid))
                end = mid;
            else
                start = mid;
        }
        return start;
    }

    /**
     * Implement your solution here
     */
    public static boolean coverable(int[] houses, int numOfAccessPoints, double distance) {
        Arrays.sort(houses);
        double d = distance*2;
        int a = 1;

        //1st condition: if total distance covered by access points is more than the total house range, instantly coverable
        if(numOfAccessPoints * d >= houses[houses.length-1]) return true;
        //2nd condition: each iteration, assign house to access point A until the house is out of range, then add new A
        //and next few houses will be assigned to A+1 and so forth
        int start = houses[0];

        for (int house : houses) {
            if (house > d + start) {
                a += 1;
                start = house;
            }
        }
        //false if a is more than given access points
        return a <= numOfAccessPoints;
    }
}
