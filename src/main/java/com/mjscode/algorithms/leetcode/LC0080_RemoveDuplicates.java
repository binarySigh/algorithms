package com.mjscode.algorithms.leetcode;

/**
 * //给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 * //
 * // 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * //
 * // 说明：
 * //
 * // 为什么返回数值是整数，但输出的答案是数组呢？
 * //
 * // 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * //
 * // 你可以想象内部操作如下:
 * //
 * //// nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * //int len = removeDuplicates(nums);
 * //
 * //// 在函数里修改输入数组对于调用者是可见的。
 * //// 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * //for (int i = 0; i < len; i++) {
 * //    print(nums[i]);
 * //}
 * // 示例 1：
 * //
 * //输入：nums = [1,1,1,2,2,3]
 * //输出：5, nums = [1,1,2,2,3]
 * //解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [0,0,1,1,1,1,2,3,3]
 * //输出：7, nums = [0,0,1,1,2,3,3]
 * //解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的
 * //元素。
 * //
 * // 提示：
 * //
 * // 0 <= nums.length <= 3 * 104
 * // -104 <= nums[i] <= 104
 * // nums 已按升序排列
 * //
 * // Related Topics 数组 双指针
 * @author binarySigh
 * @date 2021/4/6 0:13
 */
public class LC0080_RemoveDuplicates {
    public static void main(String[] args){
        //int[] nums = {0,0,1,1,1,1,2,3,3};
        //int[] nums = {1,2,3,3,4,4,5,6,7};
        //int[] nums = {1,2,3,4,5,6,7};
        //int[] nums = {0,0,1,1,1,1,2,3,3,3,3,3,3,4,4,5,6,6,6,7};
        int[] nums = {1,1,1};
        System.out.println(removeDuplicates(nums));
    }

    /**
     *执行结果： 通过
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 80.75% 的用户
     * 内存消耗： 38.4 MB , 在所有 Java 提交中击败了 87.25% 的用户
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length <= 2){
            return nums == null ? 0 : nums.length;
        }
        //index是调整后数组的长度
        int index = 2;
        //遍历指针
        int pos = 2;
        while(pos < nums.length){
            while(nums[pos] == nums[index - 2]) {
                //如果此时pos已是最后一个位置，则直接将index返回，防止越位
                if(pos == nums.length - 1){
                    return index;
                }
                pos++;
            }
            if(index < pos){
                nums[index] = nums[pos];
            }
            index++;
            pos++;
        }
        return index;
    }
}
