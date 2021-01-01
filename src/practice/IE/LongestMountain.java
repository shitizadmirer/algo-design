package practice.IE;

public class LongestMountain {

    public int getLongestMountainSequence(int arr[]){

        int n = arr.length;
        if(n<=2){
            return 0;
        }

        int maxLen = 0;

        int incCount = 0;
        for(int i =1;i<n-1;i++){


            if(arr[i] > arr[i+1] && arr[i]> arr[i-1]){
                int decCount = 0;
                incCount++;
                int j =i;
                while ( j< n-1 && arr[j+1] < arr[j]){
                    decCount++;
                    j++;
                }
                maxLen = Math.max(incCount + decCount + 1, maxLen);
                incCount = 0;
                i = j;

            }else if (arr[i]>arr[i-1]){
                incCount++;
            }else {
                incCount = 0;
            }

        }

        return maxLen;

    }


    public static void main(String[] args) {


        LongestMountain longestMountain = new LongestMountain();

//        System.out.println(
//                longestMountain.getLongestMountainSequence(new int[]{1,2,9,8,7,5,6,9,8,7,5,6,9,8,7,5,6,9,8,7,5,6})
//        );


        System.out.println(
                longestMountain.getLongestMountainSequence(new int[]{2,2,2})
        );


    }
}
