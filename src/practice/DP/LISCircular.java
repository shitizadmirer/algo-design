package practice.DP;

public class LISCircular {

    public int getMaxCircularLISLength(int [] arr){
        int n = arr.length;
        if (n ==0){
             return 0;
        }
        int [] newArr = new int[2*n];
        for(int i =0;i<n;i++){

            newArr[i] = arr[i];
            newArr[i+n] = arr[i];
        }

        int [] lisArr = new int[2*n];

        int maxLen = 0;
        for(int i =0;i<2*n;i++){
            lisArr[i]=1;
            for(int j = Math.max(i-n+1,0);j<i;j++){
                if (newArr[j]<newArr[i]){
                    lisArr[i] = Math.max(lisArr[i], lisArr[j]+1);
                }
            }
            maxLen = Math.max(maxLen, lisArr[i]);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int []arr1 = new int[]{5, 4, 3, 2, 1};
        int []arr2 = new int[]{5, 6, 7, 1, 2, 3};

        System.out.println(new LISCircular().getMaxCircularLISLength(arr1));
        System.out.println(new LISCircular().getMaxCircularLISLength(arr2));
    }
}
