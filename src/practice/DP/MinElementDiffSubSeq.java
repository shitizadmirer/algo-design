package practice.DP;


import java.util.ArrayList;
import java.util.List;

class Sequence{
    Integer firstElement;
    Integer count;

    public Sequence(Integer firstElement, Integer count){
        this.count = count;
        this.firstElement = firstElement;
    }
}

enum InvolveType{
    INC, DEC, NA
}
class SequenceOptions{

    InvolveType involveType;
    Sequence increasing;
    Sequence decreasing;

    Sequence notInvolved;

    public SequenceOptions(InvolveType involveType, Sequence increasing,Sequence decreasing,  Sequence notInvolved){
        this.increasing = increasing;
        this.decreasing = decreasing;
        this.notInvolved = notInvolved;
        this.involveType = involveType;
    }
}
public class MinElementDiffSubSeq {


    public int findMinElements(int arr[]){
        int n = arr.length;
        if(n == 0){
            return 0;
        }

        Sequence defaultSeq = new Sequence(null, 0);
        SequenceOptions[] sequenceOptions = new SequenceOptions[3];
        sequenceOptions[0] = new SequenceOptions(InvolveType.INC, new Sequence(arr[n-1], 1), defaultSeq, defaultSeq);
        sequenceOptions[1] = new SequenceOptions(InvolveType.DEC, defaultSeq, new Sequence(arr[n-1], 1), defaultSeq);
        sequenceOptions[2] = new SequenceOptions(InvolveType.NA, defaultSeq,defaultSeq, new Sequence(arr[n-1], 1));


        for(int i =n-2;i>=0;i--){
            int currentElement = arr[i];

            sequenceOptions = getSeqForCurrentElement(sequenceOptions,currentElement);
        }

        Integer minValue = Integer.MAX_VALUE;
        for (SequenceOptions sequenceOption :  sequenceOptions){
            if (minValue >  sequenceOption.notInvolved.count){
                minValue  = sequenceOption.notInvolved.count;
            }
        }
        return minValue;
    }

    private SequenceOptions[] getSeqForCurrentElement(SequenceOptions[] nextElemSeq, int currentElement){

        SequenceOptions[] newOptions = new SequenceOptions[3];

        newOptions[0] = formIncreasingSeq(nextElemSeq,currentElement);
        newOptions[1] = formDecreasingSeq(nextElemSeq,currentElement);
        newOptions[2] = formNotInvolvedSeq(nextElemSeq,currentElement);
        return newOptions;
    }


    private SequenceOptions formIncreasingSeq(SequenceOptions[] nextElemSeq, int currentElement){

        List<SequenceOptions> validSeq = new ArrayList<>();
        for (SequenceOptions sequenceOptions :  nextElemSeq){
            Integer firstElement = sequenceOptions.increasing.firstElement;
            if (firstElement==null){
                validSeq.add(sequenceOptions);
            }
            else if (firstElement > currentElement){
                validSeq.add(sequenceOptions);
            }
        }
        Integer maxCount = 0;
        Integer naCount = 0;

        SequenceOptions result = new SequenceOptions(InvolveType.INC, null,null,null);

        for (SequenceOptions sequenceOptions :  validSeq){

            Integer count  = sequenceOptions.increasing.count+1;
            if (count>maxCount){
                maxCount = count;
                naCount = sequenceOptions.notInvolved.count;

                result.decreasing = sequenceOptions.decreasing;
                result.notInvolved = sequenceOptions.notInvolved;
                result.increasing = new Sequence(currentElement, maxCount);
            }else if (count.equals(maxCount)){
                if (naCount > sequenceOptions.notInvolved.count){
                    maxCount = count;
                    naCount = sequenceOptions.notInvolved.count;
                    result.decreasing = sequenceOptions.decreasing;
                    result.notInvolved = sequenceOptions.notInvolved;
                    result.increasing = new Sequence(currentElement, maxCount);
                }
            }
        }



        return result;

    }

    private SequenceOptions formDecreasingSeq(SequenceOptions[] nextElemSeq, int currentElement){
        List<SequenceOptions> validSeq = new ArrayList<>();
        for (SequenceOptions sequenceOptions :  nextElemSeq){
            Integer firstElement = sequenceOptions.decreasing.firstElement;
            if (firstElement==null){
                validSeq.add(sequenceOptions);
            }
            else if (firstElement < currentElement){
                validSeq.add(sequenceOptions);
            }
        }
        Integer maxCount = 0;
        Integer naCount = 0;

        SequenceOptions result = new SequenceOptions(InvolveType.DEC, null,null,null);

        for (SequenceOptions sequenceOptions :  validSeq){

            Integer count  = sequenceOptions.decreasing.count+1;
            if (count>maxCount){
                maxCount = count;
                naCount = sequenceOptions.notInvolved.count;

                result.increasing = sequenceOptions.increasing;
                result.notInvolved = sequenceOptions.notInvolved;
                result.decreasing = new Sequence(currentElement, maxCount);
            }else if (count.equals(maxCount)){
                if (naCount > sequenceOptions.notInvolved.count){
                    maxCount = count;
                    naCount = sequenceOptions.notInvolved.count;
                    result.increasing = sequenceOptions.increasing;
                    result.notInvolved = sequenceOptions.notInvolved;
                    result.decreasing = new Sequence(currentElement, maxCount);
                }
            }
        }

        return result;
    }

    private SequenceOptions formNotInvolvedSeq(SequenceOptions[] nextElemSeq, int currentElement){

        int minInvolvedCount = Integer.MAX_VALUE;
        SequenceOptions result = new SequenceOptions(InvolveType.NA, null,null,null);
        for (SequenceOptions sequenceOptions : nextElemSeq){
            if (minInvolvedCount > sequenceOptions.notInvolved.count){

                minInvolvedCount = sequenceOptions.notInvolved.count;
                result.notInvolved = new Sequence(currentElement, sequenceOptions.notInvolved.count+1);
                result.increasing = sequenceOptions.increasing;
                result.decreasing = sequenceOptions.decreasing;
            }
        }

        return result;
    }


    public static void main(String[] args) {

        MinElementDiffSubSeq minElementDiffSubSeq = new MinElementDiffSubSeq();
        int answer= minElementDiffSubSeq.findMinElements(new int[]{7,8,1,2,4,6,3,5,2,1,8,7});
        System.out.println(answer);
    }
}
