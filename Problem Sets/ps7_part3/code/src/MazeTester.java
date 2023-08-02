import static org.junit.Assert.*;
import org.junit.Test;

public class MazeTester {
    @Test
    public void testPathSearch1() {
        try {
            Maze maze = Maze.readMaze("code/maze-empty.txt");
            IMazeSolver solver = new MazeSolver();
            solver.initialize(maze);
            int expected = 6;
            int answer = solver.pathSearch(0, 0, 3, 3);
            assertEquals(expected, answer);

        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch2() {
        try {
            Maze maze = Maze.readMaze("code/maze-dense.txt");
            IMazeSolver solver = new MazeSolver();
            solver.initialize(maze);
            Integer expected = null;
            Integer answer = solver.pathSearch(0, 0, 3, 3);
            assertEquals(expected, answer);

        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathsearch3() {
        try {
            Maze maze = Maze.readMaze("code/maze-dense.txt");
            IMazeSolver solver = new MazeSolver();
            solver.initialize(maze);
            Integer expected = 0;
            Integer answer = solver.pathSearch(0, 0, 0, 0);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch4() {
        try {
            Maze maze = Maze.readMaze("code/haunted-maze-simple.txt");
            IMazeSolver solver = new MazeSolver();
            solver.initialize(maze);
            Integer expected = (int) 'ü' + (int) 'W' + (int) 'û' + 1;
            Integer answer = solver.pathSearch(0, 0, 0, 4);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch5() {
        try {
            Maze maze = Maze.readMaze("code/haunted-maze-sample.txt");
            IMazeSolver solver = new MazeSolver();
            solver.initialize(maze);
            Integer expected = 314;
            Integer answer = solver.pathSearch(0, 1, 0, 5);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }

    @Test
    public void testPathSearch6() {
        try {
            Maze maze = Maze.readMaze("code/test1.txt");
            MazeSolver solver = new MazeSolver();
            solver.initialize(maze);
            Integer expected = (int) '!' * 5 + 2;
            Integer answer = solver.pathSearch(0, 0, 0, 5);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }
    @Test
    public void testPathSearch7() {
        try {
            Maze maze = Maze.readMaze("code/test2.txt");
            MazeSolver solver = new MazeSolver();
            solver.initialize(maze);
            Integer expected = (int) '!' * 7 + 1;
            Integer answer = solver.pathSearch(0, 0, 3, 5);
            assertEquals(expected, answer);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals("no exceptions", "exceptions");
        }
    }
}