package com.mjscode.algorithms.leetcode;

import java.util.*;

/**
 * //给定一个未排序的整数数组，找到最长递增子序列的个数。
 * //
 * // 示例 1:
 * //
 * //输入: [1,3,5,4,7]
 * //输出: 2
 * //解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * //
 * // 示例 2:
 * //
 * //输入: [2,2,2,2,2]
 * //输出: 5
 * //解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * //
 * // 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 * // Related Topics 树状数组 线段树 数组 动态规划
 * @author binarySigh
 * @date 2021/9/20 8:49
 */
public class LC0673_FindNumberOfLIS {
    public static void main(String[] args) {
        // --> 2
        //int[] nums1 = {1,3,5,4,7};

        // --> 5
        //int[] nums1 = {2,2,2,2,2};

        int[] nums1 = {12};
        System.out.println(findNumberOfLIS(nums1));
    }

    /**
     * 解答成功:
     * 		执行耗时:7 ms,击败了99.28% 的Java用户
     * 		内存消耗:38.9 MB,击败了5.02% 的Java用户
     * @param nums
     * @return
     */
    public static int findNumberOfLIS(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        List<TreeMap<Integer, Integer>> dp = new ArrayList<>(nums.length);
        for(int i = 0; i < nums.length; i++){
            int cur = nums[i];
            int[] info = findIdxAndCnts(dp, cur);
            if(info[0] == dp.size()){
                TreeMap<Integer, Integer> tmp = new TreeMap<>();
                tmp.put(cur, info[1]);
                dp.add(tmp);
            } else {
                dp.get(info[0]).put(cur, dp.get(info[0]).getOrDefault(cur, 0) + info[1]);
            }
        }
        int ans = 0;
        for(Map.Entry<Integer, Integer> entry : dp.get(dp.size() - 1).entrySet()){
            ans += entry.getValue();
        }
        return ans;
    }

    /**
     * 1. 0位置信息是 cur 能扩展到的递增子序列的长度位置；
     * 2. 1位置信息是 以当前的cur为结尾的最长子序列共有几个
     * @param dp
     * @param cur
     * @return
     */
    public static int[] findIdxAndCnts(List<TreeMap<Integer, Integer>> dp, int cur) {
        // 1. 找到 cur 能插在序列长度为多少的 位置之后
        int l = 0, r = dp.size() - 1;
        int ans = -1;
        while(l <= r){
            int mid = l + ((r - l) >> 1);
            TreeMap<Integer, Integer> map = dp.get(mid);
            if(map.firstKey() < cur){
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        // 在找到的长度下标位置，看看cur实际比里面多少个数更大
        int cnts = 0;
        if(ans > -1) {
            //说明找到的是实际有效位，在有效位的map中查找究竟有几个是在cur之前的
            for(Map.Entry<Integer, Integer> entry : dp.get(ans).entrySet()) {
                if(entry.getKey() < cur) {
                    cnts += entry.getValue();
                } else {
                    break;
                }
            }
        } else {
            cnts = 1;
        }
        return new int[]{++ans, cnts};
    }
}
