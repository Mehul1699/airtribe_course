package org.example;

// Leet code Hard question 42

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 **/
public class TrappingRainWater {

    public static int trapWater(int[] height) {
        int water = 0;
        int n = height.length;
        int maxIndex = 0;
        int maxHeight = height[0];
        for (int i = 1; i < n; i++) {
            if (height[i] > maxHeight) {
                maxHeight = height[i];
                maxIndex = i;
            }
        }
        int currentMax = height[0];
        for (int i = 0; i <= maxIndex; i++) {
            currentMax = Math.max(currentMax, height[i]);
            water = water + (currentMax - height[i]);
        }

        currentMax = height[n - 1];
        for (int j = n - 1; j >= maxIndex; j--) {
            currentMax = Math.max(currentMax, height[j]);
            water = water + (currentMax - height[j]);
        }
        return water;
    }

    static void main() {
        int[] waterTower1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] waterTower2 = {4, 2, 0, 3, 2, 5};
        System.out.println("Total trapped water for tower 1: " + trapWater(waterTower1));
        System.out.println("Total trapped water for tower 2: " + trapWater(waterTower2));
    }

}
