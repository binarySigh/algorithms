package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * //给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[
 * //j]) 都应当满足：
 * //
 * // answer[i] % answer[j] == 0 ，或
 * // answer[j] % answer[i] == 0
 * //
 * // 如果存在多个有效解子集，返回其中任何一个均可。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,2,3]
 * //输出：[1,2]
 * //解释：[1,3] 也会被视为正确答案。
 * //
 * // 示例 2：
 * //
 * //输入：nums = [1,2,4,8]
 * //输出：[1,2,4,8]
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 1000
 * // 1 <= nums[i] <= 2 * 10⁹
 * // nums 中的所有整数 互不相同
 * //
 * // Related Topics 数组 数学 动态规划 排序
 * @author binarySigh
 * @date 2021/9/26 22:13
 */
public class LC0368_LargestDivisibleSubset {

    public static void main(String[] args) {
        // --> [1, 2]
        int[] nums = {1,2,3};

        // --> [1, 2, 4, 8]
        //int[] nums = {1,2,4,8};

        // --> [4, 8, 240]
        //int[] nums = {4,8,10,240};

        List<Integer> ans = largestDivisibleSubset(nums);
        System.out.println(ans);
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int maxIdx = 0;
        for(int i = 0; i < nums.length; i++){
            int preMax = -1;
            for(int j = 0; j < i; j++) {
                if(nums[i] % nums[j] == 0) {
                    if(preMax == -1){
                        preMax = j;
                    } else if(ans.get(j).size() > ans.get(preMax).size()) {
                        preMax = j;
                    }
                }
            }
            List<Integer> tmp = null;
            if(preMax >= 0){
                tmp = new ArrayList<>(ans.get(preMax));
            } else {
                tmp = new ArrayList<>();
            }
            tmp.add(nums[i]);
            ans.add(tmp);
            if(tmp.size() > ans.get(maxIdx).size()){
                maxIdx = i;
            }
        }
        return ans.get(maxIdx);
    }

}
