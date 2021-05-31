package faizan.java.basics.comparator;

public class Student {
	private int rollNumber;
	private String name;
	private int enrolmentNumber;
	public int getRollNumber() {
		return rollNumber;
	}
	
	public Student(int rollNumber, String name, int enrolmentNumber) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
		this.enrolmentNumber = enrolmentNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = name;
	}
	public int getEnrolmentNumber() {
		return enrolmentNumber;
	}
	public void setEnrolmentNumber(int enrolmentNumber) {
		this.enrolmentNumber = enrolmentNumber;
	}

	@Override
	public String toString() {
		return("rollNumber=" + rollNumber + ", name=" + name + ", enrolmentNumber=" + enrolmentNumber);
	}
	
}
