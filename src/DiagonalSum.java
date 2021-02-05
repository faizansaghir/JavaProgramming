import java.util.ArrayList;

public class DiagonalSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<Integer>> elements=new ArrayList<>();
		int value=1;
		for(int i=0;i<3;i++) {
			ArrayList<Integer> row=new ArrayList<>();
			for(int j=0;j<3;j++,value*=2) {
				row.add(value);
			}
			elements.add(row);
		}
		System.out.println("Elements of matrix are:");
		for(ArrayList<Integer> row:elements) {
			for(int element:row) {
				System.out.print(element+" ");
			}
			System.out.println();
		}
		int forwardSum=0,backwardSum=0;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++,value++) {
				if(i==j) {
					forwardSum+=elements.get(i).get(j);
				}
				if(i+j==2) {
					backwardSum+=elements.get(i).get(j);
				}
			}
		}
		System.out.println("Forward diagonal sum = "+forwardSum);
		System.out.println("Backward diagonal sum = "+backwardSum);
	}

}
