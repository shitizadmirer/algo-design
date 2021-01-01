package practice.DP;

import java.util.HashMap;
import java.util.Map;

public class MinElementsAfterRemoval {


    private int [] arr;
    private int n ;
    private int diff;
    private Map<String,Integer> dp;


    public int getMinElementCount(int arr[], int diff){
        this.arr = arr;
        this.n = arr.length;
        this.diff = diff;
        this.dp = new HashMap<>();

        StringBuilder indexStr = new StringBuilder();
        for (int i =0;i<n;i++){
            indexStr.append(i).append(",");
        }
        String index = indexStr.toString().substring(0, indexStr.toString().length()-1);

        return getMinElements(index);
    }



    private int getMinElements(String indices){
        if (indices == null || indices.length()==0){
            return 0;
        }

        if (dp.containsKey(indices)){
            return dp.get(indices);
        }

        int ans = (indices.length()/2)+1;
        for (int i =0;i<n;i++){

            if (arr[i]!=-1){

                int j =i+1;
                while (j<n && arr[j]==-1){
                    j++;
                }
                int k =j+1;
                while (k<n&& arr[k]==-1){
                    k++;
                }

                if (j<n&&k<n && arr[i]+diff == arr[j]&& arr[j]+diff ==arr[k]){
                    int tempI = arr[i], tempJ = arr[j], tempK = arr[k];
                    arr[i]=-1;arr[j]=-1;arr[k]=-1;
                    StringBuilder indexStr = new StringBuilder();
                    for (int l = 0;l<n;l++){
                        if (arr[l]!=-1){
                            indexStr.append(l).append(",");
                        }
                    }

                    String index = indexStr.toString();
                    if (index.length()>0){
                        index = index.substring(0,index.length()-1);
                    }
                    int result = getMinElements(index);
                    ans = Math.min(result, ans);
                    arr[i] = tempI;arr[j] = tempJ;arr[k] = tempK;

                }
            }

        }

        dp.put(indices, ans);
        return ans;
    }


    public static void main(String[] args) {

        MinElementsAfterRemoval minElementsAfterRemoval = new MinElementsAfterRemoval();
        System.out.println(minElementsAfterRemoval.getMinElementCount(new int[]{2, 3, 4, 5, 6, 4},1));
    }

}
