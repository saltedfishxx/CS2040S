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

class Runner {
    private String name;
    private double leg1;
    private double leg2;

    public Runner (String name, double l1, double l2) {
        this.name = name;
        this.leg1 = l1;
        this.leg2 = l2;
    }

    public String getName(){
        return this.name;
    }

    public double getLeg1(){
        return this.leg1;
    }

    public double getLeg2(){
        return this.leg2;
    }
}

class CompareSpeedLeg2 implements Comparator<Runner> {
    @Override
    public int compare(Runner r1, Runner r2) {
        if(r1.getLeg2() < r2.getLeg2())
            return -1;
        else if (r1.getLeg2() > r2.getLeg2())
            return 1;
        return 0;
     }
}

public class Assignment1 {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        int noRunners = io.nextInt();
        double min = 1000000000000.0;
        double eps = 0.0000000001;

        Runner[] runners = new Runner[noRunners+1];
        ArrayList<Runner> sortedRunners = new ArrayList<Runner>();
        for (int i = 1; i <= noRunners; i++){
            String runner = io.nextLine();
            String[] run = runner.split(" ");
            runners[i] = new Runner(run[0], Double.valueOf(run[1]), Double.valueOf(run[2]));    
        }

        String r1 = "";
        String r2 = "";
        String r3 = "";
        String r4 = "";
        for (int i = 1; i <= noRunners; i++)
		{
            sortedRunners.clear();
			double sum = runners[i].getLeg1();
			for (int j = 1; j <= noRunners; j++)
			{
				if (j != i)
				{
					sortedRunners.add(runners[j]);
				}
			}

			Collections.sort(sortedRunners, new CompareSpeedLeg2());
			for (int j = 0; j <= 2; j++)
			{
				sum += sortedRunners.get(j).getLeg2();
			}
			if (sum - min < eps)
			{
				min = sum;
				r1 = runners[i].getName();
				r2 = sortedRunners.get(0).getName();
				r3 = sortedRunners.get(1).getName();
				r4 = sortedRunners.get(2).getName();
			}

		}

        System.out.println(min);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
        
        io.close();
    }
}
