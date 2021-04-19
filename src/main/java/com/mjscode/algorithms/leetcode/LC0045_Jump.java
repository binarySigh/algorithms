package com.mjscode.algorithms.leetcode;

/**
 * //给定一个非负整数数组，你最初位于数组的第一个位置。
 * //
 * // 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * //
 * // 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * //
 * // 示例:
 * //
 * // 输入: [2,3,1,1,4]
 * //输出: 2
 * //解释: 跳到最后一个位置的最小跳跃数是 2。
 * //     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * //
 * // 说明:
 * //
 * // 假设你总是可以到达数组的最后一个位置。
 * // Related Topics 贪心算法 数组
 * @author binarySigh
 * @date 2021/4/19 22:11
 */
public class LC0045_Jump {
    public static void main(String[] args){
        //int[] nums = {2,3,0,1,4};
        int[] nums = {5,9,3,2,1,0,2,3,3,1,0,0};
        System.out.println(jump(nums));
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了48.59% 的Java用户
     * 		内存消耗:35.7 MB,击败了89.03% 的Java用户
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        if(nums == null || nums.length <= 1 || nums[0] == 0){
            return 0;
        }
        if(nums.length == 2 || nums[0] >= nums.length - 1){
            return 1;
        }
        int[] step = new int[nums.length];
        for(int i = nums.length - 2; i >= 0; i--){
            if(nums[i] + i >= nums.length - 1){
                step[i] = 1;
                continue;
            }
            if(nums[i] == 0){
                step[i] = Integer.MAX_VALUE;
                continue;
            }
            step[i] = Integer.MAX_VALUE;
            for(int j = 1; j <= nums[i] && j + i < nums.length; j++){
                step[i] = Math.min(step[i], step[j + i]);
            }
            if(step[i] != Integer.MAX_VALUE){
                step[i]++;
            }
        }
        return step[0];
    }
}
