package algorithms.search;

import java.util.LinkedList;
import java.util.List;
import algorithms.maze.Maze3d;
import algorithms.maze.Position;
import algorithms.search.PositionHelper;

public class BFS implements Search{

	List<PositionHelper> toReturn;
	Maze3d myMaze;
	int [][][] boardHelper;
	public List<Position>  FindPath(Maze3d maze) {
		myMaze = maze;
		Position Start = myMaze.getStartPosition();
		Position Goal = myMaze.getGoalPosition();
		toReturn = new LinkedList<PositionHelper>();
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
				PositionHelper ph = p;
				if(p == myMaze.getGoalPosition()){
					
				}
				FindHelper(p, Goal);
			}
		}
	}
	
	private PositionHelper [][][] createMazeHelper(){
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
	
	private PositionHelper[][][] initializeBoard(int [][][] Board){
		PositionHelper[][][] myBoard = new PositionHelper[myMaze.getHeight()][ myMaze.getWidth()][ myMaze.getDepth()];
		for(int i = 0; i < myMaze.getHeight(); i++){
			for(int j = 0; j < myMaze.getWidth(); j++){
				for(int k = 0; k < myMaze.getDepth(); k++){
					if (Board[i][j][k] == 0){
						myBoard[i][j][k].setValue(Integer.MAX_VALUE);
					}
					else{
						myBoard[i][j][k].setValue(-1);//stands for wall
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
