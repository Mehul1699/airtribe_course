package org.example;

import java.util.Arrays;

public class BubbleSort {

    public static void bubbleSort(int[] unsorted) {
        System.out.println("Before sorting");
        System.out.println(Arrays.toString(unsorted));
        int length = unsorted.length;
        boolean isSwapped;    // For the case if array is already sorted. Through this, the time complexity will reduce down to O(N). Otherwise, it is O(N^2).
        for (int i = 1; i < length - 1; i++) {
            isSwapped = false;
            for (int j = 0; j < length - 1; j++) {
                if (unsorted[j + 1] < unsorted[j]) {
                    int temp = unsorted[j + 1];
                    unsorted[j + 1] = unsorted[j];
                    unsorted[j] = temp;
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }
        System.out.println("After sorting");
        System.out.println(Arrays.toString(unsorted));
    }

    static void main() {
        int[] arr = {45, 80, 22, 1, 9, 55, 71, 60};
        bubbleSort(arr);
    }
}
