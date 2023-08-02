import java.util.*;

public class MazeGenerator {

    private MazeGenerator() {
    }

    // TODO: Feel free to modify the method parameters.

    public static Maze generateMaze(int rows, int columns) {

        // Initialize the maze with all walls intact
        Room[][] rooms = new Room[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                rooms[i][j] = new Room(true, true, true, true);
            }
        }
        Maze maze = new Maze(rooms);

        // Recursive backtracking
        Random rand = new Random();
        int startRow = rand.nextInt(rows);
        int startCol = rand.nextInt(columns);
        recursiveBacktracking(maze, startRow, startCol);

        return maze;
    }

    /**
     * Recursive backtracking algorithm to generate a maze.
     *
     * @param maze the maze being generated
     * @param row the current row
     * @param col the current column
     */
    private static void recursiveBacktracking(Maze maze, int row, int col) {
        Room currentRoom = maze.getRoom(row, col);
        currentRoom.onPath = true;

        // Randomly shuffle the list of neighbors
        List<int[]> neighbors = new ArrayList<>();
        if (row > 0) {
            neighbors.add(new int[] {row-1, col}); // North
        }
        if (row < maze.getRows()-1) {
            neighbors.add(new int[] {row+1, col}); // South
        }
        if (col > 0) {
            neighbors.add(new int[] {row, col-1}); // West
        }
        if (col < maze.getColumns()-1) {
            neighbors.add(new int[] {row, col+1}); // East
        }
        Collections.shuffle(neighbors);

        // Visit each neighbor recursively
        for (int[] neighbor : neighbors) {
            int neighborRow = neighbor[0];
            int neighborCol = neighbor[1];
            Room neighborRoom = maze.getRoom(neighborRow, neighborCol);

            if (!neighborRoom.onPath) {
                // Remove the wall between the current room and the neighbor
                if (neighborRow == row-1) {
                    currentRoom.northWall = false;
                    neighborRoom.southWall = false;
                } else if (neighborRow == row+1) {
                    currentRoom.southWall = false;
                    neighborRoom.northWall = false;
                } else if (neighborCol == col-1) {
                    currentRoom.westWall = false;
                    neighborRoom.eastWall = false;
                } else if (neighborCol == col+1) {
                    currentRoom.eastWall = false;
                    neighborRoom.westWall = false;
                }

                recursiveBacktracking(maze, neighborRow, neighborCol);
            }
        }
    }
}
