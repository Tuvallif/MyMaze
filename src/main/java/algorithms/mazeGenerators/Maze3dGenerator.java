package algorithms.mazeGenerators;

import algorithms.maze.Maze3d;

public interface Maze3dGenerator {

	public String measureAlgorithmTime() ;

	public Maze3d generate() ;

}
