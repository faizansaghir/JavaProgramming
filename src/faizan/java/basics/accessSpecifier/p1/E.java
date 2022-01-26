package faizan.java.basics.accessSpecifier.p1;

public class E {
	public void go() {
		A obj=new A();
		obj.printDefault();
		obj.printProtected();
		obj.printPublic();
		//private method not accessible
	}
}
