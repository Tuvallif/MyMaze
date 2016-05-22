package algorithms.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import algorithms.demo.Searchable;
import algorithms.maze.Position;

public class NewBFS implements Search{

	Comparator myComp;
	Searchable searchableBoard;

	NewBFS(Searchable srcbrd,Comparator comp){
		this.searchableBoard = srcbrd;
		this.myComp = comp;
	}
	
	public List<Position> FindPath() {
		List<Position> result = new LinkedList<Position>();
		Queue<Position> toVisit = new PriorityQueue<Position>(100 , myComp);
		toVisit.add(searchableBoard.getGoalPosition());

		while(!toVisit.isEmpty()){
			Position current = toVisit.poll();
			result.add(current);
			List<Position> possibleMoves = searchableBoard.getPossibleMovesPositions(current);
			for(Position p:possibleMoves){
				if(!result.contains(p)){
					toVisit.add(p);
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


