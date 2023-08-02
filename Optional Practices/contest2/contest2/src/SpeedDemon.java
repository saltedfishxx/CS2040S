import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class SpeedDemon {

    /**
     * Returns the number of pairs of entries containing an identical multiset,
     * as described in the pdf.
     *
     * @param filename name of the file containing the input
     * @return number of pairs
     */
    public int processData(String filename) {
        int numPairs = 0;
        Map<Integer, Integer> multisetCounts = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            // Read the first line which contains the number of entries
            String line = br.readLine();
            int n = Integer.parseInt(line);

            // Loop through the remaining lines and process each entry
            for (int i = 0; i < n; i++) {
                line = br.readLine().trim();

                // Count the frequency of each character in the entry
                int[] charCounts = new int[94];
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    charCounts[c - 33]++;
                }

                // Convert the character counts to a string representation
                int multiset = getHashCode(charCounts, line);

                // Increment the count for this multiset
                int count = multisetCounts.getOrDefault(multiset, 0);
                numPairs += count;
                multisetCounts.put(multiset, count + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numPairs;
    }
    private int getHashCode(int[] arr, String line) {
        int hash = 7;
        for (int j : arr) {
            hash += (j * 16777619) ^ line.hashCode();
        }
        return hash;
    }
        // DO NOT EDIT this method for contest submission, as it will used by the grader
    public static void main(String[] args) {
        SpeedDemon dataProcessor = new SpeedDemon();
        int answer = dataProcessor.processData(args[0]);    // Expects input file name as CLI argument
        System.out.println(answer);
    }
}
