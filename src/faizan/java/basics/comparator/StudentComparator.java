package faizan.java.basics.comparator;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student>{

	@Override
	public int compare(Student s1, Student s2) {
		// TODO Auto-generated method stub
		if(s1.getEnrolmentNumber()<s2.getEnrolmentNumber())
			return -1;
		return 0;
	}
}
