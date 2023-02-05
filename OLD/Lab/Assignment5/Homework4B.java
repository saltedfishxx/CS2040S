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

class Pair<T1, T2> {
    public T1 first;
    public T2 second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T1 firstValue, T2 secondValue) {
        first = firstValue;
        second = secondValue;
    }

    public Pair(Pair<T1, T2> pairToCopy) {
        first = pairToCopy.first;
        second = pairToCopy.second;
    }

}


public class Homework4B {

    private static int find(ArrayList<Integer> d, int a) {
        if (d.get(a) == -1) {
            return a;
        }

        d.set(a, find(d, d.get(a)));
        return d.get(a);
    }

    static void join(ArrayList<Integer> d, int a, int b) {
        a = find(d, a);
        b = find(d, b);

        if (a == b) {
            return;
        }

        d.set(a, b);
    }

    static void depthFS(ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> vis, Stack<Integer> r, int curr) {
        vis.add(curr);
        for (var i : adj.get(curr)) {
            int countA = Collections.frequency(vis, i);
            if (countA == 0) {
                vis.add(i);
                depthFS(adj, vis, r, i);
            }
        }

        r.push(curr);
    }

    static void depthFS2(ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> vis, Stack<Integer> r, ArrayList<Integer> dis,
            int curr) {
        for (var i : adj.get(curr)) {
            int countA = Collections.frequency(vis, i);
            if (countA == 0) {
                join(dis, curr, i);
                vis.add(i);
                depthFS2(adj, vis, r, dis, i);
            }
        }
    }

    public static void main(String[] args) {
        FastIO io = new FastIO();
        int c = io.nextInt();
        for (int i = 0; i < c; i++) {
            int n = io.nextInt();
            int m = io.nextInt();

            ArrayList<ArrayList<Integer>> adj1 = new ArrayList<ArrayList<Integer>>(n);
            ArrayList<ArrayList<Integer>> adj2 = new ArrayList<ArrayList<Integer>>(n);
            for(int j=0; j < n; j++) {
                adj1.add(new ArrayList<Integer>());
                adj2.add(new ArrayList<Integer>());
            }
            ArrayList<Pair<Integer, Integer>> edges = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                int n1 = io.nextInt();
                int n2 = io.nextInt();
                n1--;
                n2--;
                adj1.get(n1).add(n2);
                adj2.get(n2).add(n1);
                edges.add(new Pair<Integer, Integer>(n1, n2));

            }
            
            ArrayList<Integer> visited = new ArrayList<>();
            Stack<Integer> recent = new Stack<>();
            for (int k = 0; k < n; k++) {
                int countA = Collections.frequency(visited, k);
                if (countA > 0) {
                    continue;
                }

                depthFS(adj1, visited, recent, k);
            }

            ArrayList<Integer> dis = new ArrayList<Integer>(Collections.nCopies(n, -1));
            Map<Integer, Integer> deg = new HashMap<Integer, Integer>();
            visited.clear();
            while (!recent.empty()) {
                int curr = recent.peek();
                recent.pop();
                visited.add(curr);

                depthFS2(adj2, visited, recent, dis, curr);
            }

            // Build "Metagraph"
            for (int l = 0; l < dis.size(); l++) {
                deg.put(find(dis, l), 0);
            }

            // Find degrees
            for (var e : edges) {
                if (find(dis, e.first) != find(dis, e.second)) {
                    int newValue = deg.get(find(dis, e.second)) + 1;
                    deg.replace(dis.get(e.second), newValue);
                }
            }

            // Find degree 0
            int total = 0;
            for (int d : deg.values()) {
                if (d == 0) {
                    total++;
                }
            }

            // Print answer
            System.out.print(total);
            System.out.print("\n");
            io.close();
        }

    }

}
