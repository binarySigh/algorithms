package com.mjscode.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * //给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * //
 * // 示例:
 * //
 * // 输入: n = 4, k = 2
 * //输出:
 * //[
 * //  [2,4],
 * //  [3,4],
 * //  [2,3],
 * //  [1,2],
 * //  [1,3],
 * //  [1,4],
 * //]
 * // Related Topics 回溯算法
 * @author binarySigh
 * @date 2021/5/20 23:44
 */
public class LC0077_Combine {
    public static void main(String[] args){
        int n = 4, k = 4;
        List<List<Integer>> ans = combine(n, k);
    }

    /**
     * 解答成功:
     * 		执行耗时:21 ms,击败了47.91% 的Java用户
     * 		内存消耗:40.1 MB,击败了15.14% 的Java用户
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if(n < 1 || k < 1 || k > n){
            return ans;
        }
        List<Integer> list = new ArrayList<>(k);
        if(n == 1){
            list.add(1);
            ans.add(list);
            return ans;
        }
        process(n, k, list, ans);
        return ans;
    }

    public static void process(int n, int k, List<Integer> list, List<List<Integer>> ans){
        if(k == 0 || n == 0){
            if(k == 0) {
                List<Integer> cur = new ArrayList<>(k);
                for (Integer i : list) {
                    cur.add(i);
                }
                ans.add(cur);
            }
            return;
        }
        list.add(n);
        process(n - 1, k - 1, list, ans);
        list.remove(list.size() - 1);
        process(n - 1, k, list, ans);
    }
}
