package practice.DP;

public class MinCostForTrainDestination {

    private int[][] resultMatrix;
    private int n;
    static int INF = Integer.MAX_VALUE;

    public int getMinCostForTrainDestination(int[][] cost) {
        this.n = cost.length;
        if (n == 0) {
            return 0;
        }
        initializeResultMatrix();

        for (int i = n - 1; i >= 0; i--) {

            resultMatrix[i][n - 1] = cost[i][n - 1];
            for (int j = n - 1; j > i; j--) {

                resultMatrix[i][n - 1] = Math.min(resultMatrix[i][n - 1], cost[i][j] + resultMatrix[j][n - 1]);

            }

        }

        return resultMatrix[0][n-1];
    }

    private void initializeResultMatrix() {
        this.resultMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

    }

    public static void main(String[] args) {

        int[][] cost = {{0, 15, 80, 90},
                {INF, 0, 40, 50},
                {INF, INF, 0, 70},
                {INF, INF, INF, 0}
        };
        System.out.println(new MinCostForTrainDestination().getMinCostForTrainDestination(cost));

    }
}
