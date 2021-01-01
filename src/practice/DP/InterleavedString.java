package practice.DP;

public class InterleavedString {


    public boolean isInterleaved(String A, String B, String C){

        int n = A.length();
        int m  = B.length();
        int l = C.length();
        if(n+m!=l){
            return false;
        }

        boolean [][]dp =  new boolean[n+1][m+1];
        for (int i =0;i<=n;i++){
            for (int j =0;j<=m;j++){
                if (i ==0 && j==0){
                    dp[i][j] = true;
                    continue;
                }
                if (i==0){
                    dp[i][j] = B.charAt(j-1) == C.charAt(j-1) && dp [i][j-1];
                    continue;
                }
                if (j==0){
                    dp[i][j] = A.charAt(i-1) == C.charAt(i-1) && dp [i-1][j];
                    continue;
                }
                int sum  = i+j-1;
                char c = C.charAt(sum);
                char a = A.charAt(i-1);
                char b = B.charAt(j-1);
                if (a==b && a==c){
                    dp[i][j] = dp[i][j-1]||dp[i-1][j];
                }else if (a==c){
                    dp[i][j] = dp[i-1][j];
                }else if (b ==c){
                    dp[i][j] = dp[i][j-1];
                }

            }
        }
        return dp[n][m];

    }





    public static void main(String[] args) {


        InterleavedString interleavedString = new InterleavedString();
        System.out.println(interleavedString.isInterleaved("XXY","XXZ","XXXYXY"));

    }
}
