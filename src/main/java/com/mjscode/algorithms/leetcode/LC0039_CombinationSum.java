package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * //给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * //
 * // candidates 中的数字可以无限制重复被选取。
 * //
 * // 说明：
 * //
 * // 所有数字（包括 target）都是正整数。
 * // 解集不能包含重复的组合。
 * //
 * // 示例 1：
 * //
 * // 输入：candidates = [2,3,6,7], target = 7,
 * //所求解集为：
 * //[
 * //  [7],
 * //  [2,2,3]
 * //]
 * // 示例 2：
 * //
 * // 输入：candidates = [2,3,5], target = 8,
 * //所求解集为：
 * //[
 * //  [2,2,2,2],
 * //  [2,3,3],
 * //  [3,5]
 * //]
 * // 提示：
 * //
 * // 1 <= candidates.length <= 30
 * // 1 <= candidates[i] <= 200
 * // candidate 中的每个元素都是独一无二的。
 * // 1 <= target <= 500
 * //
 * // Related Topics 数组 回溯算法
 * @author binarySigh
 * @date 2021/5/19 22:17
 */
public class LC0039_CombinationSum {
    public static void main(String[] args){
        int[] candidates = {2};
        int target = 5;
        List<List<Integer>> ans = combinationSum(candidates, target);
    }

    /**
     * 解答成功:
     * 		执行耗时:2 ms,击败了99.96% 的Java用户
     * 		内存消耗:38.5 MB,击败了86.63% 的Java用户
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if(candidates.length == 1){
            if(target % candidates[0] == 0){
                ArrayList<Integer> list = new ArrayList<>();
                for(int i = 0; i < target / candidates[0]; i++){
                    list.add(candidates[0]);
                }
                ans.add(list);
            }
            return ans;
        }
        Arrays.sort(candidates);
        int last = candidates.length - 1;
        process(candidates, ans, new ArrayList<Integer>(), target, last);
        return ans;
    }

    public static void process(int[] candidates, List<List<Integer>> ans, ArrayList<Integer> list, int target, int pos){
        if(pos < 0){
            if(target == 0){
                List<Integer> cur = new ArrayList<>();
                for(int i = 0; i < list.size(); i++){
                    cur.add(list.get(i));
                }
                ans.add(cur);
            }
            return;
        }
        //处理当前位置
        int preLeft = target;
        while(preLeft >= 0 && preLeft - candidates[pos] >= 0){
            list.add(candidates[pos]);
            preLeft -= candidates[pos];
        }
        do {
            process(candidates, ans, list, preLeft, pos - 1);
            if(preLeft == target){
                break;
            }
            if(preLeft < target) {
                preLeft += candidates[pos];
                list.remove(list.size() - 1);
            }
        } while(preLeft <= target);
    }
}
