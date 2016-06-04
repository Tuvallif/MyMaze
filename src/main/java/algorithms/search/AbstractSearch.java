package algorithms.search;

import java.util.Comparator;
import java.util.List;

import algorithms.demo.Searchable;
import algorithms.maze.Position;

/**
 * @author Tuval Lifshitz
 *
 */
public abstract class AbstractSearch implements Search {

	/**
	 * the path that was created from the search algorithem
	 */
	List<Position> myPath;
	
	/**
	 * the srchable type to seach in
	 */
	Searchable srchbl;
	
	/**
	 * a comparator to check which one is bigger
	 */
	Comparator<Position> myComp;

	/* (non-Javadoc)
	 * @see algorithms.search.Search#compare(algorithms.maze.Position, algorithms.maze.Position)
	 */
	@Override
	public int compare(Position e1, Position e2) {
		return 0;
	}

	/**
	 * the general comparator to use
	 */
	Comparator<Position> c = new Comparator<Position>() {
		public int compare(Position p1, Position p2) {
			return this.compare(p1, p2);
		}
	};

}
