package algorithms.search;

import java.util.List;

import algorithms.maze.Maze3d;
import algorithms.maze.Position;

public class BestFirstSearch implements Search{

	private Search src;
	BestFirstSearch(Search searchType){
		this.src = searchType;
	}
	public List<Position> FindPath(Maze3d myMaze) {
		// how do i determine the priority?
		return src.FindPath(myMaze);
	}

}
