package dumps;
import java.io.*;
import java.util.*;

import FastIO;

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

public class Lab2 {
    public static void main(String[] args) {
        Map<Character,String> dict = new HashMap<>();
        dict.put('a', "2");
        dict.put('b', "22");
        dict.put('c', "222");
        dict.put('d', "3");
        dict.put('e', "33");
        dict.put('f', "333");
        dict.put('g', "4");
        dict.put('h', "44");
        dict.put('i', "444");
        dict.put('j', "5");
        dict.put('k', "55");
        dict.put('l', "555");
        dict.put('m', "6");
        dict.put('n', "66");
        dict.put('o', "666");
        dict.put('p', "7");
        dict.put('q', "77");
        dict.put('r', "777");
        dict.put('s', "7777");
        dict.put('t', "8");
        dict.put('u', "88");
        dict.put('v', "888");
        dict.put('w', "9");
        dict.put('x', "99");
        dict.put('y', "999");
        dict.put('z', "9999");
        dict.put(' ', "0");

        FastIO io = new FastIO();
        int caseCount = io.nextInt();
        String lastChar = "";
        for(int i = 0; i < caseCount; i++) {  //loop no of cases
            String message = io.nextLine();
            StringBuilder sb = new StringBuilder();
            sb.append("Case #").append(i + 1).append(": ");
            
            for (int j = 0; j < message.length(); j++){ //loop message
                String letter = dict.get(message.charAt(j));
                
                if (lastChar.equals("")) {
                    lastChar = Character.toString(letter.charAt(0));
                }
                if (lastChar.equals(Character.toString(letter.charAt(0)))) {
                    sb.append(" ");
                }
                sb.append(letter);
                lastChar = Character.toString(letter.charAt(letter.length()-1));

            }
            String finalMessage = sb.toString();
            System.out.println(finalMessage);
        }
        io.close();
        
    }
}
