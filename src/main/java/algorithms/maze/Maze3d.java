package algorithms.maze;

import java.util.List;

import algorithms.exceptions.MyPositionIsWallException;

public interface Maze3d {

	Position getStartPosition();

	String[] getPossibleMoves(Position p) throws MyPositionIsWallException;

	List<Position> getPossibleMovesPositions(Position p) throws MyPositionIsWallException;

	Position getGoalPosition();

	int[][] getCrossSectionByX(int x);

	int[][] getCrossSectionByY(int y);

	int[][] getCrossSectionByZ(int z);

	int getHeight();

	int getWidth();

	int getDepth();

	void setWall(Position p, boolean wall);

	List<Position> getNeighborPositions(Position p);

	// List<Position> getNeighborAtLevel(Position p) throws
	// MyPositionIsWallException ;

	int getValueAtPosition(Position p);
	
	void printMaze();

}
