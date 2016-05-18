package algorithms.search;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import algorithms.maze.Maze3d;
import algorithms.maze.MyPosition;
import algorithms.maze.Position;
import algorithms.search.PositionHelper;
import algorithms.search.PositionHelper.Color;

public class BFS implements Search{

	Queue<Position> queue;
	Maze3d myMaze;
	PositionHelper [][][] boardHelper;
	List<Position> visited;
	
	public List<Position>  FindPath(Maze3d maze) {
		myMaze = maze;
		visited = new LinkedList<Position>();
		Position Start = myMaze.getStartPosition();
		Position Goal = myMaze.getGoalPosition();
		queue = new LinkedList<Position>();
		List<PositionHelper>  toReturn = new LinkedList<PositionHelper>();
		boardHelper = createMazeHelper();
		return FindHelper(Start,Goal);
	}
	
	private  List<Position> FindHelper(Position start, Position Goal){
		//checking first the start of maze
		if(start == myMaze.getStartPosition()){
			//initialize start 
			boardHelper[start.getHeight()][start.getWidth()][start.getDepth()].setFather(null);
			boardHelper[start.getHeight()][start.getWidth()][start.getDepth()].setMycolor(Color.BLACK);
			boardHelper[start.getHeight()][start.getWidth()][start.getDepth()].setMydistance(0);
			boardHelper[start.getHeight()][start.getWidth()][start.getDepth()].setValue(0);//makes sense to have value?
		}
		search(start);
		while(queue.isEmpty() == false){
			Position curPos = queue.poll();
		}
		else{
			
		}

		//if you are here then start is not in visited
		visited.add(start);
		start.
		//going over the list
		for(Position p: myMaze.getNeighborPositions(start)){
			PositionHelper ph = p;
			if(p == myMaze.getGoalPosition()){

			}
			FindHelper(p, Goal);
		}
	}

	
	private void search(Position p){
		//mark the cell as visited
		boardHelper[p.getHeight()][p.getWidth()][p.getDepth()].setMycolor(Color.BLACK);
		visited.add(p);
		for(Position neiPos : myMaze.getNeighborPositions(p)){
			//if not a wall
			if(myMaze.getValueAtPosition(neiPos) == 0){
				queue.add(neiPos);
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
