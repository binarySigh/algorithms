package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给你一个整数数组 nums，请你将该数组升序排列。
 * //
 * // 示例 1：
 * //
 * // 输入：nums = [5,2,3,1]
 * //输出：[1,2,3,5]
 * //
 * // 示例 2：
 * //
 * // 输入：nums = [5,1,1,2,0,0]
 * //输出：[0,0,1,1,2,5]
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 50000
 * // -50000 <= nums[i] <= 50000
 * //
 * // Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序
 * @author binarySigh
 * @date 2021/9/3 21:42
 */
public class LC0912_SortArray {
    public static void main(String[] args){
        int[] nums = {5,2,3,1,32,2,-1,0,23,-99};
        int[] ans = sortArray(nums);
        ArrayUtils.showArray(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:24 ms,击败了44.56% 的Java用户
     * 		内存消耗:47.4 MB,击败了62.06% 的Java用户
     * @param nums
     * @return
     */
    public static int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public static void quickSort(int[] nums, int l, int r){
        if(l >= r){
            return;
        }
        int ran = (int)(Math.random() * (r - l) + l);
        swap(nums, ran, r);
        int s = l - 1, b = r;
        for(int i = l; i < b;){
            if(nums[i] < nums[r]){
                swap(nums, ++s, i++);
            } else if(nums[i] == nums[r]){
                i++;
            } else {
                swap(nums, i, --b);
            }
        }
        swap(nums, b, r);
        quickSort(nums, l, s);
        quickSort(nums, ++b, r);
    }

    public static void swap(int[] nums, int i, int j){
        if(nums[i] != nums[j]){
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }
}
