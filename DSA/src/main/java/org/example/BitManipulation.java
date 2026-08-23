package org.example;

import java.util.Arrays;

public class BitManipulation {

    // LeetCode - Easy - 136. Single Number

    /**
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * <p>
     * Example 1:
     * Input: nums = [2,2,1]
     * Output: 1
     * <p>
     * Example 2:
     * Input: nums = [4,1,2,1,2]
     * Output: 4
     * <p>
     * Example 3:
     * Input: nums = [1]
     * Output: 1
     */
    public static int singleNumber(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            ans = ans ^ i;
        }
        return ans;
    }


    // LeetCode - Easy - 191. Number of 1 Bits

    /**
     * Given a positive integer n, write a function that returns the number of set bits in its binary representation (also known as the Hamming weight).
     * <p>
     * Example 1:
     * Input: n = 11
     * Output: 3
     * Explanation:
     * The input binary string 1011 has a total of three set bits.
     * <p>
     * Example 2:
     * Input: n = 128
     * Output: 1
     * Explanation:
     * The input binary string 10000000 has a total of one set bit.
     * <p>
     * Example 3:
     * Input: n = 2147483645
     * Output: 30
     * Explanation:
     * The input binary string 1111111111111111111111111111101 has a total of thirty set bits.
     */
    public static boolean isIthBitSetUsingOR(int num, int i) {
        int ans = num | (1 << i);
        if (ans == num)
            return true;
        return false;
    }

    public static boolean isIthBitSetUsingXOR(int num, int i) {
        int ans = num ^ (1 << i);
        double twoi = Math.pow(2, i);
        int expectedSum = num + (int) twoi;
        if (ans == expectedSum)
            return false;
        return true;
    }

    public static boolean isIthBitSetUsingAND(int num, int i) {
        int ans = num & (1 << i);
        if (ans == 0)
            return false;
        return true;
    }

    public static int hammingWeight(int num) {
        int hw = 0;
        for (int i = 0; i < 32; i++) {
            if (isIthBitSetUsingAND(num, i)) {
                hw++;
            }
        }
        return hw;
    }

    // LeetCode - Easy - 338. Counting Bits

    /**
     * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
     * Do not solve it with built-in functions (i.e., like __builtin_popcount in C++).
     * <p>
     * Example 1:
     * Input: n = 2
     * Output: [0,1,1]
     * Explanation:
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * <p>
     * Example 2:
     * Input: n = 5
     * Output: [0,1,1,2,1,2]
     * Explanation:
     * 0 --> 0
     * 1 --> 1
     * 2 --> 10
     * 3 --> 11
     * 4 --> 100
     * 5 --> 101
     */
    public static int[] countBits1(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = hammingWeight(i);
        }
        return ans;
    }


    // LeetCode - Medium - 260. Single Number III

    /**
     * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
     * Find the two elements that appear only once. You can return the answer in any order.
     * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
     * <p>
     * Example 1:
     * Input: nums = [1,2,1,3,2,5]
     * Output: [3,5]
     * Explanation:  [5, 3] is also a valid answer.
     * <p>
     * Example 2:
     * Input: nums = [-1,0]
     * Output: [-1,0]
     * <p>
     * Example 3:
     * Input: nums = [0,1]
     * Output: [1,0]
     */
    public static int[] singleNumber3(int[] nums) {
        // Step 1: XOR all the bits
        int xorAns = 0;
        for (int n : nums) {
            xorAns = xorAns ^ n;
        }

        // Step 2: Find the first set bit
        int id = -1;
        for (int i = 0; i < 32; i++) {
            if (isIthBitSetUsingAND(xorAns, i)) {
                id = i;
                break;
            }
        }

        // Step 3: Divide into 2 sets
        int num1 = 0;   // Consists of numbers with id bit set
        int num2 = 0;   // Consists of numbers with id bit unset
        for (int n : nums) {
            if (isIthBitSetUsingAND(n, id)) {
                num1 = num1 ^ n;
            } else {
                num2 = num2 ^ n;
            }
        }
        int[] ans = new int[2];
        ans[0] = num1;
        ans[1] = num2;
        return ans;
    }

    // LeetCode - Medium - 137. Single Number II

    /**
     * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * <p>
     * Example 1:
     * Input: nums = [2,2,3,2]
     * Output: 3
     * <p>
     * Example 2:
     * Input: nums = [0,1,0,1,0,1,99]
     * Output: 99
     */
    public static int singleNumber2(int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int n : nums) {
                if (isIthBitSetUsingAND(n, i)) {
                    count++;
                }
            }
            if (count % 3 == 1) {
                result = result | (1 << i);     // Push 1 to ith position. We are doing OR with result to merge with result.
            }
        }
        return result;
    }

    static void main() {
        /*
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println("Single number: " + singleNumber(nums));
         */

        /*
        int num = 2147483645;
        System.out.println("Hamming weight: " + hammingWeight(num));
         */

        /*
        int n = 5;
        System.out.println("Count bits 1: " + Arrays.toString(countBits1(n)));
         */

        /*
        int[] num = {1, 2, 1, 3, 2, 5};
        System.out.println("Count bits 3: " + Arrays.toString(singleNumber3(num)));
         */

        int[] num1 = {0, 1, 0, 1, 0, 1, 99};
        System.out.println("Count bits 2: " + singleNumber2(num1));
    }
}
