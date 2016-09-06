package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import algorithms.maze.Maze3d;
import algorithms.maze.MyMaze3d;
import algorithms.maze.MyPosition;
import algorithms.maze.Position;

/**
 * Created by style on 20/05/2016.
 */
public class PrimMaze3dGenerator extends AbstractMaze3dGenerator implements Maze3dGenerator {
	private static final Logger logger = LoggerFactory.getLogger(PrimMaze3dGenerator.class);
	MyMaze3d maze;
	private List<Position> walls;
	private int height;
	private int width;
	private int depth;
	private Position start;
	private Position goal;

	public PrimMaze3dGenerator(int height, int width, int depth) {
		this.height = height;
		this.width = width;
		this.depth = depth;
		start = new MyPosition(0, 0, 0);
		goal = new MyPosition(9, 9, 0);
	}
	
	public PrimMaze3dGenerator(Position start, Position goal, int height, int width, int depth) {
		
		this.height = height;
		this.width = width;
		this.depth = depth;
		this.start = start;
		this.goal = goal;
	}

	public Maze3d generate() {
		maze = new MyMaze3d(start, goal, height, width, depth, true);
		maze.setWall(start, false);
		this.walls = new ArrayList<Position>();
		addWalls(start);

		int num;
		while (!walls.isEmpty()) {
			num = (int) (Math.random() * (this.walls.size()));
			/* Create Maze3d */
			CreateMaze3d(this.walls.get(num));
			this.walls.remove(num);
		}
		printMaze();
		return maze;
	}

	private void printMaze() {
		System.out.println("PRINTING PRIM");
		for (int k = 0; k < this.maze.getDepth(); ++k) {
			System.out.println(" d =" + k);
			for (int i = 0; i < this.maze.getHeight(); ++i) {

				for (int j = 0; j < this.maze.getWidth(); ++j) {

					// System.out.print(" i ="+ i);
					// System.out.print(" j ="+ j);
					// System.out.print(" k ="+ k + "value is ");
					System.out.print(this.maze.getValueAtPosition(new MyPosition(i, j, k)));
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	private void addWalls(Position start) {
		List<Position> neighbors = maze.getNeighborPositions(start);
		for (Position p : neighbors) {
			if (maze.getValueAtPosition(p) == 1) {
				this.walls.add(p);
			}
		}
	}

	private void CreateMaze3d(Position p) {

		char typeWall;
		if (maze.getStartPosition().getHeight() % 2 != p.getHeight() % 2)
			typeWall = 'x';
		else if (maze.getStartPosition().getWidth() % 2 != p.getWidth() % 2)
			typeWall = 'y';
		else
			typeWall = 'z';

		Position a;
		Position b;
		if (typeWall == 'x') {
			a = new MyPosition(p.getHeight() + 1, p.getWidth(), p.getDepth());
			b = new MyPosition(p.getHeight() - 1, p.getWidth(), p.getDepth());
			markPath(a, b, p);
		} else if (typeWall == 'y') {
			a = new MyPosition(p.getHeight(), p.getWidth() + 1, p.getDepth());
			b = new MyPosition(p.getHeight(), p.getWidth() - 1, p.getDepth());
			markPath(a, b, p);
		} else { // Z
			a = new MyPosition(p.getHeight(), p.getWidth(), p.getDepth() + 1);
			b = new MyPosition(p.getHeight(), p.getWidth(), p.getDepth() - 1);
			markPath(a, b, p);
		}
	}

	private void markPath(Position a, Position b, Position p) {
		if (maze.getValueAtPosition(a) != 0 || maze.getValueAtPosition(b) != 0) {
			if (maze.getValueAtPosition(a) != 0) {
				maze.setWall(a, false);
				addWalls(a);
				// maze.setGoalPosition(a);
			} else {
				maze.setWall(b, false);
				addWalls(b);
				// maze.setGoalPosition(b);
			}
			maze.setWall(p, false);
		}
	}

}
