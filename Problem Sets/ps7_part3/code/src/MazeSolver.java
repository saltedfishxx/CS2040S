import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;

public class MazeSolver implements IMazeSolver {
    private static final int TRUE_WALL = Integer.MAX_VALUE;
    private static final int EMPTY_SPACE = 0;
    private static final List<Function<Room, Integer>> WALL_FUNCTIONS = Arrays.asList(
            Room::getNorthWall,
            Room::getEastWall,
            Room::getWestWall,
            Room::getSouthWall
    );
    private static final int[][] DELTAS = new int[][]{
            {-1, 0}, // North
            {0, 1}, // East
            {0, -1}, // West
            {1, 0} // South
    };

    private Maze maze;
    boolean[][] visited;
    int[][] feared;
    boolean status;
    boolean[][] isQueued; // checks if Coordinate has been added into frontier before
    PriorityQueue<Coordinate> frontier;

    static class Coordinate implements Comparable<Coordinate> {
        Room room;
        int row;
        int col;
        int fear;

        public Coordinate(Room room, int row, int col, int fear) {
            this.room = room;
            this.row = row;
            this.col = col;
            this.fear = fear;
        }

        @Override
        public int compareTo(Coordinate coor) {
            return Integer.compare(this.fear, coor.fear);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Coordinate) {
                Coordinate coor = (Coordinate) o;
                return coor.getRow() == row && coor.getCol() == col;
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("(row: %s, col: %s, fear: %s)", row, col, fear);
        }


        public int getRow() {
            return this.row;
        }

        public int getCol() {
            return this.col;
        }

        public Room getRoom() {
            return this.room;
        }
    }

    public MazeSolver() {
        // TODO: Initialize variables.
        this.maze = null;
        status = false;
        frontier = new PriorityQueue<>();
    }

    @Override
    public void initialize(Maze maze) {
        // TODO: Initialize the solver.
        this.maze = maze;
        status = false;
        visited = new boolean[maze.getRows()][maze.getColumns()];
        feared = new int[maze.getRows()][maze.getColumns()];
        isQueued = new boolean[maze.getRows()][maze.getColumns()];
    }

    @Override
    public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
        // TODO: Find minimum fear level.
        // If maze is empty/null, return error
        if (maze == null) throw new Exception("Maze not initialized");
        if (startRow < 0 || startCol < 0 || startRow >= maze.getRows() || startCol >= maze.getColumns()
                || endRow < 0 || endCol < 0 || endRow >= maze.getRows() || endCol >= maze.getColumns())
            throw new IllegalArgumentException("Invalid arguments");

        //init maze to false
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                this.visited[i][j] = false;
                this.feared[i][j] = Integer.MAX_VALUE;
                this.isQueued[i][j] = false;
            }
        }
        //reinit queue
        frontier.clear();
        status = false;
        frontier = new PriorityQueue<>();
        Room start = maze.getRoom(startRow, startCol);
        feared[startRow][startCol] = 0;
        isQueued[startRow][startCol] = true;
        Coordinate startCoor = new Coordinate(start, startRow, startCol, 0);
        frontier.add(startCoor);

        while (!frontier.isEmpty() && !status) {
            Coordinate coor = frontier.poll();
            Room room = coor.getRoom();
            int r = coor.getRow();
            int c = coor.getCol();

            if (r == endRow && c == endCol) {
                status = true;
                break;
            }

            //check for every direction
            for (int i = 0; i < 4; i++) {
                int newFear = WALL_FUNCTIONS.get(i).apply(room);
                int newR = r + DELTAS[i][0];
                int newC = c + DELTAS[i][1];
                if (newR >= 0 && newC >= 0 && newR < maze.getRows() && newC < maze.getColumns()) {
                    if (!visited[newR][newC]) {
                        if (newFear == EMPTY_SPACE) {
                            if (feared[newR][newC] > feared[r][c] + 1) {
                                feared[newR][newC] = feared[r][c] + 1;
                            }
                            Coordinate nextCoor = new Coordinate(maze.getRoom(newR, newC), newR, newC, feared[newR][newC]);
                            frontier.add(nextCoor);
                            isQueued[newR][newC] = true;
                        } else if (newFear != TRUE_WALL) {
                            if (feared[newR][newC] > feared[r][c] + newFear) {
                                feared[newR][newC] = feared[r][c] + newFear;
                            }
                            Coordinate nextCoor = new Coordinate(maze.getRoom(newR, newC), newR, newC, feared[newR][newC]);
                            //decrease the key if it has been queued before
                            if (isQueued[newR][newC])
                                frontier.removeIf(e -> e.equals(nextCoor) && e.fear > nextCoor.fear);
                            frontier.add(nextCoor);
                            isQueued[newR][newC] = true;
                        }
                    }
                }
            }
            visited[r][c] = true;

        }
        if (status)
            return feared[endRow][endCol];
        else
            return null;
    }

    @Override
    public Integer bonusSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
        // TODO: Find minimum fear level given new rules.
        // If maze is empty/null, return error
        if (maze == null) throw new Exception("Maze not initialized");
        frontier.clear();
        frontier = new PriorityQueue<>();
        Coordinate start = new Coordinate(maze.getRoom(startRow, startCol), startRow, startCol, 0);
        frontier.add(start);
        this.feared = new int[maze.getRows()][maze.getColumns()];
        this.visited = new boolean[maze.getRows()][maze.getColumns()];
        //init maze to false
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                this.feared[i][j] = Integer.MAX_VALUE;
                this.visited[i][j] = false;
            }
        }
        feared[startRow][startCol] = 0;
        while (!frontier.isEmpty() && !status) {
            Coordinate curr = frontier.poll();

            //check for all direction
            for (int i = 0; i < 4; i++) {
                int newR = curr.row + DELTAS[i][0];
                int newC = curr.col + DELTAS[i][1];
                if (newR >= 0 && newC >= 0 && newR < maze.getRows() && newC < maze.getColumns()) {
                    findNeighbour(curr, i);
                }
            }
        }
        return feared[endRow][endCol] == Integer.MAX_VALUE
                ? null : feared[endRow][endCol];
    }

    private void findNeighbour(Coordinate src, int dir) {
        int newR = src.row + DELTAS[dir][0];
        int newC = src.col + DELTAS[dir][1];
        Room room = maze.getRoom(src.row, src.col);
        int fear = WALL_FUNCTIONS.get(dir).apply(room);
        if (fear == TRUE_WALL)
            return;
        //if scariness level is greater than fear level, update fear level to the same scariness level
        if (fear > feared[src.row][src.col]) {
            if (feared[newR][newC] > fear) {
                feared[newR][newC] = fear;
                if (fear == EMPTY_SPACE)
                    feared[newR][newC]++;
                Coordinate nextCoor = new Coordinate(maze.getRoom(newR, newC), newR, newC, feared[newR][newC]);
                frontier.add(nextCoor);
            }
        } else {
            if (feared[newR][newC] > feared[src.row][src.col]) {
                feared[newR][newC] = feared[src.row][src.col];
                if (fear == EMPTY_SPACE)
                    feared[newR][newC]++;
                Coordinate nextCoor = new Coordinate(maze.getRoom(newR, newC), newR, newC, feared[newR][newC]);
                frontier.add(nextCoor);
            }
        }

    }

    int specR;
    int specC;
    boolean hasVisited;

    @Override
    public Integer bonusSearch(int startRow, int startCol, int endRow, int endCol, int sRow, int sCol) throws Exception {
        // TODO: Find minimum fear level given new rules and special room.
        if (maze == null) throw new Exception("Maze not initialized");
        frontier.clear();
        frontier = new PriorityQueue<>();
        Coordinate start = new Coordinate(maze.getRoom(startRow, startCol), startRow, startCol, 0);
        frontier.add(start);
        feared = new int[maze.getRows()][maze.getColumns()];
        //init maze to false
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                this.feared[i][j] = Integer.MAX_VALUE;
            }
        }
        feared[startRow][startCol] = 0;
        this.specR = sRow;
        this.specC = sCol;
        this.hasVisited = false;

        while (!frontier.isEmpty()) {
            Coordinate curr = frontier.poll();
            for (int i = 0; i < 4; i++) {
                int newR = curr.row + DELTAS[i][0];
                int newC = curr.col + DELTAS[i][1];
                if (newR >= 0 && newC >= 0 && newR < maze.getRows() && newC < maze.getColumns()) {
                    modifiedFindNeighbour(curr, i);
                }
            }
        }

        return feared[endRow][endCol] == Integer.MAX_VALUE
                ? null : feared[endRow][endCol];
    }

    private void modifiedFindNeighbour(Coordinate src, int dir) {
        int newR = src.row + DELTAS[dir][0];
        int newC = src.col + DELTAS[dir][1];
        int wall = WALL_FUNCTIONS.get(dir).apply(maze.getRoom(src.row, src.col));
        if (wall == TRUE_WALL) {
            return;
        }
        // check if the next Room is the special room
        if (newR == specR && newC == specC && !hasVisited) {
            hasVisited = true;
            feared[newR][newC] = -1;
            Coordinate nextCoor = new Coordinate(maze.getRoom(newR, newC), newR, newC, feared[newR][newC]);
            frontier.add(nextCoor);
        }
        // special case when next room is EMPTY_SPACE
        if (feared[src.row][src.col] == -1 && wall == EMPTY_SPACE) {
            feared[newR][newC] = 0;
            Coordinate nextCoor = new Coordinate(maze.getRoom(newR, newC), newR, newC, feared[newR][newC]);
            frontier.add(nextCoor);
        } else if (wall > feared[src.row][src.col]) {
            if (feared[newR][newC] > wall) {
                feared[newR][newC] = wall;
                if (wall == EMPTY_SPACE)
                    feared[newR][newC]++;
                Coordinate nextCoor = new Coordinate(maze.getRoom(newR, newC), newR, newC, feared[newR][newC]);
                frontier.add(nextCoor);
            }
        } else {
            if (feared[newR][newC] > feared[src.row][src.col]) {
                feared[newR][newC] = feared[src.row][src.col];
                if (wall == EMPTY_SPACE)
                    feared[newR][newC]++;
                Coordinate nextCoor = new Coordinate(maze.getRoom(newR, newC), newR, newC, feared[newR][newC]);
                frontier.add(nextCoor);
            }
        }
    }

    public static void main(String[] args) {
        try {
            Maze maze = Maze.readMaze("code/haunted-maze-sample.txt");
            IMazeSolver solver = new MazeSolver();
            solver.initialize(maze);

            System.out.println(solver.pathSearch(0, 0, 0, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
