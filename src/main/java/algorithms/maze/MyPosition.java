package algorithms.maze;

public class MyPosition implements Position {
	private int height;
	private int width;
	private int depth;
	
	public MyPosition(int height, int width, int depth) {
		super();
		this.height = height;
		this.width = width;
		this.depth = depth;
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

	public String toString() {
		return "Position [height=" + height + ", width=" + width + ", depth=" + depth + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + depth;
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyPosition other = (MyPosition) obj;
		if (depth != other.depth)
			return false;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
	

}
