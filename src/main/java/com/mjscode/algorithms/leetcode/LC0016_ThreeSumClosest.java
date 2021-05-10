package com.mjscode.algorithms.leetcode;

import java.util.Arrays;

/**
 * //给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
 * //。假定每组输入只存在唯一答案。
 * //
 * // 示例：
 * //
 * // 输入：nums = [-1,2,1,-4], target = 1
 * //输出：2
 * //解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * //
 * // 提示：
 * //
 * // 3 <= nums.length <= 10^3
 * // -10^3 <= nums[i] <= 10^3
 * // -10^4 <= target <= 10^4
 * //
 * // Related Topics 数组 双指针
 * @author binarySigh
 * @date 2021/5/10 20:53
 */
public class LC0016_ThreeSumClosest {
    public static void main(String[] args){
        int[] nums = {-1,2,1,-4};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }

    /**
     * 排序 + 双指针
     * 解答成功:
     * 		执行耗时:6 ms,击败了82.75% 的Java用户
     * 		内存消耗:38 MB,击败了80.24% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        int closest = Integer.MAX_VALUE;
        int len = nums.length;
        for(int i = 0; i <= len - 3; i++){
            int l = i + 1, r = len - 1;
            int sum = 0;
            int dis = 0;
            while(l < r){
                sum = nums[i] + nums[l] + nums[r];
                dis = Math.abs(sum - target);
                if(dis < closest){
                    closest = dis;
                    ans = sum;
                }
                if(sum < target){
                    l++;
                } else if(sum > target){
                    r--;
                } else {
                    return sum;
                }
            }
        }
        return ans;
    }
}
