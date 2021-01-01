package practice.DP;

public class MaxHeapFormation {

    private int[][] C;
    private int[] log2;
    private int[] numOfHeaps;

    private int[][] getBinomialCoefficients(int n) {
        int[][] C = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            C[0][i] = 0;
        }
        C[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    C[i][j] = C[i - 1][j - 1] + C[i-1][j];
                }
            }
        }
        return C;
    }

    private int[] getLogarithm(int n) {
        int[] log2 = new int[n + 1];
        log2[0] = 0;
        log2[1] = 0;
        int log = -1, power = 1;
        for (int i = 1; i <= n; i++) {
            if (power == i) {
                log++;
                power *= 2;
            }
            log2[i] = log;
        }
        return log2;
    }


    private int getLeft(int n) {
        if (n == 1) {
            return 0;
        }

        int h = log2[n];

        int elements = 1 << h;

        int lastLevel = n - (elements - 1);
        if (lastLevel >= (elements / 2)) {
            return (elements - 1);
        } else {
            return (elements-1-(elements/2 - lastLevel));
        }
    }


    private int numberOfHeaps(int n) {

        if (n <= 1) {
            return 1;
        }
        if (numOfHeaps[n] != -1) {
            return numOfHeaps[n];
        }
        int left = getLeft(n);
        int ans = C[n - 1][left] * numberOfHeaps(left) * numberOfHeaps(n - 1 - left);
        this.numOfHeaps[n] = ans;
        return ans;
    }

    public int getMaximumHeapsFormed(int n) {
        this.C = getBinomialCoefficients(n);
        this.log2 = getLogarithm(n);
        this.numOfHeaps = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            this.numOfHeaps[i] = -1;
        }
        return numberOfHeaps(n);
    }






    static int MAXN = 105; // maximum value of n here

    // dp[i] = number of max heaps for i distinct integers
    static int[] dp = new int[MAXN];

    // nck[i][j] = number of ways to choose j elements
    //         form i elements, no order */
    static int[][] nck = new int[MAXN][MAXN];

    // log2[i] = floor of logarithm of base 2 of i
    static int[] logtwo = new int[MAXN];

    // to calculate nCk
    public static int choose(int n, int k)
    {
        if (k > n)
        {
            return 0;
        }
        if (n <= 1)
        {
            return 1;
        }
        if (k == 0)
        {
            return 1;
        }

        if (nck[n][k] != -1)
        {
            return nck[n][k];
        }

        int answer = choose(n - 1, k - 1) + choose(n - 1, k);
        nck[n][k] = answer;
        return answer;
    }

    // calculate l for give value of n
    public static int getLeft2(int n)
    {
        if (n == 1)
        {
            return 0;
        }

        int h = logtwo[n];

        // max number of elements that can be present in the
        // hth level of any heap
        int numh = (1 << h); //(2 ^ h)

        // number of elements that are actually present in
        // last level(hth level)
        // (2^h - 1)
        int last = n - ((1 << h) - 1);

        // if more than half-filled
        if (last >= (numh / 2))
        {
            return (1 << h) - 1; // (2^h) - 1
        }
        else
        {
            return (1 << h) - 1 - ((numh / 2) - last);
        }
    }

    // find maximum number of heaps for n
    public static int numberOfHeaps2(int n)
    {
        if (n <= 1)
        {
            return 1;
        }

        if (dp[n] != -1)
        {
            return dp[n];
        }

        int left = getLeft2(n);
        int ans = (choose(n - 1, left) * numberOfHeaps2(left))
                * (numberOfHeaps2(n - 1 - left));
        dp[n] = ans;
        return ans;
    }

    // function to initialize arrays
    public static int solve(int n)
    {
        for (int i = 0; i <= n; i++)
        {
            dp[i] = -1;
        }

        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                nck[i][j] = -1;
            }
        }

        int currLog2 = -1;
        int currPower2 = 1;

        // for each power of two find logarithm
        for (int i = 1; i <= n; i++)
        {
            if (currPower2 == i)
            {
                currLog2++;
                currPower2 *= 2;
            }
            logtwo[i] = currLog2;
        }

        return numberOfHeaps2(n);
    }

    public static void main(String[] args) {

        int n = 1;
        System.out.println(new MaxHeapFormation().getMaximumHeapsFormed(n));
        System.out.println(solve(n));
    }
}
