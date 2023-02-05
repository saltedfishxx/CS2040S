import java.io.*;
import java.util.*;

/**
 * Fast I/O
 * 
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
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

class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    T key;
    int height;
    int size;
    Node<T> left;
    Node<T> right;

    Node(T key) {
        this.key = key;
    }

    @Override
    public int compareTo(Node<T> o) {
        return this.key.compareTo(o.key);
    };
}

class AVLTree<T extends Comparable<T>> {

    Node<T> root;

    public AVLTree() {
        root = null;
    }

    public Node<T> find(T key) {
        Node<T> current = root;
        while (current != null) {
            if (current.key.compareTo(key) == 0) {
                break;
            }
            current = current.key.compareTo(key) < 0 ? current.left : current.right;
        }
        return current;
    }

    public void insert(T key) {
        root = insert(root, key);
    }

    public void delete(T key) {
        root = delete(root, key);
    }

    public Node<T> getRoot() {
        return root;
    }

    public int height() {
        return root == null ? -1 : root.height;
    }

    public int size() {
        return root == null ? 0 : root.size;
    }

    private Node<T> insert(Node<T> node, T key) {
        if (node == null) {
            Node<T> n = new Node<T>(key);
            return n;
        } else if (node.key.compareTo(key) < 0) {
            node.left = insert(node.left, key);
        } else if (node.key.compareTo(key) > 0) {
            node.right = insert(node.right, key);
        } else {
        }
        return rebalance(node);
    }

    private Node<T> delete(Node<T> node, T key) {
        if (node == null) {
            updateSize(node);
            return node;
        } else if (node.key.compareTo(key) < 0) {
            node.left = delete(node.left, key);
        } else if (node.key.compareTo(key) > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node<T> mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    private Node<T> mostLeftChild(Node<T> node) {
        Node<T> current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node<T> rebalance(Node<T> z) {
        updateHeight(z);
        updateSize(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<T> rotateLeft(Node<T> y) {
        Node<T> x = y.right;
        Node<T> z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private void updateHeight(Node<T> n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private void updateSize(Node<T> n) {
        n.size = size(n.left) + size(n.right) + 1;
    }

    private int height(Node<T> n) {
        return n == null ? -1 : n.height;
    }

    private int size(Node<T> n) {
        return n == null ? 0 : n.size;
    }

    public int getBalance(Node<T> n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }
}

class Team implements Comparable<Team> {
    int ID;
    int score;
    int penalty;

    public Team(int i, int score, int penalty) {
        this.ID = i;
        this.score = 0;
        this.penalty = penalty;
    }

    @Override
    public int compareTo(Team o) {
        if (this.ID != o.ID) {
            if (this.score != o.score)
                return this.score - o.score;
            if (this.penalty != o.penalty)
                return o.penalty - this.penalty;
        }
        return o.ID - this.ID;
    }

}

public class AssignmentB {

    public static void main(String[] args) {
        FastIO io = new FastIO();
        AVLTree<Team> tree = new AVLTree<Team>();

        int noTeams = io.nextInt();
        int noEvents = io.nextInt();
        Team favTeam = null;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < noEvents; i++) {
            int teamNo = io.nextInt();
            int penalty = io.nextInt();

            Team team = new Team(teamNo, 0, penalty);
            Node<Team> existingTeam = tree.find(team);
            if (existingTeam != null) { // if there is existing team in records
                // update score
                int prevPen = existingTeam.key.penalty;
                int prevSc = existingTeam.key.score;
                // remove previous node and update with new
                tree.delete(team);
                tree.insert(new Team(teamNo, prevSc++, prevPen + penalty));
            } else {
                // new team to add
                tree.insert(team);
            }

            // if its team 1, save/update team info
            if (teamNo == 1) {
                favTeam = team;
            }

            // find team 1
            if (favTeam != null) {
                Node<Team> fav = tree.find(favTeam);
                int rank = fav.right != null ? fav.right.size : 1;
                sb.append(rank + "\n");
            } else {
                int rank = tree.size() == 0 ? noTeams - 1 : tree.size() + 2;
                sb.append(rank + "\n");
            }
        }

        System.out.println(sb);
        io.close();
    }
}
