package algorithms.mazeGenerators;

import algorithms.maze.Maze3d;

public abstract class AbstractMaze3dGenerator implements Maze3dGenerator {

	AbstractMaze3dGenerator() {
	}

	public abstract Maze3d generate();

	public String measureAlgorithmTime() {
		long timeAtBegginig = System.currentTimeMillis();
		generate();
		long timeAtEnd = System.currentTimeMillis();
		long totalTime = timeAtEnd - timeAtBegginig;

		return Long.toString(totalTime);
	}

}