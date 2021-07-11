package faizan.java.basics.generics.subClassConstraint;

import java.util.ArrayList;
import java.util.List;

public class WithBoundedWildcard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Circle c1=new Circle(0,0,1);
		Circle c2=new Circle(10,0,5);
		Circle c3=new Circle(0,10,7);
		
		Rectangle r1 = new Rectangle(0,0,5,10);
		Rectangle r2 = new Rectangle(10,0,3,4);
		Rectangle r3 = new Rectangle(0,10,5,11);
	
		ArrayList<Shape> shapes=new ArrayList<>();
		shapes.add(r1);
		shapes.add(r2);
		shapes.add(r3);
		shapes.add(c1);
		shapes.add(c2);
		shapes.add(c3);
		
		
		
		drawAll(shapes);
		
		ArrayList<Rectangle> rectangles=new ArrayList<>();
		
		rectangles.add(r1);
		rectangles.add(r2);
		rectangles.add(r3);
		
		ArrayList<Circle> circles=new ArrayList<>();
		
		circles.add(c1);
		circles.add(c2);
		circles.add(c3);
		
		drawAll(rectangles);
		drawAll(circles);
	}
	
	public static void drawAll(List<? extends Shape> shapes) {
		System.out.println("SHAPES ARE BEING DRAWN...");
		for(Shape shape:shapes) {
			shape.draw();
		}
		System.out.println();
	}

}
