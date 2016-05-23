package algorithms.demo;

import java.util.LinkedList;
import java.util.List;

import algorithms.maze.*;

public class mySearchable implements Searchable {

	private Maze3d myMaze;
	private List <Vertex> myVertexes;
	
	public mySearchable(Maze3d maze) {
		this.myMaze = maze;
		myVertexes = new LinkedList<Vertex>();
	}
	public Position getGoalPosition() {
		return myMaze.getGoalPosition();
	}
	public List<Position> getPossibleMovesPositions(Position pos) {
		return myMaze.getPossibleMovesPositions(pos);
	}
	public Position getStartPosition() {
		return myMaze.getStartPosition();
	}
	
	public void addToVertexList(Position toAdd, Vertex father){
		myVertexes.add(new Vertex(toAdd, father));
	}
	
	
	
	public List<Position> getPath() {
		List<Position> toReturn = new LinkedList<Position>();
		Vertex ver = findVerInPath(myMaze.getStartPosition());;
		while(ver != null){
			toReturn.add(ver.getP());
			ver = ver.getFather();
		}
		
		return toReturn;
	}
	public void addToVertexList(Vertex toAdd) {
		myVertexes.add(toAdd);
		
	}
	public Vertex findVerInPath(Position p) {
		for (int i = 0; i < myVertexes.size(); ++i){
			if(myVertexes.get(i).getP().equals(p)){
				return myVertexes.get(i);
			}
		}
		
		return null;
	}
	public void addToVertexList(Position toAdd, Position father) {
		Vertex f = findVerInPath(father);
		addToVertexList(toAdd, f);
		
	}

	
	

}
