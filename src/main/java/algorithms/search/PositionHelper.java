package algorithms.search;

import algorithms.maze.MyPosition;
import algorithms.maze.Position;

public class PositionHelper{


	
	public PositionHelper getFather() {
		return father;
	}

	public void setFather(PositionHelper father) {
		this.father = father;
	}

	public int getMydistance() {
		return mydistance;
	}

	public void setMydistance(int mydistance) {
		this.mydistance = mydistance;
	}

	public Color getMycolor() {
		Color toReturn;
		switch(mycolor){
		case WHITE:
			toReturn = Color.WHITE;
			break;
		case BLACK:
			toReturn = Color.BLACK;
			break;
		case GREY:
			toReturn = Color.GREY;
			break;
		default:
			toReturn = Color.YELLOW;
			System.out.println("illegal color!!!");
			break;
		}
		
		return toReturn;
	}

	public void setMycolor(Color mycolor) {
		this.mycolor = mycolor;
	}

	private PositionHelper father;
	private int mydistance;
	private Color mycolor;
	int value;
	private Position myPos;
	
	public PositionHelper(int height, int width, int depth) {
		mydistance = Integer.MAX_VALUE;
		mycolor = Color.WHITE;
		father = null;
		myPos = new MyPosition(height,width, depth);
	}
	
	public enum Color{
		WHITE, GREY, BLACK, YELLOW
	}

	public void setValue(int value) {
		this.value = value;
		
	}

	public Position getPos() {
		Position toReturn = new MyPosition(myPos.getHeight(), myPos.getWidth(), myPos.getDepth());
		
		return toReturn;	
	}
	
	



}
