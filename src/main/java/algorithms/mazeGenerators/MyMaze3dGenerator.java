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
		Position curPos = result.getStartPosition();

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

		return result;
	}

	private Position geRandomPosition() {
		Position result;
		if (nextPositions.isEmpty() == true) {
			result = null;
		} else {
			Random rand = new Random(System.currentTimeMillis());
			int index = rand.nextInt(nextPositions.size());
			result = nextPositions.get(index);
		}

		return result;
	}

	private boolean determineValueForCell(Position p) {
		List<Position> myList = result.getNeighborPositions(p);
		boolean hasOneVisitedWall = false;
		int unvisitedNeighbors = 0, visitedNeighbores = 0;

		while (myList.isEmpty() == false) {
			Position currNeighbor = myList.remove(0);
			if (!visited.contains(currNeighbor)) {
				unvisitedNeighbors++;
			}else{
				visitedNeighbores++;
			}
		}
		if ((unvisitedNeighbors >= 1 && visitedNeighbores == 1) || p == result.getStartPosition() || p == result.getGoalPosition()) {
			hasOneVisitedWall = true;
			result.setWall(p, false);
		}
		logger.debug("value for cell {} {}", p, result.getValueAtPosition(p));
		return hasOneVisitedWall;
	}

}
