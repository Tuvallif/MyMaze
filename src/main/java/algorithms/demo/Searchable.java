package algorithms.demo;

import java.util.List;

import algorithms.maze.Position;

public interface Searchable {
	
	Position getGoalPosition();
	
	Position getStartPosition();
	
	List<Position> getPossibleMovesPositions(Position pos);
	
	void addToVertexList(Position toAdd, Vertex father);
	
	void addToVertexList(Vertex toAdd);
	
	List<Position> getPath();
	
	public Vertex findVerInPath(Position p);
	
	public void addToVertexList(Position toAdd, Position father);
	
}
