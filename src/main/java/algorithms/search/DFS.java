package algorithms.search;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import algorithms.maze.Maze3d;
import algorithms.maze.Position;

public class DFS {
	public List<Position> FindPath(Maze3d myMaze) {
		List<Position> result = new LinkedList<Position>();
		Stack<Position> toVisit = new Stack<Position>();
		toVisit.push(myMaze.getGoalPosition());
		
		while(!toVisit.isEmpty()){
			Position current = toVisit.pop();
			result.add(current);
			List<Position> possibleMoves = myMaze.getPossibleMovesPositions(current);
			for(Position p:possibleMoves){
				if(!result.contains(p)){
					toVisit.push(p);
				}
			}
		}
		return result;
	}

}
