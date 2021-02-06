package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * //
 * // 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,2,3]
 * //输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * //
 * // 示例 2：
 * //
 * //输入：nums = [0]
 * //输出：[[],[0]]
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 10
 * // -10 <= nums[i] <= 10
 * // nums 中的所有元素 互不相同
 * //
 * // Related Topics 位运算 数组 回溯算法
 * @author binarySigh
 */
public class LC0078_Subsets {
    public static void main(String[] args){
        int[] arr = {0};
        List<List<Integer>> res = process(arr);
    }

    /**
     *官方思路：用二进制位 的 0,1表示数组中某一位上的数要不要在子集中出现
     * 由于该方案的时间复杂度和空间复杂度都与回溯算法相同，因此这里不做实现
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return list;
        }

        return list;
    }
    /**
     * 回溯算法
     * 解答成功:
     * 		执行耗时:2 ms,击败了11.61% 的Java用户
     * 		内存消耗:38.9 MB,击败了20.80% 的Java用户
     * @param nums
     * @return
     */
    public static List<List<Integer>> process(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return list;
        }
        list = process(nums, 0);
        return list;
    }

    public static List<List<Integer>> process(int[] nums, int i){
        List<List<Integer>> listWith = new ArrayList<>();
        List<List<Integer>> listWithout = new ArrayList<>();
        List<List<Integer>> ret = new ArrayList<>();
        if(i == nums.length - 1){
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums[i]);
            listWith.add(tmp);
            List<Integer> not = new ArrayList<>();
            ret.add(tmp);
            ret.add(not);
            return ret;
        }
        listWith = process(nums, i + 1);
        for(int j = 0; j < listWith.size(); j++){
            listWith.get(j).add(nums[i]);
        }
        listWithout = process(nums, i + 1);
        ret.addAll(listWith);
        ret.addAll(listWithout);
        return ret;
    }
}
