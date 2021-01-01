package practice.DP;

public class StringSubsequenceCount {

    private int [][] dp;
    private int findSubSequenceCount(String str, String search){
        int n = str.length();
        int m = search.length();
        if (n ==0 || m ==0){
            return 0;
        }
        this.dp = new int [m+1][n+1];

        for (int j =0;j<=n;j++){
            this.dp[0][j]=1;
        }

        for (int i =1;i<=m;i++){
            char pat = search.charAt(i-1);
            for (int j =1;j<=n;j++){
                char orig = str.charAt(j-1);
                if (orig == pat){
                    dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                }else{
                    dp[i][j] = dp[i][j-1];
                }

            }
        }
        return dp[m][n];

    }


    public static void main(String[] args) {
        String a = "geeksforgeeks";
        String b  = "geek";

        System.out.println(new StringSubsequenceCount().findSubSequenceCount(a,b));
    }
}
