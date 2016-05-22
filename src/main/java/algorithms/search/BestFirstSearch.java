package algorithms.search;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import algorithms.maze.Maze3d;
import algorithms.maze.Position;

public class BestFirstSearch implements Search {

	private Search src;

	BestFirstSearch(Search searchType) {
		this.src = searchType;
	}

	public List<Position> FindPath() {
		return src.FindPath();
	}
	
	Comparator<Position> comparator = new Comparator<Position>() {
		
	    public int compare(Position toCalculate , Position goal) {
			return ((goal.getHeight() - toCalculate.getHeight()) + (goal.getWidth() - toCalculate.getWidth())
					+ (goal.getDepth() - toCalculate.getDepth()));
	    }
	};


}
