package practice.DP;

public class SubSequenceSubString {


    public int getLengthOfSubSequenceString(String seq, String str){
        int n = seq.length();
        int m = str.length();

        int maxResult = 0;
        for (int i =0;i<m;i++){

            int k =i;

            int j =0;
            int result = 0;
            while(j<n && k< m){
                char seqChar  = seq.charAt(j);
                char strChar = str.charAt(k);
                if (seqChar == strChar){
                    result++;
                    k++;
                }
                j++;
            }

            maxResult = Math.max(result, maxResult);
        }

        return maxResult;
    }

    public static void main(String[] args) {
        String seq = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        String str = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";

        System.out.println(new SubSequenceSubString().getLengthOfSubSequenceString(seq, str));
    }
}
