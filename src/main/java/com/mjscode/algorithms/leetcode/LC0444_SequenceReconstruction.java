package com.mjscode.algorithms.leetcode;

import java.util.*;

/**
 * //验证原始的序列 org 是否可以从序列集 seqs 中唯一地重建。序列 org 是 1 到 n 整数的排列，其中 1 ≤ n ≤ 104。重建是指在序列集
 * //seqs 中构建最短的公共超序列。（即使得所有 seqs 中的序列都是该最短序列的子序列）。确定是否只可以从 seqs 重建唯一的序列，且该序列就是 org 。
 * //
 * // 示例 1：
 * //
 * // 输入：
 * //org: [1,2,3], seqs: [[1,2],[1,3]]
 * //
 * //输出：
 * //false
 * //
 * //解释：
 * //[1,2,3] 不是可以被重建的唯一的序列，因为 [1,3,2] 也是一个合法的序列。
 * //
 * // 示例 2：
 * //
 * // 输入：
 * //org: [1,2,3], seqs: [[1,2]]
 * //
 * //输出：
 * //false
 * //
 * //解释：
 * //可以重建的序列只有 [1,2]。
 * //
 * // 示例 3：
 * //
 * // 输入：
 * //org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
 * //
 * //输出：
 * //true
 * //
 * //解释：
 * //序列 [1,2], [1,3] 和 [2,3] 可以被唯一地重建为原始的序列 [1,2,3]。
 * //
 * // 示例 4：
 * //
 * // 输入：
 * //org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
 * //
 * //输出：
 * //true
 * //
 * // Related Topics 图 拓扑排序 数组
 * @author binarySigh
 * @date 2021/9/13 20:45
 */
public class LC0444_SequenceReconstruction {

    public static void main(String[] args) {
        // -- > false
        /*int[] org = {1,2,3};
        List<Integer> s1 = Arrays.asList(1,2);
        List<Integer> s2 = Arrays.asList(1,3);
        List<List<Integer>> seqs = Arrays.asList(s1, s2);*/

        // --> false
        /*int[] org = {1,2,3};
        List<Integer> s1 = Arrays.asList(1,2);
        List<List<Integer>> seqs = Arrays.asList(s1);*/

        // --> true
        /*int[] org = {1,2,3};
        List<Integer> s1 = Arrays.asList(1,2);
        List<Integer> s2 = Arrays.asList(1,3);
        List<Integer> s3 = Arrays.asList(2,3);
        List<List<Integer>> seqs = Arrays.asList(s1, s2, s3);*/

        // --> true
        /*int[] org = {4,1,5,2,6,3};
        List<Integer> s1 = Arrays.asList(5,2,6,3);
         List<Integer> s2 = Arrays.asList(4,1,5,2);
        List<List<Integer>> seqs = Arrays.asList(s1, s2);*/

        // --> false
        int[] org = {3,2,1};
        List<Integer> s1 = Arrays.asList(1,2);
        List<Integer> s2 = Arrays.asList(1,3);
        List<Integer> s3 = Arrays.asList(2,3);
        List<List<Integer>> seqs = Arrays.asList(s1, s2, s3);

        System.out.println(sequenceReconstruction(org, seqs));
    }

    /**
     * 解答成功:
     * 		执行耗时:47 ms,击败了34.8% 的Java用户
     * 		内存消耗:49.4 MB,击败了5.4% 的Java用户
     * @param org
     * @param seqs
     * @return
     */
    public static boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs){
        if(seqs == null || seqs.size() == 0){
            return false;
        }
        int n = org.length;
        // [0][x] 弃而不用
        // [i][0] -> 入度集；[i][1] -> 出度集
        HashSet<Integer>[][] node = new HashSet[n + 1][2];
        for(int i = 1; i <= n; i++){
            node[i][0] = new HashSet<>();
            node[i][1] = new HashSet<>();
        }
        //补全图关系
        for(List<Integer> list : seqs){
            if(list.get(0) <= 0 || list.get(0) > n){
                //过滤非法指令序号
                return false;
            }
            for(int i = 1; i < list.size(); i++){
                if(list.get(i) <= 0 || list.get(i) > n){
                    return false;
                }
                int pre = list.get(i - 1);
                int cur = list.get(i);
                node[pre][1].add(cur);
                node[cur][0].add(pre);
            }
        }
        Stack<Integer> stack = new Stack<>();
        for(int i = 1; i <= n; i++){
            if(node[i][0].size() == 0){
                stack.push(i);
            }
        }
        //因为要构建唯一序列，所以任一时刻入度为0的节点都只能有一个
        int pos = 0;
        while(stack.size() == 1){
            int cur = stack.pop();
            //验证当前序列是否与给定的序列相同
            if(org[pos++] != cur){
                return false;
            }
            Iterator<Integer> ite = node[cur][1].iterator();
            while(ite.hasNext()){
                int idx = ite.next();
                node[idx][0].remove(cur);
                if(node[idx][0].size() == 0){
                    stack.push(idx);
                }
            }
        }
        return n == pos;
    }

}
