package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * //
 * // 进阶：
 * //
 * // 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * // 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 * //
 * // 示例 1:
 * //
 * //输入: nums = [1,2,3,4,5,6,7], k = 3
 * //输出: [5,6,7,1,2,3,4]
 * //解释:
 * //向右旋转 1 步: [7,1,2,3,4,5,6]
 * //向右旋转 2 步: [6,7,1,2,3,4,5]
 * //向右旋转 3 步: [5,6,7,1,2,3,4]
 * //
 * // 示例 2:
 * //
 * //输入：nums = [-1,-100,3,99], k = 2
 * //输出：[3,99,-1,-100]
 * //解释:
 * //向右旋转 1 步: [99,-1,-100,3]
 * //向右旋转 2 步: [3,99,-1,-100]
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 2 * 104
 * // -231 <= nums[i] <= 231 - 1
 * // 0 <= k <= 105
 * //
 * // Related Topics 数组 数学 双指针
 * @author binarySigh
 * @date 2021/8/15 17:42
 */
public class LC0189_Rotate {
    public static void main(String[] args){
        // --> [5,6,7,1,2,3,4]
        /*int[] nums = {1,2,3,4,5,6,7};
        int k = 3;*/

        // --> [3,99,-1,-100]
        /*int[] nums = {-1,-100,3,99};
        int k = 2;*/


        int[] nums = {1,2,3,4,5,6,7};
        int k = 9;
        rotate(nums, k);
        ArrayUtils.showArray(nums);
    }

    /**
     * 解答成功:
     * 		执行耗时:3 ms,击败了5.92% 的Java用户
     * 		内存消耗:55.2 MB,击败了69.49% 的Java用户
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        if(k == 0){
            return;
        }
        swap(nums, 0, n - k - 1);
        swap(nums, n - k, n - 1);
        swap(nums, 0, n - 1);
    }

    public static void swap(int[] nums, int l, int r) {
        while(l < r){
            if(nums[l] != nums[r]){
                nums[l] = nums[l] ^ nums[r];
                nums[r] = nums[l] ^ nums[r];
                nums[l] = nums[l] ^ nums[r];
            }
            l++;
            r--;
        }
    }
}
