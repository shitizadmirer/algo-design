package practice.DP;

public class BallArrangementDP {

    private int[][][][] dp;

    public int getWaysOfBallArrangement(int p, int q, int r){
        this.dp= new int[p+1][q+1][r+1][3];


        for(int i =0;i<=p;i++){
            for(int j =0;j<=q;j++){
                for(int k =0;k<=r;k++){
                    dp[i][j][k][0]=-1;
                    dp[i][j][k][1]=-1;
                    dp[i][j][k][2]=-1;
                }

            }
        }
        this.dp[0][0][0][0]=1;
        this.dp[0][0][0][1]=1;
        this.dp[0][0][0][2]=1;

        return getBallArrangementWays(p,q,r,-1);
    }


    private int countWays(int p, int q, int r, int last)
    {
        // if number of balls of any
        // color becomes less than 0
        // the number of ways arrangements is 0.
        if (p < 0 || q < 0 || r < 0)
            return 0;

        // If last ball required is
        // of type P and the number
        // of balls of P type is 1
        // while number of balls of
        // other color is 0 the number
        // of ways is 1.
        if (p == 1 && q == 0 && r == 0 && last == 0)
            return 1;

        // Same case as above for 'q' and 'r'
        if (p == 0 && q == 1 && r == 0 && last == 1)
            return 1;
        if (p == 0 && q == 0 && r == 1 && last == 2)
            return 1;

        // if last ball required is P
        // and the number of ways is
        // the sum of number of ways
        // to form sequence with 'p-1' P
        // balls, q Q Balls and r R balls
        // ending with Q and R.
        if (last == 0)
            return countWays(p - 1, q, r, 1) +
                    countWays(p - 1, q, r, 2);

        // Same as above case for 'q' and 'r'
        if (last == 1)
            return countWays(p, q - 1, r, 0) +
                    countWays(p, q - 1, r, 2);

        if (last == 2)
            return countWays(p, q, r - 1, 0) +
                    countWays(p, q, r - 1, 1);

        return 0;
    }


    public int countUtil(int p, int q, int r) {
        // Three cases arise:
        return countWays(p, q, r, 0) + // Last required balls is type P
                countWays(p, q, r, 1) + // Last required balls is type Q
                countWays(p, q, r, 2);  // Last required balls is type R
    }

    private int getBallArrangementWays(int p, int q, int r, int lastBall){
        if (p ==0 && q==0&& r==0){
            return 1;
        }

        if (lastBall != -1 && dp[p][q][r][lastBall]!=-1){
//            System.out.println("DP");
            return  dp[p][q][r][lastBall];
        }

        int pWays =0, qWays = 0,rWays = 0;

        if (p>0 && lastBall!=0){
            pWays=getBallArrangementWays(p-1,q,r,0);
        }

        if (q>0&& lastBall!=1){
            qWays=getBallArrangementWays(p,q-1,r,1);
        }
        if (r>0 && lastBall!=2){
            rWays=getBallArrangementWays(p,q,r-1,2);

        }
        if (lastBall!=-1) {
            dp[p][q][r][lastBall] = pWays + qWays + rWays;
        }
        return pWays + qWays + rWays;
    }

    public static void main(String[] args) {
        int p =5, q=5, r=51;

        System.out.println(new BallArrangementDP().getWaysOfBallArrangement(p,q,r));
        System.out.println(new BallArrangementDP().countUtil(p,q,r));
    }
}
