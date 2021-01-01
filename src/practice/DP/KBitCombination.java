package practice.DP;

import java.util.LinkedList;
import java.util.Queue;

public class KBitCombination {

    private Queue<StringBuilder> prevBuckets;

    private void printSortedBitSetUtils() {

        while (!prevBuckets.isEmpty()){
            StringBuilder stringBuilder  = prevBuckets.poll();
            int oneIdx = findFirstOneIdx(stringBuilder);
            for(int i = oneIdx-1;i>=0;i--){
                StringBuilder newStrBuilder = new StringBuilder(stringBuilder);
                newStrBuilder.setCharAt(i,'1');
                System.out.println(newStrBuilder.toString());
                prevBuckets.add(newStrBuilder);
            }
        }
    }

    private int findFirstOneIdx (StringBuilder stringBuilder){
        for(int i  = 0;i<stringBuilder.length();i++){
            if (stringBuilder.charAt(i) == '1'){
                return i;
            }
        }
        return stringBuilder.length();
    }

    public void printBitSet(int k){

        prevBuckets = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < k; i++) {
            stringBuilder.append('0');
        }
        prevBuckets.add(stringBuilder);
        printSortedBitSetUtils();

    }

    public static void main(String[] args) {

       new KBitCombination().printBitSet(3);
        new KBitCombination().printBitSet(4);
        new KBitCombination().printBitSet(6);
        new KBitCombination().printBitSet(7);


    }
}
