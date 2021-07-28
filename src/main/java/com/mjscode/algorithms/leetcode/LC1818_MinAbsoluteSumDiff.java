package com.mjscode.algorithms.leetcode;

import java.util.Arrays;

/**
 * //给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
 * //
 * // 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始
 * //）。
 * //
 * // 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
 * //
 * // 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
 * //
 * // |x| 定义为：
 * //
 * // 如果 x >= 0 ，值为 x ，或者
 * // 如果 x <= 0 ，值为 -x
 * //
 * // 示例 1：
 * //
 * //输入：nums1 = [1,7,5], nums2 = [2,3,5]
 * //输出：3
 * //解释：有两种可能的最优方案：
 * //- 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
 * //- 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
 * //两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
 * //
 * // 示例 2：
 * //
 * //输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
 * //输出：0
 * //解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
 * //
 * // 示例 3：
 * //
 * //输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
 * //输出：20
 * //解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
 * //绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
 * //
 * // 提示：
 * //
 * // n == nums1.length
 * // n == nums2.length
 * // 1 <= n <= 105
 * // 1 <= nums1[i], nums2[i] <= 105
 * //
 * // Related Topics 贪心 数组 二分查找 有序集合
 * @author binarySigh
 * @date 2021/7/14 20:51
 */
public class LC1818_MinAbsoluteSumDiff {
    public static void main(String[] args){
        // --> 3
        /*int[] nums1 = {1,7,5};
        int[] nums2 = {2,3,5};*/

        // --> 0
        /*int[] nums1 = {2,4,6,8,10};
        int[] nums2 = {2,4,6,8,10};*/

        // --> 20
        /*int[] nums1 = {1,10,4,4,2,7};
        int[] nums2 = {9,3,5,1,7,4};*/

        // --> 3156
        int[] nums1 = {53,48,14,71,31,55,6,80,28,19,15,40,7,21,69,15,5,42,86,15,11,54,44,62,9,100,2,26,81,87,87,18,45,29,46,100,20,87,49,86,14,74,74,52,52,60,8,25,21,96,7,90,91,42,32,34,55,20,66,36,64,67,44,51,4,46,25,57,84,23,10,84,99,33,51,28,59,88,50,41,59,69,59,65,78,50,78,50,39,91,44,78,90,83,55,5,74,96,77,46};
        int[] nums2 = {39,49,64,34,80,26,44,3,92,46,27,88,73,55,66,10,4,72,19,37,40,49,40,58,82,32,36,91,62,21,68,65,66,55,44,24,78,56,12,79,38,53,36,90,40,73,92,14,73,89,28,53,52,46,84,47,51,31,53,22,24,14,83,75,97,87,66,42,45,98,29,82,41,36,57,95,100,2,71,34,43,50,66,52,6,43,94,71,93,61,28,84,7,79,23,48,39,27,48,79};
        System.out.println(minAbsoluteSumDiff(nums1, nums2));
    }

    /**
     * 解答成功:
     * 		执行耗时:50 ms,击败了82.39% 的Java用户
     * 		内存消耗:55.9 MB,击败了65.10% 的Java用户
     * @param nums1
     * @param nums2
     * @return
     */
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2){
        int mod = (int)Math.pow(10, 9) + 7;
        int[] arr = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            arr[i] = nums1[i];
        }
        Arrays.sort(arr);
        int maxDiff = 0;
        int ans = 0;
        for(int i = 0; i < nums1.length; i++){
            int curDiff = Math.abs(nums1[i] - nums2[i]);
            ans = (ans + curDiff) % mod;
            int minDiffIdx = getMinDiff(arr, nums2[i]);
            if(minDiffIdx < arr.length){
                maxDiff = Math.max(maxDiff, curDiff - (arr[minDiffIdx] - nums2[i]));
            }
            if(minDiffIdx > 0){
                maxDiff = Math.max(maxDiff, curDiff - (nums2[i] - arr[minDiffIdx - 1]));
            }
        }
        return (ans - maxDiff + mod) % mod;
    }

    /**
     * 在arr中找到 >= target的最小的数的下标
     * @param arr
     * @param target
     * @return
     */
    public static int getMinDiff(int[] arr, int target){
        int L = 0, R = arr.length - 1;
        int ans = arr.length;
        while(L <= R){
            int mid = L + ((R - L) >> 1);
            if(arr[mid] < target){
                L = mid + 1;
            } else {
                R = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    /**
     * 不觉得自己解法思路有问题，只是题意模糊。
     *  题解思路是找到单个变化最大，以使整体最小。但是本解考虑的是取模后的最小可能
     * 时间复杂度 : N + N*logN + N *logN
     * 空间复杂度 : N(不算排序占用)
     * @param nums1
     * @param nums2
     * @return
     */
    public static int minAbsoluteSumDiff1(int[] nums1, int[] nums2) {
        int mod = (int)Math.pow(10, 9) + 7;
        int[] diffs = new int[nums1.length];
        int diffSum = 0;
        for(int i = 0; i < nums1.length; i++){
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            // 由于题目数据规模限制，diffSum + diffs[i] 一定不会超出整型限制，因此可以先加再取模
            diffSum = (diffSum + diffs[i]) % mod;
        }
        if(diffSum == 0){
            return diffSum;
        }
        // 给 nums1排序，方便下面进行二分
        Arrays.sort(nums1);
        int ans = diffSum;
        for(int i = 0; i < nums1.length; i++){
            int newDiff = diffs[i];
            int otherDiffSum = (diffSum - diffs[i] + mod) % mod;
            // 该情况下如果拿当前位置数做文章，则一定要保证修改后的diffs[i]尽量小,也就是尽量让nums1[i]尽量接近nums2[i]
            newDiff = Math.min(newDiff, Math.abs(nums2[i] - getNearestNum(nums1, nums2[i], false)));
            newDiff = Math.min(newDiff, Math.abs(nums2[i] - getNearestNum(nums1, nums2[i], true)));
            ans = Math.min(ans, (otherDiffSum + newDiff) % mod);
            // 说明diffSum + diffs[i]结果是小于mod的，
            // 该情况下如果拿当前位置数做文章，则一定要保证修改后的newDiff + otherDiffSum >= mod,且要最小
            /*newDiff = Math.abs(nums2[i] - getNearestNum(nums1, mod - otherDiffSum, true));
            ans = Math.min(ans, (otherDiffSum + newDiff) % mod);*/
            // 情况1：nums2[i] - x >= mod - otherDiffSum
            int x = getNearestNum(nums1, nums2[i] - (mod - otherDiffSum), false);
            if(x < nums2[i] && nums2[i] - (mod - otherDiffSum) >= 0) {
                newDiff = Math.abs(nums2[i] - x);
                ans = Math.min(ans, (otherDiffSum + newDiff) % mod);
            }
            // 情况2：x - nums2[i] >= mod - otherDiffSum
            x = getNearestNum(nums1, (mod - otherDiffSum) + nums2[i], true);
            if(x > nums2[i] && (mod - otherDiffSum) + nums2[i] >= 0) {
                newDiff = Math.abs(x - nums2[i]);
                ans = Math.min(ans, (otherDiffSum + newDiff) % mod);
            }
        }
        return ans;
    }

    /**
     * 返回数组中离 target 最近的一个数
     * @param nums 有序数组
     * @param target
     * @param isUpper false - 数要 <= target ；true - 数要 >= target；
     * @return
     */
    public static int getNearestNum(int[] nums, int target, boolean isUpper){
        if(isUpper && nums[nums.length - 1] < target){
            return nums[nums.length - 1];
        }
        if(!isUpper && nums[0] > target){
            return nums[0];
        }
        int L = 0, R = nums.length - 1;
        int ans = 0;
        while(L <= R){
            int mid = L + ((R - L) >> 1);
            if(isUpper){
                if(nums[mid] < target){
                    L = mid + 1;
                } else {
                    ans = nums[mid];
                    R = mid - 1;
                }
            } else {
                if(nums[mid] > target){
                    R = mid - 1;
                } else {
                    ans = nums[mid];
                    L = mid + 1;
                }
            }
        }
        return ans;
    }
}
