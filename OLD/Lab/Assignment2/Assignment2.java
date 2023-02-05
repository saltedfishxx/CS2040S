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

// Node for list
class Node
{
	public String str;
	public Node next;
    Node(String d)
        {
            this.str = d;
            this.next = null;
        } // Constructor
}

// Singly linked list
class LinkList
{
	public Node head = new Node("");
	public Node tail;
}

public class Assignment2 {

	public static void add(LinkList l1, LinkList l2)
	{
		l1.tail.next = l2.head;
		l1.tail = l2.tail;
	}

	public static void display(LinkList l)
	{
		Node next = l.head;
		while (next != null)
		{
			System.out.print(next.str);
			next = next.next;
		}
	}

    public static void main(String[] args) {
        FastIO io = new FastIO();
        int count = io.nextInt();
        LinkList[] lists = new LinkList[count];
        for (int i = 0; i < count; i++) {
            lists[i] = new LinkList();
            lists[i].head.next = null;
			lists[i].head.str = io.nextLine();
			lists[i].tail = lists[i].head;
        }

        int current = 0;
        for (int i = 0; i < count - 1; i++) {
            String opertation = io.nextLine();
            String[] ops = opertation.split(" ");
            int i1 = Integer.parseInt(ops[0]) - 1;
            int i2 = Integer.parseInt(ops[1]) - 1;
            current = i1;
            add(lists[i1], lists[i2]);

        }

        display(lists[current]);
        io.close();

    }
}