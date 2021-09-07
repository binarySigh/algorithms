package com.mjscode.algorithms.leetcode;

/**
 * //给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
 * //
 * // 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * //
 * // 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * // 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * //
 * // 示例:
 * //
 * // 输入: [1,2,3,0,2]
 * //输出: 3
 * //解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * // Related Topics 数组 动态规划
 * @author binarySigh
 * @date 2021/9/7 19:22
 */
public class LC0309_MaxProfit {
    public static void main(String[] args) {
        int[] prices1 = {7,1,18,9,2,0,10,14,9,6,1,12,19,1,4,16,11,4};

        System.out.println("ans : " + maxProfit(prices1));

    }

    /**
     *解答成功:
     * 		执行耗时:23 ms,击败了13.3% 的Java用户
     * 		内存消耗:36.3 MB,击败了86.2% 的Java用户
     * @param p
     * @return
     */
    public static int maxProfit(int[] p){
        if(p == null || p.length < 2){
            return 0;
        }
        int[] dp = new int[p.length];
        int preMax = 0;
        int ans = 0;
        for(int i = 0; i < p.length; i++){
            ans = Math.max(ans, dp[i]);
            preMax = Math.max(preMax, (i >= 2 ? dp[i - 2] : 0));
            for(int j = i + 1; j < p.length; j++){
                if(p[j] > p[i]){
                    dp[j] = Math.max(dp[j], preMax + p[j] - p[i]);
                }
            }
        }
        return ans;
    }
}
