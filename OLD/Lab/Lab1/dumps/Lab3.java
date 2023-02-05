package dumps;
import java.io.*;
import java.util.*;

import FastIO;

/**
 * Fast I/O
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
// class FastII extends PrintWriter { 
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

class CompareFirstTwoComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        return a.substring(0,2).compareTo(b.substring(0,2));
    }
}

public class Lab3 {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        int n = 1;
        while (n != 0) {
            int noCases = io.nextInt();
            if(noCases == 0){
                break;
            }else {
                ArrayList<String> names = new ArrayList<String>();
                for (int i = 0; i < noCases; i++) {
                    String name = io.nextLine();
                    names.add(name);
                }
    
                Collections.sort(names, new CompareFirstTwoComparator());
                for (String num : names) { 		      
                    System.out.println(num); 		
                }
                System.out.print("\n");
            }
        }
        io.close();
    }
}
