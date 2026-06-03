package org.example;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TwoPointers {
    static void main() {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        System.out.println(maxWater_Bruteforce(arr));
        System.out.println(maxWater_Optimized(arr));
    }

    // Leet code Question 11
    // Max Water Brute force approach
    // TC O(N^2)
    // SC O(N)
    public static int maxWater_Bruteforce(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int height = Math.min(arr[i], arr[j]);
                int width = j - i;
                int water = height * width;
                list.add(water);
            }
        }
        int finalAns = list.getFirst();
        for (int i = 0; i < list.size(); i++) {
            finalAns = Math.max(finalAns, list.get(i));
        }
        return finalAns;
    }

    // Max Water optimized - Two Pointer approach
    // TC O(N)
    // SC O(1)
    public static int maxWater_Optimized(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        int maxWater = 0;
        while (i < j) {
            int height = Math.min(arr[i], arr[j]);
            int width = j - i;
            int water = height * width;
            maxWater = Math.max(maxWater, water);
            if (arr[i] < arr[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxWater;
    }
}
