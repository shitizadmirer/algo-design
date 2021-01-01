package practice.IE;

class MaxNum {
    int maxEven;
    int maxAll;

    public MaxNum(int maxEven, int maxAll) {
        this.maxEven = maxEven;
        this.maxAll = maxAll;
    }
}

public class MaxSumEvenSubSequence {

    public int maxEvenSumSubsequence(int arr[], int k) {
        int n = arr.length;

        MaxNum[][] maxNums = new MaxNum[k][n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < k; i++) {
                if (i == 0 && j == 0) {
                    maxNums[i][j] = new MaxNum(arr[i], arr[i]);
                } else if (i == 0) {
                    int maxEven = arr[j] % 2 == 0 ? Math.max(maxNums[i][j - 1].maxEven, arr[j]) : maxNums[i][j - 1].maxEven;
                    int maxAll = Math.max(maxNums[i][j - 1].maxAll, arr[j]);
                    maxNums[i][j] = new MaxNum(maxEven, maxAll);
                }else if(j==0){
                    maxNums[i][j] = new MaxNum(0,0);

                } else {
//                    System.out.println(i);
//                    System.out.println(j);
                    maxNums[i][j] = new MaxNum(0,0);
                    int prevKMaxEven = maxNums[i][j - 1].maxEven;
                    int prevKMaxAll = maxNums[i][j - 1].maxAll;

                    int evenNum = 0, all = 0;

                    if(arr[j]%2 == 0){
                        evenNum = maxNums[i-1][j-1].maxEven + arr[j];
                        all = maxNums[i-1][j-1].maxAll + arr[j];

                    }else{
                        if(maxNums[i-1][j-1].maxAll%2!=0){
                            evenNum = maxNums[i-1][j-1].maxAll + arr[j];
                            all = maxNums[i-1][j-1].maxAll + arr[j];
                        }
                    }

                    maxNums[i][j].maxEven = Math.max(evenNum,prevKMaxEven);
                    maxNums[i][j].maxAll = Math.max(all,prevKMaxAll);

                }


            }
        }

        return maxNums[k-1][n-1].maxEven;
    }


    public static void main(String[] args) {
        MaxSumEvenSubSequence maxSumEvenSubSequence = new MaxSumEvenSubSequence();
        System.out.println(maxSumEvenSubSequence.maxEvenSumSubsequence(new int[]{5, 5, 2, 4, 3},3));
        System.out.println(maxSumEvenSubSequence.maxEvenSumSubsequence(new int[]{4,2,6,7,8},3));
    }


}
