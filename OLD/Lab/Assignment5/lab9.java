import java.io.*;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

class Item implements Comparable<Item> {
    int adjV, u, v;

    public Item (int adjV, int u, int v) {
        this.adjV = adjV;
        this.u = u;
        this.v = v;
    }

    @Override
    public int compareTo(Item o) {
        // TODO Auto-generated method stub
        int result = 0;
        if(this.adjV != o.adjV) {
            result = this.adjV - o.adjV;
        } 
        else if (this.u != o.u) {
            result = this.u - o.u;
        } else {
            result = this.v - o.v;
        }
        return result;
        

        
    }


}
public class lab9 {

    public static void main(String[] args) {

        FastIO io = new FastIO();
        int n = io.nextInt();
        int[][] adj = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                adj[i][j] = io.nextInt();
            }
        }


        List<Integer> best = new ArrayList<>(Collections.nCopies(n, 0));
        List<Integer> remaining = IntStream.range(1, n).boxed().collect(Collectors.toList());
        List<Boolean> visited = new ArrayList<>(Collections.nCopies(n-1, false));
        visited.add(0, true);
        Queue<Item> heap = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < n; i++) {
            Item item = new Item(adj[0][i], 0, i);
            heap.add(item);
            best.set(i, adj[0][i]);
        }

        int edges = 0;
        while (edges < n-1) {
            int u = 0;
            int v = 0;
            while (true) {
                Item tempp = heap.poll();
                int adjValue = tempp.adjV;
                u = tempp.u;
                v = tempp.v;
                if(visited.get(v))
                    continue;
                break;
            }
            int a = u+1;
            int b = v+1;
            sb.append(a + " " + b + "\n");
            edges++;
            visited.set(v, true);
            remaining.remove(Integer.valueOf(v));
            for (int w : remaining) {
                if(adj[v][w] < best.get(w)) {
                    best.set(w, adj[v][w]);
                    Item temp = new Item(adj[v][w], v, w);
                    heap.add(temp);
                }
            }
            
        }

        System.out.println(sb);
        io.close();
    }

}