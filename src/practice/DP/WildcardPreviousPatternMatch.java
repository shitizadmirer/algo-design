package practice.DP;

public class WildcardPreviousPatternMatch {

    public boolean doesRegexMatch(String str, String regex){

        int n = str.length();
        int m = regex.length();

        boolean [][] dp = new boolean[n+1][m+1];

        dp[0][0] = true;

        char lastChar = '\0';

        for (int i =1;i<=n;i++){

            char iChar = str.charAt(i-1);
            for (int j =1;j<=m;j++){
                char jChar = regex.charAt(j-1);
                if (iChar == jChar || jChar == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }else if (jChar == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1]|| dp[i-1][j-1];

                }else if (jChar == '+'){
                    dp[i][j] = iChar == lastChar && dp[i-1][j-1];
                }

            }

            lastChar = iChar;

        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String str = "bbccdd";
        String pat = "b+c+d+";

        System.out.println(new WildcardPreviousPatternMatch().doesRegexMatch(str, pat));
    }
}
