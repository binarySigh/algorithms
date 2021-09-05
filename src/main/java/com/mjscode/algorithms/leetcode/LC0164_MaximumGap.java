package com.mjscode.algorithms.leetcode;

import java.util.Arrays;

/**
 * //给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * //
 * // 如果数组元素个数小于 2，则返回 0。
 * //
 * // 示例 1:
 * //
 * // 输入: [3,6,9,1]
 * //输出: 3
 * //解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * //
 * // 示例 2:
 * //
 * // 输入: [10]
 * //输出: 0
 * //解释: 数组元素个数小于 2，因此返回 0。
 * //
 * // 说明:
 * //
 * // 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * // 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 * //
 * // Related Topics 数组 桶排序 基数排序 排序
 * @author binarySigh
 * @date 2021/9/5 10:45
 */
public class LC0164_MaximumGap {

    public static void main(String[] args){
        // --> 3
        int[] nums1 = {3,6,9,1};
        System.out.println("ans : " + maximumGap(nums1));
        System.out.println("com : " + compare(nums1));
    }

    public static int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2){
            return 0;
        }
        return 0;
    }

    /**
     * 解答成功:
     * 		执行耗时:43 ms,击败了50.75% 的Java用户
     * 		内存消耗:52.4 MB,击败了46.09% 的Java用户
     * @param nums
     * @return
     */
    public static int compare(int[] nums){
        if(nums == null || nums.length < 2){
            return 0;
        }
        Arrays.sort(nums);
        int min = 0;
        for(int i = 1; i < nums.length; i++){
            min = Math.max(min, nums[i] - nums[i - 1]);
        }
        return min;
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 200);
        }
        return nums;
    }
}
