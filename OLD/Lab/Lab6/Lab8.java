import java.io.*;
import java.util.*;

/**
 * Fast I/O
 * 
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
class FastIO extends PrintWriter {
    BufferedReader br;
    StringTokenizer st;

    public FastIO() {
        super(new BufferedOutputStream(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

public class Lab8 {
    public static boolean isTriangle(int row, int[][] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[row][i] == 1) {
                for (int j = i + 1; j < nums.length; j++)
                    if (nums[row][j] == 1 && nums[i][j] == 1)
                        return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        FastIO io = new FastIO();

        while (true) {
            int n = io.nextInt();

            if (n == -1)
                break;

            int[][] nums = new int[n][n];

            for (int row = 0; row < n; row++) {
                for (int column = 0; column < n; column++) {
                    nums[row][column] = io.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                if (isTriangle(i, nums))
                    continue;
                else
                    System.out.print(i + " ");
            }

            System.out.println();
        }

        io.close();
    }
}
