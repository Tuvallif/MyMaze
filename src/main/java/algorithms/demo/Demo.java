package algorithms.demo;

import algorithms.maze.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;

public class Demo {
	
	public void Run(){
		
		Maze3dGenerator myGernerator = new MyMaze3dGenerator();
		Maze3d myMaze = myGernerator.generate();
		
		
	}

}
