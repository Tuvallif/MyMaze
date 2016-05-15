package algorithms.mazeGenerators;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algorithms.maze.Maze3d;
import algorithms.maze.MyMaze3d;
import algorithms.maze.MyPosition;
import algorithms.maze.Position;

public class MyMaze3dGenerator extends AbstractMaze3dGenerator implements Maze3dGenerator {
	static final Logger logger = LoggerFactory.getLogger(MyMaze3dGenerator.class);
	List<Position> nextPositions;
	Set<Position> visited;
	Maze3d result;

	public MyMaze3dGenerator() {

		nextPositions = new LinkedList<Position>();
		visited = new HashSet<Position>();
		generate();
	}

	public Maze3d generate() {
		result = new MyMaze3d(new MyPosition(0, 0, 0), new MyPosition(3, 3, 3), 4, 4, 4, true);
		Position curPos = result.getGoalPosition();

		nextPositions.add(curPos);

		while (!nextPositions.isEmpty()) {
			// checking the cell - why doesn't it recognize curpos
			// (000)????should i implement myself?
			if (visited.contains(curPos) == false) {
				visited.add(curPos);
				List<Position> neighbors = result.getNeighborPositions(curPos);
				for (Position pos : neighbors) {
					if (!visited.contains(pos) && result.getValueAtPosition(pos) == 1) {
						nextPositions.add(pos);
					}
				}

				determineValueForCell(curPos);
				nextPositions.remove(curPos);
			}
			curPos = geRandomPosition();

		}
		printMaze();
		return result;
	}

	private Position geRandomPosition() {
		Position result;
		if (nextPositions.isEmpty() == true) {
			result = null;
		} else {
			Random rand = new Random(System.currentTimeMillis());
			int index = rand.nextInt(nextPositions.size());
			//result = nextPositions.remove(nextPositions.size());
			result = nextPositions.remove(index);
		}
		//printMaze();
		return result;
	}
	private void printMaze(){
		
		for(int i = 0; i < this.result.getHeight(); ++i){
			System.out.println(" i ="+ i);
			for(int j = 0 ; j< this.result.getWidth(); ++j){
				for(int k = 0; k < this.result.getDepth(); ++k){
					//System.out.print(" i ="+ i);
					//System.out.print(" j ="+ j);
					//System.out.print(" k ="+ k + "value is  ");
					System.out.print(this.result.getValueAtPosition(new MyPosition(i, j, k)));
				}System.out.println();
			}System.out.println();
		}
	}

	private boolean determineValueForCell(Position p) {
		List<Position> myList = result.getNeighborPositions(p);
		boolean hasOneVisitedWall = false;
		int unvisitedNeighbors = 0, visitedNeighbores = 0;

		while (myList.isEmpty() == false) {
			Position currNeighbor = myList.remove(0);
			if (this.result.getValueAtPosition(currNeighbor) == 0) {
				visitedNeighbores++;
			}else{
				unvisitedNeighbors++;
			}
		}
		if(p.getDepth() == 0 && p.getHeight() == 0 && p.getWidth() == 0){
			int i = 3;
		}
		if ((unvisitedNeighbors >= 1 && visitedNeighbores == 1) || p.equals(result.getStartPosition()) || p.equals(result.getGoalPosition())) {
			hasOneVisitedWall = true;
			result.setWall(p, false);
		}
		logger.debug("value for cell {} {}", p, result.getValueAtPosition(p));
		return hasOneVisitedWall;
	}

}
