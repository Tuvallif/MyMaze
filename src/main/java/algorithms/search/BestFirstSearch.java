package algorithms.search;

import java.util.List;

import algorithms.maze.Position;

public class BestFirstSearch extends AbstractSearch {

	private Search src;

	public BestFirstSearch(Search searchType) {
		this.src = searchType;
		myComp = this.c;
	}

	public List<Position> FindPath() {
		return src.FindPath();
	}

}
