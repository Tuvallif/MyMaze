package algorithms.demo;

import java.util.Comparator;
import java.util.List;

import algorithms.maze.Maze3d;
import algorithms.maze.Position;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.BestFirstSearch;
import algorithms.search.DFS;
import algorithms.search.Search;

public class Demo {

	public void Run() {

		Maze3dGenerator myGernerator = new MyMaze3dGenerator();
		Maze3d myMaze = myGernerator.generate();
		myMaze.printMaze();
		Searchable Srchble = new mySearchable(myMaze);
		Search srcDFS = new BestFirstSearch(new DFS(Srchble));
		Search srcBFS = new BestFirstSearch(new BFS(Srchble, c));
		Search srcBestFS = new BestFirstSearch(new BFS(Srchble, best));

		List<Position> DFSPath = srcDFS.FindPath();
		List<Position> BFSPath = srcBFS.FindPath();
		List<Position> BestFSPath = srcBestFS.FindPath();

		System.out.println("dfs");
		int i = printPath(DFSPath);
		System.out.println("bfs");
		int j = printPath(BFSPath);
		System.out.println("bestfs");
		int k = printPath(BestFSPath);

	}

	private int printPath(List<Position> toPrint) {
		int result = 0;
		if (toPrint == null) {
			System.out.println("EMPTY LIST");
		} else {
			// PRINTING THE FIRST POSITION
			Position curPos = toPrint.remove(toPrint.size() - 1);
			System.out.println(curPos.printPos());
			// PRINTING THE OTHERS
			while (toPrint.isEmpty() == false) {
				result++;
				printArrow();
				curPos = toPrint.remove(toPrint.size() - 1);
				System.out.println(curPos.printPos());

			}
		}

		return result;
	}

	Comparator<Position> c = new Comparator<Position>() {
		public int compare(Position p1, Position p2) {
			return 0;
		}
	};

	Comparator<Position> best = new Comparator<Position>() {
		public int compare(Position p1, Position p2) {
			return ((p1.getHeight() - p2.getHeight()) + (p1.getWidth() - p2.getWidth())
					+ (p1.getDepth() - p2.getDepth()));
		}
	};

	void printArrow() {
		for (int i = 0; i < 2; ++i) {
			System.out.println("        " + "||" + "       ");
		}
		System.out.println("       \\" + "||" + "/       ");
		System.out.println("        \\/       ");
	}

	public static void main(String[] args) {
		Demo myDemo = new Demo();
		myDemo.Run();
	}

}
