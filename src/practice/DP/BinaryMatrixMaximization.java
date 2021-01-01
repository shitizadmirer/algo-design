package practice.DP;

class MaxSum {
    int sum, startIdx, endIdx;

    public MaxSum(int sum, int startIdx, int endIdx) {
        this.sum = sum;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

}

public class BinaryMatrixMaximization {

    public int getMaximumOneByFlipping(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return 0;
        }

        int m = matrix[0].length;
        int maxSum = 0;
        int startRow = -1, startCol = -1, endRow = -1, endCol = -1;
        for (int k = 0; k < m; k++) {
            int[] diff = new int[n];
            for (int j = k; j < m; j++) {

                for (int i = 0; i < n; i++) {
                    diff[i] += (matrix[i][j] == 0 ? 1 : -1);
                }
                MaxSum kadaneSum = applyKadane(diff);

                if (kadaneSum.sum > maxSum) {
                    startRow = kadaneSum.startIdx;
                    endRow = kadaneSum.endIdx;
                    startCol = k;
                    endCol = j;
                    maxSum = kadaneSum.sum;
                }

            }
        }


        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (flip(startRow, endRow, startCol, endCol, i, j)) {
                    matrix[i][j] = matrix[i][j]==1?0:1;
                }
                if (matrix[i][j] == 1) {
                    result++;
                }
            }
        }

        return result;
    }

    private boolean flip(int startRow, int endRow, int startCol, int endCol, int row, int col) {
        return row >= startRow && row <= endRow && col >= startCol && col <= endCol;
    }


    private MaxSum applyKadane(int[] diff) {

        int start = 0, end = 0;

        int n = diff.length;
        int currentSum = 0;
        int maxSum = 0;
        int maxStart = -1, maxEnd = -1;


        int index = 0;
        while (index < n) {
            currentSum += diff[index];
            if (currentSum < 0) {
                currentSum = 0;
                start = index + 1;
            }

            if (currentSum > 0) {
                end = index;
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxStart = start;
                maxEnd = end;
            }
            index++;

        }
        return new MaxSum(maxSum, maxStart, maxEnd);
    }


    public int sol(int mat[][]) {
        int R = mat.length;
        int C = mat[0].length;
        int ans = 0;
        int val = 0;
        // Precomputing the number of 1s
        int ones[][] = new int[R + 1][C + 1];
        for (int i = 1; i <= R; i++)
            for (int j = 1; j <= C; j++) {
                if (mat[i - 1][j - 1] == 1)
                    val = 1;
                ones[i][j] = ones[i - 1][j] + ones[i][j - 1] -
                        ones[i - 1][j - 1] +
                        (val);
            }
        // Finding the maximum number of 1s after flipping
        for (int k = 1; k <= Math.min(R, C); k++)
            for (int i = 1; i + k - 1 <= R; i++)
                for (int j = 1; j + k - 1 <= C; j++)
                    ans = Math.max(ans, (ones[R][C] + k * k -
                            2 * cal(ones, i, j, k)));
        return ans;
    }

    private int cal(int ones[][], int x, int y, int k) {
        return ones[x + k - 1][y + k - 1] - ones[x - 1][y + k - 1]
                - ones[x + k - 1][y - 1] + ones[x - 1][y - 1];
    }


    public static void main(String[] args) {
//        int[][] mat = {
//                {0, 0, 1,0},
//                {0, 0, 1,0},
//                {1, 0, 1,0}
//        };


        int[][] mat = {
                { 0,0},
                { 0,0}
        };

        System.out.println(new BinaryMatrixMaximization().getMaximumOneByFlipping(mat));
        System.out.println(new BinaryMatrixMaximization().sol(mat));
    }


}
