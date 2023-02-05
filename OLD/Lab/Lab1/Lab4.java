import java.io.*;
import java.util.*;

/**
 * Fast I/O
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
class FastIO extends PrintWriter { 
    BufferedReader br; 
    StringTokenizer st;

    public FastIO() 
    { 
        super(new BufferedOutputStream(System.out)); 
        br = new BufferedReader(new
                InputStreamReader(System.in));
    } 

    String next() 
    { 
        while (st == null || !st.hasMoreElements()) 
        { 
            try
            { 
                st = new StringTokenizer(br.readLine()); 
            } 
            catch (IOException  e) 
            { 
                e.printStackTrace(); 
            } 
        } 
        return st.nextToken(); 
    } 

    int nextInt() 
    { 
        return Integer.parseInt(next()); 
    } 

    long nextLong() 
    { 
        return Long.parseLong(next()); 
    } 

    double nextDouble() 
    { 
        return Double.parseDouble(next()); 
    } 

    String nextLine() 
    { 
        String str = ""; 
        try
        { 
            str = br.readLine(); 
        } 
        catch (IOException e) 
        { 
            e.printStackTrace(); 
        } 
        return str; 
    } 
}

class Pair <F, S> {
    private F first;
    private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return this.first;
    }

    public S getSecond() {
        return this.second;
    }

    public void setFirst(F f) {
        this.first = f;
    }

    public void setSecond(S s) {
        this.second = s;
    }

}

public class Lab4 {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        int s = io.nextInt();
        int n = io.nextInt();

        ArrayList<Pair<Integer, Integer>> players = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            players.add(new Pair<Integer, Integer>(0, i));
        }

        int index = 0;
        while (players.size() > 1) {
            index = (index + s - 1) % players.size();
            if (players.get(index).getFirst() == 0) {
                players.get(index).setFirst(1);
                players.add(index, new Pair<Integer,Integer>(1, players.get(index).getSecond()));

            } else if (players.get(index).getFirst() == 1) {
                players.get(index).setFirst(2);
                index++;
            } else {
                players.remove(index);
            }
        }

        System.out.println(players.get(0).getSecond());
        io.close();
    }
    
}
