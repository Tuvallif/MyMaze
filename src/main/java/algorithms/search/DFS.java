package algorithms.search;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import algorithms.demo.Searchable;
import algorithms.demo.Vertex;
import algorithms.maze.Position;

public class DFS extends AbstractSearch {

	Searchable myMaze;
	List<Vertex> vertexConections;

	public DFS(Searchable maze) {
		this.myMaze = maze;
		vertexConections = new LinkedList<Vertex>();
	}

	public List<Position> FindPath() {
		List<Position> result = new LinkedList<Position>();
		Stack<Position> toVisit = new Stack<Position>();
		// creating the Position from the goal position
		Position current = myMaze.getGoalPosition();
		// adding it to the list toVisit
		toVisit.push(current);
		// adding it to the vertexList
		Vertex start = new Vertex(current, null);
		myMaze.addToVertexList(start);

		while (!toVisit.isEmpty() && !current.equals(myMaze.getStartPosition())) {
			current = toVisit.pop();
			result.add(current);
			List<Position> possibleMoves = myMaze.getPossibleMovesPositions(current);
			for (Position p : possibleMoves) {
				if (!result.contains(p)) {
					toVisit.push(p);
					myMaze.addToVertexList(p, current);
				}
			}
		}

		myPath = myMaze.CreatePositionPathFromVertex();
		return myPath;
		// return result;
	}

}
