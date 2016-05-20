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
		//List<PositionHelper>  toReturn = new LinkedList<PositionHelper>();
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
			if(boardHelper[curPos.getHeight()][curPos.getWidth()][curPos.getDepth()].getMycolor() != Color.BLACK){
				search(curPos);
			}
			boardHelper[curPos.getHeight()][curPos.getWidth()][curPos.getDepth()].setMycolor(Color.BLACK);
		}
		return buildPath();
	}

	
	private void search(Position p){
		//mark the cell as visited
		PositionHelper myStart = boardHelper[p.getHeight()][p.getWidth()][p.getDepth()];
		
		visited.add(p);
		for(Position neiPos : myMaze.getNeighborPositions(p)){
			//using the cell itself
			PositionHelper neiPosHelper = boardHelper[neiPos.getHeight()][neiPos.getWidth()][neiPos.getDepth()];
			
			//if not a wall
			if(myMaze.getValueAtPosition(neiPos) == 0 && neiPosHelper.getMydistance() > myStart.getMydistance() + 1){
				//changing the value and father
				changePositionHelper(neiPosHelper, myStart);
				neiPosHelper.setMycolor(Color.GREY);
				queue.add(neiPos);
			}
			
			
		}
	}
	
	private void changePositionHelper(PositionHelper toCheck,PositionHelper father){
		toCheck.setFather(father);
		toCheck.setMycolor(Color.GREY);
		toCheck.setMydistance(father.getMydistance() + 1);
		toCheck.setValue(0);
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
		boardHelper[startHeight][startWidth][startDepth].setMydistance(0);
		
	}
	
	private List<Position> buildPath(){
		Position curPos = myMaze.getGoalPosition();
		PositionHelper curPosHelper = boardHelper[curPos.getHeight()][curPos.getWidth()][curPos.getDepth()];
		List<Position> toReturn = new LinkedList<Position>();
		
		while(curPos != myMaze.getStartPosition()){
			toReturn.add(curPos);
			curPosHelper =  curPosHelper.getFather();
			curPos = curPosHelper.getPos();
		}
		
		return toReturn;
	}

}
