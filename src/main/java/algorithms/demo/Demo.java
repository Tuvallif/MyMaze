package algorithms.demo;

import java.util.List;

import algorithms.maze.Maze3d;
import algorithms.maze.Position;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.search.DFS;
import algorithms.search.BFS;
import algorithms.search.Search;

public class Demo {
	
	public void Run(){
		
		Maze3dGenerator myGernerator = new MyMaze3dGenerator();
		Maze3d myMaze = myGernerator.generate();
		myMaze.printMaze();
		Searchable Srchble = new mySearchable(myMaze);
		Search srcDFS = new DFS(Srchble);
		Search srcBFS = new BFS(Srchble, BFS.C);
		Search srcBestFS = new DFS(Srchble);
		
	}

}
