package practice.DP;



public class StringTransformation {

    private int[][] dp;

    private int n,m;
    private String s1,s2;

    public boolean canTransformString(String s1, String s2) {

        this.n = s1.length();
        this.m = s2.length();
        this.s1 = s1;
        this.s2 = s2;
        if (m == 0) {

            return allLowerCase(0);
        }

        if (n == 0) {
            return false;
        }

        this.dp = new int[n][m];
        for (int i =0;i<n;i++){
            for (int j =0;j< m;j++){
                dp[i][j] = -1;
            }
        }

        return isTransformable(0,0);

    }


    private boolean isTransformable(int x, int y){
        if (y>=m){
            return allLowerCase(x);
        }

        if (x>=n){
            return false;
        }

        if (dp[x][y]!=-1){
            return dp[x][y]==1;
        }

        char X = s1.charAt(x);
        char Y =  s2.charAt(y);

        boolean ans = false;
        if (Character.isLowerCase(X)){
            ans = isTransformable(x + 1,y);

        }
        ans  = ans || Character.toUpperCase(X) == Y && isTransformable(x+1, y+1);

        dp[x][y] = ans? 1:0;

        return ans;



    }

    private boolean allLowerCase(int index){
        boolean ans = true;
        for (int i = index; i < n; i++) {
            char c = s1.charAt(i);
            ans = ans && Character.isLowerCase(c);
        }
        return ans;
    }


    public static void main(String[] args) {
        String s1 = "ABcd";
        String s2 = "BCD";
        System.out.println(new StringTransformation().canTransformString(s1,s2));
    }



}
