package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * //给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * //
 * // 示例:
 * //
 * // 输入: [1,2,3]
 * //输出:
 * //[
 * //  [1,2,3],
 * //  [1,3,2],
 * //  [2,1,3],
 * //  [2,3,1],
 * //  [3,1,2],
 * //  [3,2,1]
 * //]
 * // Related Topics 回溯算法
 * @author binarySigh
 * @date 2021/4/17 23:29
 */
public class LC0046_Permute {

    public static void main(String[] args){
        int[] nums = {1,2,3,4};
        List<List<Integer>> list = permute(nums);
    }

    /**
     * 解答成功:
     * 		执行耗时:4 ms,击败了10.37% 的Java用户
     * 		内存消耗:38.8 MB,击败了35.52% 的Java用户
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        List<List<Integer>> list = new ArrayList<>();
        ArrayList<Integer> cur = new ArrayList<>();
        getList(nums, set, list, cur);
        return list;
    }

    public static void getList(int[] nums, HashSet<Integer> set, List<List<Integer>> list, ArrayList<Integer> cur){
        if(set.size() == nums.length){
            ArrayList added = new ArrayList();
            for(int i = 0; i < cur.size(); i++){
                added.add(cur.get(i));
            }
            list.add(added);
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!set.contains(nums[i])){
                cur.add(nums[i]);
                set.add(nums[i]);
                getList(nums, set, list, cur);
                set.remove(nums[i]);
                cur.remove(Integer.valueOf(nums[i]));
            }
        }
    }
}
