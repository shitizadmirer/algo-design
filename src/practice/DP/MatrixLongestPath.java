package practice.DP;

import java.util.ArrayList;
import java.util.List;

public class MatrixLongestPath {

    private int[][] resultMatrix;
    private int[][] matrix;
    private int n, m;

    public List<Integer> getMatrixLongestPath(int[][] matrix) {
        this.matrix = matrix;
        this.n = matrix.length;
        if (n == 0) {
            return new ArrayList<>();
        }
        this.m = matrix[0].length;
        intitializeResultMatrix();

        int maxResult = 1;
        int maxI = -1, maxJ = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int result = resultByDfs(i, j);
                if (result > maxResult) {
                    maxResult = result;
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        int count = resultMatrix[maxI][maxJ];
        List<Integer> resultList = new ArrayList<>();
        while (count > 0) {
            int value = matrix[maxI][maxJ];
            resultList.add(value);
            count--;
            if (inRange(maxI + 1, maxJ) && matrix[maxI + 1][maxJ] == value + 1 &&
                    resultMatrix[maxI + 1][maxJ] == count) {
                maxI = maxI + 1;

            } else if (inRange(maxI - 1, maxJ) && matrix[maxI - 1][maxJ] == value + 1 &&
                    resultMatrix[maxI - 1][maxJ] == count) {
                maxI = maxI - 1;

            } else if (inRange(maxI, maxJ + 1) && matrix[maxI][maxJ + 1] == value + 1 &&
                    resultMatrix[maxI][maxJ + 1] == count) {
                maxJ = maxJ + 1;
            } else {
                maxJ = maxJ - 1;
            }
        }

        return resultList;
    }


    private void intitializeResultMatrix() {
        this.resultMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.resultMatrix[i][j] = -1;
            }
        }


    }

    private int resultByDfs(int i, int j) {
        if (!inRange(i, j)) {
            return 0;
        }
        if (resultMatrix[i][j] != -1) {
            return resultMatrix[i][j];
        }
        int curValue = matrix[i][j];
        int result = 1;
        if (inRange(i - 1, j) && curValue + 1 == matrix[i - 1][j]) {
            result = Math.max(resultByDfs(i - 1, j) + 1, result);
        }

        if (inRange(i + 1, j) && curValue + 1 == matrix[i + 1][j]) {
            result = Math.max(resultByDfs(i + 1, j) + 1, result);
        }

        if (inRange(i, j - 1) && curValue + 1 == matrix[i][j - 1]) {
            result = Math.max(resultByDfs(i, j - 1) + 1, result);
        }

        if (inRange(i, j + 1) && curValue + 1 == matrix[i][j + 1]) {
            result = Math.max(resultByDfs(i, j + 1) + 1, result);
        }
        resultMatrix[i][j] = result;
        return result;
    }

    private boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }


    public static void main(String[] args) {

        int[][] mat = {{19, 20, 9,10},
                {18, 21, 8,11},
                {17, 6, 7,12},
                {16,15,14,13}
        };

        List<Integer> pathList = new MatrixLongestPath().getMatrixLongestPath(mat);
        for (Integer path : pathList) {
            System.out.print(path);
            System.out.print(" ");
        }

    }

}
