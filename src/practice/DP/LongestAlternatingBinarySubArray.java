package practice.DP;

public class LongestAlternatingBinarySubArray {

    public int getLongestBinarySubArray(int arr[], int n){

        int maxLen=1;
        int index=0;
        int curLen=1;
        while (index<n-1){
            if(arr[index]!=arr[index+1]){
                curLen++;
            }else{
                curLen=1;
            }
            if (curLen>maxLen){
                maxLen = curLen;
            }
            index++;
        }
        return maxLen;
    }


    public static void main(String[] args) {
        int arr[] = {1, 0, 1, 0, 1, 0,1,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,0,0,0,0,1};
        System.out.println( new LongestAlternatingBinarySubArray().getLongestBinarySubArray(arr, arr.length));
    }
}
