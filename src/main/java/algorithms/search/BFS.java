package algorithms.search;

import java.util.LinkedList;
import java.util.List;
import algorithms.maze.Maze3d;
import algorithms.maze.Position;

public class BFS implements Search{

	List<Position> toReturn;
	Maze3d myMaze;
	int [][][] boardHelper;
	public List<Position>  FindPath(Maze3d maze) {
		myMaze = maze;
		Position Start = myMaze.getStartPosition();
		Position Goal = myMaze.getGoalPosition();
		toReturn = new LinkedList<Position>();
		boardHelper = createMazeHelper();
		return FindHelper(Start,Goal);
	}
	
	private  List<Position> FindHelper(Position start, Position Goal){
		if(start == Goal){
			toReturn.add(Goal);
		}
		else if(start == null){
			 
		}
		else{
			
			for(Position p: myMaze.getNeighborPositions(start)){
				FindHelper(p, Goal);
			}
		}
	}
	
	private int [][][] createMazeHelper(){
		int myHeight = myMaze.getHeight();
		int myWidth = myMaze.getWidth();
		int myDepth = myMaze.getDepth();
		int [][][]myBoard = new int[myHeight][myWidth][myDepth];
		for(int i = 0 ;i < myHeight; i++){
			myBoard[i] = myMaze.getCrossSectionByX(i);
		}
		assignZeroAtStart(myMaze.getStartPosition());
		
		return initializeBoard(myBoard);
	}
	
	private int[][][] initializeBoard(int [][][] Board){
		int[][][] myBoard = Board;
		for(int i = 0; i < myMaze.getHeight(); i++){
			for(int j = 0; j < myMaze.getWidth(); j++){
				for(int k = 0; k < myMaze.getDepth(); k++){
					if (myBoard[i][j][k] == 0){
						myBoard[i][j][k] = Integer.MAX_VALUE;
					}
					else{
						myBoard[i][j][k] = -1;//stands for wall
					}
				}
			}
		}
		
		return myBoard;
	}
	
	private void assignZeroAtStart(Position start){
		
		int startHeight = start.getHeight();
		int startWidth = start.getWidth();
		int startDepth = start.getDepth();
		//assigning zero
		boardHelper[startHeight][startWidth][startDepth] = 0;
	}

}
