import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class MazeSolver implements IMazeSolver {
	private static final int NORTH = 0, SOUTH = 1, EAST = 2, WEST = 3;
	private static int[][] DELTAS = new int[][] {
		{ -1, 0 }, // North
		{ 1, 0 }, // South
		{ 0, 1 }, // East
		{ 0, -1 } // West
	};

	private Maze maze;
	private boolean status;
	private boolean[][] visited;
	private List<Integer> path;

	public MazeSolver() {
		// TODO: Initialize variables.
		this.status = false;
		this.maze = null;
	}

	@Override
	public void initialize(Maze maze) {
		// TODO: Initialize the solver.
		this.maze = maze;
		this.status = false;
		this.visited = new boolean[maze.getRows()][maze.getColumns()];
	}

	static class Coordinate {
		int row;
		int col;
		int dist;
		Coordinate parent;

		public Coordinate(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}
		public void setParent(Coordinate parent) {
			this.parent = parent;
		}

		public Coordinate getParent() {
			return this.parent;
		}
	}

	@Override
	public Integer pathSearch(int startRow, int startCol, int endRow, int endCol) throws Exception {
		// TODO: Find shortest path.
		this.status = false;
		// If maze is empty/null, return error
		if (maze == null) throw new Exception("Maze not initialized");
		if(startRow < 0 || startCol < 0 || startRow >= maze.getRows() || startCol >= maze.getColumns()
				|| endRow < 0 || endCol < 0 || endRow >= maze.getRows() || endCol >= maze.getColumns())
			throw new IllegalArgumentException("Invalid arguments");
		//init maze to false
		for(int i = 0; i < maze.getRows(); i++) {
			for(int j = 0; j < maze.getColumns(); j++) {
				this.visited[i][j] = false;
				maze.getRoom(i, j).onPath = false;
			}
		}

		int ans = 0;
		int count = 0;
		this.path = new ArrayList<>();
		visited[startRow][startCol] = true;
		//creates a queue for BFS
		Queue<Coordinate> nextFrontier = new LinkedList<>();
		//create starting point
		Coordinate start = new Coordinate(startRow, startCol, 0);
		Coordinate prev = null;
		start.setParent(null);
		nextFrontier.add(start);

		while (!nextFrontier.isEmpty()) {
			this.path.add(count, nextFrontier.size());

			Queue<Coordinate> queue = new LinkedList<>();
			for (Coordinate point : nextFrontier) {
				int r = point.row;
				int c = point.col;
				if (r == endRow && c == endCol) {
					this.status = true;
					prev = point;
					ans = prev.dist;

				}
				visited[r][c] = true;

				//check for 4 direction if there is walls
				for (int[] delta : DELTAS) {
					int newR = r + delta[0];
					int newC = c + delta[1];
					if (!hasWall(delta, r, c) && !visited[newR][newC]) {
						Coordinate direction = new Coordinate(newR, newC, point.dist + 1);
						queue.add(direction);
						visited[direction.row][direction.col] = true;
						direction.setParent(point);
					}
				}
			}
			nextFrontier = queue;
			count++;
		}

		//if reached end of target point, trace the path
		if (status) {
			this.maze.getRoom(start.row, start.col).onPath = true;
			while (prev.getParent() != null) {
				this.maze.getRoom(prev.row, prev.col).onPath = true;
				prev = prev.getParent();
			}
			return ans;
		} else {
			this.maze.getRoom(startRow, startCol).onPath = true;
			return null;
		}

	}

	public Integer pathSearch(int startRow, int startCol, int endRow,
							  int endCol, int superpowers) throws Exception {
		if (maze == null) {
			throw new Exception("Oh no! You cannot call me without initializing the maze!");
		}

		if (startRow < 0 || startCol < 0 || startRow >= maze.getRows() || startCol >= maze.getColumns() ||
				endRow < 0 || endCol < 0 || endRow >= maze.getRows() || endCol >= maze.getColumns()) {
			throw new IllegalArgumentException("Invalid start/end coordinate");
		}

		this.visited = new boolean[maze.getRows()][maze.getColumns()];
		this.visitedwithpower = new boolean[maze.getRows()][maze.getColumns()][superpowers + 1];
		// set all visited flag to false
		// before we begin our search
		for (int i = 0; i < maze.getRows(); ++i) {
			for (int j = 0; j < maze.getColumns(); ++j) {
				for (int k = 0; k < superpowers + 1; k++) {
					this.visitedwithpower[i][j][k] = false;
				}
				maze.getRoom(i, j).onPath = false;
				this.visited[i][j] = false;
			}
		}
		// keeps track of the number of rooms reachable per step for numReachable
		this.roomseachstep = new LinkedList<>();
		this.solved = false;
		// to keep track of number of steps
		int counter = 0;
		// keeps track of frontier for BFS
		Queue<Rm> frontier = new LinkedList<>();
		// coords of start point
		Room start = maze.getRoom(startRow, startCol);
		// start pt visited
		Rm s = new Rm(start, startRow, startCol, null, superpowers);
		visited[startRow][startCol] = true;
		// find end room
		Room end = maze.getRoom(endRow, endCol);
		// enqueue the current node
		frontier.add(s);
		Rm endpt = null;
		int endcount = 0;
		// unique rooms at previous step count
		int roomsperstep = 1;
		while (!frontier.isEmpty()) {
			// adds the number of rooms reachable at current step, in index of current step
			this.roomseachstep.add(roomsperstep);
			// number of unique unvisited rooms at current step count
			int rooms = 0;

			// room not endpoint, dequeue and enqueue adjacent rooms
			Queue<Rm> nextfrontier = new LinkedList<>();
			for (Rm rm: frontier) {
				Room room = rm.getRoom();
				int x = rm.getRow();
				int y = rm.getCol();
				int powers = rm.superpowers;

				if (room.equals(end) && !this.solved) {
					this.solved = true;
					endcount = counter;
					endpt = rm;
					// error checking
					// System.out.println("result: " + counter);
				}

				visitedwithpower[x][y][powers] = true;

				// North
				if (x > 0) {
					Room north = maze.getRoom(x - 1, y);
					// if room was visited before with the same number of powers, then current path is slower
					// or the other path led to a dead end
					// no point adding into frontier
					if (room.hasNorthWall() && rm.superpowers > 0 && !visitedwithpower[x - 1][y][rm.superpowers - 1]) {
						nextfrontier.add(new Rm(north, x - 1, y, rm, rm.superpowers - 1));
						visitedwithpower[x - 1][y][rm.superpowers - 1] = true;
						// count only unique rooms that have not been visited
						if (!visited[x - 1][y]) {
							visited[x - 1][y] = true;
							rooms++;
						}
						// no need for power if no wall, add into frontier if unique state
					} else if (!room.hasNorthWall() && !visitedwithpower[x - 1][y][rm.superpowers]) {
						nextfrontier.add(new Rm(north, x - 1, y, rm, rm.superpowers));
						visitedwithpower[x - 1][y][rm.superpowers] = true;
						if (!visited[x - 1][y]) {
							visited[x - 1][y] = true;
							rooms++;
						}
					}
				}
				// South
				if (x < maze.getRows() - 1) {
					Room south = maze.getRoom(x + 1, y);
					if (room.hasSouthWall() && rm.superpowers > 0 && !visitedwithpower[x + 1][y][rm.superpowers - 1]) {
						nextfrontier.add(new Rm(south, x + 1, y, rm, rm.superpowers - 1));
						visitedwithpower[x + 1][y][rm.superpowers - 1] = true;
						if (!visited[x + 1][y]) {
							visited[x + 1][y] = true;
							rooms++;
						}
					} else if (!room.hasSouthWall() && !visitedwithpower[x + 1][y][rm.superpowers]) {
						nextfrontier.add(new Rm(south, x + 1, y, rm, rm.superpowers));
						visitedwithpower[x + 1][y][rm.superpowers] = true;
						if (!visited[x + 1][y]) {
							visited[x + 1][y] = true;
							rooms++;
						}
					}
				}
				// East
				if (y < maze.getColumns() - 1) {
					Room east = maze.getRoom(x, y + 1);
					if (room.hasEastWall() && rm.superpowers > 0 && !visitedwithpower[x][y + 1][rm.superpowers - 1]) {
						nextfrontier.add(new Rm(east, x, y + 1, rm, rm.superpowers - 1));
						visitedwithpower[x][y + 1][rm.superpowers - 1] = true;
						if (!visited[x][y + 1]) {
							visited[x][y + 1] = true;
							rooms++;
						}
					} else if (!room.hasEastWall() && !visitedwithpower[x][y + 1][rm.superpowers]) {
						nextfrontier.add(new Rm(east, x, y + 1, rm, rm.superpowers));
						visitedwithpower[x][y + 1][rm.superpowers] = true;
						if (!visited[x][y + 1]) {
							visited[x][y + 1] = true;
							rooms++;
						}
					}
				}
				// West
				if (y > 0) {
					Room west = maze.getRoom(x, y - 1);
					if (room.hasWestWall() && rm.superpowers > 0 && !visitedwithpower[x][y - 1][rm.superpowers - 1]) {
						nextfrontier.add(new Rm(west, x, y - 1, rm, rm.superpowers - 1));
						visitedwithpower[x][y - 1][rm.superpowers - 1] = true;
						if (!visited[x][y - 1]) {
							visited[x][y - 1] = true;
							rooms++;
						}
					} else if (!room.hasWestWall() && !visitedwithpower[x][y - 1][rm.superpowers]) {
						nextfrontier.add(new Rm(west, x, y - 1, rm, rm.superpowers));
						visitedwithpower[x][y - 1][rm.superpowers] = true;
						if (!visited[x][y - 1]) {
							visited[x][y - 1] = true;
							rooms++;
						}
					}
				}
			}
			// increase counter since room is not yet found
			counter++;
			frontier = nextfrontier;
			// since steps increased, current step becomes previous step - update rooms per step
			roomsperstep = rooms;
			// error checking
			// System.out.println(frontier);
		}
		if (solved) {
			maze.getRoom(startRow, startCol).onPath = true;
			endpt.getRoom().onPath = true;
			while (endpt.prev != null) {
				endpt.prev.getRoom().onPath = true;
				endpt = endpt.prev;
			}
			return endcount;
		} else {
			return null;
		}
	}

	/**
	 * Helper method that returns if there is wall given the coordinate
	 * @param delta current wall to compare
	 * @param r current row
	 * @param c current col
	 * @return boolean if there is wall
	 */
	boolean hasWall(int[] delta, int r, int c) {
		//north
		if (delta == DELTAS[0] && maze.getRoom(r, c).hasNorthWall()) {
			return true;
		} else if (delta == DELTAS[1] && maze.getRoom(r, c).hasSouthWall()) {
			return true;
		} else if (delta == DELTAS[2] && maze.getRoom(r, c).hasEastWall()) {
			return true;
		} else return delta == DELTAS[3] && maze.getRoom(r, c).hasWestWall();
	}


	@Override
	public Integer numReachable(int k) throws Exception {
		// TODO: Find number of reachable rooms.
		if (k > path.size() - 1) {
			return 0;
		} else {
			return path.get(k);
		}
	}

	public static void main(String[] args) {
		// Do remember to remove any references to ImprovedMazePrinter before submitting
		// your code!
		try {
			Maze maze = Maze.readMaze("maze-empty.txt");
			IMazeSolver solver = new MazeSolver();
			solver.initialize(maze);

			System.out.println(solver.pathSearch(0, 0, 2, 3));
			MazePrinter.printMaze(maze);

			for (int i = 0; i <= 9; ++i) {
				System.out.println("Steps " + i + " Rooms: " + solver.numReachable(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
