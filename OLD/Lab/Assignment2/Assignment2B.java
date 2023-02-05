import java.util.LinkedList;
import java.io.*;
import java.util.*;

class FastIO extends PrintWriter {
    BufferedReader br;
    StringTokenizer st;

    public FastIO() {
        super(new BufferedOutputStream(System.out));
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

public class Assignment2B {

    public static void balance(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        int diff = l1.size() - l2.size();
        int val;
        if (diff < 0) {
            double d = (double) diff / 2;
            for (int i = 0; i < Math.floor(d); i++) {
                val = l2.getFirst();
                l2.removeFirst();
                l1.addLast(val);
            }
        } else if (diff > 1) {
            double d = (double) diff / 2;
            for (int i = 0; i < Math.floor(d); i++) {
                val = l1.getLast();
                l1.removeLast();
                l2.addFirst(val);
            }
        }

    }

    public static void main(String[] args) {
        FastIO io = new FastIO();
        int num = io.nextInt();
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();

        for (int i = 0; i < num; i++) {
            String input = io.nextLine();
            String[] command = input.split(" ");
            int index = Integer.parseInt(command[1]);
            if (command[0].equals("get")) {
                if (l1.size() > index) {
                    System.out.println(l1.get(index));
                } else {
                    System.out.println(l2.get(index - l1.size()));
                }
            } else if (command[0].equals("push_back")) {
                l2.addLast(index);
                balance(l1, l2);
            } else if (command[0].equals("push_front")) {
                l1.addFirst(index);
                balance(l1, l2);
            } else {
                l1.addLast(index);
                balance(l1, l2);

            }

        }

        io.close();

    }
}
