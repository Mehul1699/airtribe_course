package org.example;

import java.util.Arrays;

public class BinarySearch {

    // TC - O(logn)
    // SC - O(1)
    public static boolean isPresent_Binary(int[] arr, int key) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
//            int mid = (l + r) / 2;
            // To avoid integer overflow
            int mid = l + ((r - l) / 2);
            if (arr[mid] == key)
                return true;
            else if (arr[mid] > key)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return false;
    }

    static void main() {
        int[] arr = {10, 30, 50, 90, 100};
        int key = 10;
//        System.out.println("Is key present? : " + isPresent_Binary(arr, key));

        int[] nums1 = {5, 7, 7, 8, 8, 10};
//        int target = 8;
        int target = 6;
//        System.out.println("First occurance of target: " + target + " is: " + firstOcc(nums1, target));
//        System.out.println("Last occurance of target: " + target + " is: " + lastOcc(nums1, target));

        /*
//        Leetcode question 34
        int[] nums2 = {5, 7, 7, 8, 8, 8, 10};
        int fl = 8;
        int[] ans = new int[2];
        ans[0] = firstOcc(nums2, fl);
        ans[1] = lastOcc(nums2, fl);
        System.out.println("First and last occurance of target: " + target + " is: " + Arrays.toString(ans));
         */

        /*
//        Number of occurrence - Geeks for Geeks
        int[] nums3 = {5, 7, 7, 8, 8, 8, 10};
        int targetNO = 8;
        int firstOcc = firstOcc(nums3, targetNO);
        int lastOcc = lastOcc(nums3, targetNO);
        int ans = 0;
        if (firstOcc == -1 || lastOcc == -1) {
        } else {
            ans = lastOcc - firstOcc + 1;
        }
        System.out.println("Number of occurances of target: " + targetNO + " is: " + ans);
         */

        /*
//        Leetcode question 35 - Search Insert Position
        int[] nums4 = {1, 3, 5, 6};
        int tar = 7;
        System.out.println("The insert position for target: " + tar + " is: " + searchInsertPosition(nums4, tar));
         */

//        RSA
        /*
//        Leetcode question 153 - Find Minimum in Sorted Rotated Array
        int[] numsArray = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Minimum element in RSA: " + minimumInRSA(numsArray));
         */

        /*
        int[] rotatedArray = {30, 40, 50, 10, 20};
        System.out.println("Number of rotations in RSA: " + numberOfRotations(rotatedArray));
         */

        // Leetcode question 33 - Search in Rotated Sorted Array
        int[] numsArray2 = {4, 5, 6, 7, 0, 1, 2};
        int rsaTarget = 0;
        System.out.println("The index of target: " + rsaTarget + " is: " + searchInRSA(numsArray2, rsaTarget));
    }


    public static int firstOcc(int[] arr, int target) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + ((r - l) / 2);
            if (arr[mid] == target && (mid == 0 || arr[mid - 1] != target)) {
                return mid;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static int lastOcc(int[] arr, int target) {
        int n = arr.length - 1;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == target && (mid == n - 1 || arr[mid] != target)) {
                return mid;
            } else if (arr[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static int searchInsertPosition(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // RSA
    public static int minimumInRSA(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] > arr[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return arr[r];
    }

    public static int numberOfRotations(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;   // Number of rotations is same as the position of minimum element
    }

    public static int binarySearch(int[] arr, int key, int l, int r) {
        while (l <= r) {
            int mid = l + ((r - l) / 2);
            if (arr[mid] == key)
                return mid;
            else if (arr[mid] > key)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return -1;
    }

    public static int searchInRSA(int[] arr, int target) {
        int indexOfMinElement = numberOfRotations(arr); // Number of rotations is same as the position of minimum element

        int ans = binarySearch(arr, target, 0, indexOfMinElement - 1);
        if (ans == -1) {
            return binarySearch(arr, target, indexOfMinElement, arr.length - 1);
        } else {
            return ans;
        }
    }

}
