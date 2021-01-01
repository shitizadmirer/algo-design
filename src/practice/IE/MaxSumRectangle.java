package practice.IE;

public class MaxSumRectangle {

    public int maxSumSubmatrix(int[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;

        int maxSum=0;

        for(int left =0;left<m;left++){
            int[] postSum = new int[n];
            for (int right = left;right<m;right++){
                for (int i =0;i<n;i++){

                    postSum[i]+=matrix[i][right];
                    System.out.print(postSum[i]);
                    System.out.print(" ");

                }

                int curSum = 0;
                for(int i =0;i<n;i++){
                    curSum +=postSum[i];
                    if(curSum<0){
                        curSum = 0;
                    }
                    if (curSum>maxSum){
                        maxSum = curSum;
                    }

                }
            }



        }

        return maxSum;

    }


    public static void main(String[] args) {
        int arr[][] = new int[][] {
                { 1,  2, -1, -4, -20 },
                { -8, -3, 4, 2, 1 },
                { 3,  8, 10, 1, 3 },
                { -4, -1, 1, 7, -6 }
        };

        MaxSumRectangle maxSumRectangle = new MaxSumRectangle();

        // Function call
        System.out.println(maxSumRectangle.maxSumSubmatrix(arr));
    }
}
