package org.example;

import java.util.Arrays;

// Leet code question 283
/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 *
 *
 *     Example 1:
 *
 *     Input: nums = [0,1,0,3,12]
 *     Output: [1,3,12,0,0]
 *     Example 2:
 *
 *     Input: nums = [0]
 *     Output: [0]
 * */
public class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] != 0) {
                swap(i, j, nums);
                j++;
            }
            i++;
        }
    }

    static void main() {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println("After moving zeroes to end: " + Arrays.toString(nums));
    }

    public static void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
