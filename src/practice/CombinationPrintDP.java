package practice;

import java.util.ArrayList;
import java.util.List;

class NumMarker{
    int num;
    int highestPlace;

    public NumMarker(int num, int highestPlace){
        this.num = num;
        this.highestPlace = highestPlace;
    }
}
public class CombinationPrintDP {

    private int n;

    public void printBitCombinations(int n){
        this.n = n;

        List<NumMarker> markerNums = new ArrayList<>();
        for(int i =0;i<n;i++){
            NumMarker markerNum = new NumMarker(1<<i, i);
            markerNums.add(markerNum);
        }

        printNum(markerNums);
        for(int bitCount=2;bitCount<=n;bitCount++){
            List<NumMarker> placeHolderList = new ArrayList<>();

            for(NumMarker numMarker : markerNums){
                int highestPlaceSet = numMarker.highestPlace;
                int num = numMarker.num;
                for(int place = highestPlaceSet+1;place<=n;place++){
                    placeHolderList.add(new NumMarker((1<<place ) +num, place ));
                }
            }

            markerNums = placeHolderList;
            printNum(markerNums);

        }
    }

    private void printNum(List<NumMarker> markerNum){
        for(NumMarker marker: markerNum){
            printNumber(marker);
        }
        System.out.println();
    }

    private void printNumber(NumMarker numMarker){
        int num = numMarker.num;
        StringBuilder  sb = new StringBuilder();
        while(num!=0){
            if(num %2!=0){
                sb.append('1');
            }else{
                sb.append('0');
            }
            num = num/2;
        }
        for(int i =n;i>numMarker.highestPlace;i--){
            sb.append('0');
        }
        System.out.print(sb.reverse().toString() + " ");
    }


    public static void main(String[] args) {
        CombinationPrintDP combinationPrintDP =  new CombinationPrintDP();
        combinationPrintDP.printBitCombinations(7);
    }
}
