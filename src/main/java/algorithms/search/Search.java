package algorithms.search;

import java.util.Comparator;
import java.util.List;

import algorithms.maze.Position;

public interface Search extends Comparator<Position> {

	List<Position> FindPath();

	int compare(Position e1, Position e2);

}
