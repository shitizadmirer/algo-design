package practice.DP;

public class LexicographicalSort {

    private String[] arr;

    private int minCost[];
    private int n;

    private int reverseCost[];

    public int minCostForLexicographicalSort(String[] arr, int [] reverseCost){

        int n =arr.length;
        this.arr = arr;
        this.minCost = new int[n];
        this.reverseCost = reverseCost;

        this.n = n;

        for (int i =0;i<n;i++){
            minCost[i] = -1;
        }
        return minCost(0);
    }

    private int minCost(int index){

        if (index>=n){
            return 0;
        }

//        if (minCost[index] !=-1){
//            return minCost[index];
//        }

        String curStr = arr[index];
        String reverseStr = new StringBuilder(curStr).reverse().toString();

        int notReverse = Integer.MAX_VALUE, reverse = Integer.MAX_VALUE;

        if (index == 0){
            notReverse = minCost(index+1);
            String temp = arr[index];
            arr[index] = reverseStr;
            reverse = minCost(index + 1) + reverseCost[index];
            arr[index] = temp;

        }else{
            if (curStr.compareTo(arr[index-1])>=0){
                notReverse = minCost(index+1);
            }
            if (reverseStr.compareTo(arr[index-1])>=0) {
                String temp = arr[index];
                arr[index] = reverseStr;
                reverse = minCost(index + 1) + reverseCost[index];
                arr[index] = temp;
            }
        }
        minCost[index] = Math.min(reverse,notReverse);
        return minCost[index];
    }


    public int lexicographicalMinCost(String[] arr, int [] cost, int n){

        int [][] minCost = new int[n][2];

        for(int i =0;i<n;i++){
            for(int j =0;j<2;j++){
                minCost[i][j] = Integer.MAX_VALUE;
            }
        }

        minCost[0][0] = 0;
        minCost[0][1] = cost[0];

        String [] reverseArr = new String[n];

        for (int i =0;i<n;i++){
            reverseArr[i] = new StringBuilder(arr[i]).reverse().toString();
        }

        for(int i =1;i<n;i++){

            String curStr = arr[i];
            String curReverseStr = reverseArr[i];
            String prevStr = arr[i-1];
            String prevReverseStr = reverseArr[i-1];

            if (curStr.compareTo(prevStr)>=0){
                minCost[i][0] = Math.min(minCost[i][0],minCost[i-1][0]);
            }

            if (curStr.compareTo(prevReverseStr)>=0){
                minCost[i][0] = Math.min(minCost[i][0],minCost[i-1][1]);
            }

            if (curReverseStr.compareTo(prevStr)>=0){
                minCost[i][1] = Math.min(minCost[i][1],minCost[i-1][0] + cost[i]);
            }

            if (curReverseStr.compareTo(prevReverseStr)>=0){
                minCost[i][1] = Math.min(minCost[i][1],minCost[i-1][1] + cost[i]);
            }

        }

        return Math.min(minCost[n-1][0], minCost[n-1][1]);
    }


    public static int minCost(String arr[], int cost[], int N)
    {
        // dp[i][j] represents the minimum cost to
        // make first i strings sorted.
        // j = 1 means i'th string is reversed.
        // j = 0 means i'th string is not reversed.
        int [][]dp = new int[N][2];

        // initializing dp array for first string
        dp[0][0] = 0;
        dp[0][1] = cost[0];

        // getting array of reversed strings
        String []revStr = new String[N];
        for (int i = 0; i < N; i++)
        {
            revStr[i] = arr[i];
            revStr[i] = reverse(revStr[i], 0,
                    revStr[i].length() - 1);
        }

        String curStr = "";
        int curCost;

        // looping for all strings
        for (int i = 1; i < N; i++)
        {
            // Looping twice, once for string and once
            // for reversed string
            for (int j = 0; j < 2; j++)
            {
                dp[i][j] = Integer.MAX_VALUE;

                // getting current string and current
                // cost according to j
                curStr = (j == 0) ? arr[i] : revStr[i];
                curCost = (j == 0) ? 0 : cost[i];

                // Update dp value only if current string
                // is lexicographically larger
                if (curStr.compareTo(arr[i - 1]) >= 0)
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i - 1][0] + curCost);
                if (curStr.compareTo(revStr[i - 1]) >= 0)
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i - 1][1] + curCost);
            }
        }

        // getting minimum from both entries of last index
        int res = Math.min(dp[N - 1][0], dp[N - 1][1]);

        return (res == Integer.MAX_VALUE)? -1 : res;
    }


    static String reverse(String s, int start, int end)
    {

        // Temporary variable to store character
        char temp;
        char []str = s.toCharArray();
        while (start <= end)
        {

            // Swapping the first and last character
            temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {

        LexicographicalSort lexicographicalSort = new LexicographicalSort();
        String arr[] = {"aa", "ba", "ac", "as", "ad", "da", "cd"};
        int cost[] = {1, 3, 1,2,3,1,2};
        int N = arr.length;

        int res = lexicographicalSort.lexicographicalMinCost(arr, cost,N);
        int res2 = minCost(arr, cost,N);
        System.out.println(res);
        System.out.println(res2);
    }
}

