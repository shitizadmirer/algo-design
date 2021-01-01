package practice.DP;

public class LongestAPLength {

    public int getLongestAPLength(int arr[]){
        int n =arr.length;
        if(n<=2){
            return n;
        }

        int [][] LAP = new int[n+1][n+1];
        int maxLen = 2;

        for (int i =0;i<n;i++){
            LAP[i][n-1] = 2;
        }

        for(int j = n-2;j>=1;j--){

            int i = j-1, k=j+1;
            int reqAdd = 2*arr[j];
            while (i>=0&&k<n){
                int currentAdd = arr[i]+arr[k];
                if (currentAdd > reqAdd){
                    LAP[i][j]=2;
                    i--;
                }else if (currentAdd<reqAdd){
                    k++;
                }else {
                    LAP[i][j] = LAP[j][k]+1;
                    if (maxLen < LAP[i][j]){
                        maxLen = LAP[i][j];
                    }
                    i--;
                    k++;

                }
            }
            while (i>=0){
                LAP[i][j] = 2;
                i--;
            }

        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestAPLength longestAPLength = new LongestAPLength();
        int set1[] = {1, 7, 10, 13, 14, 19};
        int n1 = set1.length;
        System.out.println ( longestAPLength.getLongestAPLength(set1));

        int set2[] = {1, 7, 10, 15, 27, 29};
        int n2 = set2.length;
        System.out.println(longestAPLength.getLongestAPLength(set2));

        int set3[] = {2, 4, 6, 8, 10};
        int n3 = set3.length;
        System.out.println(longestAPLength.getLongestAPLength(set3)) ;
    }
}
