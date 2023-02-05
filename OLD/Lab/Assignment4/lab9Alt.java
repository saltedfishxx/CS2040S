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

class Land {
    int row;
    int col;

    public Land(int r, int c) {
        row = r;
        col = c;
    }
}

public class lab9Alt {



    static int row, col;
    static int count = 0;
    static char[][] plots;
    static boolean[][] visited;

    public static void dFirstSearch(int row, int col, Land plot) {
        Stack<Land> path = new Stack<Land>();
        path.push(plot);
        while (!path.isEmpty()) {
            Land curPlot = path.pop();
            int curRow = curPlot.row;
            int curCol = curPlot.col;
            // if plot is not visited, add
            if (visited[curRow][curCol] == false) {
                visited[curRow][curCol] = true;
                //check if plots are land or cloud, add them to path
                if (curRow - 1 >= 0 && plots[curRow - 1][curCol] != 'W')
                    path.push(new Land(curRow - 1, curCol));
                if (curRow + 1 < row && plots[curRow + 1][curCol] != 'W')
                    path.push(new Land(curRow + 1, curCol));
                if (curCol - 1 >= 0 && plots[curRow][curCol - 1] != 'W')
                    path.push(new Land(curRow, curCol - 1));
                if (curCol + 1 < col && plots[curRow][curCol + 1] != 'W')
                    path.push(new Land(curRow, curCol + 1));

            }
        }

    }

    public static void main(String[] args) {

        FastIO io = new FastIO();
        row = io.nextInt();
        col = io.nextInt();
        plots = new char[row][col];
        visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            String plotRow = io.nextLine();
            for (int j = 0; j < col; j++) {
                plots[i][j] = plotRow.charAt(j);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Land plot = new Land(i, j);
                if (plots[i][j] == 'L' && visited[i][j] == false) {
                    dFirstSearch(row, col, plot);
                    count++;
                }

            }
        }

        System.out.println(count);
        io.close();
    }
}
