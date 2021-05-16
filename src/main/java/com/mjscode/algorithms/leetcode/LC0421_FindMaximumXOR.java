package com.mjscode.algorithms.leetcode;

/**
 * //给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * //
 * // 进阶：你可以在 O(n) 的时间解决这个问题吗？
 * //
 * // 示例 1：
 * //
 * //输入：nums = [3,10,5,25,2,8]
 * //输出：28
 * //解释：最大运算结果是 5 XOR 25 = 28.
 * //
 * // 示例 2：
 * //
 * //输入：nums = [0]
 * //输出：0
 * //
 * // 示例 3：
 * //
 * //输入：nums = [2,4]
 * //输出：6
 * //
 * // 示例 4：
 * //
 * //输入：nums = [8,10,2]
 * //输出：10
 * //
 * // 示例 5：
 * //
 * //输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * //输出：127
 * //
 * // 提示：
 * //
 * // 1 <= nums.length <= 2 * 104
 * // 0 <= nums[i] <= 231 - 1
 * //
 * // Related Topics 位运算 字典树
 * @author binarySigh
 * @date 2021/5/16 9:00
 */
public class LC0421_FindMaximumXOR {

    public static void main(String[] args){
        int[] nums = {8,8};
        System.out.println(findMaximumXOR(nums));
    }

    /**
     * 解答成功:
     * 		执行耗时:36 ms,击败了82.30% 的Java用户
     * 		内存消耗:46.7 MB,击败了50.62% 的Java用户
     * @param nums
     * @return
     */
    public static int findMaximumXOR(int[] nums) {
        if(nums.length == 1){
            return 0;
        }
        Trie trie = new Trie();
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            trie.add(nums[i]);
            max = Math.max(max, trie.getMaxXor(nums[i]));
        }
        return max;
    }

    public static class Trie{
        TrieNode root = new TrieNode();
        public Trie(){};

        public void add(int num){
            TrieNode cur = root;
            for(int i = 30; i >= 0; i--){
                int curBinary = (((1 << i) | num) == num) ? 1 : 0;
                if(cur.node[curBinary] == null) {
                    cur.node[curBinary] = new TrieNode();
                }
                cur = cur.node[curBinary];
            }
        }

        public int getMaxXor(int num){
            int ret = 0;
            TrieNode cur = root;
            for(int i = 30; i >= 0; i--){
                int curBinary = (((1 << i) | num) == num) ? 1 : 0;
                if(cur.node[1 - curBinary] != null){
                    ret |= (1 << i);
                    cur = cur.node[1 - curBinary];
                } else {
                    cur = cur.node[curBinary];
                }
            }
            return ret;
        }
    }

    public static class TrieNode{
        TrieNode[] node = new TrieNode[2];
        public TrieNode(){};
    }
}
