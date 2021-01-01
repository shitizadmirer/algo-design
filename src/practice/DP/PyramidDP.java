package practice.DP;

import java.util.ArrayList;
import java.util.List;

public class PyramidDP {

    public int pyramidConstructCost(int rocks[]){

        int n = rocks.length;
        if(n==0){
            return 0;
        }
        int [] heights = new int[n];

        for(int i =0;i<n;i++){
            if(i==0) {
                heights[i] = Math.min(i + 1, rocks[i]);
            }else{
                heights[i] = Math.min(heights[i-1]+1, Math.min(i + 1, rocks[i]));
            }
        }

        int maxHeight = Integer.MIN_VALUE;
        List<Integer> maxIndices = new ArrayList<>();


        for(int i =n-1;i>=0;i--) {
            if (i == n - 1) {
                heights[i] = Math.min(heights[i], Math.min(n-i, rocks[i]));
            } else {
                heights[i] = Math.min(Math.min(heights[i], heights[i+1]+1), Math.min(n-i, rocks[i]));
            }
            if(heights[i]>maxHeight){
                maxHeight = heights[i];
                maxIndices.clear();
                maxIndices.add(i);
            }else if (heights[i]==maxHeight){
                maxIndices.add(i);
            }
        }


        int minCost = Integer.MAX_VALUE;
        for(Integer index : maxIndices){
            minCost = Math.min(minCost, calculateCost(maxHeight,index,rocks));
        }
        return minCost;

    }

    private int calculateCost(int maxHeight, int maxIndex, int[] rocks){
        int n = rocks.length;
        int cost = 0;

        int left=maxIndex,right=maxIndex+1;
        int currentHeight = maxHeight-1;
        while(right<n){
            cost+=(rocks[right]-currentHeight);
            if(currentHeight>0){
                currentHeight--;
            }

            right++;
        }

        currentHeight = maxHeight;
        while(left>=0){
            cost+=(rocks[left]-currentHeight);
            if(currentHeight>0){
                currentHeight--;
            }
            left--;
        }
        return cost;
    }


    public int minPyramidCost(int arr[], int N)
    {

        // Store the maximum possible pyramid height
        int left[] = new int[N];
        int right[] = new int[N];

        // Maximum height at start is 1
        left[0] = Math.min(arr[0], 1);

        // For each position calculate maximum height
        for(int i = 1; i < N; ++i)
            left[i] = Math.min(arr[i],
                    Math.min(left[i - 1] + 1,
                            i + 1));

        // Maximum height at end is 1
        right[N - 1] = Math.min(arr[N - 1], 1);

        // For each position calculate maximum height
        for(int i = N - 2; i >= 0; --i)
            right[i] = Math.min(arr[i],
                    Math.min(right[i + 1] + 1,
                            N - i));

        // Find minimum possible among
        // calculated values
        int tot[] = new int[N];
        for(int i = 0; i < N; ++i)
            tot[i] = Math.min(right[i], left[i]);

        // Find maximum height of pyramid
        int max_ind = 0;
        for(int i = 0; i < N; ++i)
            if (tot[i] > tot[max_ind])
                max_ind = i;

        // Calculate cost of this pyramid
        int cost = 0;
        int height = tot[max_ind];

        // Calculate cost of left half
        for(int x = max_ind; x >= 0; --x)
        {
            cost += arr[x] - height;
            if (height > 0)
                --height;
        }

        // Calculate cost of right half
        height = tot[max_ind] - 1;
        for(int x = max_ind + 1; x < N; ++x)
        {
            cost += arr[x] - height;
            if (height > 0)
                --height;
        }
        return cost;
    }

    public static void main(String[] args) {
        int arr[] = { 1,4,4,6,2,1,5,6,7,3,54,6,7,9,89,4,5,6,7,8,9,1,2,3,4};
        System.out.println(new PyramidDP().pyramidConstructCost(arr));
        System.out.println(new PyramidDP().minPyramidCost(arr, arr.length));
    }
}
