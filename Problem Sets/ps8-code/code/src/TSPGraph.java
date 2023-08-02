import java.util.LinkedList;
import java.util.List;

public class TSPGraph implements IApproximateTSP {

    @Override
    public void MST(TSPMap map) {
        // TODO: implement this method
        // use of prim's algorithm and treemap prio queue
        TreeMapPriorityQueue<Double, Integer> queue = new TreeMapPriorityQueue<>();
        // array to keep track of visited/popped nodes
        boolean[] visited = new boolean[map.getCount()];
        // array to keep track of parent node
        int[] parent = new int[map.getCount()];
        // init arrays
        for (int i = 1; i< map.getCount(); i++) {
            visited[i] = false;
            parent[i] = 0;
        }

        //visit source node
        queue.add(0, 0.0);
        parent[0] = 0;
        while(!queue.isEmpty()) {
            // travel to node with minimum distance
            int next = queue.extractMin();
            visited[next] = true;
            map.setLink(next, parent[next]);

            // if nodes have not been visited + are lower distance from current node, add into prio queue
            for(int i = 0; i < map.getCount(); i++) {
                if(!visited[i] && queue.lookup(i) != null) {
                    if(map.pointDistance(next, i) < queue.lookup(i)) {
                        queue.decreasePriority(i, map.pointDistance(next, i));
                        parent[i] = next;
                    }
                } else if (!visited[i] && queue.lookup(i) == null) {
                    queue.add(i, map.pointDistance(next, i));
                }
            }
        }
        map.redraw();
    }

    @Override
    public void TSP(TSPMap map) {
        MST(map);
        // TODO: implement the rest of this method.
        // Use of DFS
        boolean[] visited = new boolean[map.getCount()];
        // init array values to false
        for(boolean v : visited) {
            v = false;
        }
        // linked list to keep track of path to all nodes
        List<Integer> path = new LinkedList<>();
        path.add(0);
        depthSearch(map, 0, visited, path);
        //return back to source
        path.add(0);
        // delete maplinks
        for(int i = 0; i < map.getCount(); i++) {
            map.eraseLink(i);
        }
        for(int i = 0; i < path.size() - 1; i++) {
            map.setLink(path.get(i), path.get(i + 1));
        }

        map.redraw();

    }

    private void depthSearch(TSPMap map, int index, boolean[] visited, List<Integer> path) {
        visited[index] = true;
        TreeMapPriorityQueue<Double, Integer> queue = new TreeMapPriorityQueue<>();

        for(int i = 0; i < map.getCount(); i++) {
            int src = map.getLink(i);
            // if node is connected to index node, add them
            if(src == index && !visited[i])
                queue.add(i, map.pointDistance(index, i));
        }
        // traverse the path and when leaf/end node is reached, return to parent, add next unvisited adjacent node
        while (!queue.isEmpty()) {
            int next = queue.extractMin();
            path.add(next);
            depthSearch(map, next, visited, path);
        }

    }

    @Override
    public boolean isValidTour(TSPMap map) {
        // Note: this function should with with *any* map, and not just results from TSP().
        // TODO: implement this method

        // boolean to check if path becomes a cycle
        boolean cycle = false;
        boolean[] visited = new boolean[map.getCount()];
        // init array values to false
        for(boolean v : visited) {
            v = false;
        }
        //visit source
        int start = 0;
        visited[start] = true;
        // trace links until end
        while (map.getLink(start) != -1) {
            int next = map.getLink(start);
            if(visited[next]) {
                if (next == 0) {
                    cycle = true;
                    break;
                }
                break;
            }
            // node has not been visited, update and move to next node
            visited[next] = true;
            start = next;
        }

        // check if all nodes visited
        boolean visit = true;
        for(boolean v : visited) {
            if(!v) {
                visit = false;
                break;
            }

        }
        return visit && cycle;
    }

    @Override
    public double tourDistance(TSPMap map) {
        // Note: this function should with with *any* map, and not just results from TSP().
        // TODO: implement this method
        if (isValidTour(map)) {
            int current = 0;
            double dist = 0;
            while (map.getLink(current) != -1) {
                int next = map.getLink(current);
                dist += map.pointDistance(current, next);
                if (next == 0) {
                    break;
                }
                current = next;
            }
            return dist;
        }
        return -1;
    }

    public static void main(String[] args) {
        TSPMap map = new TSPMap(args.length > 0 ? args[0] : "code/hundredpoints.txt");
        TSPGraph graph = new TSPGraph();

        // graph.MST(map);
        graph.TSP(map);
        // System.out.println(graph.isValidTour(map));
        // System.out.println(graph.tourDistance(map));
    }
}
