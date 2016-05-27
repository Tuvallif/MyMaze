package algorithms.search;

import java.util.Comparator;
import java.util.List;

import algorithms.maze.Position;

public abstract class AbstractSearch implements Search {

	List<Position> myPath;
	Comparator<Position> myComp;

	@Override
	public int compare(Position e1, Position e2) {
		return 0;
	}

	Comparator<Position> c = new Comparator<Position>() {
		public int compare(Position p1, Position p2) {
			return this.compare(p1, p2);
		}
	};

}
