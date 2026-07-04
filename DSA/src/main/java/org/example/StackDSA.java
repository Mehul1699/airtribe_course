package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

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

        System.out.println("Largest Rectangle: " + largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

}
