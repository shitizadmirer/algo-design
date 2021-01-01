package practice.DP;

import java.util.Vector;

public class LCSLengthIncrease {



    private int [][] findLCS(String strA,String strB){

        int n = strA.length();
        int m  = strB.length();
        int [][] lcs = new int[n+2][m+2];

        for (int i =1;i<=n;i++){
            for (int j =1;j<=m;j++){
                if (strA.charAt(i-1) == strB.charAt(j-1)){
                    lcs[i][j] = 1+ lcs[i-1][j-1];
                }else {
                    lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
                }
            }
        }
        return lcs;
    }


    public int findWaysForIncreasingLCS(String A, String B){

        int [][] lcs = findLCS(A,B);
        int [][] lcsNewReversed = findLCS(new StringBuilder(A).reverse().toString(), new StringBuilder(B).reverse().toString());

        int n  = A.length();
        int m  = B.length();
        int [][]lcsReversed = new int[n+2][m+2];
        for (int i =1;i<=n;i++){
            for (int j =1;j<=m;j++){
                lcsReversed[i][j] = lcsNewReversed[n-i+1][m-j+1];

            }
        }

        Vector<Integer> [] pos = new Vector[256];

        for (int i =0;i<256;i++){
            pos[i] = new Vector<>();
        }
        for (int i  = 0;i<m;i++){
            pos[B.charAt(i)].add(i+1);
        }

        int actualLcs = lcs[n][m];
        int ways = 0;
        for(int i =0;i<=n;i++){
            for (int j =0;j<256;j++) {
                for (int x : pos[j]) {
                    if ((lcs[i][x - 1] + lcsReversed[i + 1][x + 1]) == actualLcs) {
                        ways++;
                    }
                }
            }
        }

        return ways;
    }

    public static void main(String[] args) {
        System.out.println(new LCSLengthIncrease().findWaysForIncreasingLCS("aa", "baaa"));
    }
}
