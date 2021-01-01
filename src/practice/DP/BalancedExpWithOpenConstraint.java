package practice.DP;

import java.util.HashSet;
import java.util.Set;

public class BalancedExpWithOpenConstraint {

    private int[][] dp;
    private Set<Integer> openIndices;
    private int n;

    public int getWaysToFormBalancedExpression(int n, Set<Integer> openIndices){
        this.openIndices = openIndices;
        this.dp = new int[n+1][n+1];
        this.n = n;

        for (int i =0;i<=n;i++){
            for (int j =0;j<=n;j++){
                dp[i][j]=-1;
            }
        }
        return getWays(n, n);
    }

    private int getWays(int open, int close){
//        System.out.println(open);
//        System.out.println(close);
        if (open>close){
            return 0;
        }
        if (open==0){
            return 1;
        }

        if (dp[open][close]!=-1){
//            System.out.println("DP");
            return dp[open][close];
        }

        int index = 2*n-open-close;
        int result;
        if (openIndices.contains(index)){
            result = getWays(open-1, close);
        }else{
            result = getWays(open-1,close) + getWays(open, close-1);
        }
        dp[open][close] = result;
        return result;
    }


    static final int N = 1000;

    // function to calculate the number
// of proper bracket sequence
    public long arrangeBraces(int n, int pos[], int k) {

        // hash array to mark the
        // positions of opening brackets
        boolean h[] = new boolean[N];

        // dp 2d array
        int dp[][] = new int[N][N];

        // mark positions in hash array
        for (int i = 0; i < k; i++) {
            h[pos[i]] = true;
        }

        // first position marked as 1
        dp[0][0] = 1;

        // iterate and formulate the recurrences
        for (int i = 1; i <= 2 * n; i++) {
            for (int j = 0; j <= 2 * n; j++) {

                // if position has a opening bracket
                if (h[i]) {
                    if (j != 0) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = 0;
                    }
                } else if (j != 0) {
                    dp[i][j] = dp[i - 1][j - 1]
                            + dp[i - 1][j + 1];
                } else {
                    dp[i][j] = dp[i - 1][j + 1];
                }
            }
        }

        // return answer
        return dp[2 * n][0];
    }


    public static void main(String[] args) {

        Set<Integer> positions = new HashSet<>();
//        positions.add(2);
//        positions.add(3);
//        positions.add(5);
//        positions.add(6);
        int n =13;

        int[] pos = {};
        System.out.println(new BalancedExpWithOpenConstraint().getWaysToFormBalancedExpression(n, positions));
        System.out.println(new BalancedExpWithOpenConstraint().arrangeBraces(n,pos,pos.length));
    }


}
