package practice;

import java.util.*;

public class AtMostKDistinctOdd {

    private int[] arr;
    private int n;

    private Set<List<Integer>> getUniqueSubArray(int[] arr, int k){
        this.arr = arr;
        this.n = arr.length;

        int i =0, j=0;
        int oddCount = 0;

        Set<List<Integer>> result = new HashSet<>();
        while (j<n){
            if (arr[j] % 2 != 0) {
                oddCount++;
                if (oddCount > k) {
                    while (i <= j && arr[i] % 2 == 0) {
                        i++;
                    }
                    oddCount--;
                    i++;
                }
            }
            result.addAll(formSetOnInclusion(i,j));
            j++;
        }
        return result;
    }

    private Set<List<Integer>> formSetOnInclusion(int start, int end){
        if (start>end){
            return new HashSet<>();
        }

        Set<List<Integer>> result = new HashSet<>();

        List<Integer> prevArray = new ArrayList<>();

        for(int i = end;i>=start;i--){
            List<Integer> newArray = new ArrayList<>(prevArray);
            newArray.add(arr[i]);
            result.add(newArray);
            prevArray = newArray;
        }
        for (List<Integer> list: result){
            Collections.reverse(list);
        }
        return result;
    }


    public static void main(String[] args) {

        AtMostKDistinctOdd atMostKDistinctOdd =  new AtMostKDistinctOdd();

//        int[] arr = new int[]{183,
//                43,
//                167,
//                130,
//                12,
//                223,
//                60,
//                44,
//                134,
//                236,
//                34,
//                66,
//                196,
//                100,
//                79,
//                179,
//                225,
//                16,
//                57,
//                22,
//                185,
//                228
//        };
        int [] arr = new int[]{3,2,3,4};

        Set<List<Integer>> uniqueSubArray = atMostKDistinctOdd.getUniqueSubArray(arr, 1);

        System.out.println(uniqueSubArray);
        System.out.println(uniqueSubArray.size());


    }
}
