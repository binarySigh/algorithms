package com.mjscode.algorithms.leetcode;

/**
 * //给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * //
 * // 示例 1:
 * //
 * // 输入: [2,3,-2,4]
 * //输出: 6
 * //解释: 子数组 [2,3] 有最大乘积 6。
 * //
 * // 示例 2:
 * //
 * // 输入: [-2,0,-1]
 * //输出: 0
 * //解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * // Related Topics 数组 动态规划
 * @author binarySigh
 * @date 2021/8/13 22:43
 */
public class LC0152_MaxProduct {
    public static void main(String[] args){
        // --> 6
        //int[] nums = {2,3,-2,4};

        // --> 0
        int[] nums = {-2,0,-1};
        System.out.println(maxProduct(nums));
    }

    /**
     * 解答成功:
     * 		执行耗时:2 ms,击败了86.16% 的Java用户
     * 		内存消耗:38.4 MB,击败了18.87% 的Java用户
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        while(nums == null || nums.length < 1){
            return 0;
        }
        long max = nums[0];
        long min = nums[0];
        long ans = nums[0];
        for(int i = 1; i < nums.length; i++){
            long curMax = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            long curMin = Math.min(Math.min(max * nums[i], min * nums[i]), nums[i]);
            ans = Math.max(curMax, ans);
            max = curMax;
            min = curMin;
        }
        return (int)ans;
    }
}
