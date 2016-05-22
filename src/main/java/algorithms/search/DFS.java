package algorithms.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import algorithms.demo.Searchable;
import algorithms.demo.mySearchable;
import algorithms.maze.Maze3d;
import algorithms.maze.Position;

public class DFS implements Search{

	Searchable myMaze;
	
	DFS(Searchable maze){
		this.myMaze = maze;
	}
	
	public List<Position> FindPath() {
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
	
	Comparator<Position> comparator = new Comparator<Position>() {
		
	    public int compare(Position toCalculate , Position goal) {
			return 0;
	    }
	};

}
