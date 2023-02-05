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


class AssignmentA {

    static StringBuilder sb = new StringBuilder();

    public static int find(int drawer, int parent[]) {
        int set = drawer;
        int temp = drawer;
        while (parent[set] != set)
            set = parent[set];
        while (parent[drawer] != set) {
            temp = parent[drawer];
            parent[drawer] = set;
            drawer = temp;
        }
        return set;
    }

    public static void unionSet(int d1, int d2, int[] parent) {
        int set1 = find(d1, parent);
        int set2 = find(d2, parent);
        parent[set1] = set2;
        if(set1 == set2) {
            parent[set2] = 0;
        }
        sb.append("LADICA\n");
    }

    public static void main(String[] args) {
        final int MAX = 300000;
        int[] parent = new int[MAX + 1];
        FastIO io = new FastIO();
        int n = io.nextInt();
        int l = io.nextInt();
        parent[0] = 0;
        for (int i = 1; i <= l; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            int a = io.nextInt();
            int b = io.nextInt();

            if (find(a, parent) == a) {
                unionSet(a, b, parent);
            } else if (find(b, parent) == b) {
                unionSet(b, a, parent);
            } else if (find(a, parent) > 0) {
                unionSet(a, b, parent);
            } else if (find(b, parent) > 0) {
                unionSet(b, a, parent);
            } else {
                sb.append("SMECE\n");
            }
        }
        System.out.println(sb);
        io.close();
    }
}
