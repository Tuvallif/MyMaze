package algorithms.search;

import java.util.List;

import algorithms.maze.Maze3d;
import algorithms.maze.Position;

public interface Search {
	
	List<Position> FindPath(Maze3d myMaze);
	
	
}

