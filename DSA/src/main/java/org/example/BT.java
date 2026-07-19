package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BT {

    private static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }

    }

    static int size(Node root) {
        if (root == null)
            return 0;
        int leftSize = size(root.left);
        int rightSize = size(root.right);
        return leftSize + rightSize + 1;
    }

    static int sum(Node root) {
        if (root == null)
            return 0;
        int left_sum = sum(root.left);
        int right_sum = sum(root.right);
        return left_sum + right_sum + root.val;
    }

    static int maximumOfTree(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;
        int left_max = maximumOfTree(root.left);
        int right_max = maximumOfTree(root.right);
        return Math.max(Math.max(left_max, right_max), root.val);
    }

    static int minimumOfTree(Node root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int left_min = minimumOfTree(root.left);
        int right_min = minimumOfTree(root.right);
        return Math.min(Math.min(left_min, right_min), root.val);
    }

    static boolean isPresent(Node root, int key) {
        if (root == null)
            return false;
        boolean left_present = isPresent(root.left, key);
        boolean right_present = isPresent(root.right, key);
        return left_present || right_present || root.val == key;
    }

    // Leetcode - Medium - 129. Sum Root to Leaf Numbers

    /**
     * You are given the root of a binary tree containing digits from 0 to 9 only.
     * <p>
     * Each root-to-leaf path in the tree represents a number.
     * <p>
     * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
     * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
     * <p>
     * A leaf node is a node with no children.
     * <p>
     * Example 1:
     * Input: root = [1,2,3]
     * Output: 25
     * Explanation:
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     * Therefore, sum = 12 + 13 = 25.
     * <p>
     * <p>
     * Example 2:
     * Input: root = [4,9,0,5,1]
     * Output: 1026
     * Explanation:
     * The root-to-leaf path 4->9->5 represents the number 495.
     * The root-to-leaf path 4->9->1 represents the number 491.
     * The root-to-leaf path 4->0 represents the number 40.
     * Therefore, sum = 495 + 491 + 40 = 1026.
     */
    static int sum = 0;

    static void helper(Node root, int csum) {
        if (root == null)
            return;
        csum = (csum * 10) + root.val;
        if (root.left == null && root.right == null) {
            sum += csum;
        } else {
            helper(root.left, csum);
            helper(root.right, csum);
        }
    }

    static int sumOfNumbers(Node root) {
        helper(root, 0);
        return sum;
    }

    // InterviewBit - Easy - Path to Given Node

    /**
     * Given a Binary Tree A containing N nodes.
     * <p>
     * You need to find the path from Root to a given node B.
     * <p>
     * NOTE:
     * No two nodes in the tree have same data values.
     * You can assume that B is present in the tree A and a path always exists.
     * <p>
     * Input 1:
     * <p>
     * A =
     * <p>
     * 1
     * /   \
     * 2     3
     * / \   / \
     * 4   5 6   7
     * <p>
     * <p>
     * B = 5
     * <p>
     * Input 2:
     * <p>
     * A =
     * 1
     * /   \
     * 2     3
     * / \ .   \
     * 4   5 .   6
     * <p>
     * <p>
     * B = 1
     * <p>
     * <p>
     * <p>
     * <p>
     * Example Output
     * Output 1:
     * <p>
     * [1, 2, 5]
     * Output 2:
     * <p>
     * [1]
     */
    static boolean isPresent_Helper(Node root, int key, ArrayList<Integer> path) {
        if (root == null)
            return false;
        boolean lAns = isPresent_Helper(root.left, key, path);
        boolean rAns = isPresent_Helper(root.right, key, path);
        boolean fAns = lAns || rAns || root.val == key;
        if (fAns) {
            path.add(root.val);
            return true;
        } else {
            return false;
        }
    }

    static List<Integer> pathToNode(Node root, int key) {
        ArrayList<Integer> ans = new ArrayList<>();
        isPresent_Helper(root, key, ans);
        Collections.reverse(ans);    // Reverse because the answer needs to be root to the key node. Currently, it is key to root
        return ans;
    }

    // Function to find the height of the binary tree
    static int findHeight(Node root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Helper function to perform inorder traversal and
    // populate the 2D matrix
    static void inorder(Node root, int row, int col,
                        int height,
                        List<List<String>> ans) {
        if (root == null) {
            return;
        }

        // Calculate offset for child positions
        int offset = (int) Math.pow(2, height - row - 1);

        // Traverse the left subtree
        if (root.left != null) {
            inorder(root.left, row + 1, col - offset,
                    height, ans);
        }

        // Place the current node's value in the matrix
        ans.get(row).set(col, String.valueOf(root.val));

        // Traverse the right subtree
        if (root.right != null) {
            inorder(root.right, row + 1, col + offset,
                    height, ans);
        }
    }

    // Function to convert the binary tree to a 2D matrix
    static List<List<String>> treeToMatrix(Node root) {

        // Find the height of the tree
        int height = findHeight(root);

        // Rows are height + 1; columns are 2^(height+1) - 1
        int rows = height + 1;
        int cols = (int) Math.pow(2, height + 1) - 1;

        // Initialize 2D matrix with empty strings
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>(Collections
                    .nCopies(cols, ""));
            ans.add(row);
        }

        // Populate the matrix using inorder traversal
        inorder(root, 0, (cols - 1) / 2, height, ans);

        return ans;
    }

    // Function to print a 2D matrix
    static void print2DArray(List<List<String>> arr) {
        for (List<String> row : arr) {
            for (String cell : row) {
                if (cell.isEmpty()) {
                    System.out.print(" ");
                } else {
                    System.out.print(cell);
                }
            }
            System.out.println();
        }
    }

    static void main() {
        Node root = new Node(1);
        Node rootL1 = new Node(3);
        Node rootR1 = new Node(4);
        Node l2 = new Node(2);
        Node r2 = new Node(8);
        Node r3 = new Node(7);
        root.left = rootL1;
        root.right = rootR1;
        rootL1.left = l2;
        rootL1.right = r2;
        rootR1.right = r3;
        List<List<String>> result = treeToMatrix(root);
        print2DArray(result);
        /*
        System.out.println("Size of the Binary Tree: " + size(root));
         */
        /*
        System.out.println("Sum of the Binary Tree: " + sum(root));
         */
        /*
        System.out.println(
                "Maximum of Tree: " + maximumOfTree(root)
        );
         */

        /*
        System.out.println(
                "Minimum of Tree: " + minimumOfTree(root)
        );
         */

        /*
        int key = 4;
        System.out.println(
                "Checking if the key: " + key + " is present in the tree: " + isPresent(root, key)
        );
         */
        /*
        System.out.println(
                "Sum root to leaf nodes: " + sumOfNumbers(root)
        );
         */
        int key = 8;
        System.out.println(
                "Root to key node: " + key + " path: " + pathToNode(root, key)
        );
    }

}
