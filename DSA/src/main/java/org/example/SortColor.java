package org.example;

import java.util.Arrays;

// Leet code question: 75 (Medium)
/*
* Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
* We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
* You must solve this problem without using the library's sort function.

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
 */
public class SortColor {

    public static void sortColors(int[] nums) {
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        while (j <= k) {
            if (nums[j] == 0) {
                swap(i, j, nums);
                i++;
                j++;
            } else if (nums[j] == 1) {
                j++;
            } else if (nums[j] == 2) {
                swap(j, k, nums);
                k--;
            }
        }
    }

    public static void swap(int a, int b, int[] num) {
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }

    static void main() {
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        int[] nums2 = {2, 0, 1};
        sortColors(nums1);
        System.out.println("After sorting nums1 is : " + Arrays.toString(nums1));
        sortColors(nums2);
        System.out.println("After sorting nums2 is : " + Arrays.toString(nums2));
    }

}
