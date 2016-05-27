package algorithms.maze;

import algorithms.mazeGenerators.Maze3dGenerator;

public class Run {
	private static void testMazeGenerator(Maze3dGenerator mg) {
		// prints the time it takes the algorithm to run
		System.out.println(mg.measureAlgorithmTime(/* ... */));
		// generate another 3d maze
		Maze3d maze = mg.generate(/* your parameters */);
		// get the maze entrance
		Position p = maze.getStartPosition();
		// print the position
		System.out.println(p); // format "{x,y,z}"
		// get all the possible moves from a position
		String[] moves = maze.getPossibleMoves(p);
		// print the moves
		for (String move : moves)
			System.out.println(move);
		// prints the maze exit position
		System.out.println(maze.getGoalPosition());
		try {
			// get 2d cross sections of the 3d maze
			int[][] maze2dx = maze.getCrossSectionByX(2);
			// TODO add code to print the array
			int[][] maze2dy = maze.getCrossSectionByY(5);
			// TODO add code to print the array
			int[][] maze2dz = maze.getCrossSectionByZ(0);
			// TODO add code to print the array
			// this should throw an exception!
			maze.getCrossSectionByX(-1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("good!");
		}
	}

	// public static void main(String[] args) {
	// //testMazeGenerator(new SimpleMaze3dGenerator());
	// //testMazeGenerator(new MyMaze3dGenerator());
	//
	// }

}