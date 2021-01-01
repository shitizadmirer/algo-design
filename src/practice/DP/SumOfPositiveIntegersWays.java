package practice.DP;

import java.util.Arrays;

public class SumOfPositiveIntegersWays {

    private  int[] waysDP;




    public int countWays(int n)
    {

        // table[i] will be storing the
        // number of solutions for value
        // i. We need n+1 rows as the
        // table is consturcted in bottom
        // up manner using the base case
        // (n = 0)
        int table[] = new int[n + 1];

        // Initialize all table values as 0
        Arrays.fill(table, 0);

        // Base case (If given value is 0)
        table[0] = 1;

        // Pick all integer one by one and
        // update the table[] values after
        // the index greater than or equal
        // to n
        for (int i = 1; i < n; i++)
            for (int j = i; j <= n; j++)
                table[j] += table[j - i];

        return table[n];
    }

    public static void main(String[] args) {

        int n =2;
//        System.out.println(new SumOfPositiveIntegersWays().getWaysToWriteAsSum(n));
        System.out.println(new SumOfPositiveIntegersWays().countWays(n));
    }
}
