import java.util.Scanner;

public class MergeSort {
    public static void MSort(int[] arr,int l,int h,int[] arrS){
        int mid=(l+h)/2;
        if(l!=h){
            MSort(arr,l,mid,arrS);
            MSort(arr,(mid+1),h,arrS);
            Merge(arr,l,h,arrS);
        }
    }
    public static void Merge(int[] arr,int l,int h,int arrS[]){
        int p=l,mid=(l+h)/2,q=mid+1;
        for(int i=l;i<=h;i++){
            if(p<=mid && q<=h){
                if(arr[p]<arr[q]){
                    arrS[i]=arr[p];
                    p++;
                }
                else{
                    arrS[i]=arr[q];
                    q++;
                }
            }
            else if(p>mid){
                arrS[i]=arr[q];
                q++;
            }
            else{
                arrS[i]=arr[p];
                p++;
            }
        }
        for(int i=l;i<=h;i++){
            arr[i]=arrS[i];
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Number Of Elements");
        int n=sc.nextInt();
        System.out.println("Enter Elements:");
        int[] arr=new int[n];
        int[] arrS=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=sc.nextInt();
        }
        for (int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
        int h=n-1;
        System.out.println();
        MSort(arr,0,h,arrS);
        for (int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
