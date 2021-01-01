package practice.DP;

public class SCS {

    public String shortestCommonSuperSeq(String X,String Y){

        int n = X.length();
        int m  = Y.length();

        int [][] scs = new int[n+1][m+1];
        for(int i =0;i<=n;i++){
            for (int j =0;j<=m;j++){
                if(i==0){
                    scs[i][j] = j;
                }else if (j==0){
                    scs[i][j] = i;
                } else if(X.charAt(i-1)==Y.charAt(j-1)){
                    scs[i][j] = scs[i-1][j-1]+1;
                }else{
                    scs[i][j] = Math.min(scs[i-1][j],scs[i][j-1])+1;
                }
            }
        }

        System.out.println(scs[n][m]);
        StringBuilder stringBuilder = new StringBuilder();
        int i =n,j=m;

        while(i>0&&j>0){
            char x = X.charAt(i-1);
            char y = Y.charAt(j-1);
            if(x==y){
                stringBuilder.append(x);
                i--;
                j--;
            }else{
                if (scs[i-1][j]<scs[i][j-1]){
                    stringBuilder.append(x);
                    i--;
                }else {
                    stringBuilder.append(y);
                    j--;
                }
            }
        }
        while (j>0){
            stringBuilder.append(Y.charAt(j-1));
            j--;
        }
        while (i>0){
            stringBuilder.append(X.charAt(i-1));
            i--;
        }
        return stringBuilder.reverse().toString();
    }


    public static void main(String[] args) {
        String a = "algorithm";
        String b = "rhythm";
        System.out.println(new SCS().shortestCommonSuperSeq(a,b));
    }
}
