package dumps;
import java.io.*;
import java.util.*;

import FastIO;

/**
 * Fast I/O
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
// class FastIO extends PrintWriter 
// { 
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

public class Lab1 {

    public static void main(String[] args) {
        FastIO io = new FastIO();
        int n = io.nextInt();
        String outputString = "Anywhere is fine I guess";

        for (int i = 0; i < n; i++) {
            int noOfItems = io.nextInt();
            String resName = io.nextLine();
            boolean peaSoup = false;
            boolean panCakes = false;

            for (int j = 0; j < noOfItems; j++) {
                String itemName = io.nextLine();
        
                if (itemName.equals("pancakes")) {
                    panCakes = true;
                }  
                else if (itemName.equals("pea soup")) {
                    peaSoup = true;
                }                  
            }

            if (panCakes && peaSoup) {
                outputString = resName;
                break;
            }
        }
        System.out.println(outputString);
        io.close();

    } //end main

}