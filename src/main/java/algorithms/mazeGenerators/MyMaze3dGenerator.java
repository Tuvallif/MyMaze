package algorithms.mazeGenerators;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
	Random rand;
	final int SIZE_OF_MAZE = 7;

	public MyMaze3dGenerator() {

		nextPositions = new LinkedList<Position>();
		visited = new HashSet<Position>();
		rand = new Random(System.currentTimeMillis());
		// generate();
	}

	public Maze3d generate() {
		result = new MyMaze3d(new MyPosition(0, 0, 0),
				new MyPosition(SIZE_OF_MAZE - 1, SIZE_OF_MAZE - 1, SIZE_OF_MAZE - 1), SIZE_OF_MAZE, SIZE_OF_MAZE,
				SIZE_OF_MAZE, true);
		Position curPos = result.getGoalPosition();

		nextPositions.add(curPos);

		while (!nextPositions.isEmpty()) {
			// checking the cell - why doesn't it recognize curpos
			// (000)????should i implement myself?
			curPos = nextPositions.remove(rand.nextInt(nextPositions.size()));

			logger.debug("{}", curPos);
			visited.add(curPos);

			List<Position> neighbors = result.getNeighborPositions(curPos);
			if (isVertex(curPos)) {
				// nextPositions.add(getRandomPosition(curPos));
				result.setWall(curPos, false);
				for (Position pos : neighbors) {
					if (!visited.contains(pos) && (!isAlwaysWall(pos))) {
						nextPositions.add(pos);
					}
				}
			} else {
				int i = 0;
				for (Position pos : neighbors) {
					if (!visited.contains(pos) && (!isAlwaysWall(pos))) {
						nextPositions.add(pos);
					}
					if (visited.contains(pos) && (!isAlwaysWall(pos))) {
						i++;
					}

				}
				if (i == 2) {
					result.setWall(curPos, false);
				}
			}
			Position door = getRandomPosition(curPos);
			result.setWall(door, false);
			// printMaze();
			// determineValueForCell(curPos);

			// curPos = geRandomPosition();

		}
		printMaze();
		return result;
	}

	private Position getRandomPosition(Position p) {
		Position resultPos;
		List<Position> neighbors = result.getNeighborPositions(p);
		while (true) {
			int index = rand.nextInt(neighbors.size());
			resultPos = neighbors.get(index);
			if (!isAlwaysWall(resultPos)) {
				return resultPos;
			}
		}

		// printMaze()
	}

	private void printMaze() {

		for (int i = 0; i < this.result.getHeight(); ++i) {
			System.out.println(" Height =" + i);
			for (int j = 0; j < this.result.getWidth(); ++j) {
				for (int k = 0; k < this.result.getDepth(); ++k) {
					// System.out.print(" i ="+ i);
					// System.out.print(" j ="+ j);
					// System.out.print(" k ="+ k + "value is ");
					System.out.print(this.result.getValueAtPosition(new MyPosition(i, j, k)));
				}
				System.out.println();
			}
			System.out.println();
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
			} else {
				unvisitedNeighbors++;
			}
		}
		if (p.getDepth() == 0 && p.getHeight() == 0 && p.getWidth() == 0) {
			int i = 3;
		}
		if ((unvisitedNeighbors >= 1 && visitedNeighbores == 1) || p.equals(result.getStartPosition())
				|| p.equals(result.getGoalPosition())) {
			hasOneVisitedWall = true;
			result.setWall(p, false);
		}
		logger.debug("value for cell {} {}", p, result.getValueAtPosition(p));
		return hasOneVisitedWall;
	}

	private boolean isAlwaysWall(Position toCheck) {
		int numberOfOdd = toCheck.getHeight() % 2 + toCheck.getWidth() % 2 + toCheck.getDepth() % 2;
		return numberOfOdd > 1;
	}

	private boolean isEdge(Position toCheck) {
		int numberOfOdd = toCheck.getHeight() % 2 + toCheck.getWidth() % 2 + toCheck.getDepth() % 2;
		return numberOfOdd == 1;
	}

	private boolean isVertex(Position toCheck) {
		int numberOfOdd = toCheck.getHeight() % 2 + toCheck.getWidth() % 2 + toCheck.getDepth() % 2;
		return numberOfOdd == 0;
	}

}
