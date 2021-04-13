package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * //
 * // 如果数组中不存在目标值 target，返回 [-1, -1]。
 * //
 * // 进阶：
 * //
 * // 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * //
 * // 示例 1：
 * //
 * //输入：nums = [5,7,7,8,8,10], target = 8
 * //输出：[3,4]
 * //
 * // 示例 2：
 * //
 * //输入：nums = [5,7,7,8,8,10], target = 6
 * //输出：[-1,-1]
 * //
 * // 示例 3：
 * //
 * //输入：nums = [], target = 0
 * //输出：[-1,-1]
 * //
 * // 提示：
 * //
 * // 0 <= nums.length <= 105
 * // -109 <= nums[i] <= 109
 * // nums 是一个非递减数组
 * // -109 <= target <= 109
 * //
 * // Related Topics 数组 二分查找
 * @author binarySigh
 * @date 2021/4/13 22:20
 */
public class LC0034_SearchRange {

    public static void main(String[] args){
        //int[] nums = {5,7,7,8,8,10};
        int[] nums = {1100};
        int target = 1100;
        int[] res = searchRange(nums, target);
        ArrayUtils.showArray(res);
    }

    /**
     *解答成功:
     * 		执行耗时:0 ms,击败了100.00% 的Java用户
     * 		内存消耗:41.7 MB,击败了52.65% 的Java用户
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if(nums == null || nums.length == 0){
            return res;
        }
        if(nums.length == 1){
            return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        }
        res[1] = findLast(nums, target);
        res[0] = res[1] == -1 ? -1 : findLast(nums, target - 1) + 1;
        if(res[0] > res[1]){
            res[0] = -1;
            res[1] = -1;
        }
        return res;
    }

    /**
     * 寻找 nums数组中，小于等于 target 的最右一个数的下标
     * @param nums
     * @param target
     * @return
     */
    public static int findLast(int[] nums, int target){
        int index = -1;
        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        while(l <= r){
            mid = l + ((r - l) >> 1);
            if(nums[mid] <= target){
                index = Math.max(index, mid);
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return index;
    }
}
