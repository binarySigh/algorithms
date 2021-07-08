package com.mjscode.algorithms.leetcode;

import java.util.HashMap;

/**
 * //给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 * //
 * // 子数组 是数组的一段连续部分。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,0,1,0,1], goal = 2
 * //输出：4
 * //解释：
 * //有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
 * //
 * // 示例 2：
 * //
 * //输入：nums = [0,0,0,0,0], goal = 0
 * //输出：15
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 3 * 104
 * // nums[i] 不是 0 就是 1
 * // 0 <= goal <= nums.length
 * //
 * // Related Topics 数组 哈希表 前缀和 滑动窗口
 * @author binarySigh
 * @date 2021/7/8 22:38
 */
public class LC0930_NumSubarraysWithSum {
    public static void main(String[] args){
        int[] nums = {1,0,1,0,1};
        int goal = 2;
        System.out.println(numSubarraysWithSum(nums, goal));
    }

    /**
     * 解答成功:
     * 		执行耗时:20 ms,击败了62.53% 的Java用户
     * 		内存消耗:43 MB,击败了16.19% 的Java用户
     * @param nums
     * @param goal
     * @return
     */
    public static int numSubarraysWithSum(int[] nums, int goal) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0 ,1);
        int sum = 0;
        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            int count = map.getOrDefault(sum - goal, 0);
            ans += count;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
