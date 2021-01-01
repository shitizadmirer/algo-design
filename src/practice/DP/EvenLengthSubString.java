package practice.DP;

public class EvenLengthSubString {

    public int getMaxEvenLengthString(String str){
        int n =str.length();
        if(n==0){
            return 0;
        }

        int maxLength=0;

        for(int i =0;i<n-1;i++){
            int leftIndex = i, rightIndex=i+1;

            int leftSum=0, rightSum=0;

            while (leftIndex>=0&&rightIndex<n){
                int leftValue = str.charAt(leftIndex)-'0';
                int rightValue = str.charAt(rightIndex)-'0';
                leftSum+=leftValue;
                rightSum+=rightValue;

                if (leftSum==rightSum){
                    maxLength = Math.max(maxLength, rightIndex-leftIndex+1);
                }

                leftIndex--;rightIndex++;

            }

        }

        return maxLength;
    }


    public int findLength(String str)
    {
        int n = str.length();
        int maxlen = 0; // Initialize result

        // A 2D table where sum[i][j] stores
        // sum of digits from str[i] to str[j].
        // Only filled entries are the entries
        // where j >= i
        int sum[][] = new int[n][n];

        // Fill the diagonal values for
        // substrings of length 1
        for (int i = 0; i < n; i++)
            sum[i][i] = str.charAt(i) - '0';

        // Fill entries for substrings of
        // length 2 to n
        for (int len = 2; len <= n; len++)
        {
            // Pick i and j for current substring
            for (int i = 0; i < n - len + 1; i++)
            {
                int j = i + len - 1;
                int k = len/2;

                // Calculate value of sum[i][j]
                sum[i][j] = sum[i][j-k] +
                        sum[j-k+1][j];

                // Update result if 'len' is even,
                // left and right sums are same
                // and len is more than maxlen
                if (len % 2 == 0 && sum[i][j-k] ==
                        sum[(j-k+1)][j] && len > maxlen)
                    maxlen = len;
            }
        }
        return maxlen;
    }


    public static void main(String[] args) {
        String str = "11";
        System.out.println("Length of the substring is "
                + new EvenLengthSubString().getMaxEvenLengthString(str));

        System.out.println("Length of the substring is "
                + new EvenLengthSubString().findLength(str));
    }
}
