package practice.DP;


import java.util.Vector;

public class BracketResults {



    public Vector<Integer> getPossibleBracketResults(String expression){
        char[] operators = getOperators(expression);
        char[] operands = getOperands(expression);
        int operandSize = operands.length;
        Vector<Integer> [][] results = new Vector[operandSize][operandSize];
        for(int len=0;len<operandSize;len++){
            for(int j =0;j<operandSize-len;j++){
                Vector<Integer> indexResults = new Vector<>();
                if(len==0){
                    indexResults.add(Character.getNumericValue(operands[j]));
                }else{
                    for(int k = j+1;k<=j+len && k< operandSize;k++){
                        indexResults.addAll(mergeResults(results[j][k - 1], results[k][j + len], operators[k - 1]));

                    }
                }
                results[j][j + len] = indexResults;
            }
        }
        return results[0][operandSize-1];
    }

    private Vector<Integer> mergeResults(Vector<Integer> A, Vector<Integer> B, char operator){
        Vector<Integer> results = new Vector<>();
        for(Integer a: A){
            for (Integer b:B){
                int value;
                if (operator == '-'){
                    value = a-b;
                }else if(operator == '+'){
                    value = a+b;
                }else{
                    value = a*b;
                }
                results.add(value);
            }
        }
        return results;
    }

    private char[] getOperators(String expression){
        int size = expression.length();
        char[] operators = new char[size/2];

        int opIndex = 0;
        for(int i =0;i<expression.length();i++){
            if(expression.charAt(i) == '-' || expression.charAt(i) == '+' || expression.charAt(i) == '*'){
                operators[opIndex++] = expression.charAt(i);
            }
        }
        return operators;
    }

    private char[] getOperands(String expression){

        int size = expression.length();
        char[] operands = new char[(size/2)+1];

        int opIndex = 0;
        for(int i =0;i<expression.length();i++){
            char chr  = expression.charAt(i);
            if(chr>=48&&chr<=57){
                operands[opIndex++] = expression.charAt(i);
            }
        }
        return operands;
    }


    public static void main(String[] args) {


        Vector<Integer> answer = new BracketResults().getPossibleBracketResults("5*4-3*2");
        for(Integer a:  answer) {
            System.out.println(a);
        }
    }

}
