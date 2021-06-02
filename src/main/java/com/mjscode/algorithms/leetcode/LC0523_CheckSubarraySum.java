package com.mjscode.algorithms.leetcode;

import java.util.HashMap;

/**
 * //给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 * //
 * // 子数组大小 至少为 2 ，且
 * // 子数组元素总和为 k 的倍数。
 * //
 * // 如果存在，返回 true ；否则，返回 false 。
 * //
 * // 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [23,2,4,6,7], k = 6
 * //输出：true
 * //解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [23,2,6,4,7], k = 6
 * //输出：true
 * //解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
 * //42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
 * //
 * // 示例 3：
 * //
 * //输入：nums = [23,2,6,4,7], k = 13
 * //输出：false
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 105
 * // 0 <= nums[i] <= 109
 * // 0 <= sum(nums[i]) <= 231 - 1
 * // 1 <= k <= 231 - 1
 * //
 * // Related Topics 数学 动态规划
 * @author binarySigh
 * @date 2021/6/2 20:47
 */
public class LC0523_CheckSubarraySum {
    public static void main(String[] args){
        int[] nums = {23,2,6,4,7};
        int k = 13;
        System.out.println(checkSubarraySum(nums, k));
    }

    /**
     * 解答成功:
     * 		执行耗时:24 ms,击败了21.35% 的Java用户
     * 		内存消耗:52.7 MB,击败了54.16% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length < 2){
            return false;
        }
        int[] preSum = new int[nums.length];
        HashMap<Integer, Integer> record = new HashMap<>();
        preSum[0] = nums[0];
        record.put(0, -1);
        record.put(preSum[0] % k, 0);
        for(int i = 1; i < nums.length; i++){
            preSum[i] = preSum[i - 1] + nums[i];
            if(preSum[i] % k == 0){
                return true;
            }
            int left = preSum[i] % k;
            if(record.containsKey(left) && i - record.get(left) > 1){
                return true;
            } else if(!record.containsKey(left)){
                record.put(left, i);
            }
        }
        return false;
    }

    public static boolean checkSubarraySum1(int[] nums, int k) {
        if(nums.length < 2){
            return false;
        }
        int[] preSum = new int[nums.length];
        HashMap<Integer, Integer> record = new HashMap<>();
        preSum[0] = nums[0];
        record.put(0, -1);
        record.put(preSum[0] % k, 0);
        for(int i = 1; i < nums.length; i++){
            preSum[i] = preSum[i - 1] + nums[i];
            if(preSum[i] % k == 0){
                return true;
            }
            int left = preSum[i] % k;
            if(record.containsKey(left) && i - record.get(left) > 1){
                return true;
            } else if(!record.containsKey(left)){
                record.put(left, i);
            }
        }
        return false;
    }
}
