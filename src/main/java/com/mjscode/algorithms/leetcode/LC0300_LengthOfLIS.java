package com.mjscode.algorithms.leetcode;

/**
 * //给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * //
 * // 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
 * //列。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [10,9,2,5,3,7,101,18]
 * //输出：4
 * //解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [0,1,0,3,2,3]
 * //输出：4
 * //
 * // 示例 3：
 * //
 * //输入：nums = [7,7,7,7,7,7,7]
 * //输出：1
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 2500
 * // -104 <= nums[i] <= 104
 * //
 * // 进阶：
 * //
 * // 你可以设计时间复杂度为 O(n2) 的解决方案吗？
 * // 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 * //
 * // Related Topics 二分查找 动态规划
 *
 * @author binarySigh
 */
public class LC0300_LengthOfLIS {

    /**
     * 本题优化方向：
     *      尝试二分查找的解法
     * @param args
     */
    public static void main(String[] args){

    }

    /**
     * 复杂度 O(n2) 的解决方案
     * 解答成功:
     * 		执行耗时:65 ms,击败了40.14% 的Java用户
     * 		内存消耗:37.9 MB,击败了49.49% 的Java用户
     * @param arr
     * @return
     */
    public static int process(int[] arr){
        int[] max = new int[arr.length];
        max[arr.length - 1] = 1;
        int totalMax = 1;
        int curMax = 1;
        for(int i = arr.length - 2; i >= 0; i--){
            for(int j = i; j < arr.length; j++){
                curMax = arr[i] < arr[j] ? Math.max(max[j] + 1, curMax) : curMax;
            }
            max[i] = curMax;
            curMax = 1;
            totalMax = Math.max(max[i], totalMax);
        }
        return totalMax;
    }
}
