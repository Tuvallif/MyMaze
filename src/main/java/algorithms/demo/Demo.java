package algorithms.demo;

import java.util.Comparator;
import java.util.List;

import algorithms.maze.Maze3d;
import algorithms.maze.Position;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.search.DFS;
import algorithms.search.BFS;
import algorithms.search.BestFirstSearch;
import algorithms.search.Search;

public class Demo {
	
	public void Run(){
		
		Maze3dGenerator myGernerator = new MyMaze3dGenerator();
		Maze3d myMaze = myGernerator.generate();
		myMaze.printMaze();
		Searchable Srchble = new mySearchable(myMaze);
		Search srcDFS = new BestFirstSearch(new DFS(Srchble));
		Search srcBFS = new BestFirstSearch(new BFS(Srchble, simpleComparator));
		Search srcBestFS = new BestFirstSearch(new BFS(Srchble, bestComparator));
		
		List<Position> DFSPath = srcDFS.FindPath();
		List<Position> BFSPath = srcBFS.FindPath();
		List<Position> BestFSPath = srcBestFS.FindPath();
		
		System.out.println("dfs");
		printPath(DFSPath);
		System.out.println("bfs");
		printPath(BFSPath);
		System.out.println("bestfs");
		printPath(BestFSPath);
		
		
		
		
	}
	
	private void printPath(List<Position> toPrint){
		if(toPrint == null){
			System.out.println("EMPTY LIST"); 
		}else{
			Position curPos = toPrint.remove(toPrint.size() - 1);
			while(toPrint.isEmpty() == false){
				System.out.println(curPos.printPos());
				printArrow();
				curPos = toPrint.remove(toPrint.size() - 1);
			}
		}
	}
	public Comparator<Position> simpleComparator = new Comparator<Position>() {	
		public int compare(Position toCalculate , Position goal) {
			return 0;
		}
	};
	
	Comparator<Position> bestComparator = new Comparator<Position>() {
		
	    public int compare(Position toCalculate , Position goal) {
			return ((goal.getHeight() - toCalculate.getHeight()) + (goal.getWidth() - toCalculate.getWidth())
					+ (goal.getDepth() - toCalculate.getDepth()));
	    }
	};
	
	void printArrow(){
		for(int i = 0; i < 2 ; ++i ){
			System.out.println("        " + "||" + "       ");
		}
		System.out.println("       \\" + "||" + "/       ");
		System.out.println("        \\/       ");
	}
	
	public static void main(String[]args){
		Demo myDemo = new Demo();
		myDemo.Run();
	}

}
