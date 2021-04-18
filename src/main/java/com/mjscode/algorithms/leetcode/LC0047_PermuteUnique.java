package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * //给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,1,2]
 * //输出：
 * //[[1,1,2],
 * // [1,2,1],
 * // [2,1,1]]
 * //
 * // 示例 2：
 * //
 * //输入：nums = [1,2,3]
 * //输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 8
 * // -10 <= nums[i] <= 10
 * //
 * // Related Topics 回溯算法
 * @author binarySigh
 * @date 2021/4/18 23:16
 */
public class LC0047_PermuteUnique {
    public static void main(String[] args){
        //int[] nums = {1,2,3};
        int[] nums = {2,2,1,1};
        List<List<Integer>> res = permuteUnique(nums);
    }

    /**
     * 解答成功:
     * 		执行耗时:163 ms,击败了6.40% 的Java用户
     * 		内存消耗:39.1 MB,击败了71.44% 的Java用户
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        fillList(nums, res, new HashSet<Integer>(), new HashSet<String>(), new ArrayList<Integer>());
        return res;
    }

    /**
     *
     * @param nums 原数组
     * @param res 最终答案集合
     * @param set 当前已选择的数，因为有重复元素，故此处存放的数组下标
     * @param dis 已选择过的排列组合，用于去重
     * @param ans 当前已选择的数
     */
    public static void fillList(int[] nums, List<List<Integer>> res, HashSet<Integer> set, HashSet<String> dis, ArrayList<Integer> ans){
        if(ans.size() == nums.length){
            List<Integer> cur = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < ans.size(); i++){
                cur.add(ans.get(i));
                sb.append(ans.get(i)).append("-");
            }
            if(!dis.contains(sb.toString())){
                dis.add(sb.toString());
                res.add(cur);
            }
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!set.contains(i)){
                set.add(i);
                ans.add(nums[i]);
                fillList(nums, res, set, dis, ans);
                set.remove(i);
                //因为会有重复元素，此处一定要remove最后一个元素
                ans.remove(ans.size() - 1);
            }
        }
    }
}
