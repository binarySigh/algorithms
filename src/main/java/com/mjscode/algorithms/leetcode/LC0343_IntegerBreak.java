package com.mjscode.algorithms.leetcode;

/**
 * //给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * //
 * // 示例 1:
 * //
 * // 输入: 2
 * //输出: 1
 * //解释: 2 = 1 + 1, 1 × 1 = 1。
 * //
 * // 示例 2:
 * //
 * // 输入: 10
 * //输出: 36
 * //解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * //
 * // 说明: 你可以假设 n 不小于 2 且不大于 58。
 * // Related Topics 数学 动态规划
 * @author binarySigh
 * @date 2021/9/8 21:00
 */
public class LC0343_IntegerBreak {
    public static void main(String[] args){

        int n = 10;
        System.out.println(integerBreak(n));
    }

    /**
     *  解答成功:
     * 		执行耗时:1 ms,击败了70.32% 的Java用户
     * 		内存消耗:35.1 MB,击败了75.36% 的Java用户
     * @param n
     * @return
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        int max = dp[2];
        for(int i = 3; i <= n; i++){
            for(int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j));
                // 仅在当前位置切成两段，查看其乘积
                dp[i] = Math.max(dp[i], j * (i - j));
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
