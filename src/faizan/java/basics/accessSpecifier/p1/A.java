package faizan.java.basics.accessSpecifier.p1;

public class A {
	public void printPublic() {
		System.out.println("Public method accessed");
	}
	void printDefault() {
		System.out.println("Default method accessed");
	}
	// Default method with default keyword declaration only allowed in Interface
	protected void printProtected() {
		System.out.println("Protected method accessed");
	}
	private void printPrivate() {
		System.out.println("Private method accessed");
	}
	public void go() {
		printDefault();
		printPrivate();
		printProtected();
		printPublic();
	}
}

