package practice.DP;

public class MinSumOneOfThreeConsecutive {

    private int[] arr;
    private int[] dp;
    private int n;

    public int findMinSumOfThreeConsecutive(int[] arr) {
        this.n = arr.length;
        if (n == 0) {
            return 0;
        }
        this.arr = arr;
        this.dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }
        return findMinSumIndex(0);
    }

    private int findMinSumIndex(int index) {


        if (index >= n) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }

        int minSum = Integer.MAX_VALUE;


        if (index < n) {
            minSum = Math.min(minSum, findMinSumIndex(index + 1) + arr[index]);
        }
        if (index + 1 < n) {
            minSum = Math.min(minSum, findMinSumIndex(index + 2) + +arr[index + 1]);
        }else {
            minSum = 0;
        }

        if (index + 2 < n) {
            minSum = Math.min(minSum, findMinSumIndex(index + 3) + arr[index + 2]);
        }else {
            minSum = 0;
        }

        return dp[index] = minSum;

    }


    public static void main(String[] args) {

        int[] arr1 = new int[]{1, 2, 3,4,5,6};
        int[] arr2 = new int[]{1, 2, 3, 6, 7, 1};
        int[] arr3 = new int[]{1, 2, 3, 6, 7, 1, 8, 6, 2, 7, 7, 1};

        System.out.println(new MinSumOneOfThreeConsecutive().findMinSumOfThreeConsecutive(arr1));
        System.out.println(new MinSumOneOfThreeConsecutive().findMinSumOfThreeConsecutive(arr2));
        System.out.println(new MinSumOneOfThreeConsecutive().findMinSumOfThreeConsecutive(arr3));

    }


}
