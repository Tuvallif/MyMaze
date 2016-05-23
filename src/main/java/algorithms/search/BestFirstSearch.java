package algorithms.search;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import algorithms.maze.Maze3d;
import algorithms.maze.Position;

public class BestFirstSearch extends AbstractSearch {

	private Search src;

	public BestFirstSearch(Search searchType) {
		this.src = searchType;
	}

	public List<Position> FindPath() {
		return src.FindPath();
	}
	



}
