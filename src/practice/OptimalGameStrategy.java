package practice;

public class OptimalGameStrategy {

    private int [][] maxWinByIndex;
    private int [] winnings;

    public int maxWinnings(int prizes[]){
        int n  = prizes.length;
        this.winnings = prizes;
        maxWinByIndex = new int[n][n];
        for(int i =0;i<n;i++){
            for(int j=0;j<n;j++){
                maxWinByIndex[i][j] = -1;
            }
        }
        int totalPrize = 0;
        for(int i =0;i<n;i++){
            totalPrize += prizes[i];
        }
        return getMaxWinning(0,n-1, totalPrize);
    }

    private int getMaxWinning(int fromIdx, int toIdx, int totalPrice){

        if (fromIdx> toIdx){
            return 0;
        }
        if(maxWinByIndex[fromIdx][toIdx]!=-1) {
            return maxWinByIndex[fromIdx][toIdx];
        }
        if (fromIdx == toIdx){
            return maxWinByIndex[fromIdx][toIdx] = winnings[fromIdx];
        }


        int prizeFromStart = getMaxWinning(fromIdx+1,toIdx, totalPrice-winnings[fromIdx]);
        int prizeFromEnd = getMaxWinning(fromIdx, toIdx-1, totalPrice-winnings[toIdx]);

        return maxWinByIndex[fromIdx][toIdx] = totalPrice - Math.max(prizeFromStart, prizeFromEnd);
    }


    public static void main(String[] args) {

        OptimalGameStrategy optimalGameStrategy = new OptimalGameStrategy();
        System.out.println(optimalGameStrategy.maxWinnings(new int[]{5, 3 ,7 ,10}));
    }

}
