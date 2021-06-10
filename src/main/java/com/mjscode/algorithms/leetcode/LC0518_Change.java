package com.mjscode.algorithms.leetcode;

/**
 * //给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * //
 * // 示例 1:
 * //
 * // 输入: amount = 5, coins = [1, 2, 5]
 * //输出: 4
 * //解释: 有四种方式可以凑成总金额:
 * //5=5
 * //5=2+2+1
 * //5=2+1+1+1
 * //5=1+1+1+1+1
 * //
 * // 示例 2:
 * //
 * // 输入: amount = 3, coins = [2]
 * //输出: 0
 * //解释: 只用面额2的硬币不能凑成总金额3。
 * //
 * // 示例 3:
 * //
 * // 输入: amount = 10, coins = [10]
 * //输出: 1
 * //
 * // 注意:
 * //
 * // 你可以假设：
 * //
 * // 0 <= amount (总金额) <= 5000
 * // 1 <= coin (硬币面额) <= 5000
 * // 硬币种类不超过 500 种
 * // 结果符合 32 位符号整数
 * @author binarySigh
 * @date 2021/6/10 22:21
 */
public class LC0518_Change {

    public static void main(String[] args){
        int amount1 = 5;
        int[] coins1 = {1, 2, 5};
        System.out.println(change(amount1, coins1));
    }

    /**
     * 解答成功:
     * 		执行耗时:39 ms,击败了5.14% 的Java用户
     * 		内存消耗:42.3 MB,击败了28.45% 的Java用户
     * @param amount
     * @param coins
     * @return
     */
    public static int change(int amount, int[] coins) {
        int[][] dp = new int[amount + 1][coins.length];
        //第一层填充
        for(int i = 0; i <= amount; i += coins[0]){
            dp[i][0] = 1;
        }
        //后续层填充
        for(int j = 0; j < coins.length - 1; j++){
            for(int i = 0; i <= amount; i++){
                dp[i][j + 1] += dp[i][j];
                for(int t = i + coins[j  + 1]; t <= amount; t += coins[j + 1]){
                    dp[t][j + 1] += dp[i][j];
                }
            }
        }
        return dp[amount][coins.length - 1];
    }
}
