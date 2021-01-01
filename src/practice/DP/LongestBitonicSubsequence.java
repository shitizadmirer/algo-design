package practice.DP;

import java.util.*;

public class LongestBitonicSubsequence {

    TreeMap<Integer, List<Integer>> lisList = new TreeMap<>();
    List<Integer> highList = new ArrayList<>();

    public List<Integer> getBitonicSubSeq(int[] forward, int[] backward) {

        List<Integer> forwardLIS = getLongestLIS(forward);

        int n = backward.length;

        for (int i = 0; i < (n / 2); i++) {
            int temp = backward[i];
            backward[i] = backward[n - 1 - i];
            backward[n - 1 - i] = temp;
        }
        List<Integer> backwardLIS = getLongestLIS(backward);
        Collections.reverse(backwardLIS);
        forwardLIS.addAll(backwardLIS);
        return forwardLIS;
    }

    private List<Integer> getLongestLIS(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            placeNumber(arr[i]);
        }
        List<Integer> lastList = null;
        for (Map.Entry<Integer, List<Integer>> entry : lisList.entrySet()) {
            lastList = entry.getValue();
        }
        lisList.clear();
        highList.clear();
        return lastList;
    }

    private void placeNumber(int num) {

        if (lisList.isEmpty()) {
            List<Integer> newNums = new ArrayList<>();
            newNums.add(num);
            lisList.put(newNums.size(), newNums);
            highList.add(num);
        } else {

            int ceilIndex = getCeilIndex(num);
            int n = lisList.size();
            List<Integer> lis;
            if (ceilIndex < n) {
                lis = lisList.get(ceilIndex + 1);
                lis.set(lis.size() - 1, num);
                highList.set(ceilIndex, num);
            } else {
                List<Integer> oldlis = lisList.get(n);
                lis = new ArrayList<>(oldlis);
                lis.add(num);
                highList.add(num);

            }
            lisList.put(lis.size(), lis);
        }
    }

    private int getCeilIndex(int num) {
        int n = highList.size();
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int midNo = highList.get(mid);

            if (mid == n - 1) {
                return midNo < num ? n : mid;
            } else {
                if (midNo > num) {
                    high = mid - 1;
                } else if (midNo < num && highList.get(mid + 1) > num) {
                    return mid + 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return low;
    }


    public static void main(String[] args) {
        int[] arr1 = new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        int[] arr2 = new int[]{6, 5, 4, 3, 2, 1, 3, 5, 2};
        List<Integer> lis = new LongestBitonicSubsequence().getBitonicSubSeq(arr1, arr2);

        for (Integer l : lis) {
            System.out.print(l);
            System.out.print(" ");

        }
    }
}
