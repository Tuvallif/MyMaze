package algorithms.search;

import java.util.List;

import algorithms.maze.Position;

public interface Search {
	
	List<Position> FindPath(Position Start, Position Goal);
	
	
}

