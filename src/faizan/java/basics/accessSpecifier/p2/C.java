package faizan.java.basics.accessSpecifier.p2;

import faizan.java.basics.accessSpecifier.p1.A;

public class C extends A{
	public void go() {
		printProtected();
		printPublic();
		// private and default not accessible
	} 
}
