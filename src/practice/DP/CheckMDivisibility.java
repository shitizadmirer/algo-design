package practice.DP;

import java.util.HashSet;
import java.util.Set;

public class CheckMDivisibility {

    private int[] arr;
    private int n, m;

    public boolean checkDivisibility(int[] arr, int m) {
        this.arr = arr;
        this.m = m;
        this.n = arr.length;

        Set<Integer> possibleModulo = getModulo(1);

        Set<Integer> currentModulo = new HashSet<>();
        for (Integer modulo : possibleModulo) {
            currentModulo.add((modulo + arr[0]) % m);
        }
        return currentModulo.contains(0);

    }

    private Set<Integer> getModulo(int index) {
        if (index >= n) {
            Set<Integer> modulo = new HashSet<>();
            modulo.add(0);
            return modulo;
        }

        Set<Integer> possibleModulo = getModulo(index + 1);
        Set<Integer> currentModulo = new HashSet<>();

        for (Integer modulo : possibleModulo) {
            currentModulo.add((modulo + arr[index]) % m);
            currentModulo.add((modulo - arr[index]) % m);
        }
        return currentModulo;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 9};
        System.out.println(new CheckMDivisibility().checkDivisibility(arr, 2));
    }


}
