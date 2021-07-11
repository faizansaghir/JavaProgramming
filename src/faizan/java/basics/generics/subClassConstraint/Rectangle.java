package faizan.java.basics.generics.subClassConstraint;

public class Rectangle implements Shape{

	private int x,y,height,width;
	
	@Override
	public void draw() {
		System.out.println("Rectangle Drawn At ("+x+","+y+") Of Height "+height+" And Width "+width);			
	}

	public Rectangle(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

}
