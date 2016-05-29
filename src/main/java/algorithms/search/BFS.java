package algorithms.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import algorithms.demo.Searchable;
import algorithms.demo.Vertex;
import algorithms.maze.Position;

public class BFS extends AbstractSearch {

	Searchable searchableBoard;

	// Comparator<Position> c = new Comparator<Position>() {
	// public int compare(Position p1, Position p2){
	// return BFS.this.compare(p1, p2);
	// }
	// };

	public BFS(Searchable srcbrd, Comparator<Position> myComparator) {
		this.searchableBoard = srcbrd;
		this.myComp = myComparator;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Search#FindPath()
	 */
	public List<Position> FindPath() {
		List<Position> result = new LinkedList<Position>();
		PriorityQueue<Position> toVisit = new PriorityQueue<Position>(myComp);
		// creating the Position from the goal position
		Position current = searchableBoard.getGoalPosition();
		// adding it to the list toVisit
		toVisit.add(current);
		// adding it to the vertexList
		Vertex start = new Vertex(current, null);
		searchableBoard.addToVertexList(start);

		while (!toVisit.isEmpty() && !current.equals(searchableBoard.getStartPosition())) {
			current = toVisit.poll();
			result.add(current);
			List<Position> possibleMoves = searchableBoard.getPossibleMovesPositions(current);
			for (Position p : possibleMoves) {
				if (!result.contains(p)) {
					toVisit.add(p);
					searchableBoard.addToVertexList(p, current);
				}
			}
		}
		myPath = searchableBoard.CreatePositionPathFromVertex();
		return myPath;
		// return result;
	}

}
