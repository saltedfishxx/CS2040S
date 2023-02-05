// import java.io.*;
// import java.util.*;

// /**
//  * Fast I/O
//  * 
//  * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
//  */
// class FastIO extends PrintWriter {
//     BufferedReader br;
//     StringTokenizer st;

//     public FastIO() {
//         super(new BufferedOutputStream(System.out));
//         br = new BufferedReader(new InputStreamReader(System.in));
//     }

//     String next() {
//         while (st == null || !st.hasMoreElements()) {
//             try {
//                 st = new StringTokenizer(br.readLine());
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//         return st.nextToken();
//     }

//     int nextInt() {
//         return Integer.parseInt(next());
//     }

//     long nextLong() {
//         return Long.parseLong(next());
//     }

//     double nextDouble() {
//         return Double.parseDouble(next());
//     }

//     String nextLine() {
//         String str = "";
//         try {
//             str = br.readLine();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         return str;
//     }
// }

// class Land {
//     int row;
//     int col;

//     public Land(int r, int c) {
//         row = r;
//         col = c;
//     }
// }

public class lab9 {

    static int row, col;
    static char[][] plots;
    static boolean[][] isConsist;
    // Direction vectors
    static int dRow[] = { 0, 0, -1, 1 };
    static int dCol[] = { -1, 1, 0, 0 };

    public static void bFirstSearch(Land plot) {
        Queue<Land> path = new ArrayDeque<>();
        path.offer(plot);
        while (!path.isEmpty()) {
            Land curPlot = path.poll();
            //if plot is already a land, skip
            if (isConsist[curPlot.row][curPlot.col])
                continue;
            isConsist[curPlot.row][curPlot.col] = true;
            for (int i = 0; i < dRow.length; i++) {
                int r = curPlot.row + dRow[i];
                int c = curPlot.col + dCol[i];
                // if plot is water or out of bounds, skip queue
                if (r < 0 || c < 0 || r >= row || c >= col || plots[r][c] == 'W')
                    continue;
                path.offer(new Land(r, c));
            }
        }

    }

    public static void main(String[] args) {
        FastIO io = new FastIO();

        row = io.nextInt();
        col = io.nextInt();
        plots = new char[row][col];
        isConsist = new boolean[row][col];

        Queue<Land> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            String plotRow = io.nextLine();

            for (int j = 0; j < col; j++) {
                plots[i][j] = plotRow.charAt(j);
                if (plots[i][j] == 'L')
                    queue.offer(new Land(i, j));
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            Land currentPlt = queue.poll();
            if (isConsist[currentPlt.row][currentPlt.col])
                continue;
            bFirstSearch(currentPlt);
            count++;
        }
        System.out.println(count);
        io.close();
    }

}
