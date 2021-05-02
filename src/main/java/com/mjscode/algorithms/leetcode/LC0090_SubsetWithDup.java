package com.mjscode.algorithms.leetcode;

import java.util.*;

/**
 * //给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * //
 * // 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * //
 * // 示例 1：
 * //
 * //输入：nums = [1,2,2]
 * //输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
 * //
 * // Related Topics 数组 回溯算法
 * @author binarySigh
 * @date 2021/4/12 22:45
 */
public class LC0090_SubsetWithDup {

    public static void main(String[] args){
        //int[] nums = {1,2,2};
        //int[] nums = {0};

        // 期待结果：[[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]
        //int[] nums = {1,2,3};

        //期望结果: [[],[1],[1,4],[1,4,4],[1,4,4,4],[1,4,4,4,4],[4],[4,4],[4,4,4],[4,4,4,4]]
        int[] nums = {4,4,4,1,4};
        List<List<Integer>> list = subsetsWithDup(nums);
        System.out.println("[[],[1],[1,4],[1,4,4],[1,4,4,4],[1,4,4,4,4],[4],[4,4],[4,4,4],[4,4,4,4]]");
        System.out.println(list);
    }

    /**
     * 本体需要额外注意：集合中的元素之间是无序的。也即{1，2，3}和{1，3，2}，{2，1，3}都是同一个集合。
     * 所以本题在做的时候需要先排序，防止重复元素影响；另外由于无序性的特点，还要按照子序列的方式来处理
     * 解答成功:
     * 		执行耗时:3 ms,击败了32.57% 的Java用户
     * 		内存消耗:38.4 MB,击败了97.51% 的Java用户
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums.length < 1){
            return list;
        }
        Arrays.sort(nums);
        fillList(nums, list, new ArrayList<>(),0, new HashSet<>());
        return list;
    }

    public static void fillList(int[] nums, List<List<Integer>> list, List<Integer> cur, int index, HashSet<String> set){
        if(index == nums.length){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < cur.size(); i++){
                sb.append(cur.get(i)).append('-');
            }
            if(!set.contains(sb.toString())){
                set.add(sb.toString());
                list.add(cur);
            }
            return;
        }
        List<Integer> tmp = new ArrayList<>();
        for(int i = 0; i < cur.size(); i++){
            tmp.add(cur.get(i));
        }
        fillList(nums, list, tmp,index + 1, set);
        tmp = new ArrayList<>();
        for(int i = 0; i < cur.size(); i++){
            tmp.add(cur.get(i));
        }
        tmp.add(nums[index]);
        fillList(nums, list, tmp,index + 1, set);
    }

}
