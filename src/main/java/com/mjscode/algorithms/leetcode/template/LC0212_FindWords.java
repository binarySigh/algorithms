package com.mjscode.algorithms.leetcode.template;

import java.util.ArrayList;
import java.util.List;

/**
 * //给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。
 * //
 * // 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
 * //用。
 * //
 * // 示例 1：
 * //
 * //输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
 * //,"v"]], words = ["oath","pea","eat","rain"]
 * //输出：["eat","oath"]
 * //
 * // 示例 2：
 * //
 * //输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * //输出：[]
 * //
 * // 提示：
 * //
 * // m == board.length
 * // n == board[i].length
 * // 1 <= m, n <= 12
 * // board[i][j] 是一个小写英文字母
 * // 1 <= words.length <= 3 * 104
 * // 1 <= words[i].length <= 10
 * // words[i] 由小写英文字母组成
 * // words 中的所有字符串互不相同
 * //
 * // Related Topics 字典树 数组 字符串 回溯 矩阵
 * @author binarySigh
 * @date 2021/9/16 23:24
 */
public class LC0212_FindWords {

    public static void main(String[] args) {
        // --> [eat, oath]
        /*char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};*/

        // --> []
        /*char[][] board = {
            {'a','b'},
            {'c','d'}
        };
        String[] words = {"abcd"};*/

        // --> []
        /*char[][] board = {
            {'a'}
        };
        String[] words = {"abcd"};*/

        // --> [a]
        /*char[][] board = {
            {'a'}
        };
        String[] words = {"a"};*/

        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath","oatae","oatak","oaaak","fhta","fhkaa","flvrkhf"};

        List<String> ans = findWords(board, words);
        System.out.println(ans);
    }

    /**
     * 执行结果： 通过
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 98.5% 的用户
     * 内存消耗： 37.3 MB , 在所有 Java 提交中击败了 62.0% 的用户
     * @param board
     * @param words
     * @return
     */
    public static List<String> findWords(char[][] board, String[] words){
        int m = board.length, n = board[0].length;
        Node root = new Node();
        // 初始化前缀树
        Node cur = root;
        for(int i = 0; i < words.length; i++){
            cur = root;
            for(int j = 0; j < words[i].length(); j++){
                int idx = words[i].charAt(j) - 'a';
                if(cur.next[idx] == null){
                    cur.next[idx] = new Node();
                    cur.next[idx].word = words[i].substring(0, j + 1);
                } else {
                    cur.next[idx].path++;
                }
                cur = cur.next[idx];
            }
            cur.end++;
        }
        List<String> ans = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];
        int idx = 0;
        int ret = 0;
        //DFS + 前缀树 完成回溯搜索
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                idx = board[i][j] - 'a';
                if(root.next[idx] != null){
                    ret = dfs(board, i, j, visited, root.next[idx], ans);
                    root.next[idx].path -= ret;
                    if(root.next[idx].path == 0){
                        root.next[idx] = null;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 注：当前前缀树节点path--，以达到ans去重的效果，同时也可以剪枝，降低board全局dfs的复杂度
     * 方法返回值 是上层节点在递归回来时应该减掉的其path的值
     * @param board
     * @param x
     * @param y
     * @param visited
     * @param root
     * @param ans
     * @return
     */
    public static int dfs(char[][] board, int x, int y, boolean[][] visited, Node root, List<String> ans){
        visited[x][y] = true;
        if(root == null){
            return 0;
        }
        int ret = 0;
        if(root.end == 1){
            ans.add(root.word);
            root.end--;
            ret++;
        }
        int idx = 0;
        int nextCnts = 0;
        //上
        if(x - 1 >= 0 && !visited[x - 1][y]){
            idx = board[x - 1][y] - 'a';
            if(root.next[idx] != null){
                nextCnts = dfs(board, x - 1, y, visited, root.next[idx], ans);
                //检查设置下一层的path,如果设置完发现下一级path为0，则说明下面分支的单词已经全部收集完，则断连该支路
                root.next[idx].path -= nextCnts;
                if(root.next[idx].path == 0){
                    root.next[idx] = null;
                }
                ret += nextCnts;
            }
        }
        //下
        if(x + 1 < board.length && !visited[x + 1][y]){
            idx = board[x + 1][y] - 'a';
            if(root.next[idx] != null){
                nextCnts = dfs(board, x + 1, y, visited, root.next[idx], ans);
                root.next[idx].path -= nextCnts;
                if(root.next[idx].path == 0){
                    root.next[idx] = null;
                }
                ret += nextCnts;
            }
        }
        //左
        if(y - 1 >= 0 && !visited[x][y - 1]){
            idx = board[x][y - 1] - 'a';
            if(root.next[idx] != null){
                nextCnts = dfs(board, x, y - 1, visited, root.next[idx], ans);
                root.next[idx].path -= nextCnts;
                if(root.next[idx].path == 0){
                    root.next[idx] = null;
                }
                ret += nextCnts;
            }
        }
        //右
        if(y + 1 < board[0].length && !visited[x][y + 1]){
            idx = board[x][y + 1] - 'a';
            if(root.next[idx] != null){
                nextCnts = dfs(board, x, y + 1, visited, root.next[idx], ans);
                root.next[idx].path -= nextCnts;
                if(root.next[idx].path == 0){
                    root.next[idx] = null;
                }
                ret += nextCnts;
            }
        }
        visited[x][y] = false;
        return ret;
    }

    public static class Node {
        int path;
        int end;
        String word;
        Node[] next;
        public Node(){
            this.path = 1;
            this.end = 0;
            this.word = "";
            this.next = new Node[26];
        }
    }

}
