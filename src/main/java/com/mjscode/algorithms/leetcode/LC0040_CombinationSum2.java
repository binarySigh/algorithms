package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * //给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * //
 * // candidates 中的每个数字在每个组合中只能使用一次。
 * //
 * // 注意：解集不能包含重复的组合。
 * //
 * // 示例 1:
 * //
 * //输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * //输出:
 * //[
 * //[1,1,6],
 * //[1,2,5],
 * //[1,7],
 * //[2,6]
 * //]
 * //
 * // 示例 2:
 * //
 * //输入: candidates = [2,5,2,1,2], target = 5,
 * //输出:
 * //[
 * //[1,2,2],
 * //[5]
 * //]
 * //
 * // 提示:
 * //
 * // 1 <= candidates.length <= 100
 * // 1 <= candidates[i] <= 50
 * // 1 <= target <= 30
 * @author binarySigh
 * @date 2021/9/9 22:18
 */
public class LC0040_CombinationSum2 {
    public static void main(String[] args){
        // --> [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
        /*int[] cand = {10,1,2,7,6,1,5};
        int target = 8;*/

        // --> [[1, 2, 2], [5]]
        /*int[] cand = {2,5,2,1,2};
        int target = 5;*/

        int[] cand = {2,5,2,1,2};
        int target = 7;

        List<List<Integer>> ans = combinationSum2(cand, target);
        System.out.println(ans);
    }

    /**
     * 解答成功:
     * 		执行耗时:2 ms,击败了99.58% 的Java用户
     * 		内存消耗:38.8 MB,击败了17.02% 的Java用户
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>(candidates.length);
        process(candidates, target, 0, tmp, ans);
        return ans;
    }

    public static void process(int[] candidates, int target, int idx, List<Integer> tmp, List<List<Integer>> ans){
        if(idx >= candidates.length || target == 0){
            if(target == 0){
                List<Integer> cur = new ArrayList<>(tmp);
                ans.add(cur);
            }
            return;
        }
        for(int i = idx; i < candidates.length; ){
            if(target - candidates[i] >= 0){
                tmp.add(candidates[i]);
                process(candidates, target - candidates[i], i + 1, tmp, ans);
                tmp.remove(tmp.size() - 1);
            }
            int j = i;
            while(j < candidates.length && candidates[i] == candidates[j]){
                j++;
            }
            i = j;
        }
    }
}
