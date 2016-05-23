package algorithms.search;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import algorithms.demo.Searchable;
import algorithms.demo.Vertex;
import algorithms.maze.Position;

public class BFS extends AbstractSearch{

	Comparator<Position> myComp;
	Searchable searchableBoard;

	public BFS(Searchable srcbrd,Comparator<Position> comp){
		this.searchableBoard = srcbrd;
		this.myComp = comp;
	}
	
	public List<Position> FindPath() {
		List<Position> result = new LinkedList<Position>();
		Queue<Position> toVisit = new PriorityQueue<Position>(100 , myComp);
		//creating the Position from the goal position
		Position current = searchableBoard.getGoalPosition();
		//adding it to the list toVisit
		toVisit.add(current);
		//adding it to the vertexList
		Vertex start = new Vertex(current, null);
		searchableBoard.addToVertexList(start);

		while(!toVisit.isEmpty()){
			current = toVisit.poll();
			result.add(current);
			List<Position> possibleMoves = searchableBoard.getPossibleMovesPositions(current);
			for(Position p:possibleMoves){
				if(!result.contains(p)){
					toVisit.add(p);
					searchableBoard.addToVertexList(p, current);
				}
			}
		}
		return searchableBoard.getPath();
		//return result;
	}



}


