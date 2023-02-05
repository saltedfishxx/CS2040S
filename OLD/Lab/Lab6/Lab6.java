import java.io.*;
import java.util.*;

/**
 * Fast I/O
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
// class FastIO extends PrintWriter { 
//     BufferedReader br; 
//     StringTokenizer st;

//     public FastIO() 
//     { 
//         super(new BufferedOutputStream(System.out)); 
//         br = new BufferedReader(new
//                 InputStreamReader(System.in));
//     } 

//     String next() 
//     { 
//         while (st == null || !st.hasMoreElements()) 
//         { 
//             try
//             { 
//                 st = new StringTokenizer(br.readLine()); 
//             } 
//             catch (IOException  e) 
//             { 
//                 e.printStackTrace(); 
//             } 
//         } 
//         return st.nextToken(); 
//     } 

//     int nextInt() 
//     { 
//         return Integer.parseInt(next()); 
//     } 

//     long nextLong() 
//     { 
//         return Long.parseLong(next()); 
//     } 

//     double nextDouble() 
//     { 
//         return Double.parseDouble(next()); 
//     } 

//     String nextLine() 
//     { 
//         String str = ""; 
//         try
//         { 
//             str = br.readLine(); 
//         } 
//         catch (IOException e) 
//         { 
//             e.printStackTrace(); 
//         } 
//         return str; 
//     } 
// }

class Lab6 {

    public static int save(Queue<Integer> arrivals, Queue<Integer> departures,int m) {
        int saves = 0;
        while (!arrivals.isEmpty()) {
            int arrival = arrivals.poll();
            while (arrival - departures.peek() > m) {
                departures.poll();
            }
            if (departures.peek() <= arrival) {
                departures.poll();
                saves += 1;
            }
        }

        return saves;
    }

    

    public static void main(String[] args) {

        FastIO io = new FastIO();
        int n = io.nextInt();
        int m = io.nextInt();
        Queue<Integer> arrivals = new PriorityQueue<>();
        Queue<Integer> departures = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int a = io.nextInt();
            int b = io.nextInt();
            arrivals.add(a);
            departures.add(a+b);

        }

        int saves = save(arrivals, departures, m);
        System.out.println(saves);
        io.close();
    }
}