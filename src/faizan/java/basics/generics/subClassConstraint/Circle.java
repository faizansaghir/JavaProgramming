package faizan.java.basics.generics.subClassConstraint;

public class Circle implements Shape{
	private int x,y,radius; 
	
	public Circle(int x,int y,int radius) {
		this.x=x;
		this.y=y;
		this.radius=radius;
	}
	
	@Override
	public void draw() {
		System.out.println("Circle Drawn At ("+x+","+y+") Of Radius "+radius);		
	}

}
