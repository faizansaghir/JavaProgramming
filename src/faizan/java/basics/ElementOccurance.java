package faizan.java.basics;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

public class ElementOccurance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> userInput=new ArrayList<Integer>();
		userInput.add(1);
		userInput.add(3);
		userInput.add(2);
		userInput.add(1);
		userInput.add(4);
		System.out.println(userInput);
		HashMap<Integer,Integer> elementMapping=new HashMap<>();
		for(int i:userInput) {
			if(elementMapping.containsKey(i)) {
				elementMapping.put(i, elementMapping.get(i)+1);
			}
			else {
				elementMapping.put(i, 1);
			}
		}
		for(int i:elementMapping.keySet()) {
			System.out.println(i+" exists "+elementMapping.get(i)+" times.");
		}
	}

}
