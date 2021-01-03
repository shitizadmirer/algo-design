package practice.DP;


class PossibilityValue {
    boolean possible;
    int value;

    public PossibilityValue(boolean possible, int value) {
        this.possible = possible;
        this.value = value;
    }
}

public class PossiblePathInMatrix {


    private int n, m;
    private int[][] mat;
    private PossibilityValue[][][] dp;

    public int traversePowerAbsorbingValue(int[][] mat, int k) {
        this.mat = mat;
        this.n = mat.length;
        this.m = mat[0].length;
        this.dp = new PossibilityValue[n][m][k+1];

        PossibilityValue possibilityValue = isPossible(0, 0, k);

        return possibilityValue.possible ? possibilityValue.value : -1;

    }

    private PossibilityValue isPossible(int i, int j, int power) {
        if (!inRange(i, j) || power<0) {
            return new PossibilityValue(false, 0);
        }

        if (i == n - 1 && j == m - 1 && power >= mat[i][j]) {
            return new PossibilityValue(true, mat[i][j]);
        }


        if (dp[i][j][power] != null) {
            return dp[i][j][power];
        }
        PossibilityValue right = isPossible(i, j + 1, power - mat[i][j]);
        PossibilityValue down = isPossible(i + 1, j, power - mat[i][j]);
        PossibilityValue diagonal = isPossible(i + 1, j + 1, power - mat[i][j]);

        boolean possibility = right.possible || down.possible || diagonal.possible;
        int value = 0;
        if (right.possible) {
            value = Math.max(value, right.value);
        }
        if (down.possible) {
            value = Math.max(value, down.value);
        }
        if (diagonal.possible) {
            value = Math.max(value, diagonal.value);
        }

        return dp[i][j][power] = new PossibilityValue(possibility, value+mat[i][j]);
    }

    private boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }


    public static void main(String[] args) {
        int[][] mat = {{2, 3, 1},
                {6, 1, 9},
                {8, 2, 3}};
        int k = 7;

        System.out.println(new PossiblePathInMatrix().traversePowerAbsorbingValue(mat, k));
    }


}
