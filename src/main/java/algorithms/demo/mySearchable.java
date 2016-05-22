package algorithms.demo;

import java.util.List;
import algorithms.maze.Maze3d;
import algorithms.maze.*;

public class mySearchable implements Searchable {

	Maze3d myMaze;
	
	public mySearchable(Maze3d maze) {
		this.myMaze = maze;
	}
	public Position getGoalPosition() {
		return myMaze.getGoalPosition();
	}
	public List<Position> getPossibleMovesPositions(Position pos) {
		return myMaze.getPossibleMovesPositions(pos);
	}
	
	

}
