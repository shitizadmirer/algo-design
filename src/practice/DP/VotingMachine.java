package practice.DP;

import java.util.Arrays;

public class VotingMachine {



    public boolean isVotingPossible(int [] voteTimes, int maxAllowed){

        int totalSum =0;
        int n  = voteTimes.length;
        for(int i =0;i<n;i++){
            totalSum+=voteTimes[i];
        }

        Arrays.sort(voteTimes);

        for(int i =0;i<n;i++){
            int prefixSum  = 0;
            for (int j =0;j<n;j++){
                prefixSum += voteTimes[j];
                int groupA = prefixSum - voteTimes[i];
                int groupB = totalSum - prefixSum + voteTimes[i];
                if(groupA >= 0 && groupA<=maxAllowed && groupB>=0&& groupB<=maxAllowed){
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int [] voteTimes = new int[]{1,1,2,2,3,3,4,4,5,5,6,6};
        System.out.println(new VotingMachine().isVotingPossible(voteTimes,42));
    }
}
