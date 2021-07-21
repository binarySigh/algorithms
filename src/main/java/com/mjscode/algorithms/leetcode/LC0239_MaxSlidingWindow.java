package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

import java.util.LinkedList;

/**
 * //给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
 * //。
 * //
 * // 返回滑动窗口中的最大值。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * //输出：[3,3,5,5,6,7]
 * //解释：
 * //滑动窗口的位置                最大值
 * //---------------               -----
 * //[1  3  -1] -3  5  3  6  7       3
 * // 1 [3  -1  -3] 5  3  6  7       3
 * // 1  3 [-1  -3  5] 3  6  7       5
 * // 1  3  -1 [-3  5  3] 6  7       5
 * // 1  3  -1  -3 [5  3  6] 7       6
 * // 1  3  -1  -3  5 [3  6  7]      7
 * //
 * // 示例 2：
 * //
 * //输入：nums = [1], k = 1
 * //输出：[1]
 * //
 * // 示例 3：
 * //
 * //输入：nums = [1,-1], k = 1
 * //输出：[1,-1]
 * //
 * // 示例 4：
 * //
 * //输入：nums = [9,11], k = 2
 * //输出：[11]
 * //
 * // 示例 5：
 * //
 * //输入：nums = [4,-2], k = 2
 * //输出：[4]
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 105
 * // -104 <= nums[i] <= 104
 * // 1 <= k <= nums.length
 * //
 * // Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列）
 * @author binarySigh
 * @date 2021/7/21 22:03
 */
public class LC0239_MaxSlidingWindow {
    public static void main(String[] args){
        // --> [3,3,5,5,6,7]
        /*int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;*/

        // --> [1]
        /*int[] nums = {1};
        int k = 1;*/

        // --> [1,-1]
        /*int[] nums = {1,-1};
        int k = 1;*/

        // --> [11]
        /*int[] nums = {9,11};
        int k = 2;*/

        // --> [4]
        int[] nums = {4,-2};
        int k = 2;
        int[] ans = maxSlidingWindow(nums, k);
        ArrayUtils.showArray(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:28 ms,击败了95.69% 的Java用户
     * 		内存消耗:52.7 MB,击败了70.27% 的Java用户
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int size = 0;
        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(0);
        //窗口左右边界，其中R是到不了的右边界
        int L = 0, R = 1;
        for(;R <= nums.length; R++){
            if(R - L == k){
                ans[size++] = nums[q.peekFirst()];
                if(q.peekFirst() == L++){
                    q.pollFirst();
                }
            }
            if(R == nums.length){
                break;
            }
            while(!q.isEmpty() && nums[q.peekLast()] <= nums[R]){
                q.pollLast();
            }
            q.addLast(R);
        }
        return ans;
    }
}
