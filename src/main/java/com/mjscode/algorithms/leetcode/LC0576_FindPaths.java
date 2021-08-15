package com.mjscode.algorithms.leetcode;

/**
 * //给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以
 * //穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 * //
 * // 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对
 * //109 + 7 取余 后的结果。
 * //
 * // 示例 1：
 * //
 * //输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * //输出：6
 * //
 * // 示例 2：
 * //
 * //输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * //输出：12
 * //
 * // 提示：
 * //
 * // 1 <= m, n <= 50
 * // 0 <= maxMove <= 50
 * // 0 <= startRow < m
 * // 0 <= startColumn < n
 * //
 * // Related Topics 动态规划
 * @author binarySigh
 * @date 2021/8/15 9:39
 */
public class LC0576_FindPaths {
    public static void main(String[] args){
        // --> 6
        /*int m = 2, n = 2;
        int maxMove = 2;
        int startRow = 0, startCol = 0;*/

        // --> 12
        int m1 = 1, n1 = 3;
        int maxMove1 = 3;
        int startRow1 = 0, startCol1 = 1;
        System.out.println("Compare : " + compare(m1, n1, maxMove1, startRow1, startCol1));
        System.out.println("Answer  : " + findPaths(m1, n1, maxMove1, startRow1, startCol1));

        System.out.println("----------BEGIN----------");
        for(int i = 0; i < 10_000; i++) {
            int m = (int)(Math.random() * 10) + 1;
            int n = (int)(Math.random() * 10) + 1;
            int maxMove = (int)(Math.random() * 21);
            int startRow = (int)(Math.random() * m);
            int startColumn = (int)(Math.random() * n);
            int ans = findPaths(m, n, maxMove, startRow, startColumn);
            int com = compare(m, n, maxMove, startRow, startColumn);
            if(com != ans){
                System.out.println("-------Oops!---------");
                System.out.println("m = " + m + ", n = " + n);
                System.out.println("maxMove = " + maxMove);
                System.out.println("startRow = " + startRow + ", startColumn = " + startColumn);
                System.out.println("Compare : " + com);
                System.out.println("Answer  : " + ans);
                break;
            }
        }
        System.out.println("----------ENDS----------");
    }

    /**
     * 解答成功:
     * 		执行耗时:11 ms,击败了62.12% 的Java用户
     * 		内存消耗:37.6 MB,击败了62.58% 的Java用户
     * @param m
     * @param n
     * @param maxMove
     * @param startRow
     * @param startColumn
     * @return
     */
    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if(maxMove == 0){
            return 0;
        }
        int mod = (int)Math.pow(10, 9) + 7;
        int ans = 0;
        int[][][] dp = new int[m][n][maxMove];
        dp[startRow][startColumn][0] = 1;
        for(int l = 0; l < maxMove - 1; l++){
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    // 上
                    if(i == 0){
                        ans = (ans + dp[i][j][l]) % mod;
                    } else {
                        dp[i - 1][j][l + 1] = (dp[i - 1][j][l + 1] + dp[i][j][l]) % mod;
                    }
                    // 下
                    if(i >= m - 1){
                        ans = (ans + dp[i][j][l]) % mod;
                    } else {
                        dp[i + 1][j][l + 1] = (dp[i + 1][j][l + 1] + dp[i][j][l]) % mod;
                    }
                    // 左
                    if(j == 0){
                        ans = (ans + dp[i][j][l]) % mod;
                    } else {
                        dp[i][j - 1][l + 1] = (dp[i][j - 1][l + 1] + dp[i][j][l]) % mod;
                    }
                    // 右
                    if(j >= n - 1){
                        ans = (ans + dp[i][j][l]) % mod;
                    } else {
                        dp[i][j + 1][l + 1] = (dp[i][j + 1][l + 1] + dp[i][j][l]) % mod;
                    }
                }
            }
        }
        for(int i = 0; i < m; i++){
            ans = (ans + dp[i][0][maxMove - 1]) % mod;
            ans = (ans + dp[i][n - 1][maxMove - 1]) % mod;
        }
        for(int j = 0; j < n; j++){
            ans = (ans + dp[0][j][maxMove - 1]) % mod;
            ans = (ans + dp[m - 1][j][maxMove - 1]) % mod;
        }
        return ans;
    }

    public static int compare(int m, int n, int maxMove, int startRow, int startColumn){
        if(startRow < 0 || startRow >= m || startColumn < 0 || startColumn >= n){
            return 1;
        }
        if(maxMove == 0){
            return 0;
        }
        int mod = (int)Math.pow(10, 9) + 7;
        int ans = (compare(m, n, maxMove - 1, startRow - 1, startColumn) +
                compare(m, n, maxMove - 1, startRow + 1, startColumn)) % mod +
                (compare(m, n, maxMove - 1, startRow, startColumn - 1) +
                compare(m, n, maxMove - 1, startRow, startColumn + 1)) % mod;
        return ans % mod;
    }
}
