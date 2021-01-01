package practice.DP;

public class RegexMatchString {


    private String regex;
    private String str;

    private int regexLen;
    private int strLen;

    private int [][] dp ;

    public boolean doesRegexMatchString(String regex, String str){
        this.regex = regex;
        this.str = str;

        this.regexLen = this.regex.length();
        this.strLen = this.str.length();

        this.dp = new int[regexLen][strLen];
        for(int i =0;i<regexLen;i++){
            for (int j =0;j<strLen;j++){
                dp[i][j]  = -1;
            }
        }

        return regexStrMatch(0,0);

    }


    private boolean regexStrMatch(int regexIdx, int strIdx){

        if (regexIdx>=regexLen && strIdx >= strLen){
            return true;
        }else if (regexIdx>=regexLen){
            return false;
        }else  if (strIdx>=strLen){
            return false;
        }

        if (dp[regexIdx][strIdx]!=-1){
            return dp[regexIdx][strIdx] == 1;
        }
        char regChar = regex.charAt(regexIdx);
        char strChar = str.charAt(strIdx);

        if (regChar == '*') {
            boolean ans1 = regexStrMatch(regexIdx+1, strIdx+1);
            boolean ans2 = regexStrMatch(regexIdx+1, strIdx);
            boolean ans3 = regexStrMatch(regexIdx, strIdx+1);
            this.dp[regexIdx][strIdx] =  ans1 ||  ans2 || ans3 ?  1:0;

        }else if (regChar == '?'){

            this.dp[regexIdx][strIdx] =  regexStrMatch(regexIdx+1, strIdx+1) ? 1:0;
        }else{
            this.dp[regexIdx][strIdx] =  regChar == strChar ? 1:0;
        }

        return this.dp[regexIdx][strIdx] == 1;
    }

    public static void main(String[] args) {


        RegexMatchString regexMatchString = new RegexMatchString();

        System.out.println(regexMatchString.doesRegexMatchString("*****ba*****ab","baaabab"));

        System.out.println(regexMatchString.doesRegexMatchString("baaa?ab","baaabab"));

        System.out.println(regexMatchString.doesRegexMatchString("*****ba*****ab","baaabab"));

        System.out.println(regexMatchString.doesRegexMatchString("a*ab","baaabab"));
    }

}
