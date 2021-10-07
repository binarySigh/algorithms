package com.mjscode.algorithms.leetcode;

/**
 * //给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 * //
 * // 示例 1：
 * //
 * //输入：[3, 2, 1]
 * //输出：1
 * //解释：第三大的数是 1 。
 * //
 * // 示例 2：
 * //
 * //输入：[1, 2]
 * //输出：2
 * //解释：第三大的数不存在, 所以返回最大的数 2 。
 * //
 * // 示例 3：
 * //
 * //输入：[2, 2, 3, 1]
 * //输出：1
 * //解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * //此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 10⁴
 * // -2³¹ <= nums[i] <= 2³¹ - 1
 * //
 * // 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？
 * // Related Topics 数组 排序
 * @author binarySigh
 * @date 2021/10/6 8:58
 */
public class LC0414_ThirdMax {
    public static void main(String[] args) {
        int[] nums1 = {3,2,2,2,3};
        System.out.println(thirdMax(nums1));
    }

    /**
     * 解答成功:
     * 		执行耗时:2 ms,击败了53.46% 的Java用户
     * 		内存消耗:38.2 MB,击败了60.72% 的Java用户
     * @param nums
     * @return
     */
    public static int thirdMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i : nums){
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int preMax = max - 1;
        int curMax = min;
        int i = 2;
        while(i > 0) {
            for (int cur : nums) {
                if(cur <= preMax) {
                    curMax = Math.max(curMax, cur);
                }
            }
            if(curMax > preMax) {
                return max;
            }
            preMax = curMax - 1;
            i--;
            curMax = i == 0 ? curMax : min;
        }
        return curMax;
    }
}
