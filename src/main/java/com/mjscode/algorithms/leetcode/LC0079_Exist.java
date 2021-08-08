package com.mjscode.algorithms.leetcode;

/**
 * //给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * //
 * // 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * //
 * // 示例 1：
 * //
 * //输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
 * //CCED"
 * //输出：true
 * //
 * // 示例 2：
 * //
 * //输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SE
 * //E"
 * //输出：true
 * //
 * // 示例 3：
 * //
 * //输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
 * //CB"
 * //输出：false
 * //
 * // 提示：
 * //
 * // m == board.length
 * // n = board[i].length
 * // 1 <= m, n <= 6
 * // 1 <= word.length <= 15
 * // board 和 word 仅由大小写英文字母组成
 * //
 * // 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 * // Related Topics 数组 回溯 矩阵
 * @author binarySigh
 * @date 2021/8/8 22:25
 */
public class LC0079_Exist {
    public static void main(String[] args){
        // --> true
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String s = "ABCCED";

        // --> true
        /*char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String s = "SEE";*/

        // --> false
        /*char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String s = "ABCB";*/

        /*char[][] board = {
                {'a'}
        };
        String s = "a";*/

        /*char[][] board = {
                {'b','b'},
                {'a','b'},
                {'b','a'}
        };
        String s = "a";*/
        System.out.println(exist(board, s));
    }

    /**
     * 解答成功:
     * 		执行耗时:103 ms,击败了50.55% 的Java用户
     * 		内存消耗:36.4 MB,击败了63.62% 的Java用户
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                int mIdx = i * board[0].length + j;
                if(find(board, word, mIdx, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean find(char[][] board, String word, int mIdx, int sIdx){
        int colLen = board[0].length;
        int rowLen = board.length;
        int row = mIdx / colLen;
        int col = mIdx % colLen;
        if(sIdx == word.length() - 1 && board[row][col] == word.charAt(sIdx)){
            return true;
        }
        if(board[row][col] != word.charAt(sIdx)){
            return false;
        }
        char tmp = board[row][col];
        board[row][col] = '*';
        // 上
        if(row > 0) {
            if (find(board, word, mIdx - colLen, sIdx + 1)) {
                board[row][col] = tmp;
                return true;
            }
        }
        // 下
        if(row < rowLen - 1) {
            if (find(board, word, mIdx + colLen, sIdx + 1)) {
                board[row][col] = tmp;
                return true;
            }
        }
        // 左
        if(col > 0) {
            if (find(board, word, mIdx - 1, sIdx + 1)) {
                board[row][col] = tmp;
                return true;
            }
        }
        // 右
        if(col < colLen - 1) {
            if (find(board, word, mIdx + 1, sIdx + 1)) {
                board[row][col] = tmp;
                return true;
            }
        }
        board[row][col] = tmp;
        return false;
    }
}
