package com.mjscode.algorithms.leetcode;

/**
 * //给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,5,11,5]
 * //输出：true
 * //解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [1,2,3,5]
 * //输出：false
 * //解释：数组不能分割成两个元素和相等的子集。
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 200
 * // 1 <= nums[i] <= 100
 * //
 * // Related Topics 数组 动态规划
 * @author binarySigh
 * @date 2021/9/27 23:27
 */
public class LC0416_CanPartition {

    public static void main(String[] args) {
        // --> true
        /*int[] nums1 = {1,5,11,5};*/

        // --> false
        //int[] nums1 = {1,2,3,5};

        //int[] nums1 = {100};

        int[] nums1 = {9,5};

        System.out.println(canPartition(nums1));
    }

    public static boolean canPartition(int[] nums) {
        int sum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            sum += nums[i];
        }
        if((sum & 1) == 1 || nums.length == 1) {
            return false;
        }
        sum >>= 1;
        boolean[][] dp = new boolean[sum + 1][nums.length];
        if(nums[0] <= sum){
            dp[nums[0]][0] = true;
        }
        dp[0][0] = true;
        for(int j = 0; j < nums.length - 1; j++){
            for(int i = 0; i <= sum; i++){
                if(!dp[i][j]) {
                    continue;
                }
                dp[i][j + 1] = true;
                if(i + nums[j + 1] <= sum) {
                    dp[i + nums[j + 1]][j + 1] = true;
                }
            }
        }
        return dp[sum][nums.length - 1];
    }

}
