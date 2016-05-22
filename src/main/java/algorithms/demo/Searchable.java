package algorithms.demo;

import java.util.List;

import algorithms.maze.Position;

public interface Searchable {
	
	Position getGoalPosition();
	
	List<Position> getPossibleMovesPositions(Position pos);
	
}
