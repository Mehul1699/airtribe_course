package org.example;

import java.util.*;

/**
 * RM*WA*
 * R - Remove
 * M* - Mark visited
 * W - Do work. Print or check condition accordingly
 * A* - Add neighbours
 */
public class Graph2BFS {


    // GFG - Easy - BFS of graph

    /**
     * Given a connected undirected graph containing V vertices, represented by a 2-d adjacency list adj[][], where each adj[i] represents the list of vertices connected to vertex i. Perform a Breadth First Search (BFS) traversal starting from vertex 0, visiting vertices from left to right according to the given adjacency list, and return a list containing the BFS traversal of the graph.
     * <p>
     * Note: Do traverse in the same order as they are in the given adjacency list.
     * <p>
     * Examples:
     * <p>
     * Input: adj[][] = [[2, 3, 1], [0], [0, 4], [0], [2]]
     * <p>
     * Output: [0, 2, 3, 1, 4]
     * Explanation: Starting from 0, the BFS traversal will follow these steps:
     * Visit 0 → Output: 0
     * Visit 2 (first neighbor of 0) → Output: 0, 2
     * Visit 3 (next neighbor of 0) → Output: 0, 2, 3
     * Visit 1 (next neighbor of 0) → Output: 0, 2, 3, 1
     * Visit 4 (neighbor of 2) → Final Output: 0, 2, 3, 1, 4
     * Input: adj[][] = [[1, 2], [0, 2], [0, 1, 3, 4], [2], [2]]
     * <p>
     * Output: [0, 1, 2, 3, 4]
     * Explanation: Starting from 0, the BFS traversal proceeds as follows:
     * Visit 0 → Output: 0
     * Visit 1 (the first neighbor of 0) → Output: 0, 1
     * Visit 2 (the next neighbor of 0) → Output: 0, 1, 2
     * Visit 3 (the first neighbor of 2 that hasn't been visited yet) → Output: 0, 1, 2, 3
     * Visit 4 (the next neighbor of 2) → Final Output: 0, 1, 2, 3, 4
     */
    public static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> graph) {
        boolean[] vis = new boolean[graph.size()];
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        q.add(0);
        while (!q.isEmpty()) {
            // RM*WA*
            int fnt = q.remove();
            if (vis[fnt])
                continue;
            vis[fnt] = true;
            ans.add(fnt);
            for (int nbr : graph.get(fnt)) {
                if (!vis[nbr])
                    q.add(nbr);
            }
        }
        return ans;
    }

    // LeetCode - Medium - 994. Rotting Oranges

    /**
     * You are given an m x n grid where each cell can have one of three values:
     * <p>
     * 0 representing an empty cell,
     * 1 representing a fresh orange, or
     * 2 representing a rotten orange.
     * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
     * <p>
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
     * Output: 4
     * <p>
     * Example 2:
     * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
     * Output: -1
     * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
     * <p>
     * Example 3:
     * Input: grid = [[0,2]]
     * Output: 0
     * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
     */
    private static class oranges {
        int i;
        int j;
        int time;

        oranges(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }
    }

    private static int orangesRotting(int[][] grid) {
        int fc = 0;
        int rc = 0;
        Queue<oranges> q = new LinkedList<>();
        int ftime = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    rc++;
                    q.add(new oranges(i, j, 0));
                } else if (grid[i][j] == 1) {
                    fc++;
                }
            }
        }
        while (!q.isEmpty()) {
            // RM*WA*
            oranges fnt = q.remove();
            if (grid[fnt.i][fnt.j] == 5)
                continue;
            if (grid[fnt.i][fnt.j] == 1)
                fc--;
            grid[fnt.i][fnt.j] = 5;

            ftime = Math.max(ftime, fnt.time);

            int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            int i = fnt.i;
            int j = fnt.j;
            for (int[] d : dir) {
                int ni = i + d[0];
                int nj = j + d[1];

                if (ni >= 0 && nj >= 0 && ni < grid.length && nj < grid[0].length && grid[ni][nj] == 1)
                    q.add(new oranges(ni, nj, fnt.time + 1));
            }
        }
        if (fc != 0)
            return -1;
        return ftime;
    }

    // LeetCode - Medium - 542. 01 Matrix

    /**
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     * <p>
     * The distance between two cells sharing a common edge is 1.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
     * Output: [[0,0,0],[0,1,0],[0,0,0]]
     * Example 2:
     * <p>
     * <p>
     * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
     * Output: [[0,0,0],[0,1,0],[1,2,1]]
     */
    private static class cell {
        int i;
        int j;
        int dist;

        cell(int a, int b, int c) {
            this.i = a;
            this.j = b;
            this.dist = c;
        }
    }

    private static int[][] updateMatrix(int[][] arr) {
        int[][] ans = new int[arr.length][arr[0].length];
        Queue<cell> q = new LinkedList<>();
        boolean[][] vis = new boolean[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) {
                    q.add(new cell(i, j, 0));
                }
            }
        }
        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            // RM*WA*
            cell fnt = q.remove();
            if (vis[fnt.i][fnt.j])
                continue;
            vis[fnt.i][fnt.j] = true;
            ans[fnt.i][fnt.j] = fnt.dist;
            for (int[] d : dir) {
                int ni = fnt.i + d[0];
                int nj = fnt.j + d[1];
                if (ni >= 0 && nj >= 0 && ni < arr.length && nj < arr[0].length && arr[ni][nj] == 1) {
                    q.add(new cell(ni, nj, fnt.dist + 1));
                }
            }
        }
        return ans;
    }

    // LeetCode - Hard - 127. Word Ladder

    /**
     * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
     * <p>
     * Every adjacent pair of words differs by a single letter.
     * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
     * sk == endWord
     * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * Output: 5
     * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
     * Example 2:
     * <p>
     * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
     * Output: 0
     * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
     */
    private static class DictionaryData {
        String str;
        int val;

        DictionaryData(String str, int val) {
            this.str = str;
            this.val = val;
        }
    }

    private static int ladderLength(String src, String dest, List<String> wordList) {
        HashSet<String> hs = new HashSet<>(wordList);  // For quick get iteration
        if (!hs.contains(dest)) {
            return 0;
        }
        Queue<DictionaryData> q = new LinkedList<>();
        q.add(new DictionaryData(src, 1));

        HashSet<String> vis = new HashSet<>();
        vis.add(src);

        while (!q.isEmpty()) {
            DictionaryData fnt = q.remove();
            if (fnt.str.equals(dest))
                return fnt.val;

            for (int idx = 0; idx < fnt.str.length(); idx++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    StringBuilder newSb = new StringBuilder(fnt.str);
                    newSb.setCharAt(idx, ch);
                    String newString = newSb.toString();

                    if (hs.contains(newString) && !vis.contains(newString)) {
                        q.add(new DictionaryData(newString, fnt.val + 1));
                        vis.add(newString);
                    }
                }
            }
        }
        return 0;
    }

    static void main() {
        /*
        ArrayList<ArrayList<Integer>> input1 = new ArrayList<>();

        input1.add(new ArrayList<>(List.of(2, 3, 1)));
        input1.add(new ArrayList<>(List.of(0)));
        input1.add(new ArrayList<>(List.of(0, 4)));
        input1.add(new ArrayList<>(List.of(0)));
        input1.add(new ArrayList<>(List.of(2)));

        ArrayList<ArrayList<Integer>> input2 = new ArrayList<>();
        input2.add(new ArrayList<>(List.of(1, 2)));
        input2.add(new ArrayList<>(List.of(0, 2)));
        input2.add(new ArrayList<>(List.of(0, 1, 3, 4)));
        input2.add(new ArrayList<>(List.of(2)));
        input2.add(new ArrayList<>(List.of(2)));


        System.out.println("BFS: " + bfs(input2));
         */

        /*
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int[][] grid2 = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};

        System.out.println("Minimum time to Rotten oranges: " + orangesRotting(grid2));
         */

        /*
        int[][] grid3 = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        System.out.println("Updated matrix as per distance from 0: " + Arrays.deepToString(updateMatrix(grid3)));
         */

        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        List<String> wordList2 = List.of("hot", "dot", "dog", "lot", "log");
        String src = "hit";
        String dest = "cog";

        System.out.println("Length of ladder: " + ladderLength(src, dest, wordList2));
    }
}
