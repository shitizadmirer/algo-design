package practice.IE;

public class MaxDiffElements {

    public int findMaxDiff(int arr[]){

        int n =arr.length;

        int maxDiff = Integer.MIN_VALUE,minElement = arr[0];

        for (int i =1;i<n;i++){
            if (arr[i] - minElement > maxDiff){
                maxDiff = arr[i] - minElement;
            }
            if (arr[i]<minElement){
                minElement = arr[i];
            }
        }

        return maxDiff;
    }

    public int findMaxDiffBySubArray(int arr[]){

        int n = arr.length;
        int maxDiff = 0;
        int currDiff = 0;
        for (int i =1;i<n;i++){
            int diff = arr[i]-arr[i-1];
            if (currDiff > 0){
                currDiff+=diff;
            }else{
                currDiff = diff;
            }

            if (maxDiff < currDiff){
                maxDiff = currDiff;
            }
        }
        return maxDiff;
    }





    public static void main(String[] args) {
        int arr[] = {1, 2, 90, 10, 110};

        System.out.println(new MaxDiffElements().findMaxDiff(arr));
        System.out.println(new MaxDiffElements().findMaxDiffBySubArray(arr));


        int arr2[] = {7, 9, 5, 6, 3, 2};
        System.out.println(new MaxDiffElements().findMaxDiff(arr2));
        System.out.println(new MaxDiffElements().findMaxDiffBySubArray(arr2));

        int arr3[] = {2, 3, 10, 6, 4, 8, 1};
        System.out.println(new MaxDiffElements().findMaxDiff(arr3));
        System.out.println(new MaxDiffElements().findMaxDiffBySubArray(arr3));

        int arr4[] = {10,9,8,7,6,5,4,3,2,1};
        System.out.println(new MaxDiffElements().findMaxDiff(arr4));
        System.out.println(new MaxDiffElements().findMaxDiffBySubArray(arr4));
    }
}
