package practice.DP;

import java.util.ArrayList;
import java.util.List;

public class SumOfProductCombinations {

    public List<Integer> getSumOfProductCombination(int n){

        List<Integer> results = new ArrayList<>();


        int [] comb = new int[n+1];
        int sum =0;

        for(int i=1;i<=n;i++){
            sum+=i;
            comb[i]=i;
        }
        results.add(sum);
        for(int fn=2;fn<=n;fn++){
            int fnSum=0;
            int last=1;
            int []a = new int[n+1];
            for(int i=n-2+1;i>0;i--){
                a[i] = ((a[i+1]/last) + comb[i+1])*i;
                last=i;
                fnSum+=a[i];
            }

            results.add(fnSum);
            for(int i =1;i<=n;i++){
                comb[i]=a[i];
            }

        }
        return results;

    }


    static void postfix(int a[], int n)
    {
        for (int i = n - 1; i > 0; i--)
        {
            a[i - 1] = a[i - 1] + a[i];
        }
    }

    // modify the array such that we don't
    // have to compute the products which
    // are obtained before
    static void modify(int a[], int n)
    {
        for (int i = 1; i < n; i++)
        {
            a[i - 1] = i * a[i];
        }
    }

    // finding sum of all combination
    // taken 1 to N at a time
    static void allCombination(int a[], int n)
    {
        int sum = 0;

        // sum taken 1 at time is simply sum of 1 - N
        for (int i = 1; i <= n; i++)
        {
            sum += i;
        }
        System.out.println(  sum);

        // for sum of products for all combination
        for (int i = 1; i < n; i++)
        {

            // finding postfix array
            postfix(a, n - i + 1);

            // sum of products taken i+1 at a time
            sum = 0;
            for (int j = 1; j <= n - i; j++)
            {
                sum += (j * a[j]);
            }
            System.out.println( sum);

            // modify the array for overlapping problem
            modify(a, n);
        }
    }



    public static void main(String[] args) {


        int n = 11;
        List<Integer> combinationsSums = new SumOfProductCombinations().getSumOfProductCombination(n);
        for (Integer combination: combinationsSums){
            System.out.println(combination);
        }



        int[] a = new int[n];

        // storing numbers from 1 to N
        for (int i = 0; i < n; i++)
        {
            a[i] = i + 1;
        }

        allCombination(a, n);

    }
}
