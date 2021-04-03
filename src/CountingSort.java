
public class CountingSort {
	public static int[] getCountArray(int[] arr) {
		int max=getMaxValue(arr);
		int[] countArr=new int[max+1];
		initializeArray(countArr,0);
		for(int i=0;i<arr.length;i++) {
			int currentCount=countArr[arr[i]];
			countArr[arr[i]]=currentCount+1;
		}
		return countArr;
	}
	public static int getMaxValue(int[] arr) {
		int maxVal=arr[0];
		int length=arr.length;
		for(int i=1;i<length;i++) {
			if(arr[i]>maxVal) {
				maxVal=arr[i];
			}
		}
		return maxVal;
	}
	public static void initializeArray(int[] arr,int val) {
		int length=arr.length;
		for(int i=0;i<length;i++) {
			arr[i]=val;
		}
	}
	public static void printArray(int[] arr) {
		int length=arr.length;
		System.out.print('[');
		for(int i=0;i<length-1;i++) {
			System.out.print(arr[i]+",");
		}
		System.out.print(arr[length-1]);
		System.out.println(']');
	}
	public static int[] getCumulativeArray(int[] arr) {
		int length=arr.length;
		int[] cumulativeArray=new int[length];
		int cumulativeValue=0;
		for(int i=0;i<length;i++) {
			cumulativeValue+=arr[i];
			cumulativeArray[i]=cumulativeValue;
		}
		return cumulativeArray;
	}
	public static int[] getSortedArray(int[] arr,int[] cumulative) {
		for(int i=cumulative.length-1;i>0;i--) {
			cumulative[i]=cumulative[i-1];
		}
		cumulative[0]=0;
		int[] sorted=new int[arr.length];
		for(int j=0;j<arr.length;j++) {
			int indexOfCurrentVal=cumulative[arr[j]];
			cumulative[arr[j]]+=1;
			sorted[indexOfCurrentVal]=arr[j];
		}
		return sorted;
	}
	public static void sortArray(int[] arr) {
		int[] count=getCountArray(arr);
		int[] cumulative=getCumulativeArray(count);
		int[] sortedArray=getSortedArray(arr,cumulative);
		printArray(sortedArray);
	}
	public static void main(String args[]) {
		int[] arr= {1,0,1,3,1,3,5,4,7,5,3};
		sortArray(arr);
	}

}
