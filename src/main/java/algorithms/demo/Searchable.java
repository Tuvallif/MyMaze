package algorithms.demo;

import java.util.List;

import algorithms.maze.Position;

public interface Searchable {

	Position getGoalPosition();

	Position getStartPosition();

	int getSize();

	List<Position> getPossibleMovesPositions(Position pos);

	void addToVertexList(Position toAdd, Vertex father);

	void addToVertexList(Vertex toAdd);

	List<Position> CreatePositionPathFromVertex();

	public Vertex findVerInPath(Position p);

	public void addToVertexList(Position toAdd, Position father);

}
