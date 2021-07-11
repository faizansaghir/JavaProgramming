package faizan.java.basics.generics.wildcard;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

	public static void main(String[] args) {
		WithoutWildcard<String> g1=new WithoutWildcard<>(1,"Amazon");
		WithoutWildcard<Integer> g2=new WithoutWildcard<>(2,25);
		WithoutWildcard<List<String>> g3=new WithoutWildcard<>(3);
		WithoutWildcard<String> g4=new WithoutWildcard<>(4,"Flipkart");
		
		ArrayList<String> g3List=new ArrayList<>() ;
		g3List.add("Apple");
		g3List.add("Google");
		g3List.add("Facebook");
		
		g3.setData(g3List);
		
		System.out.println("Without Wildcard Generics");
		System.out.println(g1);
		System.out.println(g2);
		System.out.println(g3);
		System.out.println(g4);
		System.out.println();
		
		System.out.println("Comparing String Lengths");
		System.out.println(g1+"("+g1.toString().length()+") is greater than "+g4+"("+g4.toString().length()+") ? "+g1.isGreaterThan(g4));
		System.out.println();
		
		/* System.out.println(g1+" is greater than "+g2+" ? "+g1.isGreaterThan(g2)); 
		 * 		Causes error as WithoutWildcard<E> other is mentioned as formal parameter
		 * 		Hence the type of other object should also be String
		 * To overcome this issue, we use wildcard
		 */
		
		WithWildcard<String> w1=new WithWildcard<>(1,"Amazon");
		WithWildcard<Integer> w2=new WithWildcard<>(2,25);
		WithWildcard<List<String>> w3=new WithWildcard<>(3);
		WithWildcard<String> w4=new WithWildcard<>(4,"Flipkart");
		
		ArrayList<String> w3List=new ArrayList<>() ;
		w3List.add("Apple");
		w3List.add("Google");
		w3List.add("Facebook");
		
		w3.setData(w3List);
		
		System.out.println("With Wildcard Generics");
		System.out.println(w1);
		System.out.println(w2);
		System.out.println(w3);
		System.out.println(w4);
		System.out.println();
		
		System.out.println("Comparing String Lengths");
		System.out.println(w1+"("+w1.toString().length()+") is greater than "+w2+"("+w2.toString().length()+") ? "+w1.isGreaterThan(w2));
		System.out.println(w1+"("+w1.toString().length()+") is greater than "+w3+"("+w3.toString().length()+") ? "+w1.isGreaterThan(w3));
		System.out.println(w1+"("+w1.toString().length()+") is greater than "+w4+"("+w4.toString().length()+") ? "+w1.isGreaterThan(w4));
		System.out.println(w2+"("+w2.toString().length()+") is greater than "+w3+"("+w3.toString().length()+") ? "+w2.isGreaterThan(w3));
		System.out.println(w2+"("+w2.toString().length()+") is greater than "+w4+"("+w4.toString().length()+") ? "+w2.isGreaterThan(w4));
		System.out.println(w3+"("+w3.toString().length()+") is greater than "+w4+"("+w4.toString().length()+") ? "+w3.isGreaterThan(w4));
		
	}

}
