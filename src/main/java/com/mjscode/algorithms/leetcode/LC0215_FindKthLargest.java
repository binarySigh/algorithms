package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.Arrays;

/**
 * //给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * //
 * // 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * //
 * // 示例 1:
 * //
 * //输入: [3,2,1,5,6,4] 和 k = 2
 * //输出: 5
 * //
 * // 示例 2:
 * //
 * //输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * //输出: 4
 * //
 * // 提示：
 * //
 * // 1 <= k <= nums.length <= 104
 * // -104 <= nums[i] <= 104
 * //
 * // Related Topics 数组 分治 快速选择 排序 堆（优先队列）
 * @author binarySigh
 * @date 2021/9/10 20:56
 */
public class LC0215_FindKthLargest {
    public static void main(String[] args){
        // --> 5
        /*int[] nums1 = {3,2,1,5,6,4};
        int k1 = 2;*/

        // --> 4
        int[] nums1 = {3,2,3,1,2,4,5,5,6};
        int k1 = 4;
        System.out.println(findKthLargest(nums1, k1));


        System.out.println("-----------------TEST BEGIN-----------");
        for(int i = 0; i < 10_0000; i++){
            int len = (int)(Math.random() * 200) + 1;
            int[] nums = getNums(len);
            int k = (int)(Math.random() * len) + 1;
            int ans = findKthLargest(nums, k);
            int com = compare(nums, k);
            if(ans != com){
                System.out.println("--------Oops!--------");
                ArrayUtils.showArray(nums);
                System.out.println(ans);
                System.out.println(com);
                break;
            }
        }
        System.out.println("-----------------TEST END-----------");
    }

    /**
     * 解答成功:
     * 		执行耗时:3 ms,击败了60.40% 的Java用户
     * 		内存消耗:38.6 MB,击败了71.29% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        return find(nums, 0, nums.length - 1, nums.length - k);
    }

    public static int find(int[] nums, int l, int r, int k){
        if(l == r){
            return nums[l];
        }
        int ran = l + (int)(Math.random() * (r - l + 1));
        swap(nums, ran, r);
        int i = l, j = r;
        for(int t = l; t < j; ){
            if(nums[t] > nums[r]){
                swap(nums, t, --j);
            } else if(nums[t] < nums[r]){
                swap(nums, i++, t++);
            } else{
                t++;
            }
        }
        swap(nums, j, r);
        if(i <= k && j >= k){
            return nums[j];
        } else if(i > k){
            return find(nums, l, --i, k);
        } else if(j < k){
            return find(nums, ++j, r, k);
        }
        return 0;
    }

    public static void swap(int[] nums, int l, int r){
        if(l < r && nums[l] != nums[r]){
            nums[l] = nums[l] ^ nums[r];
            nums[r] = nums[l] ^ nums[r];
            nums[l] = nums[l] ^ nums[r];
        }
    }


    // 对数器部分
    public static int compare(int[] nums, int k){
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static int[] getNums(int len){
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = (int)(Math.random() * 200) - (int)(Math.random() * 200);
        }
        return nums;
    }
}
