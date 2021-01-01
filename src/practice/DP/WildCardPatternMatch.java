package practice.DP;

public class WildCardPatternMatch {

    private boolean[][] dp;
    private int n, m;

    private String str, regex;

    public boolean doesStringMatchRegex(String str, String regex) {

        n = str.length();
        m = regex.length();

        this.dp = new boolean[n+1][m+1];

        this.str = str;
        this.regex = regex;

        this.dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            char iChar = str.charAt(i-1);
            for (int j = 1; j <= m; j++) {
                char jChar = regex.charAt(j-1);
                if (iChar == jChar || jChar=='?'){
                    this.dp[i][j] = this.dp[i-1][j-1];
                }else if (jChar == '*'){
                    dp[i][j] = dp[i-1][j-1] || dp[i-1][j] || dp[i][j-1];
                }
            }
        }


        return this.dp[n][m];


    }

//    private int doesRegexMatch(int strIdx, int regIdx) {
//        if (!inRange(strIdx, n) && !inRange(regIdx, m)) {
//            return 1;
//        }
//        if (!inRange(strIdx, n)) {
//            return 0;
//        }
//
//        if (!inRange(regIdx, m)) {
//            return 0;
//        }
//
//        if (dp[strIdx][regIdx] != -1) {
//            return dp[strIdx][regIdx];
//        }
//
//        char sChar = str.charAt(strIdx);
//        char rChar = regex.charAt(regIdx);
//
//        int finalRes = 0;
//        if (sChar == rChar) {
//            finalRes = doesRegexMatch(strIdx - 1, regIdx - 1);
//        }
//        if (rChar == '*') {
//            int res1 = doesRegexMatch(strIdx - 1, regIdx);
//            int res2 = doesRegexMatch(strIdx - 1, regIdx - 1);
//            int res3 = doesRegexMatch(strIdx, regIdx - 1);
//
//            finalRes = res1 == 1 || res2 == 1 || res3 == 1 ? 1 : 0;
//
//        }
//        if (rChar == '?') {
//            finalRes = doesRegexMatch(strIdx - 1, regIdx - 1);
//
//        }
//        return dp[strIdx][regIdx] = finalRes;
//
//    }

    private boolean inRange(int index, int n) {
        return index>=0 && index<n;
    }

    public static void main(String[] args) {
        String str = "baaabab";
        String regex = "??????*********bc";
        System.out.println(new WildCardPatternMatch().doesStringMatchRegex(str, regex));

    }
}
