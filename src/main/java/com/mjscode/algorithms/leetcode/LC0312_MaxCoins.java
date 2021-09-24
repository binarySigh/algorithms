package com.mjscode.algorithms.leetcode;

/**
 * //有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * //
 * // 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
 * // 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * //
 * // 求所能获得硬币的最大数量。
 * //
 * //示例 1：
 * //
 * //输入：nums = [3,1,5,8]
 * //输出：167
 * //解释：
 * //nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * //coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * //
 * // 示例 2：
 * //
 * //输入：nums = [1,5]
 * //输出：10
 * //
 * // 提示：
 * //
 * // n == nums.length
 * // 1 <= n <= 500
 * // 0 <= nums[i] <= 100
 * //
 * // Related Topics 数组 动态规划
 * @author binarySigh
 * @date 2021/9/24 21:12
 */
public class LC0312_MaxCoins {

    public static void main(String[] args) {
        // --> 167
        //int[] nums = {3,1,5,8};

        // --> 10
        //int[] nums = {1,5};

        int[] nums = {3};
        System.out.println(maxCoins(nums));

        double d = Double.MAX_VALUE;
    }

    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n + 2];
        arr[0] = 1;
        arr[arr.length - 1] = 1;
        for(int i = 0; i < nums.length; i++){
            arr[i + 1] = nums[i];
        }
        int[][] dp = new int[n + 2][n + 2];
        for(int j = 0; j < n + 2; j++){
            for(int i = j - 2; i >= 0; i--){
                if(i == j - 2){
                    dp[i][j] = arr[i] * arr[i + 1] * arr[j];
                    continue;
                }
                int cur = 0;
                for(int t = i + 1; t < j; t++){
                    cur = arr[t] * arr[i] * arr[j] + dp[i][t] + dp[t][j];
                    dp[i][j] = Math.max(dp[i][j], cur);
                }
            }
        }
        return dp[0][n + 1];
    }

}
