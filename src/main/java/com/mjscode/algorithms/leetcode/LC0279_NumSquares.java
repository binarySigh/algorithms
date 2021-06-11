package com.mjscode.algorithms.leetcode;

/**
 * //给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * //
 * // 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * //
 * // 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * //
 * // 示例 1：
 * //
 * //输入：n = 12
 * //输出：3
 * //解释：12 = 4 + 4 + 4
 * //
 * // 示例 2：
 * //
 * //输入：n = 13
 * //输出：2
 * //解释：13 = 4 + 9
 * //
 * // 提示：
 * //
 * // 1 <= n <= 104
 * //
 * // Related Topics 广度优先搜索 数学 动态规划
 * @author binarySigh
 * @date 2021/6/11 21:23
 */
public class LC0279_NumSquares {

    public static void main(String[] args){
        int n = 1000000;
        System.out.println("斜率优化：" + numSquares(n));
        System.out.println("动态规划：" + numSquares1(n));
        //System.out.println("暴力方法：" + compare(n));
    }

    /**
     * 解答成功:
     * 		执行耗时:139 ms,击败了15.96% 的Java用户
     * 		内存消耗:37.4 MB,击败了78.83% 的Java用户
     * 1. 斜率优化
     * 2. 进一步空间优化：将 两行数组优化为一行数组。因为依赖关系中只依赖本行的值和上一行前 curPower 个值，
     *      而上一行前 curPower 个值因为第一个 if分支会全部保留到本行。因此只需要使用一行数组即可
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        int maxSqrt = (int)Math.sqrt(n);
        int[] dp = new int[n + 1];
        //第一行
        for(int i = 1; i <= n; i++){
            dp[i] = i;
        }
        //后续行
        for(int i = 2; i <= maxSqrt; i++){
            int curPower = i * i;
            for(int j = 1; j <= n; j++){
                if(j < curPower){
                    continue;
                } else if(j % curPower == 0){
                    dp[j] = j / curPower;
                } else {
                    int prePower = (j / curPower) * curPower;
                    dp[j] = Math.min(dp[j], dp[prePower] + dp[j - prePower]);
                }
            }
        }
        return dp[n];
    }

    /**
     * 初步空间优化：将 maxSqrt * n 的二维矩阵优化成 2 * n 的两行数组
     * @param n
     * @return
     */
    public static int numSquares1(int n){
        int maxSqrt = (int)Math.sqrt(n);
        int[][] dp = new int[2][n + 1];
        //第一行
        for(int i = 1; i <= n; i++){
            dp[0][i] = i;
        }
        int row = 1;
        //后续行
        for(int i = 2; i <= maxSqrt; i++){
            for(int j = 1; j <= n; j++){
                int curPower = i * i;
                dp[row][j] = dp[row ^ 1][j];
                int t = j;
                int count = 0;
                while(t - curPower >= 0){
                    count++;
                    t -= curPower;
                    dp[row][j] = Math.min(dp[row][j], dp[row ^ 1][t] + count);
                }
            }
            row ^= 1;
        }
        return dp[row ^ 1][n];
    }

    public static int compare(int n){
        return process(n, 1, 0);
    }

    public static int process(int n, int cur, int counts){
        int curPower = cur * cur;
        if(n == 0){
            return counts;
        }
        if(n - curPower <= 0){
            return n - curPower == 0 ? counts + 1 : Integer.MAX_VALUE;
        }
        int ways = process(n, cur + 1, counts);
        while(n - curPower >= 0){
            n -= curPower;
            ways = Math.min(ways, process(n, cur + 1, ++counts));
        }
        return ways;
    }
}
