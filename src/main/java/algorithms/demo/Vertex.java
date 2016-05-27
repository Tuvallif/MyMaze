package algorithms.demo;

import algorithms.maze.Position;

public class Vertex {

	private Position p;

	private Vertex father;

	public Vertex(Position self, Vertex father) {
		this.p = self;
		this.father = father;
	}

	public Position getP() {
		return p;
	}

	public void setP(Position p) {
		this.p = p;
	}

	public Vertex getFather() {
		return father;
	}

	public void setFather(Vertex father) {
		this.father = father;
	}

}
