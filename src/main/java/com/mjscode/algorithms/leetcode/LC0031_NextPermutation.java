package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * //
 * // 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * //
 * // 必须 原地 修改，只允许使用额外常数空间。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,2,3]
 * //输出：[1,3,2]
 * //
 * // 示例 2：
 * //
 * //输入：nums = [3,2,1]
 * //输出：[1,2,3]
 * //
 * // 示例 3：
 * //
 * //输入：nums = [1,1,5]
 * //输出：[1,5,1]
 * //
 * // 示例 4：
 * //
 * //输入：nums = [1]
 * //输出：[1]
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 100
 * // 0 <= nums[i] <= 100
 * //
 * // Related Topics 数组
 * @author binarySigh
 * @date 2021/5/13 22:46
 */
public class LC0031_NextPermutation {
    public static void main(String[] args){
        int[] nums = {1,5,1};

        //int[] nums = {1,2};
        ArrayUtils.showArray(nums);
        nextPermutation(nums);
        ArrayUtils.showArray(nums);
    }

    /**
     * 解答成功:
     * 		执行耗时:1 ms,击败了97.80% 的Java用户
     * 		内存消耗:38.7 MB,击败了51.96% 的Java用户
     * 思路：
     * 1. 从尾到头遍历查找第一个 pre,使得 nums[pre] < nums[pre+1];
     * 2. 从pre+1 往后找，找到最后一个 des，使得nums[des] > nums[pre];
     * 3. 交换 pre,des 两个位置的数，并将 pre(不含)往后的数升序排列
     * 4. 如果第一步找不到这样的 pre，那么说明当前已是字典序最大的排列，直接将整个数组升序排列
     *
     * 复杂度：
     * 1. 时间：O(N)。遍历有限次原数组即可
     * 2. 空间：O(1)。由于数组元素特点，只申请了容量有限的桶空间
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1){
            return;
        }
        int[] buckets = new int[101];
        int des = nums.length - 1;
        while(des > 0 && nums[des - 1] >= nums[des]){
            buckets[nums[des]]++;
            des--;
        }
        buckets[nums[des]]++;
        int pre = des - 1;
        // pre < 0,说明当前排列已是字典序最大的排列，则直接将整个数组升序就行
        if(pre >= 0){
            while(des + 1 < nums.length && nums[des + 1] > nums[pre]){
                des++;
            }
            buckets[nums[des]]--;
            buckets[nums[pre]]++;
            //交换 pre,des 位置的数
            nums[pre] = nums[pre] ^ nums[des];
            nums[des] = nums[pre] ^ nums[des];
            nums[pre] = nums[pre] ^ nums[des];
        }
        //将 pre 之后位置的数升序排列
        pre++;
        for(int i = 0; i < 101 && pre < nums.length; i++){
            while(buckets[i]-- > 0){
                nums[pre++] = i;
            }
        }
    }
}
