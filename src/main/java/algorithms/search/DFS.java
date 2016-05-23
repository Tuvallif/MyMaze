package algorithms.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.naming.spi.DirStateFactory.Result;

import algorithms.demo.Searchable;
import algorithms.demo.Vertex;
import algorithms.demo.mySearchable;
import algorithms.maze.Maze3d;
import algorithms.maze.Position;

public class DFS extends AbstractSearch{

	Searchable myMaze;
	
	public DFS(Searchable maze){
		this.myMaze = maze;
	}
	
	public List<Position> FindPath() {
		List<Position> result = new LinkedList<Position>();
		Stack<Position> toVisit = new Stack<Position>();
		//creating the Position from the goal position
		Position current = myMaze.getGoalPosition();
		//adding it to the list toVisit
		toVisit.push(current);
		//adding it to the vertexList
		Vertex start = new Vertex(current, null);
		myMaze.addToVertexList(start);
		
		while(!toVisit.isEmpty() && !current.equals(myMaze.getStartPosition())){
			current = toVisit.pop();			
			result.add(current);
			List<Position> possibleMoves = myMaze.getPossibleMovesPositions(current);
			for(Position p:possibleMoves){
				if(!result.contains(p) ){
					toVisit.push(p);
					myMaze.addToVertexList(p, current);
				}
			}
		}
		
		return myMaze.getPath();
		//return result;
	}
	
	
	Comparator<Position> comparator = new Comparator<Position>() {
		
	    public int compare(Position toCalculate , Position goal) {
			return 0;
	    }
	};
	
//	private  List<Position> getLastNeighbor( List<Position> listToCheck){
//		Position helper = myMaze.getGoalPosition();
//		while(!helper.equals(myMaze.getStartPosition()){
//			helper = myMaze.getPossibleMovesPositions(helper);
//			listToCheck.remove(0);
//			helper = listToCheck.get(0);
//			
//		}
//
//		return listToCheck;
//		
//	}

}
