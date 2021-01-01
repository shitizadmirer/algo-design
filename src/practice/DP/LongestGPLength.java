package practice.DP;

public class LongestGPLength {

    public int getLongestGPLen(int arr[]){

        int n =arr.length;
        if (n<=2){
            return n;
        }
        int [][] LGP = new int[n+1][n+1];
        for(int i=0;i<n;i++){
            LGP[i][n-1] = (arr[n-1]%arr[i]) ==0 ? 2:1;
        }

        int maxLen = 1;

        for(int j =n-2;j>=1;j--){

            int i =j-1, k=j+1;
            int reqProd = arr[j]*arr[j];
            while (i>=0 && k<n) {
                int currentProd = arr[i] * arr[k];
                if (reqProd == currentProd) {
                    LGP[i][j] = (arr[j]%arr[i]) ==0 ? LGP[j][k] + 1 : 1;
                    maxLen = Math.max(maxLen, LGP[i][j]);
                    i--;
                    k++;
                } else if (reqProd > currentProd) {
                    k++;
                } else {
                    LGP[i][j] = (arr[j]%arr[i]) ==0 ? 2:1;
                    i--;
                }
            }

            while (i>=0){
                LGP[i][j]=2;
                i--;
            }

        }
        return maxLen;

    }

    public static void main(String[] args) {
        int set1[] = { 1, 3, 9, 27, 81, 243 };
        LongestGPLength longestGPLength = new LongestGPLength();
        int n1 = set1.length;
        System.out.print(longestGPLength.getLongestGPLen(set1) + "\n");

        int set2[] = { 1, 3, 4, 9, 7, 27 };
        int n2 = set2.length;
        System.out.print(longestGPLength.getLongestGPLen(set2) + "\n");

        int set3[] = { 2, 3, 5, 7, 11, 13 };
        int n3 = set3.length;
        System.out.print(longestGPLength.getLongestGPLen(set3) + "\n");

        int set4[] = {9,8,7,6,5,4,3,2,1};
        System.out.print(longestGPLength.getLongestGPLen(set4) + "\n");
    }
}
