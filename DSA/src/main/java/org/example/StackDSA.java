package org.example;

import java.util.*;

public class StackDSA {

    // LeetCode question: 20 - Easy - Valid Parentheses

    /**
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * <p>
     * An input string is valid if:
     * <p>
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Every close bracket has a corresponding open bracket of the same type.
     * <p>
     * Example 1:
     * Input: s = "()"
     * Output: true
     * <p>
     * Example 2:
     * Input: s = "()[]{}"
     * Output: true
     * <p>
     * Example 3:
     * Input: s = "(]"
     * Output: false
     * <p>
     * Example 4:
     * Input: s = "([])"
     * Output: true
     * <p>
     * Example 5:
     * Input: s = "([)]"
     * Output: false
     *
     */
    static boolean validParentheses(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if ((s.charAt(i) == ')' && stack.peek() == '(') || (s.charAt(i) == '}' && stack.peek() == '{') ||
                            (s.charAt(i) == ']' && stack.peek() == '[')) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    // LeetCode Question 32 - Hard - Longest Valid Parentheses
    // Not a stack based question

    /**
     * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses substring.
     * <p>
     * Example 1:
     * Input: s = "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()".
     * <p>
     * Example 2:
     * Input: s = ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()".
     * <p>
     * Example 3:
     * Input: s = ""
     * Output: 0
     */
    // TC - O(N)
    // SC - O(1)
    static int longestValidParentheses(String s) {
        int ob = 0;
        int cb = 0;
        int maxAns = 0;
        int currentAns = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                ob++;
            } else {
                cb++;
            }
            if (ob == cb) {
                currentAns = cb + ob;
            }
            maxAns = Math.max(currentAns, maxAns);
            if (cb > ob) {
                cb = 0;
                ob = 0;
            }
        }
        ob = 0;
        cb = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                ob++;
            } else {
                cb++;
            }
            if (ob == cb) {
                currentAns = cb + ob;
            }
            maxAns = Math.max(currentAns, maxAns);
            if (ob > cb) {
                cb = 0;
                ob = 0;
            }
        }
        return maxAns;
    }

    static int[] NGOR(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            // Remove Smaller elements
            while (stack.size() > 0 && arr[i] >= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }

    static int[] NGOL(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (stack.size() > 0 && arr[i] >= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }

    static int[] NSOR(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (stack.size() > 0 && arr[i] <= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }

    static int[] NSOL(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (stack.size() > 0 && arr[i] <= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }

    // GFG - Stock Span Problem

    /**
     * The stock span problem is a financial problem where we have a series of daily price quotes for a stock and we need to calculate the span of stock price for all days.
     * You are given an array arr[] representing daily stock prices, the stock span for the i-th day is the number of consecutive days up to day i (including day i itself) for which the price of the stock is less than or equal to the price on day i. Return the span of stock prices for each day in the given sequence.
     * <p>
     * Examples:
     * Input: arr[] = [100, 80, 90, 120]
     * Output: [1, 1, 2, 4]
     * Explanation: Traversing the given input span 100 is greater than equal to 100 and there are no more days behind it so
     * the span is 1, 80 is greater than equal to 80 and smaller than 100 so the span is 1, 90 is greater than equal to 90 and 80
     * so the span is 2, 120 is greater than 90, 80 and 100 so the span is 4. So the output will be [1, 1, 2, 4].
     * <p>
     * Input: arr[] = [10, 4, 5, 90, 120, 80]
     * Output: [1, 1, 2, 4, 5, 1]
     * Explanation: Traversing the given input span 10 is greater than equal to 10 and there are no more days behind it so the span is 1,
     * 4 is greater than equal to 4 and smaller than 10 so the span is 1, 5 is greater than equal to 4 and 5 and smaller than 10 so the span is 2, and so on.
     * Hence the output will be [1, 1, 2, 4, 5, 1].
     */
    static ArrayList<Integer> stockSpan(int[] arr) {
        ArrayList<Integer> fAns = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            // NGOL
            while (stack.size() > 0 && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(i);
        }

        for (int i = 0; i < ans.length; i++) {
            fAns.add(i - ans[i]);
        }

        return fAns;
    }


    // LeetCode Question 84 - Hard - Largest Rectangle in Histogram

    /**
     * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
     * <p>
     * Example 1:
     * Input: heights = [2,1,5,6,2,3]
     * Output: 10
     * Explanation: The above is a histogram where width of each bar is 1.
     * The largest rectangle is shown in the red area, which has an area = 10 units.
     * <p>
     * <p>
     * Example 2:
     * Input: heights = [2,4]
     * Output: 4
     *
     */
    static int largestRectangleArea(int[] heights) {
        int[] nsol = NSOL_helper(heights);
        int[] nsor = NSOR_helper(heights);
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int width = nsor[i] - nsol[i] - 1;
            int height = heights[i];
            int currentArea = width * height;
            if (currentArea > maxArea) {
                maxArea = currentArea;
            }
        }
        return maxArea;
    }

    static int[] NSOR_helper(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (stack.size() > 0 && arr[i] <= arr[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = arr.length;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(i);
        }
        return ans;
    }

    static int[] NSOL_helper(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (stack.size() > 0 && arr[i] <= arr[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(i);
        }
        return ans;
    }

    static void runStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(45);
        stack.push(33);
        stack.push(12);
        stack.push(9);

        System.out.println(stack);

        System.out.println("Top element: " + stack.peek());

        System.out.println("Popping: " + stack.pop());

        System.out.println("Top Element: " + stack.peek());
    }

    // Assignment questions


    // Reverse a string using stack
    static String reverse(String str) {
        // code here
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            s.append(stack.peek());
            stack.pop();
        }
        return s.toString();
    }


    // Leetcode - Medium Q.856 - Score of Parentheses

    /**
     * Given a balanced parentheses string s, return the score of the string.
     * <p>
     * The score of a balanced parentheses string is based on the following rule:
     * <p>
     * "()" has score 1.
     * AB has score A + B, where A and B are balanced parentheses strings.
     * (A) has score 2 * A, where A is a balanced parentheses string.
     * <p>
     * <p>
     * Example 1:
     * Input: s = "()"
     * Output: 1
     * <p>
     * Example 2:
     * Input: s = "(())"
     * Output: 2
     * <p>
     * Example 3:
     * Input: s = "()()"
     * Output: 2
     *
     */

    static int scoreOfParenthesis(String s) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(0);
            } else {
                if (stack.peek() == 0) {
                    stack.pop();
                    stack.push(1);
                } else {
                    int sum = 0;
                    while (stack.peek() != 0) {
                        sum += stack.pop();
                    }
                    stack.pop();
                    stack.push(Math.max(2 * sum, 1)); // 2*sum for nested parenthesis and 1 in case ()
                }
            }
        }
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    // Leetcode - Medium - 150. Evaluate Reverse Polish Notation
    static int reversePolishNotation(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].equals("+") && !tokens[i].equals("-") && !tokens[i].equals("/") && !tokens[i].equals("*")) {
                stack.push(Integer.parseInt(tokens[i]));
            } else {
                int a = stack.pop();
                int b = stack.pop();
                switch (tokens[i]) {
                    case "+" -> stack.push(b + a);
                    case "-" -> stack.push(b - a);
                    case "*" -> stack.push(b * a);
                    case "/" -> stack.push(b / a);
                }
            }
        }
        return stack.pop();
    }

    // Leetcode - 496. Next Greater Element 1

    /**
     * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
     * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
     * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
     * If there is no next greater element, then the answer for this query is -1.
     * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
     * Output: [-1,3,-1]
     * Explanation: The next greater element for each value of nums1 is as follows:
     * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
     * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
     * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
     * <p>
     * Example 2:
     * Input: nums1 = [2,4], nums2 = [1,2,3,4]
     * Output: [3,-1]
     * Explanation: The next greater element for each value of nums1 is as follows:
     * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
     * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
     */

    static int[] nextGreater1(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                map.put(nums2[i], -1);
            } else {
                map.put(nums2[i], stack.peek());
            }
            stack.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = map.get(nums1[i]);
        }
        return nums1;
    }

    // Leetcode - Medium - 503. Next Greater Element II

    /**
     * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
     * <p>
     * The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly
     * to find its next greater number. If it doesn't exist, return -1 for this number.
     * <p>
     * Example 1:
     * Input: nums = [1,2,1]
     * Output: [2,-1,2]
     * Explanation: The first 1's next greater number is 2;
     * The number 2 can't find next greater number.
     * The second 1's next greater number needs to search circularly, which is also 2.
     * <p>
     * Example 2:
     * Input: nums = [1,2,3,4,3]
     * Output: [2,3,4,-1,4]
     */
    static int[] nextGreater2(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] ans = new int[n];
        for (int i = (2 * n) - 1; i >= 0; i--) {  // Using 2*n because we need to run circular loop
            // Whenever you need the actual element, use: nums[i % n]
            while (!stack.isEmpty() && arr[i % n] >= arr[stack.peek() % n]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i%n] = -1;
            } else {
                ans[i%n] = arr[stack.peek() % n];
            }
            stack.push(i);
        }
        return ans;
    }

    static void main() {
        /*
        runStack();
         */

        /*
        System.out.println("Is string valid: " + validParentheses("([])"));
         */

        /*
        System.out.println("Length of longest valid parentheses: " + longestValidParentheses(")()())"));
         */

        int[] arr = {2, 5, 9, 3, 1, 12, 6, 8, 7};
        /*
        System.out.println("Next Great on Right (NGOR): " + Arrays.toString(NGOR(arr)));
         */

        /*
        System.out.println("Next Great on Left (NGOL): " + Arrays.toString(NGOL(arr)));
         */

        /*
        System.out.println("Next Smaller on Right (NSOR): " + Arrays.toString(NSOR(arr)));
         */

        /*
        System.out.println("Next Smaller on Left (NSOL): " + Arrays.toString(NSOL(arr)));
         */

        /*
        int[] stock = {10, 4, 5, 90, 120, 80};
        System.out.println("Stock span of stock: " + Arrays.toString(stock) + " is: " + stockSpan(stock));
         */

        /*
        System.out.println("Largest Rectangle: " + largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
         */

        /*
        String s = "GeeksForGeeks";
        System.out.println("Reverse of string: " + s + " is: " + reverse(s));
         */

        /*
        String para = "(()(()))";  // 6 -> () = 1 => (()) = 2 => 1+2 = 3 => (3) outer parenthesis => 3*2 = 6
        System.out.println("Score of parenthesis string: " + para + " is: " + scoreOfParenthesis(para));
         */

        /*
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};  // 22
        System.out.println("Reverse polish notation of: " + Arrays.toString(tokens) + " is: " + reversePolishNotation(tokens));
         */

        /*
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println("Next greater of : " + Arrays.toString(nums1) + " in: " + Arrays.toString(nums1) + " is: " + Arrays.toString(nextGreater1(nums1, nums2)));
         */

        int[] nums3 = {1, 2, 3, 4, 3};
        System.out.println("Next greater 2 of: " + Arrays.toString(nums3) + " is: " + Arrays.toString(nextGreater2(nums3)));
    }

}
