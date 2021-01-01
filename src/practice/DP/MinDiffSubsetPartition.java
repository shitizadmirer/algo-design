package practice.DP;

public class MinDiffSubsetPartition {

    public int getMinDiffPartition(int[] arr, int n) {

        if (n == 0) {
            return 0;
        }

        int sum = 0;
        for (int value : arr) {
            sum += value;
        }

        boolean[][] possibleSum = new boolean[n + 1][sum + 1];

        possibleSum[0][0] = true;

        for (int i = 1; i <= n; i++) {
            int num = arr[i - 1];
            for (int j = 1; j <= sum; j++) {
                possibleSum[i][j] = possibleSum[i - 1][j];
                if (j - num >= 0) {
                    possibleSum[i][j] = possibleSum[i][j] || possibleSum[i-1][j - num];
                }
            }
        }

        int minDiff = sum;
        for (int j = 0; j <= sum; j++) {
            if(possibleSum[n][j]) {
                int secondSum = sum - j;
                minDiff = Math.min(Math.abs(j - secondSum), minDiff);
            }
        }
        return minDiff;
    }

    public int findMin(int arr[], int n)
    {
        // Calculate sum of all elements
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += arr[i];

        // Create an array to store
        // results of subproblems
        boolean dp[][] = new boolean[n + 1][sum + 1];

        // Initialize first column as true.
        // 0 sum is possible  with all elements.
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        // Initialize top row, except dp[0][0],
        // as false. With 0 elements, no other
        // sum except 0 is possible
        for (int i = 1; i <= sum; i++)
            dp[0][i] = false;

        // Fill the partition table
        // in bottom up manner
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= sum; j++)
            {
                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];

                // If i'th element is included
                if (arr[i - 1] <= j)
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
            }
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--)
        {
            // Find the
            if (dp[n][j] == true)
            {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }


    public static void main(String[] args) {
        int arr[] = {3,3};
        System.out.println(new MinDiffSubsetPartition().getMinDiffPartition(arr, arr.length));
        System.out.println(new MinDiffSubsetPartition().findMin(arr, arr.length));
    }
}
