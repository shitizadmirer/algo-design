package practice.DP;

import java.util.Objects;

public class ABCFormation {


    private int[][] dpFormation;

    public int findStringWays(String str){
        int n =str.length();
        this.dpFormation = new int[n][3];
        int max = 0;

        for(int i = n-1;i>=0;i--){
            char chr = str.charAt(i);
            int ascii = chr - 'a';
            Character reqChar = getRequiredChar(chr);
            int sameCharCount = 0;
            int reqCharCount = 0;
            for(int j =i+1;j<n;j++){
                if (chr == str.charAt(j)){
                    sameCharCount += dpFormation[j][ascii];
                }
            }
            for (int j =i+1;j<n;j++){
                if (Objects.equals(reqChar,str.charAt(j))){
                    reqCharCount   += dpFormation[j][reqChar-'a'];
                }
            }
            if (Objects.equals(chr,'c')&& sameCharCount == 0){
                dpFormation[i][ascii] = 1;
            }else {
                dpFormation[i][ascii] = sameCharCount + ((2*(reqCharCount-1))+1);
            }
            max = Math.max(dpFormation[i][0], max);
        }
        return max;

    }

    public int findWaysG4G(String s){
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == 'a')
                aCount = (1 + 2 * aCount);

            else if (s.charAt(i) == 'b')
                bCount = (aCount + 2 * bCount);


            else if (s.charAt(i) == 'c')
                cCount = (bCount + 2 * cCount);
        }

        return cCount;
    }

    private Character getRequiredChar(char chr){
        if (chr == 'a'){
            return 'b';
        }else  if (chr =='b'){
            return 'c';
        }
        return null;
    }

    public int findCount(String s){
        int aCount = 0;
        int bCount  = 0;
        int cCount = 0;
        for(int i =s.length()-1;i>=0;i--){
            char chr = s.charAt(i);
            if (chr == 'a'){
                aCount = 2*aCount + bCount;
            } else if (chr == 'b') {
                bCount = 2*bCount + cCount;
            } else if (chr == 'c') {
                cCount = 1+2*cCount;
            }
        }
        return aCount;
    }

    public static void main(String[] args) {

        System.out.println( new ABCFormation().findCount("abcabc"));
        System.out.println( new ABCFormation().findCount("abbc"));
        System.out.println( new ABCFormation().findCount("ababcababababababababababaabababababac"));

        System.out.println("Comparing");
        System.out.println( new ABCFormation().findWaysG4G("abcabc"));
        System.out.println( new ABCFormation().findWaysG4G("abbc"));
        System.out.println( new ABCFormation().findWaysG4G("ababcababababababababababaabababababac"));
    }

}
