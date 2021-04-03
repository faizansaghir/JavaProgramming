import java.util.ArrayList;
import java.util.HashMap;

public class TowerOfHanoi {
	public static void TOH(int n,ArrayList<Integer> from,ArrayList<Integer> to,ArrayList<Integer> aux) {
		if(n==0) {
			put(n,from,to);
		}
		else {
			TOH(n-1,from,aux,to);
			put(n,from,to);
			TOH(n-1,aux,to,from);
		}
	}
	public static void print(ArrayList<Integer> from,ArrayList<Integer> to,ArrayList<Integer> aux) {
		System.out.println("From:"+"\t"+from);
		System.out.println("Auxilary:"+"\t"+aux);
		System.out.println("To:"+"\t"+to);
	}
	public static void put(int n,ArrayList<Integer> from,ArrayList<Integer> to) {
		int lastIndex=from.size()-1;
		int val=from.get(lastIndex);
		from.remove(lastIndex);
		to.add(val);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> f=new ArrayList<>();
		f.add(4);
		f.add(3);
		f.add(2);
		f.add(1);
		ArrayList<Integer> t=new ArrayList<>();
		ArrayList<Integer> a=new ArrayList<>();
		TOH(3,f,t,a);
		print(f,t,a);
	}

}
