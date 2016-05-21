package algorithms.search;

import java.util.LinkedList;
import java.util.List;

import algorithms.maze.Maze3d;
import algorithms.maze.Position;

public class NewBFS implements Search{

	public List<Position> FindPath(Maze3d myMaze) {
		List<Position> result = new LinkedList<Position>();
		List<Position> toVisit = new LinkedList<Position>();
		toVisit.add(myMaze.getGoalPosition());
		
		while(!toVisit.isEmpty()){
			Position current = toVisit.remove(0);
			result.add(current);
			List<Position> possibleMoves = myMaze.getPossibleMovesPositions(current);
			for(Position p:possibleMoves){
				if(!result.contains(p)){
					toVisit.add(p);
				}
			}
		}
		return result;
	}

}
