package practice.DP;

public class OrderedPairsFormation {

    public int getOrderedPairsCount(int []arr){
        int n = arr.length;
        int count=0;
        for(int i =0;i<n;i++){
            for(int j =i+1;j<n;j++){
                if ((arr[i]&arr[j])==0){
                    count+=2;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {

        int a[] = { 3, 4, 2 };
        System.out.println(new OrderedPairsFormation().getOrderedPairsCount(a));
    }
}
