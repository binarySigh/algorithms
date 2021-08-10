package com.mjscode.algorithms.leetcode;

/**
 * //如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * //
 * // 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * //
 * // 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * //
 * // 子数组 是数组中的一个连续序列。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,2,3,4]
 * //输出：3
 * //解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [1]
 * //输出：0
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 5000
 * // -1000 <= nums[i] <= 1000
 * //
 * // Related Topics 数组 动态规划
 * @author binarySigh
 * @date 2021/8/10 19:55
 */
public class LC413_NumberOfArithmeticSlices {
    public static void main(String[] args){
        int[] nums = {1,2,3,4};
        //int[] nums = {1,2,3,8,9,10};
        System.out.println(numberOfArithmeticSlices(nums));
    }

    /**
     * 解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:35.8 MB,击败了98.45% 的Java用户
     * @param nums
     * @return
     */
    public static int numberOfArithmeticSlices(int[] nums) {
        if(nums.length < 3) {
            return 0;
        }
        int diff = nums[1] - nums[0];
        int count = 1;
        int ans = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] - nums[i - 1] == diff && i == nums.length - 1){
                count++;
                ans += (count - 1) * (count - 2) / 2;
                break;
            }
            if(nums[i] - nums[i - 1] != diff || i == nums.length - 1){
                ans += (count - 1) * (count - 2) / 2;
                diff = nums[i] - nums[i - 1];
                count = 2;
            } else {
                count++;
            }
        }
        return ans;
    }
}
