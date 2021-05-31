package faizan.java.basics.comparator;

import java.util.Arrays;

public class ComparatorMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1=new Student(1,"Adil",23);
		Student s2=new Student(2,"Faizan",20);
		Student s3=new Student(3,"Nazir",25);
		Student s4=new Student(4,"Saba",22);
		Student[] students= {s1,s2,s3,s4};
		System.out.println("Before Sorting:");
		for(Student student:students) {
			System.out.println(student);
		}
		System.out.println("After Sorting:");
		Arrays.sort(students, new StudentComparator());
		for(Student student:students) {
			System.out.println(student);
		}
		
	}

}
