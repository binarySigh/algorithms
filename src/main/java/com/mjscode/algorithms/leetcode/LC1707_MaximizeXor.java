package com.mjscode.algorithms.leetcode;

import com.mjscode.algorithms.utils.ArrayUtils;

/**
 * //给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
 * //
 * // 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR
 * // xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
 * //
 * // 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个
 * //查询的答案。
 * //
 * // 示例 1：
 * //
 * // 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * //输出：[3,3,7]
 * //解释：
 * //1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
 * //2) 1 XOR 2 = 3.
 * //3) 5 XOR 2 = 7.
 * //
 * // 示例 2：
 * //
 * // 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
 * //输出：[15,-1,5]
 * //
 * // 提示：
 * //
 * // 1 <= nums.length, queries.length <= 105
 * // queries[i].length == 2
 * // 0 <= nums[j], xi, mi <= 109
 * //
 * // Related Topics 位运算 字典树
 * @author binarySigh
 * @date 2021/5/23 0:02
 */
public class LC1707_MaximizeXor {
    public static void main(String[] args){
        // -> [3,3,7]
        /*int[] nums = {0,1,2,3,4};
        int[][] queries = {
                {3,1},
                {1,3},
                {5,6}
        };*/

        // -> [15,-1,5]
        int[] nums = {5,2,4,6,6,3};
        int[][] queries = {
                {12,4},
                {8,1},
                {6,3}
        };

        int[] res = maximizeXor(nums, queries);
        ArrayUtils.showArray(res);
    }

    /**
     * 解答成功:
     * 		执行耗时:320 ms,击败了56.39% 的Java用户
     * 		内存消耗:108 MB,击败了93.98% 的Java用户
     * @param nums
     * @param queries
     * @return
     */
    public static int[] maximizeXor(int[] nums, int[][] queries) {
        int[] ans = new int[queries.length];
        Trie trie = new Trie();
        for(int i : nums){
            trie.add(i);
        }
        for(int i = 0; i < queries.length; i++){
            ans[i] = trie.getMaxXorWithLimit(queries[i][0], queries[i][1]);
        }
        return ans;
    }

    public static class Trie{
        TrieNode root = new TrieNode(Integer.MAX_VALUE);
        public Trie(){}

        public void add(int num){
            TrieNode cur = root;
            root.min = Math.min(root.min, num);
            for(int i = 31; i >= 0; i--){
                int index = ((1 << i) | num) == num ? 1 : 0;
                if(cur.node[index] == null){
                    cur.node[index] = new TrieNode(num);
                } else {
                    cur.node[index].min = Math.min(cur.node[index].min, num);
                }
                cur = cur.node[index];
            }
        }

        public int getMaxXorWithLimit(int num, int limit){
            TrieNode cur = root;
            if(root.min > limit){
                return -1;
            }
            int res = 0;
            for(int i = 31; i >= 0; i--){
                int index = ((1 << i) | num) == num ? 0 : 1;
                if(cur.node[index] != null && cur.node[index].min <= limit){
                    res |= (1 << i);
                    cur = cur.node[index];
                } else {
                    cur = cur.node[index ^ 1];
                }
            }
            return res;
        }
    }

    public static class TrieNode{
        int min;
        TrieNode[] node = new TrieNode[2];
        public TrieNode(){}
        public TrieNode(int m){
            this.min = m;
        }
    }
}
