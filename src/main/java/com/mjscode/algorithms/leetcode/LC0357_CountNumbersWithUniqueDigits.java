package com.mjscode.algorithms.leetcode;

/**
 * //给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。
 * //
 * // 示例:
 * //
 * // 输入: 2
 * //输出: 91
 * //解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 * //
 * // Related Topics 数学 动态规划 回溯
 * @author binarySigh
 * @date 2021/9/24 21:11
 */
public class LC0357_CountNumbersWithUniqueDigits {

    public static void main(String[] args) {
        int n = 8;
        System.out.println(countNumbersWithUniqueDigits(n));

    }

    public static int countNumbersWithUniqueDigits(int n) {
        if(n <= 1){
            return (int)Math.pow(10, n);
        }
        n = Math.min(n, 10);
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 10;
        for(int i = 2; i <= n; i++){
            dp[i] = (10 - (i - 1)) * (dp[i - 1] - dp[i - 2]) + dp[i - 1];
        }
        return dp[n];
    }

}
