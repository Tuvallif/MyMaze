package algorithms.maze;

public interface Position extends Comparable<Position> {
	int getHeight();

	int getWidth();

	int getDepth();

	String printPos();

	int compareTo(Position p);
}
