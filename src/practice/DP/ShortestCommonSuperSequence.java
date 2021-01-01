package practice.DP;

public class ShortestCommonSuperSequence {

    private String strA;

    private String strB;

    private String dp[][];


    private String shortestCommonSuperSequence(int i , int j){

        if (i == strA.length() && j == strB.length()){
            return "";
        }else if (i == strA.length()){
            return strB.substring(j);
        }else if (j == strB.length()){
            return strA.substring(i);
        }
        if (dp[i][j]!=null){
            System.out.println("Hit dp");
            return dp[i][j];
        }
        char chrA = strA.charAt(i);
        char chrB  = strB.charAt(j);
        if (chrA ==chrB){
            dp[i][j] =  chrA + shortestCommonSuperSequence(i+1,j+1);
        }else {
            String first = chrA + shortestCommonSuperSequence(i+1,j);
            String second = chrB+shortestCommonSuperSequence(i, j+1);
            dp [i][j] =  first.length() > second.length() ? second : first;
        }
        return dp[i][j];
    }
    public String commonSuperSequence(String A, String B){
        this.dp = new String[A.length()][B.length()];
        this.strB = B;
        this.strA = A;
        return shortestCommonSuperSequence(0,0);
    }


    public static void main(String[] args) {
        System.out.println(new ShortestCommonSuperSequence().commonSuperSequence("HELLO", "GEEK"));
        System.out.println(new ShortestCommonSuperSequence().commonSuperSequence("AGGTAB", "GXTXAYB"));

    }

}
