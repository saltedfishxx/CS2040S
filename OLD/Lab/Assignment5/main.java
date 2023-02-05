import java.lang.*;
import java.util.*;

public class main {
	public static void main(String[] args) {
		FastReader fio = new FastReader();
		int row = fio.nextInt();
		int col = fio.nextInt();
		int inf = 2000000000;
		int[][] height = new int[row][col];
		int[][] shortest = new int[row][col];
		int[][] visited = new int[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) { 
				height[i][j] = fio.nextInt();
				shortest[i][j] = inf;
				visited[i][j] = 0;
			}
		}
		shortest[0][0] = 0;

		PriorityQueue<IntegerTriple> queue = new PriorityQueue<IntegerTriple>();
		queue.add(new IntegerTriple(0, 0, 0));
		while(!queue.isEmpty() && visited[row-1][col-1] == 0) {
			IntegerTriple top = queue.poll();
			int currx = top.second();
			int curry = top.third();
			int[] dx = {1, -1, 0, 0};
			int[] dy = {0, 0, 1, -1};

			if(visited[currx][curry] == 1) {
				continue;
			}

			visited[currx][curry] = 1;

			for(int i = 0; i < 4; i++) {
				int nextx = currx + dx[i];
				int nexty = curry + dy[i];

				if(!inMap(nextx, nexty, row, col)) {
					continue;
				}

				int hDiff = Math.max(0, height[nextx][nexty] - height[currx][curry]);
				hDiff = Math.max(hDiff, shortest[currx][curry]);

				if(shortest[nextx][nexty] > hDiff) {
					shortest[nextx][nexty] = hDiff;
					queue.add(new IntegerTriple(hDiff, nextx, nexty));
				}
			}
		}
		System.out.println(shortest[row-1][col-1]);
	}

	public static boolean inMap(int nextx, int nexty, int row, int col) {
		return nextx >= 0 && nexty >= 0 && nextx < row && nexty < col;
	}
}

class IntegerTriple implements Comparable<IntegerTriple> {
	private int first;
	private int second;
	private int third;

	public IntegerTriple(int first, int second, int third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public int compareTo(IntegerTriple o) {
		if(this.first != o.first()) {
			return this.first - o.first();
		} else if(this.second != o.second()) {
			return this.second - o.second();
		} else {
			return this.third - o.third();
		}
	}

	public int first() {
		return this.first;
	}

	public int second() {
		return this.second;
	}

	public int third() {
		return this.third;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public void setThird(int third) {
		this.third = third;
	}
}


