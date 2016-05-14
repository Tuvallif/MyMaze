package algorithms.maze;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import algorithms.exceptions.MyPositionIsWallException;

public class MyMaze3d implements Maze3d {
	private int[][][] myBoard;
	private Position start;
	private Position goal;

	private int height;
	private int width;
	private int depth;

	public MyMaze3d(Position start, Position goal, int height, int width, int depth, boolean setAsWall) {
		super();
		this.start = start;
		this.goal = goal;
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.myBoard = new int[height][width][depth];
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				for (int k = 0; k < depth; ++k) {
					this.myBoard[i][j][k] = setAsWall ? 1 : 0;
				}
			}
		}
	}

	public Position getStartPosition() {
		return this.start;
	}

	public Position getGoalPosition() {
		return this.goal;
	}

	private boolean cellExists(int currHeight, int currWidth, int currDepth) {
		return (currHeight < this.height && currHeight >= 0 && currWidth < this.width && currWidth >= 0
				&& currDepth < this.depth && currDepth >= 0);
	}

	private boolean cellIsNotWall(int currHeight, int currWidth, int currDepth) {
		return (cellExists(currHeight, currWidth, currDepth) && myBoard[currHeight][currWidth][currDepth] == 0);
	}

	public int[][] getCrossSectionByX(int currHeight) {
		int[][] res = new int[this.width][this.depth];

		for (int i = 0; i < this.width; ++i) {
			for (int j = 0; j < this.depth; ++j) {
				res[i][j] = this.myBoard[currHeight][i][j];
			}
		}

		return res;
	}

	public int[][] getCrossSectionByY(int currWidth) {
		int[][] res = new int[this.height][this.depth];

		for (int i = 0; i < this.height; ++i) {
			for (int j = 0; j < this.depth; ++j) {
				res[i][j] = this.myBoard[i][currWidth][j];
			}
		}

		return res;
	}

	public int[][] getCrossSectionByZ(int currDepth) {
		int[][] res = new int[this.height][this.width];

		for (int i = 0; i < this.height; ++i) {
			for (int j = 0; j < this.width; ++j) {
				res[i][j] = this.myBoard[i][j][currDepth];
			}
		}

		return res;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getDepth() {
		return depth;
	}

	public void setWall(Position p, boolean wall) {
		if (cellExists(p.getHeight(), p.getWidth(), p.getDepth())) {
			this.myBoard[p.getHeight()][p.getWidth()][p.getDepth()] = wall ? 1 : 0;
		}
	}
	


	public String[] getPossibleMoves(Position p) throws MyPositionIsWallException {
		// If position is a wall
		if (!cellExists(p.getHeight(), p.getWidth(), p.getDepth())) {
			throw new MyPositionIsWallException();
		}
		List<Position> neighbors = getNeighborPositions(p);
		List<Position> possibleMoves = new LinkedList<Position>();
		for (Position pos : neighbors) {
			if (cellIsNotWall(pos.getHeight(), pos.getWidth(), pos.getDepth())) {
				possibleMoves.add(pos);
			}
		}

		String[] result = new String[possibleMoves.size()];
		int i = 0;
		for (Position position : possibleMoves) {
			result[i++] = position.toString();
		}

		return result;
	}

	public List<Position> getNeighborPositions(Position p) throws MyPositionIsWallException {

		List<Position> possibleMoves = new LinkedList<Position>();

		// Check up/down move

		if (cellExists(p.getHeight() + 1, p.getWidth(), p.getDepth())) {
			possibleMoves.add(new MyPosition(p.getHeight() + 1, p.getWidth(), p.getDepth()));
		}
		if (cellExists(p.getHeight() - 1, p.getWidth(), p.getDepth())) {
			possibleMoves.add(new MyPosition(p.getHeight() - 1, p.getWidth(), p.getDepth()));
		}
		// Check left/right move
		if (cellExists(p.getHeight(), p.getWidth() + 1, p.getDepth())) {
			possibleMoves.add(new MyPosition(p.getHeight(), p.getWidth() + 1, p.getDepth()));
		}
		if (cellExists(p.getHeight(), p.getWidth() - 1, p.getDepth())) {
			possibleMoves.add(new MyPosition(p.getHeight(), p.getWidth() - 1, p.getDepth()));
		}
		// Check forward/backwards move
		if (cellExists(p.getHeight(), p.getWidth(), p.getDepth() + 1)) {
			possibleMoves.add(new MyPosition(p.getHeight(), p.getWidth(), p.getDepth() + 1));
		}
		if (cellExists(p.getHeight(), p.getWidth(), p.getDepth() - 1)) {
			possibleMoves.add(new MyPosition(p.getHeight(), p.getWidth(), p.getDepth() - 1));
		}

		return possibleMoves;
	}

	// public List<Position> getNeighborAtLevel(Position p) throws
	// MyPositionIsWallException {
	// // If position is a wall
	// if (!cellIsNotWall(p.getHeight(), p.getWidth(), p.getDepth())) {
	// throw new MyPositionIsWallException();
	// }
	// List<Position> possibleMoves = new LinkedList<Position>();
	//
	// // Check up/down move
	//
	// if (cellIsNotWall(p.getHeight() + 1, p.getWidth(), p.getDepth())) {
	// possibleMoves.add(new MyPosition(p.getHeight() + 1, p.getWidth(),
	// p.getDepth()));
	// }
	// if (cellIsNotWall(p.getHeight() - 1, p.getWidth(), p.getDepth())) {
	// possibleMoves.add(new MyPosition(p.getHeight() - 1, p.getWidth(),
	// p.getDepth()));
	// }
	// // Check left/right move
	// if (cellIsNotWall(p.getHeight(), p.getWidth() + 1, p.getDepth())) {
	// possibleMoves.add(new MyPosition(p.getHeight(), p.getWidth() + 1,
	// p.getDepth()));
	// }
	// if (cellIsNotWall(p.getHeight(), p.getWidth() - 1, p.getDepth())) {
	// possibleMoves.add(new MyPosition(p.getHeight(), p.getWidth() - 1,
	// p.getDepth()));
	// }
	//
	// return possibleMoves;
	// }

	public int getValueAtPosition(Position p) {
		int result = 1;
		if (cellExists(p.getHeight(), p.getWidth(), p.getDepth())) {
			result = this.myBoard[p.getHeight()][p.getWidth()][p.getDepth()];
		}

		return result;
	}

}
