package faizan.java.basics.accessSpecifier.p1;

public class D extends A{
	public void go() {
		printDefault();
		printProtected();
		printPublic();
		//private method not accessible
	}
}
