package faizan.java.basics.generics.general;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

	public static void main(String[] args) {
		General<String> g1=new General<>(1,"Amazon");
		General<Integer> g2=new General<>(2,25);
		General<List<String>> g3=new General<>(3);
		
		ArrayList<String> g3List=new ArrayList<>() ;
		g3List.add("Apple");
		g3List.add("Google");
		g3List.add("Facebook");
		
		g3.setData(g3List);
		
		System.out.println("General Generics");
		System.out.println(g1);
		System.out.println(g2);
		System.out.println(g3);
	}

}
