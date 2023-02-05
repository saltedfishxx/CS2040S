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

public class lab10 {

    public static int minDistance(int n, double[] distances, boolean[] visited) {
        double min = Double.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < n; i++) {
            if (distances[i] < min && visited[i] == false) {
                min = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static double dijkstraAlgo(int n, double[][] graph, int src) {
        double[] distances = new double[n];
        Arrays.fill(distances, Double.MAX_VALUE);
        distances[src] = 0;
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        for (int i = 0; i < n; i++) {
            int u = minDistance(n, distances, visited);
            visited[u] = true;

            for (int j = 0; j < n; j++) {
                if (graph[u][j] > 0 && visited[j] == false && distances[j] > (distances[u] + graph[u][j])) {
                    distances[j] = distances[u] + graph[u][j];
                }
            }
        }

        return distances[n - 1];
    }

    public static void main(String[] args) {
        FastIO io = new FastIO();
        ArrayList<double[]> points = new ArrayList<>();
        points.add(new double[] { io.nextDouble(), io.nextDouble() });
        double[] end = new double[] { io.nextDouble(), io.nextDouble() };
        int n = io.nextInt();
        double[][] graph = new double[n + 2][n + 2];
        for (double[] r : graph) {
            Arrays.fill(r, 0);
        }

        for (int i = 0; i < n; i++) {
            points.add(new double[] { io.nextDouble(), io.nextDouble() });
        }
        points.add(end);

        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                double dist = 0;
                double cost = 0;
                if (i == j) {
                    graph[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    dist = Math.sqrt(Math.pow((points.get(i)[0] - points.get(j)[0]), 2)
                            + Math.pow((points.get(i)[1] - points.get(j)[1]), 2));
                    graph[i][j] = dist / 5;
                    graph[j][i] = dist / 5;
                } else {
                    dist = Math.sqrt(Math.pow((points.get(i)[0] - points.get(j)[0]), 2)
                            + Math.pow((points.get(i)[1] - points.get(j)[1]), 2));
                    if (dist >= 50)
                        cost = 2 + (dist - 50) / 5;
                    else if (dist >= 30)
                        cost = 2 + (50 - dist) / 5;
                    else
                        cost = dist / 5;
                    graph[i][j] = cost;
                    graph[j][i] = cost;
                }
            }
        }

        System.out.println(dijkstraAlgo(n+2, graph, 0));

        io.close();

    }
}
