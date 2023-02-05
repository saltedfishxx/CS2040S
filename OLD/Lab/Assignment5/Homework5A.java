import java.io.*;
import java.util.*;

/**
 * Fast I/O
 * 
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
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

class State implements Comparable<State> {
    public int x;
    public int y;
    public int val;

    public State(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(State o) {
        // TODO Auto-generated method stub
        if (this.val == o.val)
        {
            if (this.x == o.x)
            {
                return this.y < o.y ? -1 : 1;
            }
            return this.x < o.x ? -1 : 1;
        }
        return this.val > o.val ? 1 : -1;
    }

}

public class Homework5A {

    public static int[] dx = new int[]{1, -1, 0, 0};
    public static int[] dy = new int[]{0, 0, 1, -1};

    public static int n;
    public static int m;

    public static boolean inrange(int nextx, int nexty) {
        return nextx >= 0 && nexty >= 0 && nextx < n && nexty < m;
    }

    public static void main(String[] args) {
        FastIO io = new FastIO();

        n = io.nextInt();
        m = io.nextInt();

        int[][] val = new int[n][m];
        int[][] best = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                visited[i][j] = false;
                best[i][j] = Integer.MAX_VALUE;
                val[i][j] = io.nextInt();
            }
        }

        best[0][0] = 0;

        PriorityQueue<State> q = new PriorityQueue<>();
        q.offer(new State(0,0,0));
        while(!q.isEmpty()) {
            State curr = q.poll();
            int currx = curr.x;
            int curry = curr.y;
            
            if(visited[currx][curry])
                continue;
            visited[currx][curry] = true;

            for(int i = 0; i < 4; i++) {
                int nextx = currx + dx[i];
                int nexty = curry + dy[i];

                if(!inrange(nextx, nexty))
                    continue;
                int nextVal = Math.max(0, val[nextx][nexty] - val[currx][curry]);
                nextVal = Math.max(nextVal, best[currx][curry]);

                if(best[nextx][nexty] > nextVal) {
                    best[nextx][nexty] = nextVal;
                    q.offer(new State(nextx, nexty, nextVal));
                }
            }
        }
        System.out.println(best[n-1][m-1]);
        io.close();
    }

}
