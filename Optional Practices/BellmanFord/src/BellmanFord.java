import java.util.ArrayList;

public class BellmanFord {
    // DO NOT MODIFY THE TWO STATIC VARIABLES BELOW
    public static int INF = 20000000;
    public static int NEGINF = -20000000;

    // TODO: add additional attributes and/or variables needed here, if any
    public ArrayList<ArrayList<IntPair>> adj;
    public int[] shortDist;

    public BellmanFord(ArrayList<ArrayList<IntPair>> adjList) {
        // TODO: initialize your attributes here, if any
        this.adj = adjList;
        this.shortDist = new int[adjList.size()];
    }

    // TODO: add additional methods here, if any
    public void computeShortestPaths(int source) {
        shortDist[source] = 0;
        for (int i = 0; i < shortDist.length; i++) {
            if (i != source)
                shortDist[i] = INF;
        }

        for (int j = 1; j <= adj.size() - 1; j++) {
            for (int i = 0; i < adj.size(); i++) {
                for (IntPair pair : adj.get(i)) {
                    if (shortDist[i] != INF) {
                        if (shortDist[pair.first] > pair.second + shortDist[i] && shortDist[pair.first] != NEGINF) {
                            shortDist[pair.first] = pair.second + shortDist[i];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < adj.size(); i++) {
            for (IntPair pair : adj.get(i)) {
                if (shortDist[pair.first] > pair.second + shortDist[i] && shortDist[pair.first] != NEGINF) {
                    for (int j = 1; j <= adj.size(); j++) {
                        for (int k = 0; k < adj.size(); k++) {
                            for (IntPair p : adj.get(k)) {
                                if (shortDist[k] != INF && shortDist[p.first] > p.second + shortDist[k] && shortDist[p.first] != NEGINF) {
                                    shortDist[p.first] = NEGINF;
                                }
                            }
                        }
                    }
                    return;
                }
            }
        }

    }

    public int getDistance(int node) {
        // TODO: implement your getDistance operation here
        if (node >= 0 && node < adj.size()) return shortDist[node];
        else return INF;
    }

}
