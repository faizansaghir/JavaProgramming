package faizan.java.basics.accessSpecifier.p2;

import faizan.java.basics.accessSpecifier.p1.A;

public class B {
	public void go() {
		A obj=new A();
		obj.printPublic();
		// Rest of the functions not accessible
	}
}
